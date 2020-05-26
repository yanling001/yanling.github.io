### [主从复制机制](https://www.jianshu.com/p/532149db7650)
###### 主从作用
1. 为数据提供多个副本，实现高可用
2. 实现读写分离（主节点负责写数据，从节点负责读数据，主节点定期把数据同步到从节点保证数据的一致性）
* 设置主从 slave
![image](https://upload-images.jianshu.io/upload_images/16358536-619c84e3ee8819f7)
#### 两种方式
###### sync(老版本)
* **只支持全量复制**
* 全量同步 :　Redis全量复制一般发生在Slave初始化阶段，这时Slave需要将Master上的所有数据都复制一份。具体步骤如下： 
1. 从服务器连接主服务器，发送SYNC命令； 
2. 主服务器接收到SYNC命名后，开始执行BGSAVE命令生成RDB文件并使用缓冲区记录此后执行的所有写命令； 
3. 主服务器BGSAVE执行完后，向所有从服务器发送快照文件，并在发送期间继续记录被执行的写命令； 
4. 从服务器收到快照文件后丢弃所有旧数据，载入收到的快照； 
5. 主服务器快照发送完毕后开始向从服务器发送缓冲区中的写命令； 
6. 从服务器完成对快照的载入，开始接收命令请求，并执行来自主服务器缓冲区的写命令； 
###### 旧的复制方式的缺点
* 当从服务器断线重连的时候会执行全量复制效率低
###### **psync**(新版本的性能好) **主从重点**
* 支持全量复制同时也支持部分复制
#### psync的部分复制的实现原理
###### psync 命令需要 3 个组件支持：
1. 主从节点各自复制偏移量
* 参与复制的主从节点都会维护自身的复制偏移量。
主节点在处理完写入命令后，会把命令的字节长度做累加记录，统计信息在 info replication 中的 masterreploffset 指标中。
从节点每秒钟上报自身的的复制偏移量给主节点，因此主节点也会保存从节点的复制偏移量。
从节点在接收到主节点发送的命令后，也会累加自身的偏移量，统计信息在 info replication 中。
通过对比主从节点的复制偏移量，可以判断主从节点数据是否一致。

2. 主节点复制积压缓冲区
* 复制积压缓冲区是一个保存在主节点的一个固定长度的先进先出的队列。默认大小 1MB。
这个队列在 slave 连接是创建。这时主节点响应写命令时，不但会把命令发送给从节点，也会写入复制缓冲区。
他的作用就是用于部分复制和复制命令丢失的数据补救。通过 info replication 可以看到相关信息。

3. 主节点运行 ID
* 每个 redis 启动的时候，都会生成一个 40 位的运行 ID。
运行 ID 的主要作用是用来识别 Redis 节点。如果使用 ip+port 的方式，那么如果主节点重启修改了 RDB/AOF 数据，从节点再基于偏移量进行复制将是不安全的。所以，当运行 id 变化后，从节点将进行全量复制。也就是说，redis 重启后，默认从节点会进行全量复制。
###### psyn 的流程
![image](https://upload-images.jianshu.io/upload_images/16358536-7c072e3b091f78bb)


* 主节点会根据 runid 和 offset 决定返回结果：
1. 如果回复 +FULLRESYNC {runId} {offset} ，那么从节点将触发全量复制流程。
2. 如果回复 +CONTINUE，从节点将触发部分复制。
3. 如果回复 +ERR，说明主节点不支持 2.8 的 psync 命令，将使用 sync 执行全量复制。
* 到这里，数据之间的同步就讲的差不多了，篇幅还是比较长的。主要是针对 psync 命令相关之间的介绍。

###### 部分复制
![image](https://upload-images.jianshu.io/upload_images/16358536-4ff5cc14a25f0153)


>当从节点出现网络中断，超过了 repl-timeout 时间，主节点就会中断复制连接。
主节点会将请求的数据写入到“复制积压缓冲区”，默认 1MB。
当从节点恢复，重新连接上主节点，从节点会将 offset 和主节点 id 发送到主节点
主节点校验后，如果偏移量的数后的数据在缓冲区中，就发送 cuntinue 响应 —— 表示可以进行部分复制
主节点将缓冲区的数据发送到从节点，保证主从复制进行正常状态。
#### 心跳机制
> 主从都有心跳检测机制，各自模拟成对方的客户端进行通信，通过 client list 命令查看复制相关客户端信息，主节点的连接状态为 flags = M，从节点的连接状态是 flags = S。
**主节点默认每隔 10 秒对从节点发送 ping 命令，可修改配置 repl-ping-slave-period 控制发送频率。
从节点在主线程每隔一秒发送 replconf ack{offset} 命令，给主节点上报自身当前的复制偏移量。
主节点收到 replconf 信息后，判断从节点超时时间，如果超过 repl-timeout 60 秒，则判断节点下线。**
![image](https://upload-images.jianshu.io/upload_images/16358536-4b3e9bd8b9fd4068)
###### 主节点的异步处理
* 主节点不但负责数据读写，还负责把写命令同步给从节点，写命令的发送过程是异步完成，也就是说主节点处理完写命令后立即返回客户度，并不等待从节点复制完成。

### [哨兵模式(Sentinel)](https://www.jianshu.com/p/06ab9daf921d)
#### 什么是哨兵模式 解决祝主服务器宕机问题
* 哨兵模式是一种特殊的模式，首先Redis提供了哨兵的命令，哨兵是一个独立的进程，作为进程，它会独立运行。其原理是哨兵通过发送命令，等待Redis服务器响应，从而监控运行的多个Redis实例。
![image](https://upload-images.jianshu.io/upload_images/11320039-57a77ca2757d0924.png?imageMogr2/auto-orient/strip|imageView2/2/w/507/format/webp)
#### 哨兵的作用 
1. 通过发送命令，让Redis服务器返回监控其运行状态，包括主服务器和从服务器。
2. 当哨兵监测到master宕机，会自动将slave切换成master，然后通过发布订阅模式通知其他的从服务器，修改配置文件，让它们切换主机。
##### 故障切换
###### 主观下线与客观下线
>假设主服务器宕机，哨兵1先检测到这个结果，系统并不会马上进行failover过程，仅仅是哨兵1主观的认为主服务器不可用，这个现象成为主观下线。当后面的哨兵也检测到主服务器不可用，并且数量达到一定值时，那么哨兵之间就会进行一次投票，投票的结果由一个哨兵发起，进行failover操作。切换成功后，就会通过发布订阅模式，让各个哨兵把自己监控的从服务器实现切换主机，这个过程称为客观下线。这样对于客户端而言，一切都是透明的。

![image](https://upload-images.jianshu.io/upload_images/11320039-3f40b17c0412116c.png?imageMogr2/auto-orient/strip|imageView2/2/w/747/format/webp)
* 哨兵与master建立IFON链接(INFO指令获取1 master信息 2 从节点信息)
* 与所有服务器建立命令链接和订阅链接包括master(哨兵从主服务器中获取从服务器信息)
###### Sentinel之间会建立命令连接但是不会建立订阅连接
#### Sentinel之间如何建立互相发现
* 通过对redis节点的统一频道订阅一个Sentinel发送的消息会被其他Sentinel收到从而更新自己的Sentinels字典
#### 自动故障转移
* 当Master不能正常操作时哨兵会开始一次故障转移。
* 它会将失效的Master的其中一个Slave升级为新的Master，并让其他Slave改为复制新的Master。
* 当客户端试图连接失效的Master时，集群会向客户端显示新的Master的地址。
* Master和Slave切换后，Master的redis.conf、Slave的reids.conf和senisentinel的sentinel.conf配置文件的内容都会相应的改变，即，Master主服务器的redis.conf配置文件中会多一行slaveof的配置，sentinel.conf的监控目标会随之调换。
![image](https://upload-images.jianshu.io/upload_images/15083002-718b61c5cddef066.PNG?imageMogr2/auto-orient/strip|imageView2/2/format/webp)
#### 如何选举新的master 故障处理细节
![image](https://upload-images.jianshu.io/upload_images/15083002-31cc20da18483120.PNG?imageMogr2/auto-orient/strip|imageView2/2/w/740/format/webp)
1. 每个sentinel进程每秒钟一次的频率向整个集群中Master、Slave以及其它Sentinel进程发送一个PING命令。
2. 如果一个实例（instance）距离最后一次有效回复PING命令超过down-after-milliseconds选项所指定的值，这个实例会被sentinel进程标记为主观下线（SDOWN）。
3. 如果一个Master主服务器被标记为主观下线（SDOWN），则正在监视这个Master主服务器的所有 Sentinel进程要以每秒一次的频率确认Master主服务器的确进入了主观下线状态。
4. 当有足够数量的 Sentinel进程（大于等于配置文件指定的值）在指定的时间范围内确认Master主服务器进入了主观下线状态（SDOWN）， 则Master主服务器会被标记为客观下线（ODOWN）。
5. 在一般情况下， 每个 Sentinel进程会以每 10 秒一次的频率向集群中的所有Master主服务器、Slave从服务器发送 INFO 命令。
6. 当Master主服务器被 Sentinel进程标记为客观下线（ODOWN）时，Sentinel进程向下线的 Master主服务器的所有 Slave从服务器发送 INFO 命令的频率会从 10 秒一次改为每秒一次。
7. 若没有足够数量的 Sentinel进程同意 Master主服务器下线， Master主服务器的客观下线状态就会被移除。若 Master主服务器重新向 Sentinel进程发送 PING 命令返回有效回复，Master主服务器的主观下线状态就会被移除。
#### 哨兵的leader选举

* 如果主节点被判定为客观下线之后，就要选取一个哨兵节点来完成后面的故障转移工作，选举出一个leader的流程如下:
1. 每个在线的哨兵节点都可以成为领导者，当它确认（比如哨兵3）主节点下线时，会向其它哨兵发is-master-down-by-addr命令，征求判断并要求将自己设置为领导者，由领导者处理故障转移；
2. 当其它哨兵收到此命令时，可以同意或者拒绝它成为领导者；
3. 如果哨兵3发现自己在选举的票数大于等于num(sentinels)/2+1时，将成为领导者，如果没有超过，继续选举…………

![image](https://img2018.cnblogs.com/blog/1382244/201905/1382244-20190506155956342-1186812006.png)
#### 任命新的master
* sentinel状态数据结构中保存了主服务的所有从服务信息，领头sentinel按照如下的规则从从服务列表中挑选出新的主服务

1. 过滤掉主观下线的节点 
2. 选择slave-priority最高的节点，如果由则返回没有就继续选择
3. 选择出复制偏移量最大的系节点，因为复制便宜量越大则数据复制的越完整，如果由就返回了，没有就继续
4. 选择run_id最小的节点
![image](https://img2018.cnblogs.com/blog/1382244/201905/1382244-20190506160701394-131033031.png)
######   更新主从状态
* 通过slaveof no one命令，让选出来的从节点成为主节点；并通过slaveof命令让其他节点成为其从节点。 将已下线的主节点设置成新的主节点的从节点，当其回复正常时，复制新的主节点，变成新的主节点的从节点
同理，当已下线的服务重新上线时，sentinel会向其发送slaveof命令，让其成为新主的从
> 参考 [主从与哨兵](https://www.jianshu.com/p/40212051ccc9) [更新主从](https://www.cnblogs.com/Eugene-Jin/p/10819601.html)
### [集群模式](https://www.jianshu.com/p/84dbb25cc8dc)

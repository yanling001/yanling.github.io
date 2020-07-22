### 架构
![image](http://www.yunweipai.com/wp-content/uploads/2020/05/15619609754844.jpg)

* Producer：消息和数据的生产者，主要负责生产Push消息到指定Broker的Topic中。
* Broker：Kafka节点就是被称为Broker，Broker主要负责创建Topic，存储Producer所发布的消息，记录消息处理的过程，现是将消息保存到内存中，然后持久化到磁盘。
* Topic：同一个Topic的消息可以分布在一个或多个Broker上，一个Topic包含一个或者多个Partition分区，数据被存储在多个Partition中。
* replication-factor：复制因子；这个名词在上图中从未出现，在我们下一章节创建Topic时会指定该选项，意思为创建当前的Topic是否需要副本，如果在创建Topic时将此值设置为1的话，代表整个Topic在Kafka中只有一份，该复制因子数量建议与Broker节点数量一致。
* Partition：分区；在这里被称为Topic物理上的分组，一个Topic在Broker中被分为1个或者多个Partition，也可以说为每个Topic包含一个或多个Partition，(一般为kafka节. 点数CPU的总核心数量)分区在创建Topic的时候可以指定。分区才是真正存储数据的单元。


* Consumer：消息和数据的消费者，主要负责主动到已订阅的Topic中拉取消息并消费，为什么Consumer不能像Producer一样的由Broker去push数据呢？因为Broker不知道Consumer能够消费多少，如果push消息数据量过多，会造成消息阻塞，而由Consumer去主动pull数据的话，Consumer可以根据自己的处理情况去pull消息数据，消费完多少消息再次去取。这样就不会造成Consumer本身已经拿到的数据成为阻塞状态。


* ZooKeeper：ZooKeeper负责维护整个Kafka集群的状态，存储Kafka各个节点的信息及状态，实现Kafka集群的高可用，协调Kafka的工作内容。

>我们可以看到上图，Broker和Consumer有使用到ZooKeeper，而Producer并没有使用到ZooKeeper，因为Kafka从0.8版本开始，Producer并不需要根据ZooKeeper来获取集群状态，而是在配置中指定多个Broker节点进行发送消息，同时跟指定的Broker建立连接，来从该Broker中获取集群的状态信息，这是Producer可以知道集群中有多少个Broker是否在存活状态，每个Broker上的Topic有多少个Partition，Prodocuer会讲这些元信息存储到Producuer的内存中。如果Producoer像集群中的一台Broker节点发送信息超时等故障，Producer会主动刷新该内存中的元信息，以获取当前Broker集群中的最新状态，转而把信息发送给当前可用的Broker，当然Prodocuer也可以在配置中指定周期性的去刷新Broker的元信息以更新到内存中。
注意：只有Broker和ZooKeeper才是服务，而Producer和Consumer只是Kafka的SDK罢了
### 文件存储
>Kafka以Topic来进行消费管理，每个Topic包含多个Part(ition)，每个Part对应一个逻辑Log，由多个Segment组成。每个Segment中存储多条消息，消息ID由逻辑位置决定，即从消息ID可直接定位到消息的存储位置，避免ID到位置的额外映射。每个Part在内存中对应一个Index，记录每个Segment中的第一个消息偏移。
![image](https://img2018.cnblogs.com/blog/687300/201810/687300-20181012154442685-385510295.png)
* 就是：一个topic多个分区(partition)一个分区一个log一个log多个Segment，一个Segment有.log(存储数据)和.index(存储索引)文件
* topic 是逻辑上的概念，而 partition 是物理上的概念，每个 partition 对应于一个 log 文
件，该 log 文件中存储的就是 producer 生产的数据。Producer 生产的数据会被不断追加到该
log 文件末端，且每条数据都有自己的 offset。消费者组中的每个消费者，都会实时记录自己
消费到了哪个 offset，以便出错恢复时，从上次的位置继续消费。

>发布者发到某个Topic的消息会被均匀地分布到多个Part上（随机或根据用户指定的回调函数进行分布），Broker收到发布消息后往对应Part的最后一个Segment上添加该消息，当某个Segment上的消息条数到达配置值或消息发布时间超过阈值时，Segment上的消息便会被flush到磁盘上，只有flush到磁盘上的消息订阅者才能订阅到，Segment达到一定的大小后将不会再往该Segment写数据，Broker会创建新的Segment。
　　全系统分布式，即所有的Producer、Broker和Consumer都默认有多个，均为分布式的。Producer和Broker之间没有负载均衡机制。Broker和Consumer 之间利用ZooKeeper进行负载均衡。所有的Broker和Consumer都会在Zookeeper中进行注册，且Zookeeper会保存他们的一些元数据信息。如果某个Broker和Consumer发生了变化，那么所有其他的Broker和Consumer都会得到通知。

### Kafka生产者
###### 生产者的分区策略
* 分区好处
1. 便在集群中扩展，每个 Partition 可以通过调整以适应它所在的机器，而一个 topic
又可以有多个 Partition 组成，因此整个集群就可以适应任意大小的数据了；
2. 可以提高并发，因为可以以 Partition 为单位读写了。
* 分区的原则
1. 指明 partition 的情况下，直接将指明的值直接作为 partiton 值；
2. 没有指明 partition 值但有 key 的情况下，将 key 的 hash 值与 topic 的 partition 
数进行取余得到 partition 值；
3. 既没有 partition 值又没有 key 值的情况下，第一次调用时随机生成一个整数（后
面每次调用在这个整数上自增），将这个值与 topic 可用的 partition 总数取余得到 partition 
值，也就是常说的 round-robin 算法。
#### 生产者数据可靠性 (重点)

#####  ack 应答机制
######  ack应答参数
* 0：producer 不等待 broker 的 ack，这一操作提供了一个最低的延迟，broker 一接收到还
没有写入磁盘就已经返回当broker故障时有可能丢失数据 
* 1：producer 等待 broker 的 ack，partition 的 leader 落盘成功后返回 ack，==如果在 follower
同步成功之前 leader 故障，那么将会丢失数据；==
* -1（all）：producer 等待 broker 的 ack，partition 的 leader 和 follower 全部落盘成功后才
返回 ack。但是如果在 follower 同步完成后，broker 发送 ack 之前==leader 发生故障，那么会
造成数据重复==


######  何时 ACK

* kafka的策略是主从节点都全部同步才ack
###### ISR
* 采取全部同步才ack的策略会有如下问题
> leader 收到数据，所有 follower 都开始同步数据，
但有一个 follower，因为某种故障，迟迟不能与 leader 进行同步，那 leader 就要一直等下去，
直到它完成同步，才能发送 ack。这个问题怎么解决呢?
* 问题解决
> Leader 维护了一个动态的 in-sync replica set (ISR)，意为和 leader 保持同步的 follower 集
合。当 ISR 中的 follower 完成数据的同步之后，leader 就会给 follower 发送 ack。如果 follower
长时间 未 向 leader 同 步 数 据 ， 则 该 follower 将 被 踢 出 ISR ， 该 时 间 阈 值 由replica.lag.time.max.ms 参数设定。Leader 发生故障之后，就会从 ISR 中选举新的 leader。
#### 节点故障
###### Log文件中的HW和LEO
* HW：指的是消费者能见到的最大的 offset，ISR 队列中最小的 LEO。
* LEO：指的是每个副本最大的 offset；
###### （1）follower 故障
* follower 发生故障后会被临时踢出 ISR，待该 follower 恢复后，follower 会读取本地磁盘
记录的上次的 HW，并将 log 文件高于 HW 的部分截取掉，从 HW 开始向 leader 进行同步。
等该 follower 的 LEO 大于等于该 Partition 的 HW，即 follower 追上 leader 之后，就可以重
新加入 ISR 了。
###### （2）leader 故障
* leader 发生故障之后，会从 ISR 中选出一个新的 leader，之后，为保证多个副本之间的数据一致性，其余的 follower 会先将各自的 log 文件高于 HW 的部分截掉，然后从新的 leader
同步数据。
#### 精准一次=至少一次+幂等性(At Least Once + 幂等性 = Exactly Once)
* 将服务器的 ACK 级别设置为-1，可以保证 Producer 到 Server 之间不会丢失数据，即 At
Least Once (至少一次)语义。相对的，将服务器 ACK 级别设置为 0，可以保证生产者每条消息只会被
发送一次，即 At Most Once (至多一次)语义。
###### At Least Once与At Most Once
* At Least Once： 消息重复
* At Most Once： 消息丢失
* 多无法达到精准一致性
###### At Least Once + 幂等性 = Exactly Once
> 要启用幂等性，只需要将 Producer 的参数中 enable.idompotence 设置为 true 即可。Kafka
的幂等性实现其实就是将原来下游需要做的去重放在了数据上游。开启幂等性的 Producer 在
初始化的时候会被分配一个 PID，发往同一 Partition 的消息会附带 Sequence Number。而
Broker 端会对<PID, Partition, SeqNumber>做缓存，当具有相同主键的消息提交时，Broker 只
会持久化一条。
但是 PID 重启就会变化，同时不同的 Partition 也具有不同主键，所以幂等性无法保证跨
分区跨会话的 Exactly Once。
### Kafka中的消费者
###### pull与push
* consumer 采用 pull（拉）模式从 broker 中读取数据。
* 备注： rabbitmq采取的push的模式
*  push（推）模式很难适应消费速率不同的消费者，因为消息发送速率是由 broker 决定的。
它的目标是尽可能以最快速度传递消息，但是这样很容易造成 consumer 来不及处理消息，典型的表现就是拒绝服务以及网络拥塞。
* 而 pull 模式则可以根据 consumer 的消费能力以适
当的速率消费消息。
pull 模式不足之处是，如果 kafka 没有数据，消费者可能会陷入循环中，一直返回空数
据。针对这一点，Kafka 的消费者在消费数据时会传入一个时长参数 timeout，如果当前没有
数据可供消费，consumer 会等待一段时间之后再返回，这段时长即为 timeout。
###### 分区策略
* 一个 consumer group 中有多个 consumer，一个 topic 有多个 partition，所以必然会涉及
到 partition 的分配问题，即确定那个 partition 由哪个 consumer 来消费。
Kafka 有两种分配策略，一是 RoundRobin，一是 Range。
###### Offset
* 由于 consumer 在消费过程中可能会出现断电宕机等故障，consumer 恢复后，需要从故
障前的位置的继续消费，所以 consumer 需要实时记录自己消费到了哪个 offset，以便故障恢
复后继续消费
> Kafka 0.9 版本之前，consumer 默认将 offset 保存在 Zookeeper 中，从 0.9 版本开始，
consumer 默认将 offset 保存在 Kafka 一个内置的 topic 中，该 topic 为__consumer_offsets。
### kafka高性能
###### 零拷贝原理
###### 顺序写磁盘
###  zookeeper在kafka中的作用
* Kafka 集群中有一个 broker 会被选举为 Controller，负责管理集群 broker 的上下线，所
有 topic 的分区副本分配和 leader 选举等工作。
Controller 的管理工作都是依赖于 Zookeeper 的。
以下为 partition 的 leader 选举过程
### Kafka 事务
> Kafka 从 0.11 版本开始引入了事务支持。事务可以保证 Kafka 在 Exactly Once 语义的基
础上，生产和消费可以跨分区和会话，要么全部成功，要么全部失败


###### Producer 事务
为了实现跨分区跨会话的事务，需要引入一个全局唯一的 Transaction ID，并将 Producer
获得的PID 和Transaction ID 绑定。这样当Producer 重启后就可以通过正在进行的 Transaction
ID 获得原来的 PID。
为了管理 Transaction，Kafka 引入了一个新的组件 Transaction Coordinator。Producer 就
是通过和 Transaction Coordinator 交互获得 Transaction ID 对应的任务状态。Transaction
Coordinator 还负责将事务所有写入 Kafka 的一个内部 Topic，这样即使整个服务重启，由于
事务状态得到保存，进行中的事务状态可以得到恢复，从而继续进行。
######  Consumer 事务
上述事务机制主要是从 Producer 方面考虑，对于 Consumer 而言，事务的保证就会相对
较弱，尤其时无法保证 Commit 的信息被精确消费。这是由于 Consumer 可以通过 offset 访
问任意信息，而且不同的 Segment File 生命周期不同，同一事务的消息可能会出现重启后被
删除的情况
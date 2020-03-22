# [Redis](https://github.com/CyC2018/CS-Notes/blob/master/README.md#computer-%E6%93%8D%E4%BD%9C%E7%B3%BB%E7%BB%9F)
## REIDS数据结构
### redis的基本认知：
1. 　Redis 数据库里面的每个键值对（key-value） 都是由对象（object）组成的：
* 数据库键总是一个字符串对象（string object）;
* 数据库的值则可以是字符串对象、列表对象（list）、哈希对象（hash）、集合对象（set）、有序集合（sort set）对象这五种对象中的其中一种。
![image](https://img2018.cnblogs.com/blog/1289934/201906/1289934-20190621163930814-1395015700.png)
![image](https://img-blog.csdnimg.cn/20190425140401529.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0Z1dHVyZV9MTA==,size_16,color_FFFFFF,t_70)
2. 单线程
3. 数据都在内存中
4. redis与Memcached对比
5. redis的优点
6. 可实现的功能：分布式锁
7. 问题 ：缓存雪崩,缓存击穿，一致性hash...
8. 编码方式(节省内存) : raw,int,ht,zipmap,ziplist,intset,linkedlist
### 1. String
![image](https://img-blog.csdn.net/20180422013244538?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQveXgwNjI4/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
```
redis>SET msg "hello world"
```

* 　键（key）是一个字符串对象，对象的底层实现是一个保存着字符串“msg” 的SDS；
* 值（value）也是一个字符串对象，对象的底层实现是一个保存着字符串“hello world” 的SDS
###### SDS的三大属性

* len buf中已经占有的长度(表示此字符串的实际长度)
* free buf中未使用的缓冲区长度
* buf[] 实际保存字符串数据的地方
###### [SDS的特点](https://www.cnblogs.com/jaycekon/p/6227442.html)(与C语言比较)
1. 可以获取字符串的长度
2. 杜绝缓冲区溢出(扩容)
3. 惰性的空间释放
4. 二进制安全
## [链表](https://www.jianshu.com/p/cff18d587e8a)
![image](https://img-blog.csdn.net/20180422132249507?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3l4MDYyOA==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)
* 指令:：lpush lpop rpush rpop....
* 实现方式 : 
1. 在3.2版本之前:[ziplist](https://blog.csdn.net/yellowriver007/article/details/79021049)或linklist
2. 3.2之后都是quicklist
###### 使用场景
* 消息队列
* 文章列表
######  链表的特性
1. 双端：链表节点带有prev 和next 指针，获取某个节点的前置节点和后置节点的时间复杂度都是O（N）
2. 无环：表头节点的 prev 指针和表尾节点的next 都指向NULL，对立案表的访问时以NULL为截止
3. 表头和表尾：因为链表带有head指针和tail 指针，程序获取链表头结点和尾节点的时间复杂度为O(1)
4. 长度计数器：链表中存有记录链表长度的属性 len
5. 多态：链表节点使用 void* 指针来保存节点值，并且可以通过list 结构的dup 、 free、 match三个属性为节点值设置类型特定函数。
 
## [字典](https://www.jianshu.com/p/bfecf4ccf28b)
![image](https://img-blog.csdn.net/20180422213815886?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3l4MDYyOA==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)
###### rehash

* rehash 也可以参考 Java 中 HashMap 的原理。 
负载因子 = 哈希表中已保存的节点数量 / 哈希表数组大小。 
当哈希表中存放的键值对不断增多或减少，为了让负载因子在一个合理的范围内，需要对大小进行扩展或者收缩。（这里类似 HashMap 中的重新散列方法） 
1. 字典的 ht[1] 分配空间，空间的大小由 ht[0] 已经使用的键值对数量以及执行的扩张和收缩来决定。 
- 扩展操作，那么 ht[1] 分配的空间大小应是比当前 ht[0].used 值的二倍大的第一个 2 的整数幂。（比如当前使用空间 14，那么找 28 的下一个 2 的整数幂，为 32） 
- 收缩操作，取 ht[0].used 的第一个大于等于的 2 的整数幂。（比如 14，那么就是 16） 
2. 将 ht[0] 中的所有键值对，rehash 到 ht[1] 上面：根据新的大小来重新计算所有键的哈希和索引，映射到新数组的指定位置上。 
3. ht[0] 的所有键值对都迁移到 ht[1] 之后，释放 ht[0] ，然后将 ht[1] 设置为 ht[0] ，然后在 ht[1] 处新创建空白哈希表，为下一次 rehash 做准备。

###### 渐进式Hash
## [SET](https://www.jianshu.com/p/28138a5371d0?utm_campaign)
###### redis的集合对象set的底层存储结构特别神奇，我估计一般人想象不到，底层使用了intset和hashtable两种数据结构存储的，intset我们可以理解为数组，hashtable就是普通的哈希表（key为set的值，value为null）。是不是觉得用hashtable存储set是一件很神奇的事情。
* set的底层存储intset和hashtable是存在编码转换的，使用intset存储必须满足下面两个条件，否则使用hashtable，条件如下：
1. 结合对象保存的所有元素都是整数值
2. 集合对象保存的元素数量不超过512个
* hashtable的数据结构应该在前面的hash的章节已经介绍过了，所以这里着重讲一下intset这个新的数据结构好了。




## [ZSET](https://www.cnblogs.com/WJ5888/p/4516782.html)
![image](https://images0.cnblogs.com/blog2015/471532/201506/251618059081274.png)
![image](https://images0.cnblogs.com/blog2015/471532/201506/261155382203927.png)
* [跳跃表](https://blog.csdn.net/yanshuanche3765/article/details/82121043)
###### [redis设计与实现读后感](https://www.jianshu.com/p/d3c5b21d4da1)
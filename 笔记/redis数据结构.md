## REIDS数据结构
### redis的基本认知：
1. 　Redis 数据库里面的每个键值对（key-value） 都是由对象（object）组成的：
* 数据库键总是一个字符串对象（string object）;
* 数据库的值则可以是字符串对象、列表对象（list）、哈希对象（hash）、集合对象（set）、有序集合（sort set）对象这五种对象中的其中一种。
2. 单线程
3. 数据都在内存中
4. redis与Memcached对比
5. redis的优点
6. 可实现的功能：分布式锁
7. 问题 ：缓存雪崩,缓存击穿，一致性hash...
### 1. String

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
 
## 字典

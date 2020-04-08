## [Netty](https://www.jianshu.com/p/f16698aa8be2?utm_source=oschina-app)
### JDK中NIO的在包装
#### [NIO](https://mp.weixin.qq.com/s?__biz=MzU0OTk3ODQ3Ng==&mid=2247485766&idx=1&sn=eb1358a67aa342fcb9f641d3e9867489&chksm=fba6e145ccd1685317508361e4a3802232fe3765bda8028e04a9c533599aa0858e28f0e329c3&mpshare=1&scene=23&srcid=#rd)
##### [IO模型](https://www.cnblogs.com/loveer/p/11479249.html)
* 同步
* 异步
* 非阻塞
* 阻塞
* non-blocking IO仅仅要求处理的第一阶段不block即可(等待数据时可等待)，而asynchronous IO要求两个阶段都不能block住。
* linux的网络IO中是不存在异步IO的，linux的网络IO处理的第二阶段总是阻塞等待数据copy完成的。真正意义上的网络异步IO是Windows下的IOCP（IO完成端口）模型。
###### 系统的IO的全过程
1. 等待数据
2. 将数据从内核态拷贝到用户态

#####  io多路复用
1. select
2. poll
3. epoll 边沿触发 水平触发

#### JDK的NIO
###### [核心对象](https://www.jianshu.com/p/63209badd95a?utm_campaign=shakespeare)
* [Channel](https://www.cnblogs.com/ysw-go/p/5974372.html) : 管道 可以理解为io流的包装
* Buffer
* Selector
###### 存在的问题
* api难用
* epoll 的空转bug
#### reactor模型
###### 单线程模型
* Reactor单线程模型，指的是所有的I/O操作都在同一个NIO线程上面完成。对于一些小容量应用场景，可以使用单线程模型。
![image](https://upload-images.jianshu.io/upload_images/12624762-534080787f693a1a.png!web)
###### 多线程模型
* Rector多线程模型与单线程模型最大的区别就是有一组NIO线程处理I/O操作。主要用于高并发、大业务量场景。
![image](https://upload-images.jianshu.io/upload_images/12624762-494b47fc038d89af.png!web)
###### 主从模型(Netty使用的)
* .主从Reactor多线程模型：主从Reactor线程模型的特点是服务端用于接收客户端连接的不再是个1个单独的NIO线程，而是一个独立的NIO线程池。利用主从NIO线程模型，可以解决1个服务端监听线程无法有效处理所有客户端连接的性能不足问题。

![image](https://upload-images.jianshu.io/upload_images/12624762-4bb784467638e8e8.png!web)
### Netty
* Netty的工作构架图
![image](https://upload-images.jianshu.io/upload_images/1500839-55f5b1d5ddc13581.jpg)
* Netty的多线程模型(主从reactor)
![image](https://upload-images.jianshu.io/upload_images/1500839-9c4e284f0dc58d97.jpg)
###### [Netty的核心组件](https://www.jianshu.com/p/f16698aa8be2?utm_source=oschina-app)
###### Netty自定义协议包解决tcp的粘包粘包问题
###### [零拷贝原理](https://www.cnblogs.com/aspirant/p/11483152.html)
###### future模式
###### 序列化protoful
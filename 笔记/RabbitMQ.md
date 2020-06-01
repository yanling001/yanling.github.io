> 参考 https://www.jianshu.com/p/78847c203b76
### 应用场景
* 应用解耦、异步、流量削锋、数据分发、错峰流控、日志收集等等
### [消息协议AMQP](https://www.jianshu.com/p/f8b7c1907ac2)
* 基本组件
![image](https://upload-images.jianshu.io/upload_images/2383533-ae7bfccd76bc4407.png?imageMogr2/auto-orient/strip|imageView2/2/w/565/format/webp)
* 消息路由过程
![image](https://upload-images.jianshu.io/upload_images/5015984-7fd73af768f28704.png?imageMogr2/auto-orient/strip|imageView2/2/w/484/format/webp)
> 备注：Broker(消息代理)：消息代理接收来自生产者的消息，并将它们路由到相应的消费者手中；
####  交换器Exchange
>交换器用来接收生产者投递过来的消息，然后在将消息转发到消息队列当中。根据交换器的类型不同，交换器转发的规则也有所不同。目前，**AMQP提供了4种类型的交换器：direct，fanout，topic，header.**
###### direct(路由)
![image](https://upload-images.jianshu.io/upload_images/5015984-13db639d2c22f2aa.png?imageMogr2/auto-orient/strip|imageView2/2/w/385/format/webp)
>生产者生产某条消息时指定的路由关键字X与队列绑定到交换器上时指定的路由关键字Y完全一致，即X = Y。
###### fanout(订阅)
![image](https://upload-images.jianshu.io/upload_images/5015984-2f509b7f34c47170.png?imageMogr2/auto-orient/strip|imageView2/2/w/463/format/webp)
* 该类型的交换器会将消息转发给所有与之绑定的队列上。
###### topic(主题)
![image](https://upload-images.jianshu.io/upload_images/2383533-e44283c7428ead01.png?imageMogr2/auto-orient/strip|imageView2/2/w/427/format/webp)
![image](https://upload-images.jianshu.io/upload_images/5015984-275ea009bdf806a0.png?imageMogr2/auto-orient/strip|imageView2/2/w/558/format/webp)
* 该类型的交换器会视消息路由关键字和绑定路由关键字之间的匹配情况，进行消息的路由转发
###### header(不重要)
* 该类型的交换器与前面介绍的稍有不同，它不再是基于路由关键字（routing key）进行路由，而是基于多个属性进行路由的，这些属性比路由关键字更容易表示为消息的头。也就是说，用于路由的属性是取自于消息header属性的，当消息header的值与队列绑定时指定的值相同时，消息就会路由至相应的队列中。
### RabbitMQ的基本组件
![image](https://upload-images.jianshu.io/upload_images/5015984-367dd717d89ae5db.png?imageMogr2/auto-orient/strip|imageView2/2/w/554/format/webp)
### 简单工作队列
![image](https://www.rabbitmq.com/img/tutorials/python-two.png)
#### 轮询分发
#### 公平分发
* 前提 autoack设置false
#### 死信队列
> 一个消息变成死信的时候会放到死信队列(DLX) DLX与普通的交换机一样
###### 成为死信的条件
1. 消息被拒绝
2. 消息超过TTL
3. 队列达到最大长度
### ACK/NACK与持久化
* 目的保证消费端的可靠性
### 事务
* 保证发送端(生产者)的可靠性
### 消息确认机制confirm
* 保证发送端(生产者)的可靠性
### 面试问题
##### [防止消息丢失](https://www.jianshu.com/p/19e0927315da)
* 消息持久化 
1. 交换机持久化 
2. 队列持久化
3. Massege持久化
* ACK机制
1. 发送端(confirm)
2. 接受端ack
##### [防止消息重复消费](https://www.jianshu.com/p/7d5a1bf94f2e)
##### 如何保证可靠性
### 集群模式
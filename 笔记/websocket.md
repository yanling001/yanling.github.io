### websocket与http的区别
* http链接分为短链接，长链接，短链接是每次请求都要三次握手才能发送自己的信息。即每一个request对应一个response。长链接是在一定的期限内保持链接。保持TCP连接不断开。客户端与服务器通信，必须要有客户端发起然后服务器返回结果。客户端是主动的，服务器是被动的。
* WebSocket他是为了解决客户端发起多个http请求到服务器资源浏览器必须要经过长时间的轮训问题而生的，他实现了多路复用，他是全双工通信。在webSocket协议下客服端和浏览器可以同时发送信息。
  建立了WenSocket之后服务器不必在浏览器发送request请求之后才能发送信息到浏览器。这时的服务器已有主动权想什么时候发就可以发送信息到服务器。而且信息当中不必在带有head的部分信息了与http的长链接通信来说，这种方式，不仅能降低服务器的压力。而且信息当中也减少了部分多余的信息。
### websocket的连接过程
* 客户端发起HTTP握手，告诉服务端进行WebSocket协议通讯，并告知WebSocket协议版本。服务端确认协议版本，升级为WebSocket协议。之后如果有数据需要推送，会主动推送给客户端。
连接开始时，客户端使用HTTP协议和服务端升级协议，升级完成后，后续数据交换遵循WebSocket协议。
*  Request Headers

```
Accept-Encoding: gzip, deflate, br
Accept-Language: zh,zh-TW;q=0.9,en-US;q=0.8,en;q=0.7,zh-CN;q=0.6
Cache-Control: no-cache
Connection: Upgrade
Host: 127.0.0.1:3000
Origin: http://localhost:3000
Pragma: no-cache
Sec-WebSocket-Extensions: permessage-deflate; client_max_window_bits
Sec-WebSocket-Key: bwb9SFiJONXhQ/A4pLaXIg==
Sec-WebSocket-Version: 13
Upgrade: websocket  //核心字段
```
###### 重要的字段
* Connection: Upgrade 表示要升级协议
* Upgrade: websocket 要升级协议到websocket协议
* Sec-WebSocket-Version 表示websocket的版本。如果服务端不支持该版本，需要返回一个Sec-WebSocket-Versionheader，里面包含服务端支持的版本号。
* Sec-WebSocket-Key 对应服务端响应头的Sec-WebSocket-Accept，由于没有同源限制，websocket客户端可任意连接支持websocket的服务。这个就相当于一个钥匙一把锁，避免多余的，无意义的连接。

* 服务端响应的 Response Headers

```
Connection: Upgrade
Sec-WebSocket-Accept: 2jrbCWSCPlzPtxarlGTp4Y8XD20=
Upgrade: websocket
```
######  关键字段
* sec-WebSocket-Accept: 用来告知服务器愿意发起一个websocket连接， 值根据客户端请求头的Sec-WebSocket-Key计算出来

### 使用中的问题
###### 用户离线怎么存消息
* 解决： 将未在线的用户信息存入队列(看了张硕的面试准备改存到redis中)
###### nginx代理websocket部署的时候默认60秒没发消息会自己断开后台日志能看到异常

* 解决： 做个定时任务
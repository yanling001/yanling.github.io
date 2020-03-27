## [RPC](https://www.jianshu.com/p/7d6853140e13)
### 基本概念
######  RPC（Remote Procedure Call）
* 远程过程调用，简单的理解是一个节点请求另一个节点提供的服务
###### 2. 与本地方法调用的对比
* 本地调用 : 本地方法调用的函数体通过函数指针来指定
* 远程过程调用
1. 首先客户端需要告诉服务器，需要调用的函数，这里函数和进程ID存在一个映射，客户端远程调用时，需要查一下函数，找到对应的ID，然后执行函数的代码。
2. 客户端需要把本地参数传给远程函数，本地调用的过程中，直接压栈即可，但是在远程调用过程中不再同一个内存里，无法直接传递函数的参数，因此需要客户端把参数转换成字节流，传给服务端，然后服务端将字节流转换成自身能读取的格式，是一个序列化和反序列化的过程。
3.数据准备好了之后，如何进行传输？网络传输层需要把调用的ID和序列化后的参数传给服务端，然后把计算好的结果序列化传给客户端，因此TCP层即可完成上述过程，gRPC中采用的是HTTP2协议。
###### 具体步骤

```
// Client端 
//    Student student = Call(ServerAddr, addAge, student)
1. 将这个调用映射为Call ID。
2. 将Call ID，student（params）序列化，以二进制形式打包
3. 把2中得到的数据包发送给ServerAddr，这需要使用网络传输层
4. 等待服务器返回结果
5. 如果服务器调用成功，那么就将结果反序列化，并赋给student，年龄更新

// Server端
1. 在本地维护一个Call ID到函数指针的映射call_id_map，可以用Map<String, Method> callIdMap
2. 等待服务端请求
3. 得到一个请求后，将其数据包反序列化，得到Call ID
4. 通过在callIdMap中查找，得到相应的函数指针
5. 将student（params）反序列化后，在本地调用addAge()函数，得到结果
6. 将student结果序列化后通过网络返回给Client
```
###### rpc组件关系
![image](https://upload-images.jianshu.io/upload_images/7632302-ca0ba3118f4ef4fb.png?imageMogr2/auto-orient/strip|imageView2/2/format/webp)
###### rpc的模型
* 在微服务的设计中，一个服务A如果访问另一个Module下的服务B，可以采用HTTP REST传输数据，并在两个服务之间进行序列化和反序列化操作，服务B把执行结果返回过来。
![image](https://upload-images.jianshu.io/upload_images/7632302-19ad38cdd9a4b3ec.png?imageMogr2/auto-orient/strip|imageView2/2/format/webp)
* 由于HTTP在应用层中完成，整个通信的代价较高，远程过程调用中直接基于TCP进行远程调用，数据传输在传输层TCP层完成，更适合对效率要求比较高的场景，RPC主要依赖于客户端和服务端之间建立Socket链接进行，底层实现比REST更复杂。
###### [RPC、RMI、REST的区别](https://www.jianshu.com/p/024965e2c160)
* REST : 将业务对象上执行的操作映射到HTTP动词，格式非常简单，可以使用浏览器进行扩展和传输，通过JSON数据完成客户端和服务端之间的消息通信，直接支持请求/响应方式的通信。不需要中间的代理，简化了系统的架构，不同系统之间只需要对JSON进行解析和序列化即可完成数据的传递。


### RPC想法的实现GRPC
* gRPC 是一种基于二进制流的消息协议，可以采用基于Protocol Buffer的IDL定义grpc API,这是Google公司用于序列化结构化数据提供的一套语言中立的序列化机制，客户端和服务端使用HTTP/2以Protocol Buffer格式交换二进制消息。
* gRPC的优势是，设计复杂更新操作的API非常简单，具有高效紧凑的进程通信机制，在交换大量消息时效率高，远程过程调用和消息传递时可以采用双向的流式消息方式，同时客户端和服务端支持多种语言编写，互操作性强；不过gRPC的缺点是不方便与JavaScript集成，某些防火墙不支持该协议。
* 注册中心：当项目中有很多服务时，可以把所有的服务在启动的时候注册到一个注册中心里面，用于维护服务和服务器之间的列表，当注册中心接收到客户端请求时，去找到该服务是否远程可以调用，如果可以调用需要提供服务地址返回给客户端，客户端根据返回的地址和端口，去调用远程服务端的方法，执行完成之后将结果返回给客户端。这样在服务端加新功能的时候，客户端不需要直接感知服务端的方法，服务端将更新之后的结果在注册中心注册即可，而且当修改了服务端某些方法的时候，或者服务降级服务多机部署想实现负载均衡的时候，我们只需要更新注册中心的服务群即可。
![image](https://upload-images.jianshu.io/upload_images/7632302-0b09dd85b8baa318.png?imageMogr2/auto-orient/strip|imageView2/2/format/webp)
###### [grpc的使用](https://github.com/guangxush/SpringBoot_GRPC)
### [动态代理](https://www.yuque.com/aqfz37/gl082g/fq89nk#kfD1D)

* 动态代理的代码
```java
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//提供服务的接口
interface Service {
    String sellCar(String carName);
}

//实现服务的类
class ServiceImp implements Service {

    @Override
    public String sellCar(String carName) {
        return carName + "is ready!";
    }
}

//实现了InvocationHandler接口, 它是包含在反射包中的
class MyInvocationHandler implements InvocationHandler {

    private Object target;

    //在构造函数中初始化target对象
    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    //通过invoke方法, 可以调用target类中的方法
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Call:" + method.getName());
        //通过method的invoke方法调用target类中的方法
        //args是参数
        Object result = method.invoke(target, args);
        return result;
    }
}

public class DynamicProxy {
    public static void main(String[] args) {
        //要被代理的真实对象
        Service service = new ServiceImp();
        //要代理哪个真实对象, 就把这个对象输进去
        InvocationHandler invocationHandler = new MyInvocationHandler(service);
        //通过第一个参数来指定加载代理对象的类加载器
        //通过第二个参数来指定为代理对象提供服务的接口
        //通过第三个参数把代理对象关联到invocationHandler这个对象上
        Service serviceProxy =(Service) Proxy.newProxyInstance(service.getClass().getClassLoader(),service.getClass().
                        getInterfaces(), invocationHandler);
        System.out.println(serviceProxy.sellCar("Aston Martin"));

    }
}
```
* 动态代理是通过Proxy创建代理对象，然后将接口方法“代理”给InvocationHandler完成的
###### 使用动态代理的核心方法 ：
* Proxy.newProxyInstance() //生成代理对象

```
  public static Object newProxyInstance(ClassLoader loader,
                                          Class<?>[] interfaces,
                                          InvocationHandler h)
```
* 参数
1. ClassLoader : 需要代理的接口的类加载器
2. interfaces : 需要代理实现的接口
3. InvocationHandler : 接口方法是通过给InvocationHandler完成的
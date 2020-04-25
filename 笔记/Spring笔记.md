#### [师东旋的笔记](https://blog.csdn.net/wintershii/article/details/98883263)
![image](https://img-blog.csdnimg.cn/20190808190744693.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dpbnRlcnNoaWk=,size_16,color_FFFFFF,t_70)
### Spring IOC
1. IOC就是控制反转，是指创建对象的控制权的转移，以前创建对象的主动权和时机是由自己把控的，而现在这种权力转移到Spring容器中，并由容器根据配置文件去创建实例和管理各个实例之间的依赖关系，对象与对象之间松散耦合，也利于功能的复用。DI依赖注入，和控制反转是同一个概念的不同角度的描述，即 应用程序在运行时依赖IoC容器来动态注入对象需要的外部资源
2. 直观解释 :IOC让对象的创建不用去new了，可以由spring自动生产，使用java的反射机制，根据配置文件在运行时动态的去创建对象以及管理对象，并调用对象的方法的。

##### Bean 的生命周期与作用域
###### bean的生命周期
1. init-method : Bean被初始化的时候执行的方法
2. destroy-method : 在容器销毁时调用的方法
![image](https://upload-images.jianshu.io/upload_images/13118720-b9ee7e68c954eb91.png)
###### bean的作用域
* scope属性配置Bean的作用域范围
1. singletion : 默认值，=true， 在IoC容器中只存在一个Bean实例，以单例方式存在
2. prototype : 每次从容器中调用Bean时，都会返回一个新实例，即getBean()时等于new对象
3. request : 每次Http请求都会创建一个新的Bean。仅适用于WebApplicationCotext环境
4. session : 同一个Http Session共享一个Bean，不同的Session使用不同的Bean。仅适用于WebApplicationCotext环境
5. globalsession : 必须在prolet环境下使用，仅适用于WebApplicationCotext环境

##### [Bean 的加载过程](https://www.jianshu.com/p/8c24e0c804cc)
##### [Spring容器的启动过程](https://www.jianshu.com/p/d9048260a99c)

##### 依赖注入与循环依赖
* 三级缓存 :

1. singletonFactories : 单例对象工厂的cache
2. earlySingletonObjects : 提前曝光的单例对象的Cache
3. singletonObjects : 单例对象的cache
##### Bean 工厂和 Application contexts 
![image](https://upload-images.jianshu.io/upload_images/3673891-b553d04f50531589.png?imageMogr2/auto-orient/strip|imageView2/2/format/webp)

![image](https://upload-images.jianshu.io/upload_images/3673891-a1cb89429a1897c2.png?imageMogr2/auto-orient/strip|imageView2/2/format/webp)
* ApplicationContext 其实就是一个BeanFactory
1. ApplicationContext 继承了ListableBeanFactory，这个ListableBeanFactory接口它可以获取多个bean，我们看BeanFactory接口的源码可以发现，BeanFactory的接口都是获取单个bean的

2. 同时ApplicationContext 还继承了HierarchicalBeanFactory接口，这个接口可以在应用这起用多个BeanFactory，然后将多个BeanFactory设置父子关系

3. ApplicationContext 接口中的最后一个方法：AutowireCapableBeanFactory getAutowireCapableBeanFactory() throws IllegalStateException; 他的返回值是AutowireCapableBeanFactory，这个接口就是用来自动装配Bean的
######  两者的区别
* BeanFactory 接口
1. 这是一个用来访问 Spring 容器的 root 接口，要访问 Spring 容器，我们将使用 Spring 依赖注入功能，使用 BeanFactory 接口和它的子接口 特性：
2. Bean 的实例化/串联 **通常情况，BeanFactory 的实现是使用懒加载的方式，这意味着 beans 只有在我们通过
getBean() 方法直接调用它们时才进行实例化 实现 BeanFactory 最常用的 API 是 XMLBeanFactory**
---
* ApplicationContext 应用上下文
* 继承BeanFactory接口，它是Spring的一各更高级的容器，提供了更多的有用的功能；
1) 国际化（MessageSource）
2) 访问资源，如URL和文件（ResourceLoader）
3) 载入多个（有继承关系）上下文 ，使得每一个上下文都专注于一个特定的层次，比如应用的web层  
4) 消息发送、响应机制（ApplicationEventPublisher）
5) AOP（拦截器）
--- 
### Spring AOP

##### JDK动态代理 与 CGLib动态代理
---
### Spring 的事务
---

### SPringBoot启动原理

---


### SpringMVC


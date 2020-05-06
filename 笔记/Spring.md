#### [师东旋的笔记](https://blog.csdn.net/wintershii/article/details/98883263)
![image](https://img-blog.csdnimg.cn/20190808190744693.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dpbnRlcnNoaWk=,size_16,color_FFFFFF,t_70)
### [Spring IOC](https://www.jianshu.com/p/9fe5a3c25ab6)
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
###### 什么是AQP
* AOP技术利用一种称为"横切"的技术, 剖解开封装的对象内部, 并将哪些影响了多个类的公共行为封装到一个可重用模块, 并将其命名为"Aspect". 所谓"面", 就是将那些与业务无关, 却为业务模块所共同调用的逻辑或责任封装起来, 便于减少系统的重复代码, 降低模块见得耦合度, 并有利于未来的可操作性和可维护性

* AOP将软件系统分为两部分: 核心关注点和横切关注点. 业务处理的主要流程是核心关注点, 与之关系不大的是横切关注点. 横切关注点的一个特点是, 他们经常发生在核心关注点的多处, 而且基本都相似. 例如权限认证, 日志, 事务处理. AOP的作用在于分离系统中的各种关注点, 将核心关注点和横切关注点分离开来

* 事务处理，日志打印就是一个典型的aop应用，生成代理对象AopProxy然后对调用方法进行拦截，从而实现对目标对象的增强，生成AopProxy的过程由两种，当代理对象实现接口时，使用jdk动态代理，当没有接口时使用CGLIB来生成字节码文件，cglib底层采用的是对ASM，ASM是一个可以动态修改已经编译过的class文件或者是动态生成新的java class。
###### AOP的实现基础 动态代理
* Spring提供了两种方式来生成代理对象 : JDK 动态代理和CGLib, **具体使用哪种方式生成由AopProxyFactory根据AdviceSupport对象的配置来决定. 默认的策略是如果目标类是接口, 则使用JDK 动态代理, 否则使用CGLib**
##### [JDK动态代理 与 CGLib动态代理](https://www.jianshu.com/p/46d092bb737d)
###### JDK动态代理
* 利用拦截器(拦截器必须实现InvocationHanlder)加上反射机制生成一个实现代理接口的匿名类，
在调用具体方法前调用InvokeHandler来处理。
###### CGLiB动态代理
* 利用ASM开源包，对代理对象类的class文件加载进来，通过修改其字节码生成子类来处理。
###### 何时使用JDK还是CGLiB？
> 1）如果目标对象实现了接口，默认情况下会采用JDK的动态代理实现AOP。

> 2）如果目标对象实现了接口，可以强制使用CGLIB实现AOP。

> 3）如果目标对象没有实现了接口，必须采用CGLIB库，Spring会自动在JDK动态代理和CGLIB之间转换。

###### JDK动态代理和CGLIB字节码生成的区别？

> 1）JDK动态代理只能对实现了接口的类生成代理，而不能针对类。

> 2）CGLIB是针对类实现代理，主要是对指定的类生成一个子类，覆盖其中的方法，
并覆盖其中方法实现增强，但是因为采用的是继承，所以该类或方法最好不要声明成final，
对于final类或方法，是无法继承的。
###### CGlib比JDK快？
1. 使用CGLib实现动态代理，CGLib底层采用ASM字节码生成框架，使用字节码技术生成代理类，在jdk6之前比使用Java反射效率要高。唯一需要注意的是，CGLib不能对声明为final的方法进行代理，因为CGLib原理是动态生成被代理类的子类。
2. 在jdk6、jdk7、jdk8逐步对JDK动态代理优化之后，在调用次数较少的情况下，JDK代理效率高于CGLIB代理效率，只有当进行大量调用的时候，jdk6和jdk7比CGLIB代理效率低一点，但是到jdk8的时候，jdk代理效率高于CGLIB代理，总之，每一次jdk版本升级，jdk代理效率都得到提升，而CGLIB代理消息确有点跟不上步伐。


###### Spring如何选择用JDK还是CGLiB？
1. 当Bean实现接口时，Spring就会用JDK的动态代理。
2. 当Bean没有实现接口时，Spring使用CGlib是实现。
3. 可以强制使用CGlib（在spring配置中加入<aop:aspectj-autoproxy proxy-target-class="true"/>）。


---
### [Spring 的事务](https://www.jianshu.com/p/221ca974931c)
---

### SPringBoot
###### 零配置原理
* Spring Boot 自动配置原理是什么？
* 注解 @EnableAutoConfiguration, @Configuration, @ConditionalOnClass 就是自动配置的核心，首先它得是一个配置文件，其次根据类路径下是否有这个类去自动配置。
* **@EnableAutoConfiguration** 

```
@SuppressWarnings("deprecation")
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@AutoConfigurationPackage
@Import(EnableAutoConfigurationImportSelector.class)
public @interface EnableAutoConfiguration {
    ...
}
```

*  @EnableAutoConfiguration自动配置的魔法骑士就变成了：从classpath中搜寻所有的META-INF/spring.factories配置文件，并将其中org.springframework.boot.autoconfigure.EnableutoConfiguration对应的配置项通过反射（Java Refletion）实例化为对应的标注了@Configuration的JavaConfig形式的IoC容器配置类，然后汇总为一个并加载到IoC容器。
*  @EnableAutoConfiguration也是借助@Import的帮助，将所有符合自动配置条件的bean定义加载到IoC容器，仅此而已！
    @EnableAutoConfiguration会根据类路径中的jar依赖为项目进行自动配置，如：添加了spring-boot-starter-web依赖，会自动添加Tomcat和Spring MVC的依赖，Spring Boot会对Tomcat和Spring MVC进行自动配置。
* 借助EnableAutoConfigurationImportSelector，@EnableAutoConfiguration可以帮助SpringBoot应用将所有符合条件的@Configuration配置都加载到当前SpringBoot创建并使用的IoC容器

###### [SPringBoot的启动原理](https://www.cnblogs.com/xiaopotian/p/11052917.html)

---


### [SpringMVC](https://www.jianshu.com/p/d2396d3d3fda)
![image](https://upload-images.jianshu.io/upload_images/7503667-30aa54c096ffe4df.jpg)
###### 核心组件

1. 前端控制器DispatcherServlet（配置即可）
功能:中央处理器,接收请求,自己不做任何处理,而是将请求发送给其他组件进行处理。DispatcherServlet 是整个流程的控制中心。
2. 处理器映射器HandlerMapping(配置即可)
功能:根据DispatcherServlet发送的url请求路径查找Handler
常见的处理器映射器:BeanNameUrlHandlerMapping,SimpleUrlHandlerMapping,
ControllerClassNameHandlerMapping,DefaultAnnotationHandlerMapping(不建议使用)
3. 处理器适配器HandlerAdapter（配置即可）
功能:按照特定规则（HandlerAdapter要求的规则）去执行Handler。
通过HandlerAdapter对处理器进行执行，这是适配器模式的应用，通过扩展多个适配器对更多类型的处理器进行执行。
常见的处理器适配器:HttpRequestHandlerAdapter，SimpleControllerHandlerAdapter，AnnotationMethodHandlerAdapter
4. 处理器Handler即Controller(程序猿编写)
功能:编写Handler时按照HandlerAdapter的要求去做，这样适配器才可以去正确执行Handler。
5. 视图解析器ViewReslover(配置即可)
功能:进行视图解析，根据逻辑视图名解析成真正的视图。
ViewResolver负责将处理结果生成View视图，ViewResolver首先根据逻辑视图名解析成物理视图名即具体的页面地址，再生成View视图对象，最后对View进行渲染将处理结果通过页面展示给用户。
springmvc框架提供了多种View视图类型,如:jstlView、freemarkerView、pdfView...
6. 视图View(程序猿编写)
View是一个接口，实现类支持不同的View类型（jsp、freemarker、pdf...）



### [参考博客](https://www.jianshu.com/p/2a5d3b6336ba)
## 基本原理
#### [Quartz的工作原理](https://www.iteye.com/blog/maleo-672869)      
*  对于一个调度作业，通常有两个因素构成

1. **调度的内容**【What】:即调度具体做什么工作，例如是发邮件、系统备份
2. **调度的时间**【when】:什么时候工 作，这个又包含两个内容，开始时间【调度第一次执行的时间】，调度时间【在调度开始之后，在什么时间的时候，执行定义的调度工作】。

* Quartz框架提供了Job接口和Trigger接口来分别对应调度的内容和调度时间，**由于Job接口和Trigger接口是完全分离的，彼此互不关 心，他们之间要建立某种关联，就需要通过Scheduler来帮助他们建立联系。**
**Quartz启动时，会初始化一套work线程池，这套线程被用于执行预定义的调度作业。==Job和Trigger的关联根据配置的不同【基于内存的调度、 基于数据库的调度】而不同：如果是基于内存的调度，那么Job信息和Trigger信息分别放到两个HashMap中，通过RAMJobSupport来 建立关联；如果是基于数据库的调度，Job信息、Tirgger信息以及它们的关联信息都被存到数据库表中==。**
#### 核心对象
##### job
• 1.Job表示一个工作, 要执行的具体内容. 此接口中只有一个方法 :
void execute(JobExecutionContext context);
通过这个接口来另一需要执行的任务. Job运行时的信息保存在JobDataMap实例中.
##### JobDetail
• 2.JobDetail表示一个具体的可执行的调度程序, Job是这个可执行调度程序所要执行的内容, 另外JobDetail还包含了这个任务调度的方案和策略
##### Trigger
• 3.Trigger代表一个调度参数的配置, 触发器. 主要有SimpleTrigger和CronTrigger这两个子类. 当仅需要触发一次或以固定时间周期执行时, 可以使用SimpleTrigger. 当需要复杂的调度方案, 可以使用CronTrigger
##### Scheduler
• 4.Scheduler代表一个调度单独容器, 一个调度容器中可以注册多个JobDetail和Trigger. 当Trigger和JobDetail组合, 就可以被Scheduler容器调度了.
##### 执行调度原理
![image](https://files.jb51.net/file_images/article/201805/201805070933131.png)

###### 分两类线程：
* Scheduler调度线程(常规调度)
* 常规调度线程轮询存储的所有trigger，如果有需要触发的trigger，即到达了下一次触发的时间，则从任务执行线程池获取一个空闲线程，执行与该trigger关联的任务。
* 任务执行线程(Misfire调度)
* Misfire线程是扫描所有的trigger，查看是否有misfiredtrigger，如果有的话根据misfire的策略分别处理(fire now OR wait for the next fire)。
![image](https://files.jb51.net/file_images/article/201805/201805070933142.png)
#### 与 Timer的比较
Timer |  Quartz
---|---
每个任务一个线程 | 线程池
只是基于内存的调度 | 内存&数据库
不能支持很复杂的调度 | CronTrigger可以定义复杂的调
### [集群调度](https://www.jb51.net/article/139597.htm)
###### 分布式情况下定时任务会出现哪些问题？
* 答：分布式集群的情况下，怎么保证定时任务不被重复执行（解释：分布式情况下，一个项目被打成war包部署到多台服务器上,这个时候如果有任务调度的话就有可能产生幂等性（一个任务被重复执行））
###### 分布式定时任务解决方案
1. 使用zookeeper实现分布式锁，保证只有一台服务器执行job， 缺点(需要创建临时节点、和事件通知不易于扩展)
2. 使用配置文件做一个开关  ，加一个配置start=true或者start=false，如果为true，执行job，如果为false，不执行job，缺点;发布后，需要重启
3. 数据库唯一约束(意思就是说每台服务器（例如tomcat）执行job之前必须先往数据库中插入一个唯一id(主键id)，插入成功那么就执行，其他服务器就不能插入成功了，因为插入的id是唯一的)，缺点效率低
4. 使用分布式任务调度平台XXLJOB
##### Quartz集群调度解决方案
* 一个Quartz集群中的每个节点是一个独立的Quartz应用，它又管理着其他的节点。这就意味着你必须对每个节点分别启动或停止。Quartz集群中，独立的Quartz节点并不与另一其的节点或是管理节点通信，而是通过相同的数据库表来感知到另一Quartz应用的。**quartz集群实现原理，利用数据库记录job行为，并通过锁机制，使job在同一次中仅运行一次。**
![image](https://files.jb51.net/file_images/article/201805/201805070933143.png)
######  Quartz Scheduler在集群中的启动流程
* Quartz Scheduler自身是察觉不到被集群的，只有配置给Scheduler的JDBC JobStore才知道。当Quartz Scheduler启动时，**它调用JobStore的schedulerStarted()方法，它告诉JobStore Scheduler已经启动了。schedulerStarted() 方法是在JobStoreSupport类中实现的。JobStoreSupport类会根据quartz.properties文件中的设置来确定Scheduler实例是否参与到集群中。假如配置了集群，一个新的ClusterManager类的实例就被创建、初始化并启动。ClusterManager是在JobStoreSupport类中的一个内嵌类，继承了java.lang.Thread，它会定期运行，并对Scheduler实例执行检入的功能。Scheduler也要查看是否有任何一个别的集群节点失败了。检入操作执行周期在quartz.properties中配置。**
######  侦测失败的Scheduler节点
* 当一个Scheduler实例执行检入时，它会查看是否有其他的Scheduler实例在到达他们所预期的时间还未检入。这是通过检查SCHEDULER_STATE表中Scheduler记录在LAST_CHEDK_TIME列的值是否早于org.quartz.jobStore.clusterCheckinInterval来确定的。如果一个或多个节点到了预定时间还没有检入，那么运行中的Scheduler就假定它(们) 失败了。
###### 从故障实例中恢复Job
* 当一个Sheduler实例在执行某个Job时失败了，有可能由另一正常工作的Scheduler实例接过这个Job重新运行。要实现这种行为，配置给JobDetail对象的Job可恢复属性必须设置为true（job.setRequestsRecovery(true)）。如果可恢复属性被设置为false(默认为false)，当某个Scheduler在运行该job失败时，它将不会重新运行；而是由另一个Scheduler实例在下一次触发时间触发。Scheduler实例出现故障后多快能被侦测到取决于每个Scheduler的检入间隔（即2.3中提到的org.quartz.jobStore.clusterCheckinInterval）。
#### 集群执行流程
 Quartz是在同一个数据库下，其实是使用数据库（如：MySQL）做分布式锁，当任务线程执行前加锁，执行完在finally解锁。
 > Quartz中执行了如下SQL，以悲观锁的形式，对某条数据进行行锁，这样其他执行线程就必须等待
Sql代码  

```
public static final String SELECT_FOR_LOCK = "SELECT * FROM "    
            + TABLE_PREFIX_SUBST + TABLE_LOCKS + " WHERE " + COL_SCHEDULER_NAME + " = " + SCHED_NAME_SUBST    
            + " AND " + COL_LOCK_NAME + " = ? FOR UPDATE";
```
 * 具体流程如下：
```
0.调度器线程run()
 
1.获取待触发trigger
 
    1.1数据库LOCKS表TRIGGER_ACCESS行加锁
 
    1.2读取JobDetail信息
 
    1.3读取trigger表中触发器信息并标记为"已获取"
 
    1.4commit事务,释放锁
 
2.触发trigger
 
    2.1数据库LOCKS表STATE_ACCESS行加锁
 
    2.2确认trigger的状态（已经被获取过的trigger，就不会再执行了）
 
    2.3读取trigger的JobDetail信息
 
    2.4读取trigger的Calendar信息
 
    2.3更新trigger信息
 
    2.3commit事务,释放锁
 
3实例化并执行Job
 
    3.1从线程池获取线程执行JobRunShell的run方法
```
> 参考 : [Quartz管中窥豹之集群管理](https://links.jianshu.com/go?to=http%3A%2F%2Fwenqy.com%2F2018%2F04%2F03%2Fquartz%25e7%25ae%25a1%25e4%25b8%25ad%25e7%25aa%25a5%25e8%25b1%25b9%25e4%25b9%258b%25e9%259b%2586%25e7%25be%25a4%25e7%25ae%25a1%25e7%2590%2586.html)
  [集群案例](https://www.cnblogs.com/zhenyuyaodidiao/p/4755649.html) [原理详见](https://www.iteye.com/blog/youyu4-2404911) [springboot+quartz集群搭建的完整步骤](https://www.jb51.net/article/139591.htm)
## 使用
#### 基于缓存
#### 基于数据库
1. 生产环境发生内存溢出该怎么处理
2. 生产环境分配给服务器多少内存合适
3. 如何对垃圾收集器的性能调优
4. Cpu负载飙升如何处理
5. 生产环境分配多少线程合适
6. 不加log如何确定请求的执行的是那行代码
7. 不加log如何查看方法的返回值
8. JVM的字节码是什么
9. 循环体中做字符串拼接为什么效率低
10. 字符串+底层一定是StringBuilder.append吗
11. String常量池是咋回事
12. i ++ 和++i那个效率高
## [JVM参数文档](https://docs.oracle.com/javase/8/docs/technotes/tools/unix//index.html)
### JVM的参数类型
* **标准参数**
1. -help
2. -server -client
3. -version -showversion
4. -cp -classpath
* **X参数** [解释执行与JIT代码生成器](https://www.cnblogs.com/dzhou/p/9549839.html)
1. -Xint : 解释执行(java执行)
2. -Xcomp : 第一次使用就编译成本地代码
3. -Xmixed : 混合模式，JVM自己决定是否编译成本地代码
* **XX参数** 重点
###### Boolean类型 代表启用禁用
* 格式 : -XX:[+-]<name>
* 例子
1. -XX:+UseConMarkSweepGC  使用CMS
2.  -XX:+UseG1GC 使用G1
###### 非Boolean类型
* 格式 : -XX:<name>=<value>
* 例子
1. -XX:MaxGCPauseMillis=500 gc的最大停顿时间
2. -XX:GCTimeRatio=19
###### -Xmx -Xms
1. -Xmx 等价于 -XX:InitialHeapSize 初始化堆大小
2. -Xms 等价于 -XX:MaxHeapSize 分配最大堆内存

##### 如何查看JVM运行时参数
1. -XX:+PrintFlagsInitial  查看初始参数
2. -XX:+printFlagsFinal 查看末参数
##### jps指令查看 java进程

```
[root@izbp12h4o1a2hzym2nx7mwz ~]# jps
29152 Jps
13207 Bootstrap
17240 jar

```
### [JVM官方指令](https://docs.oracle.com/javase/8/docs/technotes/tools/unix//index.html)
##### jinfo指令查看java进程的信息

```
[root@izbp12h4o1a2hzym2nx7mwz ~]# jinfo 17240
java.runtime.name = Java(TM) SE Runtime Environment
java.vm.version = 25.241-b07
sun.boot.library.path = /usr/java/jdk1.8.0_241/jre/lib/amd64
java.protocol.handler.pkgs = org.springframework.boot.loader
java.vendor.url = http://java.oracle.com/

```
##### jstat指令查看jvm的类加载信息,垃圾收集信息,JIT编译信息
* 分析gc

```
[root@izbp12h4o1a2hzym2nx7mwz ~]# jstat -class 17240 1000 10
Loaded(加载的类个数)  Bytes(多少k)  Unloaded(卸载的类)  Bytes     Time (花费的时间)  
 12335              22723.1         289               444.4      18.29

```
* 解释 jstat -class 17240 1000 10 查看java进程的类加载信息
*  17240 (pid) 1000 (1000ms查看一次) 10 (总共看十次)
*  jstat -gc 获取gc的情况

```
^C[root@izbp12h4o1a2hzym2nx7mwz ~]# jstat -gc 17240 1000 10
 S0C(s0总)    S1C    S0U(s0使用)   S1U      EC       EU        OC         OU       MC     MU    CCSC   CCSU   YGC     YGCT    FGC    FGCT     GCT   
8576.0      8576.0      8183.4    0.0   68736.0   1162.8   171712.0   160091.0  70104.0 66962.6 8704.0 8189.8    264    1.554  11      2.366    3.920

```

#### jmap内存映象(查看jvm内存情况分析内存溢出)
* MAT分析jmap导出的文件内存溢出(将jmap导出的内存映像文件导入可以看到内存报告)
#### jstack(线程的堆栈跟踪)
* 用于解决cpu的飙升
* 定位死循环与死锁
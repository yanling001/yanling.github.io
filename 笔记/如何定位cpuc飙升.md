* (1) 定位耗费cpu的进程

1. top -c
```
  PID USER      PR  NI    VIRT    RES    SHR S %CPU %MEM     TIME+ COMMAND
  522 root      20   0  712448   1632    808 S 99.7  0.1  23627:24 /tmp/kdevtmpfsi 
```
* 可以看到进程消耗的cpu情况
* (2) 定位cpu消耗的线程
1. top -Hp 522 (PID)
2.  将PID转换成16进制  printf "%x \n" 7166
* (3) 定位代码
1. jstack 522 |grep '0x522（522的16进制）' -CS -color
---
###### top指令
###### ps指令
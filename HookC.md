```
HWFRD:/ # ps |grep cn.qssq666.testjni



```

结果


```
u0_a271   23912 4150  2369828 88288 SyS_epoll_ 74a7228c70 S cn.qssq666.testjni

```

```

1|HWFRD:/ # cat /proc/23912/maps |grep libmylib.so

```

```

结果
```
```
74a1d22000-74a1d61000 r-xp 00000000 fd:00 140133                         /data/app/cn.qssq666.testjn
i-2/lib/arm64/libmylib.so
74a1d70000-74a1d72000 r--p 0003e000 fd:00 140133                         /data/app/cn.qssq666.testjn
i-2/lib/arm64/libmylib.so
74a1d72000-74a1d73000 rw-p 00040000 fd:00 140133                         /data/app/cn.qssq666.testjn
i-2/lib/arm64/libmylib.so
HWFRD:/ #
```


```

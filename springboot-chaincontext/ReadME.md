该demo主要演示全链路上下文,其功能包括如下

- RestTemplate在调用下游服务时如何将链路上下文数据携带?
- Feign在调用下游服务时如何将链路上下文数据携带?
- 服务接收方如何将header或者参数中携带的相关数据设置到链路上下文中?
- 如何保证hystrix线程池的情况下链路上下文依然有效?
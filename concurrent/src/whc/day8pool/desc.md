# 三种线程池
- newFixedThreadPool
- newSingleThreadExecutor
- newCachedThreadPool
- 全部都是基于 ThreadPoolExecutor 类实现的

# 参数说明
- corePoolSize 常驻线程数量(核心)
- maximumPoolSize 最大线程数量
- keepAliveTime 空闲线程存活时间
- TimeUnit unit 时间单位
- BlockingQueue<Runnable> workQueue 任务队列
- ThreadFactory threadFactory 线程工厂
- RejectedExecutionHandler handler 拒绝策略

# 工作流程
- 主线程 => ThreadPoolExecutor.execute() =>Runnable.run() 线程创建
- 阻塞队列大小3  常驻线程池2 最大线程数5
- 常驻线程处理 , 位置用完之后放入任务队列
- 任务队列用完之后,创建新的线程处理
- 如果最大线程数量限制了,又来了新请求 那么会触发拒绝策略

# 拒绝策略
- AbortPolicy 直接抛出异常
- CallerRunsPolicy 调用者处理 不会抛弃任务 也不会   任务退回给调用者从而降低新任务的流量
- DiscardOldestPolicy 抛弃队列中等待最久的任务,然后把当前任务加入队列中尝试再次提交当前任务
- DiscardPolicy 丢弃无法处理的任务 不处理 不抛错 如果允许任务丢失这是最好的一种策略

# 实战方式
- 阿里开发手册规定 禁用Executors 创建而是使用ThreadPoolExecutor创建 规避资源耗尽的风险
- Fixed和Signle会堆积请求 导致OOM
- Cached会创建大量线程导致OOM
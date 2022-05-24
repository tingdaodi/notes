### 目录

* 一、实现多线程的方式
* 二、启动线程的确证方式
* 三、停止线程的正确方式
* 四、线程的生命周期
* 五、Thread 与 Object 类中与线程相关的重要方法
* 六、线程的属性
* 七、线程的异常
* 八、多线程性能问题，线程引入的开销、上下文切换

#### 一、实现多线程的方式

#### 1. 实现 `Runnable` 接口，重写 run() 函数，运营 start() 方法

#### 2. 继承 `Thread` 类，重写 run() 函数，运营 start() 方法

#### 3. 总结：

    * 准确的讲，创建线程只有一种方式：构造`Thread`类；
    * 实现线程的执行单元有两种方式
    * 方法一： 实现`Runnable`接口的`run`方法，并把`Runnable`实例传给`Thread`
    * 方法二：重写`Thread`的`run`方法（继承`Thread`类）
    * 1、从代码架构角度：任务只关注业务部分，线程属性等其他内容不需要关注，实现解耦
    * 2、新建、销毁线程消耗资源，创建一个线程对象反复利用
    * 3、Java不支持双继承，不利于扩展性

#### 二、启动线程正确方式

#### 1. `start()`与`run()`的比较

    ```
    Runnable runnable = ()->{
        System.out.println(Thread.currentThread().getName());
    };
    runnable.run();
    new Thread(runnable).start();

    输出结果：
    main
    Thread-0
    ```

#### 2. `start()`方法原理解读

1. 方法含义：

    ```
    a.启动新线程：请求`JVM`虚拟机，空闲时，启动新线程
    b.由父/主线程（main）, 执行子线程 start() 方法，创建新线程
    c.不能重复执行 start() 方法
    ```

2. 源码解析

    ```
    @Override
    public void run() {
    if (target != null) {
    target.run();
    }
    }
    
    执行start()方法先校验线程状态   
    if (threadStatus != 0)
    throw new IllegalThreadStateException();
    ```

#### 3. `run()`方法原理解读

* start()才是将线程丢给虚拟机执行，run 仅当做普通方法执行

#### 三、停止线程的正确方式

#### 1. 讲解原

    * 使用`interrupt`来通知，而不是强制
    * 使用A线程通知B线程中止线程，应当由目标线程实现响应通知，被停止线程更清楚如何停止，销毁

#### 2. 最佳实践：如何正确停止线程

    * 正确的停止方法：`interrupt`
    * RightWayStopThreadWithoutSleep
    





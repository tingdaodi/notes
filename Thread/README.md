## 目录

[TOC]

* 一、实现多线程的方式
* 二、启动线程的确证方式
* 三、停止线程的正确方式
* 四、线程的生命周期
* 五、Thread 与 Object 类中与线程相关的重要方法
* 六、线程的属性
* 七、线程的异常
* 八、多线程性能问题，线程引入的开销、上下文切换

### 一、实现多线程的方式

1. 实现 `Runnable` 接口，重写 `run()` 方法
2. 继承 `Thread`，重写 `run()` 方法
3. 总结
   - 实际上创建线程的方式只有一种：通过 `Thread()` 构造器
   - 实现 `Runnable` 接口，仅仅是一个普通类方法，最终还是得构造 `Thread` 对象，通过 `start()` 启动线程
   - 推荐方式：实现 `Runnable` 接口的方式
     1. 只关注业务逻辑部分代码，不需要关注线程的生命周期
     2. 线程的创建、销毁过程会消耗资源，因此最佳实践，创建一个线程反复利用
     3. 从代码实践，`Java` 不支持双继承，不利于代码拓展性

### 二、启动线程正确方式

>通过 `start()` 将线程丢给虚拟机执行

1. `start()` 和 `run()` 的比较

    ```[Java]
    Runnable runnable = () -> {
        System.out.println("Thread.currentThread().getName()");
    };
    // 执行
    runnable.run();
    // 启动
    new Thread(runnable).start();
    //  输出结果
    main
    Thread-0
    ```

    **总结：** `run()` 只是一个普通的方法，`start()` 才是启动线程的正确方式 

3. `start()` 原理解读

   * **方法涵义：** 
     - 由主线程（`main`）执行子线程 `start()` 方法创建新线程
     - 启动新线程，请求 `JVM` 虚拟机，虚拟机会在空闲时，执行新线程任务
     - 不能重复执行 `start()` 方法，线程运行后，状态已经改变，再次执行提示异常
   * **源码解析：**
     ```[Java]
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

      




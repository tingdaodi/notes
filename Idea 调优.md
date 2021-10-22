[转载] http://blog.oneapm.com/apm-tech/426.html

**【编者按】本文作者在和同事的一次讨论中发现，对 IntelliJ IDEA 内存采用不同的设置方案，会对 IDE 的速度和响应能力产生不同的影响。**

![IntelliJ IDEA 内存优化最佳实践 技术分享 第1张](http://blog.oneapm.com/content/images/2016/01/14531095971045.jpg)

_Don’t be a Scrooge and give your IDE some more memory_

_不要做守财奴，给IDE多留点内存吧。_

昨天，大家就是否自定义 [IntelliJ IDEA](http://news.oneapm.com/tag/intellij-idea/) 的内存设置进行了讨论，有些人选择默认设置，有些人会对默认的设置进行简单的变更，还有一些开发者会基于他们的需求进行全面复杂的设置。笔者目前的工作是处理几个微服务项目和一个老项目，而客户的核心业务需求非常大。对 IntelliJ IDEA 内存进行简单设置以后，笔者明显感受到了该 IDE 在速度和响应方面的改善。但当时笔者并未进行具体的测量，所以这只是主观感受而已。

不过，参与讨论的一位开发者给笔者发了一份他的设置，虽然是针对同个项目，该设置却极其复杂。笔者对自己的设置并无不满，但非常好奇，这些完全不同的设置对比 JetBrains 提供的默认设置，会有怎样的不同。

## 目标

笔者的计划是，在一个接近日常开发项目的场景下（加载一个大项目、加载2、3个微服务、git pull 后刷新大项目），测试各个设置带来的效果，并选出内存消耗和速度都达到最优时的最佳设置。

### 测试机器和项目

-   笔记本电脑：MacBook Pro Retina, 2.3GHz Intel Core i7, 16GB 1600Mhz DDR3,SSD Disc, OS X Yosemite

**项目**

-   大项目—— Monolith ，70万行代码（ [Java](http://blog.oneapm.com/tags-Java.html "Java") 8 和 Groovy ），303个Gradle模块
    
-   两个微服务——约有10000——20000行代码（ Java 8 和 Groovy ）的小项目，各有一个Gradle模块
    

### 测试场景

1.  在 Idea 中关闭所有项目
2.  基于测试文件 idea.vmoptions 进行设置
3.  重启电脑
4.  启动后关闭所有不相关的项目（ communicators 等等）
5.  打开 Idea（测试时间）
6.  打开大项目（测试时间）
7.  检查 jstat -gcutil
8.  打开两个微服务项目（测试时间）
9.  检查 jstat -gcutil
10.  返回大项目然后点击“刷新 Gradle 项目”按钮（测试时间）
11.  检查 jstat -gcutil

### jstat -gcutil

jstat 是 JDK 自带的工具，主要利用 JVM 内建的指令对 Java 应用程序的资源和性能进行实时的命令行监控，还包括对 Heap size 和垃圾回收状况的监控。它有许多选项来收集各种数据（[完整的文档](https://docs.oracle.com/javase/8/docs/technotes/tools/unix/jstat.html)），但这里只会用到： -gcutil :

```
-gcutil - Summary of garbage collection statistics.
S0: Survivor space 0 utilization as a percentage of the space's current capacity.  
S1: Survivor space 1 utilization as a percentage of the space's current capacity.  
E: Eden space utilization as a percentage of the space's current capacity.  
O: Old space utilization as a percentage of the space's current capacity.  
M: Metaspace utilization as a percentage of the space's current capacity.  
CCS: Compressed class space utilization as a percentage.  
YGC: Number of young generation GC events.  
YGCT: Young generation garbage collection time.  
FGC: Number of full GC events.  
FGCT: Full garbage collection time.  
GCT: Total garbage collection time.  
```

这个命令的输出结果如下：

```
S0     S1    E     O     M    CCS  YGC YGCT FGC  FGCT   GCT  
89.70 0.00 81.26 74.27 95.68 91.76 40 2.444 14  0.715  3.159  
```

在本文中，最重要的参数是 GC 事件（ YGC 和 FGC ）次数和收集时间（ YGCT 和 FGCT ）。

### 测试设置

笔者设置了四种不同的设置，为了好记，给它们起了不同的名字。

### 默认（灰色标识）

JetBrains 提供的默认设置：

```
-Xms128m
-Xmx750m
-XX:MaxPermSize=350m
-XX:ReservedCodeCacheSize=240m
-XX:+UseCompressedOops
```

### Big（大）（红色标识）

给 Xmx 配 4096MB， ReservedCodeCacheSize 设置 1024MB，这已经是相当多的内存了：

```
-Xms1024m
-Xmx4096m
-XX:ReservedCodeCacheSize=1024m
-XX:+UseCompressedOops
```

### Balanced(平衡的)（蓝色标识）

Xmx 和 Xms 都分配 2GB ，这是相当平衡的内存消耗：

```
-Xms2g
-Xmx2g
-XX:ReservedCodeCacheSize=1024m
-XX:+UseCompressedOops
```

### Sophisticated（复杂的）（橘色标识）

和上面一样， Xmx 和 Xms 都分配2GB，但是给 GC 和内存管理指定不同的垃圾回收器和许多不同的标志：

```
-server
-Xms2g
-Xmx2g
-XX:NewRatio=3
-Xss16m
-XX:+UseConcMarkSweepGC
-XX:+CMSParallelRemarkEnabled
-XX:ConcGCThreads=4
-XX:ReservedCodeCacheSize=240m
-XX:+AlwaysPreTouch
-XX:+TieredCompilation
-XX:+UseCompressedOops
-XX:SoftRefLRUPolicyMSPerMB=50
-Dsun.io.useCanonCaches=false
-Djava.net.preferIPv4Stack=true
-Djsse.enableSNIExtension=false
-ea
```

以上便是笔者的测试设置，为了执行该测试用例，还需要在~/Library/Preferences/IntelliJIdea15/下创建一个idea.vmoptions文件（这是 Mac OS 系统下的路径设置，[查看这篇文章](https://www.jetbrains.com/idea/help/increasing-memory-heap.html)，基于你的操作系统进行设置）

现在，执行测试用例并比较结果。

## 结果

### Idea启动时间

![IntelliJ IDEA 内存优化最佳实践 技术分享 第2张](http://blog.oneapm.com/content/images/2016/01/14531154418243.jpg)

正如上图所示，启动时间并不依赖于内存设置。 Idea 在所有场景下的测试时间都是10秒，无论内存分配有多少。这并不足为奇，因为在此早期阶段，这些设置并不会影响到应用的行为。

### 加载大项目花费的时间

现在加载 Monolith 项目及其70万行代码。 终于，出现了一些的差异。默认设置所花费的时间几乎是其它的3倍。很明显，如此庞大的代码库需要更多的内存。如果我们执行：

```
jstat -gcutil <IDEA_PID>  
```

会发现，对比其它设置， GC 在默认设置下会变得异常忙碌。

![IntelliJ IDEA 内存优化最佳实践 技术分享 第3张](http://blog.oneapm.com/content/images/2016/01/14531178557395.jpg)

![IntelliJ IDEA 内存优化最佳实践 技术分享 第4张](http://blog.oneapm.com/content/images/2016/01/14531178674463.jpg)

不仅 GC 释放内存的总时间非常高（几乎达到了50倍），而且 Full GC 的平均执行时间也非常非常长。大量的时间都花在了 Full GC 上面，这是 IDE 响应速度低的主要原因。

### 在IDEA中打开两个微服务

现在加载这两个微服务项目，在 IDEA 中打开并且对比他们所消耗的时间。

![IntelliJ IDEA 内存优化最佳实践 技术分享 第5张](http://blog.oneapm.com/content/images/2016/01/14531180218276.jpg)

在这个测试用例下，差异还是非常明显的，复杂设置表现最佳，而默认设置仍旧输给了其他两种设置。

### 再次使用jstat –gcutil

加载完两个微服务项目后，来检查一下同时打开3个项目的情况下， GC 的表现情况。经测试发现，3个不同的自定义设置表现几乎差不多，而默认设置简直弱爆了。

![IntelliJ IDEA 内存优化最佳实践 技术分享 第6张](http://blog.oneapm.com/content/images/2016/01/14531182135338.jpg)

![IntelliJ IDEA 内存优化最佳实践 技术分享 第7张](http://blog.oneapm.com/content/images/2016/01/14531182745394.jpg)

## 最后的角逐：重新加载Monolith

现在，笔者需要从仓库中获得 Monolith 项目的最新版本，并且刷新 Gradle 模块，这样， IDEA 能看到所有的新类。

![IntelliJ IDEA 内存优化最佳实践 技术分享 第8张](http://blog.oneapm.com/content/images/2016/01/14531183224719.jpg)

重要提示：代表默认设置的灰色条形柱非常高，因为 IDEA 在刷新过程中崩溃了，笔者无法测量实际时间。显然，默认分配的内存不足以执行该操作。

但从三个自定义例子中可以发现，大内存配置花费的时间是最短的。所以，内存分配还是起到了作用。

### 最后一次使用jstat-gcutil

因为 IDEA 在默认设置下无法刷新项目，所以，这次测试默认设置就不包括在里面。

![IntelliJ IDEA 内存优化最佳实践 技术分享 第9张](http://blog.oneapm.com/content/images/2016/01/14531184516458.jpg)

![IntelliJ IDEA 内存优化最佳实践 技术分享 第10张](http://blog.oneapm.com/content/images/2016/01/14531184650258.jpg)

从上图可以看出，三者之间的差异不大，但是 Big 配置下的 Full GC 执行时间最快。此外， Xmx 内存大些对响应能力提升的帮助非常明显。

## 总结

在这次简短的实验中，大家可以发现，即使对 IntelliJ IDEA 内存进行微调，都可以大大提升 IDE 性能。当然，内存分配越多，执行效果就越好。但是，你也会发现， IDE 之外许多其他应用程序也需要消耗内存，所以，大家的目标应该是在提高性能和内存消耗之间找到一个平衡。笔者认为，在大多数情况下，把 Xmx 值设置在 2G 和 3G 之间是最佳的。如果你有更多的时间可以用 jstat 和 jvisualm 检查用不同的 JVM 设置如何影响性能和内存占用。


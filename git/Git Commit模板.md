
一、Git Commit message 规范
-----------------------

常用的 Git Commit message 规范采用的是 Angular 规范。

本文参考阮老师的文章介绍：

http://www.ruanyifeng.com/blog/2016/01/commit_message_change_log.html.

Angular 规范中定义的格式有 3 个内容：

> Header  
> Body  
> Footer

### 1、 Header

Header 部分有 3 个字段： type(必需), scope(可选), subject(必需)

> type(必需) ： Type of change：commit 的类别；
>
> scope(可选)：Scope of this change：此次 commit 的影响模块；
>
> subject(必需)：Short description：简短的描述此次代码变更的主要内容

（1）type

type 用于说明 commit 的类别，常用的标识如下：

*   **feat：新功能（feature）**
*   **fix：修补 bug**
*   docs：文档（documentation）
*   style： 格式（不影响代码运行的变动, 空格, 格式化, 等等）
*   refactor：重构（即不是新增功能，也不是修改 bug 的代码变动
*   perf: 性能 (提高代码性能的改变)
*   test：增加测试或者修改测试
*   build: 影响构建系统或外部依赖项的更改 (maven,gradle,npm 等等)
*   ci: 对 CI 配置文件和脚本的更改
*   chore：对非 src 和 test 目录的修改
*   revert: Revert a commit  
    最常用的就是 feat 合 fix 两种 type；

（2）scope

scope 用于说明 commit 影响的范围，比如数据层、控制层、视图层等等，视项目不同而不同。

（3）subject

subject 是 commit 目的的简短描述，不超过 50 个字符，主要介绍此次代码变更的主要内容。

举个例子：  
eg: feat(订单模块)：订单详情接口增加订单号字段

其中， feat 对应 type 字段；订单模块对应 scope(若果 scope 有内容，括号就存在)；“订单详情接口增加订单号字段” 对应 subject，简要说明此次代码变更的主要内容。

### 2、Body

Body 部分是对本次 commit 的详细描述，可以分成多行。

如：

（1）增加订单号字段；

（2）增加了订单退款接口；

日常项目开发中，如果 Header 中 subject 已经描述清楚此次代码变更的内容后，Body 部分就可以为空。

### 3、Footer

（1）不兼容变动

（2）关闭 Issue

日常项目中开发，Footer 不常用，可为空。

### 4、Revert

若需要撤销上一次的 commit，header 部分为：revert: 上一次 commit 的 header 内容；

body 部分为：This reverts commit xxx，xxx 是上一次 commit 对应的 SHA 标识符。

二、[IDEA 插件](https://so.csdn.net/so/search?q=IDEA%E6%8F%92%E4%BB%B6&spm=1001.2101.3001.7020) Git Commit Template 使用
------------------------------------------------------------------------------------------------------------------

在 iTerm 中使用命令行的方式提交代码，我们可以使用 Commitizen 来撰写合格的 Commit message，具体使用见：http://www.ruanyifeng.com/blog/2016/01/commit_message_change_log.html。

本文主要介绍如何使用 IDEA 中的插件 Git Commit Template 来撰写 Commit message。

首先安装和下载插件 Git Commit Template：下载地址

安装后重启 IDEA，更改代码，点击 IDEA 中的 VCS 版本控制按钮：  
![](https://img-blog.csdnimg.cn/20200106135050866.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM1ODU0MjEy,size_16,color_FFFFFF,t_70)

点击 Commit 按钮：  
![](https://img-blog.csdnimg.cn/20200106135101781.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM1ODU0MjEy,size_16,color_FFFFFF,t_70)

选择 Type，填写相应内容，最后点击提交即可：

![](https://img-blog.csdnimg.cn/20200106135102831.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM1ODU0MjEy,size_16,color_FFFFFF,t_70)

参考内容

http://www.ruanyifeng.com/blog/2016/01/commit_message_change_log.html
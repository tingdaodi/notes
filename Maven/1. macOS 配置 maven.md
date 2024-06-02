> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [blog.csdn.net](https://blog.csdn.net/weixin_45626288/article/details/129729121)

#### Mac [安装 Maven](https://so.csdn.net/so/search?q=%E5%AE%89%E8%A3%85Maven&spm=1001.2101.3001.7020) 的几种方法和操作步骤

*   [方法一：通过 Homebrew 安装 Maven](#HomebrewMaven_5)
*   [方法二：通过官方网站下载安装包安装 Maven](#Maven_22)
*   [方法三：通过 SDKMAN 安装 Maven](#SDKMANMaven_51)

Maven 是一种常用的 Java [构建工具](https://so.csdn.net/so/search?q=%E6%9E%84%E5%BB%BA%E5%B7%A5%E5%85%B7&spm=1001.2101.3001.7020)，它可以自动化构建、测试和打包 Java 项目。在苹果 Mac 电脑上安装 Maven 有多种方法，下面我们就来介绍几种常见的方法和详细的操作步骤。  
![](https://img-blog.csdnimg.cn/fd3fe3b7466c4152a48231cca5ab0482.png)

方法一：通过 Homebrew 安装 Maven
------------------------

Homebrew 是 Mac 上的一种包管理器，可以方便地安装各种软件包。通过 Homebrew 安装 Maven 非常简单，只需要打开终端，依次执行以下命令：

1.  安装 Homebrew：

```
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"

```

2.  安装 Maven：

```
brew install maven

```

3.  验证安装结果：

```
mvn -version

```

如果安装成功，会输出 Maven 的版本信息。

方法二：通过官方网站下载安装包安装 Maven
-----------------------

另外一种方法是直接从 Maven 官方网站下载安装包进行安装，操作步骤如下：

1.  打开 [Maven 官方网站](https://maven.apache.org/download.cgi)，选择最新版本的 Maven，下载对应的安装包。
    
2.  解压缩安装包，在 Finder 中进入解压后的文件夹，将文件夹内的 apache-maven-x.x.x（x.x.x 为版本号）拖动到 / usr/local 目录下；这个目录根据自己喜好来，但是必须和环境配置里的一致。
    
3.  设置环境变量，在终端中输入以下命令：
    

```
sudo nano /etc/profile

```

在文件末尾添加以下内容：

```
export M2_HOME=/usr/local/apache-maven-x.x.x
export PATH=$PATH:$M2_HOME/bin

```

保存并退出编辑器，然后重新加载配置：

```
source /etc/profile

```

4.  验证安装结果：

```
mvn -version

```

如果安装成功，会输出 Maven 的版本信息。

方法三：通过 SDKMAN 安装 Maven
----------------------

SDKMAN 是一个针对 Java 开发人员的命令行工具，可以方便地安装、管理各种 Java 相关工具。通过 SDKMAN 安装 Maven 也非常简单，只需要打开终端，依次执行以下命令：

1.  安装 SDKMAN：

```
curl -s "https://get.sdkman.io" | bash

```

2.  安装 Maven：

```
sdk install maven

```

3.  验证安装结果：

```
mvn -version

```

如果安装成功，会输出 Maven 的版本信息。

通过以上三种方法中的任何一种都可以在苹果 Mac 电脑上安装 Maven，选取其中的一种方法按照操作步骤即可完成安装。

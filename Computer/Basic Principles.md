







### 第三课、布尔逻辑与逻辑门

#### 1、Binary（二进制）：0和1，开和关

>  **电路闭合，电流流过，代表“真”，电路断开，无电流流过，代表“假”**

#### 2、布尔代数与布尔代数在计算机中运用

* 常量：`true`、`false`

* 运算符：`NOT`、`AND`、`OR`；`！`、`&&`、`||`（非、与、或）

* 所谓`门（gate）`: 控制电流流过的途径？晶体管只是点控制的开关，有三个引脚：2个电极，1个控制线

  > **控制线通电，电流从一个电极流到另一个电极**

* 当控制线，当作输入（input），底部的电极，当作输出（output）

  假如开打输入（input on），输出也会打开（output on），因为电流可以流过，输出结果（on）；

  反之，无电流通过，为`off`；正常情况，输入，输出相同。

**1）`NOT`运算符**

1. 命名：非门

2. 作用：反转布尔值，`!`，输入`true`或`false`，输出`false`或`true`

3. 晶体管的实现方式：INPUT，OUTPUT，GROUND

   a. INPUT->`ON`: `PN 结`电流可以流过，然后“接地”，OUTPUT->`OFF`

      从而实现：输入`ON`，输出`OFF`

   ![image-20211021180140390](P:\Users\Ives\Documents\github\notes\Computer\image-20211021180140390.png)

   

   b. INPUT->`OFF`: `PN 结`电流不可以流过，无法接地，只能从`OUTPUT`流过；OUTPUT->`OFF`
      从而实现: 输入`OFF`，输出`ON`
   ​    ![image-20211021180651367](P:\Users\Ives\Documents\github\notes\Computer\image-20211021180651367.png)

**2）`AND`运算符**

1. 命名：与门
2. 作用：两个输入控制一个输出，两个都为`true`，才输出`true`，否则为`false`

| INPUT A | INPUT B | OUTPUT |
| ------- | ------- | ------ |
| TRUE    | TRUE    | TRUE   |
| TRUE    | FALSE   | FALSE  |
| FALSE   | TRUE    | FALSE  |
| FALSE   | FALSE   | FALSE  |

3. 晶体管的实现方式：需要2个晶体管`串联`在一起，这样就有了 2 个输入，和 1 个输出；

   INPUT A->`ON`和 INPUT B->`ON`，才会有电流经过，OUTPUT->`ON`

![image-20211021181748193](P:\Users\Ives\Documents\github\notes\Computer\image-20211021181748193.png)

**3）`OR`运算符**

1. 命名：或门

2. 作用：由 2 个输入控制 1 个输出，只要其中 1 个输入为`true`，就输出`true`

   | INPUT A | INPUT B | OUTPUT |
   | ------- | ------- | ------ |
   | TRUE    | TRUE    | TRUE   |
   | TRUE    | FALSE   | TRUE   |
   | FALSE   | TRUE    | TRUE   |
   | FALSE   | FALSE   | FALSE  |

3. 晶体管实现方式：需要将两个晶体管`并联`在一起，由 2 个输入控制 1 个输出；

   a. 只要有 1 个 INPUT->`ON`，则 OUTPUT->`ON`，电流可以通过

   b. 2个输入都为`OFF`，则 OUTPUT->`OFF`，电流无法通过

![image-20211021183107377](P:\Users\Ives\Documents\github\notes\Computer\image-20211021183107377.png)

**4）`XOR`异或运算符** 

1. 命名：异或门

2. 作用：控制两个输出，输入同名，则输出`FALSE`，反之输出`TRUE`

   a. INPUT A ->ON,INPUT B->ON => OUTPUT->OFF

   b. INPUT A ->OFF,INPUT B->OFF => OUTPUT->OFF

   c. other => ON
   
   | INPUT A | INPUT B | OUTPUT |
   | ------- | ------- | ------ |
   | TRUE    | TRUE    | FALSE  |
   | TRUE    | FALSE   | TRUE   |
   | FALSE   | TRUE    | TRUE   |
   | FALSE   | FALSE   | FALSE  |
   
3. 晶体管实现方式：1个 `OR`, 2个`AND`, 1个`NOT`构成

![image-20211025094224551](P:\Users\Ives\Documents\github\notes\Computer\image-20211025094224551.png)

#### 3、逻辑门的符号

1. 作用：封装晶体管，使用符号简化标识

2. 符号：

   - 非门（`NOT`）：三角形 + 圆圈

   - 与门（`AND`）：圆形子弹头

   - 或门（`OR`）：似未喷火的火箭简笔

   - 异或门（`XOR`）：喷火的火箭简笔

    ![image-20211025101423606](P:\Users\Ives\Documents\github\notes\Computer\image-20211025101423606.png)

### 第四课、二进制-Representing Numbers and Letters with Binary

#### 1、二进制的运算，以及原理

**1）十进制的原理：**每一位表示：10<sup>n</sup>，n >= 0; <kbd>100\`s</kbd><kbd>10\`s</kbd><kbd>1\`s </kbd>

`263`：2个100，6个10，3个1：2\*100 + 6\*10 + 3\*1 = 263

> ![image-20211025103438635](P:\Users\Ives\Documents\github\notes\Computer\image-20211025103438635.png) 

**2）二进制的原理：**每一位表示：2<sup>n</sup>，n -> 0; <kbd>4\`s</kbd><kbd>2\`s</kbd><kbd>1\`s </kbd>

> **意味着每个乘数必须是右侧乘数的两倍**

`101`：1\*4 + 2\*0 + 1\*3 = 5

> ![image-20211025104947308](P:\Users\Ives\Documents\github\notes\Computer\image-20211025104947308.png) 

**3）二进制与十进制转换**

`10110111`：每一位数乘以对应的进制数，再相加得到十进制数

![image-20211025105145819](P:\Users\Ives\Documents\github\notes\Computer\image-20211025105145819.png) 

**4）二进制加减运算**

`183 + 19`转换成二进制->`10110111 + 00010011`

![image-20211025105646909](P:\Users\Ives\Documents\github\notes\Computer\image-20211025105646909.png)  

#### 2、二进制的字节定义-Binary digit

**1）定义：**二进制中，一个`1`或`0`叫一位（`bit`）

以`8`位为例：最小`0`，最大`255`；能表示`256`个不同的值，也就是 2<sup>8</sup>

![image-20211025110134843](P:\Users\Ives\Documents\github\notes\Computer\image-20211025110134843.png) 

![image-20211025110343964](P:\Users\Ives\Documents\github\notes\Computer\image-20211025110343964.png) 

**2）字节单位换算：`byte 字节` `kilobytes 千字节` `megabytes 百万字节` `gigabytes 十亿字节` `terabytes 万亿字节`**

> **每 8 位（bit），表示一个字节（byte）**
>
> **1 byte = 8 bits**
>
> ==十进制换算==
>
> **1 kb = 1000 bytes = 8000 bits**
>
> ==二进制换算==
>
> **1 kb = 2<sup>10</sup> = 1024 bytes**
>
> ==1000 转换成 2 进制，不方便计算，因此才使用与 1000 近似的值：2<sup>10</sup>=1024==

#### 3、计算机操作系统位数：`32位`与`64位`

> 32位：最大能表示的数字：43亿左右, 2<sup>31</sup>-1
>
> 64位：最大能表示的数字：9.2 \* 10<sup>18</sup>

> **大部分计算机使用第一位表示`正负数`->符号位**

**1) 浮点数**

> 最常见的是 IEEE 754 标准:
>
> 625.9 = 0.6259 \* 10<sup>3</sup>
>
> 0.6259 <- 有效位数
>
> 3 <- 指数

![image-20211025134746690](P:\Users\Ives\Documents\github\notes\Computer\image-20211025134746690.png) 

> **二进制存储方式**
>
> 第一位：表示符号位
>
> 第二~九位：表示指数位
>
> 后面23位：表示有效位

![image-20211025135108449](P:\Users\Ives\Documents\github\notes\Computer\image-20211025135108449.png) 

**2）文字表示**

* ASCII: 美国信息交换标准代码, 发明与 1963 年；7位代码，足够存 128 个不同值
* Unicode：各国统一所有编码的标准；16 位，超过

### 第五课、算术逻辑单元-How Computers Calculate-the ALU

**一. 定义：计算机中负责运算的组件，它能执行算术（Arithemetic）和逻辑（Logic）运算**

> **真正的目标是计算，有意义的处理数字**
>
> 计算机的运算由==算数逻辑单元（ALU）==处理完成；ALU是计算的数学大脑

![image-20211025141832387](P:\Users\Ives\Documents\github\notes\Computer\image-20211025141832387.png) 

**二. 结构：1 个算术单元和 1 个逻辑单元**

**1、算术单元：负责计算机里的所有数字操作；`1=TRUE` `0=FALSE`**

> 二进制两位数计算：
>
> a. 0+0=0,1+0=1,0+1=1；与异或逻辑门结果一致；

![image-20211025143435937](P:\Users\Ives\Documents\github\notes\Computer\image-20211025143435937.png) 

>  b. 1+1=2，输出 0，进一位；需要并联一个`AND`逻辑门表示进一位；

<img src="P:\Users\Ives\Documents\github\notes\Computer\image-20211025144454986.png" alt="image-20211025144454986" style="zoom:80%;" /> 

**1）以上进制处理方式，封装为半加器**

1. 作用：计算加法
2. 实现方式：一个`XOR`与一个`AND`并联组成；

> 两个输入 A 和 B 都是 1 位；两个输出 “总和” 与 “进位”
>
> SUM-总和
>
> CARRY-进位

<img src="P:\Users\Ives\Documents\github\notes\Computer\image-20211025144726988.png" alt="image-20211025144726988" style="zoom:50%;" /> ==封装后====》 <img src="P:\Users\Ives\Documents\github\notes\Computer\image-20211025145110038.png" alt="image-20211025145110038" style="zoom:50%;" /> 

**2）全加速器**

1. 作用：半加速器处理`1+1`，输出了进位，以此计算之后的每一列，都需要3个位在一起运算
   ![image-20211025150930642](P:\Users\Ives\Documents\github\notes\Computer\image-20211025150930642.png)
2. 实现方式：由两个半加器与一个`OR`组成

  > 1. A,B -> 半加器 -> SUM 1,CARRY 1
  > 2. C, SUM1 -> 半加器 -> SUM 2,CARRY 2
  > 3. CARRY 1,CARRY 2 ->`OR`-> CARRY 3

![image-20211025151615640](P:\Users\Ives\Documents\github\notes\Computer\image-20211025151615640.png) 

3. 封装：将“全加器”作为一个独立的组件; `FULL ADDER`

   作用：把 A、B、C 三个输入加起来，输出 “总和” 和 “进位”

   ![image-20211025151949030](P:\Users\Ives\Documents\github\notes\Computer\image-20211025151949030.png) 

4. **运算：制作 8 位加法器**

   a. 构成：1 个`半加器`与 7 个`全加器`

   b. 实现方式：第一位数通过`半加器`处理后，输出的进位与第二位数通过`全加器`运算，以此类推

   ![image-20211025152215546](P:\Users\Ives\Documents\github\notes\Computer\image-20211025152215546.png) 

   c. 最后一个`全加器`有 “进位” 的输出，表示第 9 位有进位，代表 2 个数字的和太大了，超过了 8 位；这叫 “溢出”（overflow）；

   d. 避免 “溢出”，需要更多的`全加器`，可以操作 `16`和`32`位数字，让溢出更难发生，代价是更多逻辑门，以及每次进位都要一点时间；

**3）超前进位加法器 - Carry-look-ahead adder**

   a. 定义：现代计算机使用的加法器。更快的处理速度;

**4) ALU的算术单元，其它数学运算**

> **一般 ALU 没有专门的乘法和除法电路 **
>
> <em>12 * 5</em> -> 12 个5相加，需要 5 次`ALU`操作来实现乘法

![image-20211025172629634](P:\Users\Ives\Documents\github\notes\Computer\image-20211025172629634.png) 

**2. 逻辑单元**：逻辑单元执行逻辑操作

1）`ALU`逻辑单元使用 “V” 特殊符号表示:

   >**（8 BITS）**
   >
   >操作符：为 4 位，1000 = ADD，1100 = SUBTRACT
   >
   >标识：FLAG（1 BIT）：OVERFLOW, ZERO, NEGATIVE
   >
   >运算：INPUT A,B -==OPERATION CODE==-> OUTPUT（8 BITS）
   >
   >a. 两个数相减为 0 ，ZERO->TRUE：表示两个数相等
   >
   >b. 对比两个数的大小：A - B，NEGATIVE->TRUE
   >
   >c. 两个数相加溢出，OVERFLOW->TRUE

![image-20211025175050632](P:\Users\Ives\Documents\github\notes\Computer\image-20211025175050632.png)

![image-20211025180024886](P:\Users\Ives\Documents\github\notes\Computer\image-20211025180024886.png) 

### 第六章、寄存器 & 内存 - Registers and RAM

#### 1、课程概要：ALU 计算完成后，存储计算结果，以便后面多个连续的操作，这就需要内存。

- `RAM`：玩游戏时，如果断电进度会丢失；是因为电脑用的是`随机存取存储器`，简称`RAM`
- `Memory`：持久存储，电源关闭时，数据也不会丢失
- `目标`：制作存储 1 位的电路，再封装做出内存模块（与 ALU 组装做出 CPU）

#### 2、锁存器-AND-OR LATCH

- 利用`OR`，`AND`，`NOT`逻辑门，来存储一位数

- `OR`存储`1`：A 输入 1 后，B形成回路，永久输出 1；

  ![image-20211026113034125](P:\Users\Ives\Documents\github\notes\Computer\image-20211026113034125.png) 

- `AND`存储`0`: A 输入 1，一直输出 1；A 断开后，输出变成 0；

  ![image-20211026113342707](P:\Users\Ives\Documents\github\notes\Computer\image-20211026113342707.png)  

​    ![image-20211026113525936](P:\Users\Ives\Documents\github\notes\Computer\image-20211026113525936.png) 

- 使用`OR`与`AND`结合起来，做出有用的存储（memory）-- “AND-OR” 锁存器

![image-20211026113917744](P:\Users\Ives\Documents\github\notes\Computer\image-20211026113917744.png) 

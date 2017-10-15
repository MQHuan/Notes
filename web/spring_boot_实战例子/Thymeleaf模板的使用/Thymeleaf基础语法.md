## Thymeleaf 基础语法
首先要在首行添加html标签
```
<html xmlns:th="http://www.thymeleaf.org">
```

#### 1. 获取变量值
```
<p th:text="'Hello！, ' + ${name} + '!'" >3333</p>
```
**获取变量值用$符号,**对于javaBean的话使用**变量名.属性名**方式获取,这点和EL表达式一样.

另外**$表达式只能写在th标签内部**,不然不会生效,
**上面例子就是使用th:text标签的值替换p标签里面的值,**
至于p里面的原有的值只是为了给前端开发时做展示用的.这样的话很好的做到了前后端分离.


#### 2. 引入URL
Thymeleaf对于URL的处理是通过语法@{…}来处理的
```
<a th:href="@{http://blog.csdn.net/u012706811}">绝对路径</a>
<a th:href="@{/}">相对路径</a>
<a th:href="@{css/bootstrap.min.css}">Content路径,默认访问static下的css文件夹</a>
```
类似的标签有:th:href和th:src

#### 3. 字符串替换
很多时候可能我们只需要对一大段文字中的某一处地方进行替换，可以通过字符串拼接操作完成：
```
<span th:text="'Welcome to our application, ' + ${user.name} + '!'">
```
一种更简洁的方式是：
```
<span th:text="|Welcome to our application, ${user.name}!|">
```
当然这种形式限制比较多，|…|中只能包含变量表达式${…}，不能包含其他常量、条件表达式等。

#### 4.运算符
在表达式中可以使用各类算术运算符，例如+, -, *, /, %
```
th:with="isEven=(${prodStat.count} % 2 == 0)"
```
逻辑运算符>, <, <=,>=，==,!=都可以使用，唯一需要注意的是使用<,>时需要用它的HTML转义符：**“<”用“&lt”表示，“>”用“&gt”表示**
```
    th:if="${prodStat.count} &gt; 1"
    th:text="'Execution mode is ' + ( (${execMode} == 'dev')? 'Development' : 'Production')"
```

#### 5.条件

**If/Unless**
Thymeleaf中使用th:if和th:unless属性进行条件判断，
下面的例子中， &lt;a&gt;标签只有在th:if中条件成立时才显示：
```
<a th:href="@{/login}" th:if=${session.user != null}>Login</a>
```
th:unless于th:if恰好相反，只有表达式中的条件不成立，才会显示其内容。
```
<a th:href="@{/login}" th:unless=${session.user == null}>Login</a>
```

**switch**
Thymeleaf同样支持多路选择Switch结构
默认属性default可以用*表示：
```
<div th:switch="${user.role}">
  <p th:case="'admin'">User is an administrator</p>
  <p th:case="#{roles.manager}">User is a manager</p>
  <p th:case="*">User is some other thing</p>
</div>
```

#### 6.循环
渲染列表数据是一种非常常见的场景，
例如现在有n条记录需要渲染成一个表格，
该数据集合必须是可以遍历的，使用th:each标签：
```
<body>
  <h1>Product list</h1>

  <table>
    <tr>
      <th>NAME</th>
      <th>PRICE</th>
      <th>IN STOCK</th>
    </tr>
    <tr th:each="prod : ${prods}">
      <td th:text="${prod.name}">Onions</td>
      <td th:text="${prod.price}">2.41</td>
      <td th:text="${prod.inStock}? #{true} : #{false}">yes</td>
    </tr>
  </table>

  <p>
    <a href="../home.html" th:href="@{/}">Return to home</a>
  </p>
</body>
```
需要在被循环渲染的元素（这里是）中加入th:each标签，
**其中th:each=”prod : ${prods}”意味着对集合变量prods进行遍历**，
循环变量是prod在循环体中可以通过表达式访问

#### 7. Utilities
为了模板更加易用，Thymeleaf还提供了一系列Utility对象（内置于Context中），可以通过#直接访问：

* \#dates
* \#calendars
* \#numbers
* \#strings
* arrays
* lists
* sets
* maps
… 
下面用一段代码来举例一些常用的方法：
```
/*
 * Format date with the specified pattern
 * Also works with arrays, lists or sets
 */
${#dates.format(date, 'dd/MMM/yyyy HH:mm')}
${#dates.arrayFormat(datesArray, 'dd/MMM/yyyy HH:mm')}
${#dates.listFormat(datesList, 'dd/MMM/yyyy HH:mm')}
${#dates.setFormat(datesSet, 'dd/MMM/yyyy HH:mm')}

/*
 * Create a date (java.util.Date) object for the current date and time
 */
${#dates.createNow()}

/*
 * Create a date (java.util.Date) object for the current date (time set to 00:00)
 */
${#dates.createToday()}
string
/*
 * Check whether a String is empty (or null). Performs a trim() operation before check
 * Also works with arrays, lists or sets
 */
${#strings.isEmpty(name)}
${#strings.arrayIsEmpty(nameArr)}
${#strings.listIsEmpty(nameList)}
${#strings.setIsEmpty(nameSet)}

/*
 * Check whether a String starts or ends with a fragment
 * Also works with arrays, lists or sets
 */
${#strings.startsWith(name,'Don')}                  // also array*, list* and set*
${#strings.endsWith(name,endingFragment)}           // also array*, list* and set*

/*
 * Compute length
 * Also works with arrays, lists or sets
 */
${#strings.length(str)}

/*
 * Null-safe comparison and concatenation
 */
${#strings.equals(str)}
${#strings.equalsIgnoreCase(str)}
${#strings.concat(str)}
${#strings.concatReplaceNulls(str)}

/*
 * Random
 */
${#strings.randomAlphanumeric(count)}
```
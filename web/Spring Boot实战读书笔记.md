### 依赖注入
P18
依赖注入的主要目的是为了解耦，体现一种＂组合＂的概念
如果你希望你的类具备某项功能的时候，如果有两种选择，一是继承这个类，另一个是组合这个类．在这里如果继承的话，子类将与父类耦合．组合的话，与另一个类的耦合大大减低．

### AOP
P24

* 编写使用注解的被拦截类，加了这个注解的类或者方法就会被拦截
* 使用@Aspect声明一个切面，并通过@Component让此切面成为Spring容器管理的Bean
* 使用@After、@Befor、@Aroud定义 建言/通知实例（advice），可直接将拦截规则（切点/PointCut）作为参数。这儿也是业务代码所在的地方．
为了使切点复用，可使用＠PointCut专门定义拦截规则，然后在@After、@Befor、@Aroud的参数中调用
* 在配置类上使用@EnableAspectJAutoProxy开启Spring对AspectJ的支持
* 启动类上添加@AopConfig引入
* 其中符合条件的每一个被拦截处为连接点(JoinPoint)

### Bean的Scope
scope　范围  [skəʊp]

### Spring Aware
aware 觉察到  [əˈweə(r)] 

### Spring MVC的常用注解
dispatcher 调度员 [dɪˈspætʃə(r)] 
variable 变量 [ˈveəriəbl] 
produces　生产 [prəˈdju:s] 
interceptor 拦截器 [ˌɪntəˈseptə(r)]

### Spring  Boot 核心
banner 旗帜 [ˈbænə(r)] 
fluent 流利 [ˈflu:ənt] 

### starter pom
P121
Starter POMs是可以包含到应用中的一个方便的依赖关系描述符集合。你可以获取所有Spring及相关技术的一站式服务，而不需要翻阅示例代码，拷贝粘贴大量的依赖描述符。例如，如果你想使用Spring和JPA进行数据库访问，只需要在你的项目中包含spring-boot-starter-data-jpa依赖，然后你就可以开始了。

该starters包含很多你搭建项目，快速运行所需的依赖，并提供一致的，管理的传递依赖集。

### 基于Bootstrap的web应用－－angular P212　要敲
P216
angular 有角的;用角测量的，用弧度测量的;

### ViewResolver和View介绍
SpringMVC用于处理视图最重要的两个接口是ViewResolver和View。
* ViewResolver的主要作用是把一个逻辑上的视图名称解析为一个真正的视图，SpringMVC中用于把View对象呈现给客户端的是View对象本身，而ViewResolver只是把逻辑视图名称解析为对象的View对象。
* View接口的主要作用是用于处理视图，然后返回给客户端。

angular 有角的;用角测量的，用弧度测量的; [ˈæŋgjələ(r)] 
### Web相关配置
P182
Negotiating 兑现 [nɪ'ɡəʊʃɪeɪtɪŋ]
Converter  转换器  [kənˈvɜ:tə(r)]
Formatter  格式化程序
Encompassing  包括 [enˈkʌmpəs]

### 替换Tomcat
P190
exclusions 排除 [ɪkˈsklu:ʒn] 

### Websocker
P200
domain 范围，领域; [dəˈmeɪn] 
wisely 明智地;聪明地 [waɪzlɪ] 
subscribe 订阅  [səbˈskraɪb] 
permit 许可证
authentication [ɔ:ˌθentɪ'keɪʃn] 认证;身份验证;证明，
destination  [ˌdestɪˈneɪʃn] 目的，目标;目的地
### 基于Bootstrap和AngularJS的现代Web应用
Bootstrap  引导程序  [ˈbu:tstræp]
P222
directive  指令  [dəˈrektɪv] 
produce 生产 [prəˈdju:s] 
directive  指令  [dəˈrektɪv] 
restrict  限制 [rɪˈstrɪkt] 

#### Spring Boot的数据访问 
P235
Repository 仓库; [rɪˈpɒzətri] 
Iterable 可迭代的

### Spring Data JPA
relational 有关的[rɪˈleɪʃənl]
persistence 持续  [pəˈsɪstəns]

criteria [kraɪ'tɪərɪə] 标准
predicate [ˈpredɪkət] 宣布
specs   [speks] 规格

**更改后的oracle密码为boot**

jdbc6.jar的下载地址 ：http://www.oracle.com/technetwork/cn/database/enterprise-edition/jdbc-111060-097826-zhs.html

将ojdbc6.jar装到本地
```
 mvn install:install-file -DgroupId=com.oracle "-DartifactId=ojdbc6" "-Dversion=11.2.0.2.0" "-Dpackaging=jar" "-Dfile=/home/mai/Desktop/Notes/web/ojdbc6.jar"
```
####
P248的例子中，数据库用户名和密码配置如下可以跑起来
```
spring.datasource.username=system
spring.datasource.password=oracle
```
新建的用户没办法跑起来，原因未知，
写这部分的时候要注意，自定义的方法名，变量名一定不能错，
测试的时候就因为下面的少了一个字母，跑不起来
```
// @NamedQuery(name = "Person.withNameAndAddressNameQuery",//这里的Name 少了个d

@NamedQuery(name = "Person.withNameAndAddressNamedQuery",//这里必须和PersonRepository定义的withNameAndAddressNamedQuery方法一致
        query = "select p from Person p where p.name=?1 and address=?2")
```

singular <语>单数的 [ˈsɪŋgjələ(r)] 
attribute 属性  [əˈtrɪbju:t] 
declared 公然的  [dɪˈkleəd]
specification  规范 [ˌspesɪfɪˈkeɪʃn]
conjunction  连接  [kənˈdʒʌŋkʃn] 
repository  仓库;贮藏室   [rɪˈpɒzətri] 


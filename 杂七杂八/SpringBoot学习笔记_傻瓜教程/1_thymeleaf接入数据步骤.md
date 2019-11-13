## Step 1 引入依赖
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```

## Step 2 绑定数据
Controller类
```
@Controller
public class HelloController {
@RequestMapping("/list")
  public String list(Model model) {
    List<User> users=userService.getUserList();
    model.addAttribute("users", users);// 绑定数据到html文件
    return "user/list";
  }
}
```
list.html中接收绑定的数据
```
....
<!--获取绑定的数据-->
<tr  th:each="user : ${users}">
    <th scope="row" th:text="${user.id}">1</th>
    <td th:text="${user.userName}">neo</td>
    <td th:text="${user.password}">Otto</td>
    <td th:text="${user.age}">6</td>
    <td><a th:href="@{/toEdit(id=${user.id})}">edit</a></td>
    <td><a th:href="@{/delete(id=${user.id})}">delete</a></td>
</tr>
...
```

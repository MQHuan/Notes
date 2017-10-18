package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class DemoApplication {

	// 模拟一个查询，即接受前台传入的personName,然后返回Person类，因为使用的是@RestController，且返回值类型是Person,所以SpringMVC会自动将对象输出为JSON
	@RequestMapping(value = "/search", produces = {MediaType.APPLICATION_JSON_VALUE})
	public Person search(String personName) {
		return new Person(personName, 32, "hefei");
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}

package com.example.demo;


import com.example.demo.dao.PersonRepository;
import com.example.demo.support.CustomRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
// 配置@EnableJpaRepositories指定repositoryFactoryBeanClass，让自定义的Repository实现起效
@EnableJpaRepositories(repositoryFactoryBeanClass = CustomRepositoryFactoryBean.class)//如果不需要自定义，就不需添加这个注解，因为@SpringBootApplication包含的@EnableAutoConfiguration注解已经开启了对Spring DataJPA的支持
public class DemoApplication {
	@Autowired
	PersonRepository personRepository;
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}

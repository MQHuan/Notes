package com.example.demo.service.impl;


import com.example.demo.dao.PersonRepository;
import com.example.demo.domain.Person;
import com.example.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class DemoServiceImpl implements DemoService {
	@Autowired
	PersonRepository personRepository; //1 可以直接注入我们的PersonRepository的Bean

	//2 使用@Transactional注解的rollbackFor属性，指定特定异常时，数据回滚
	@Transactional(rollbackFor={IllegalArgumentException.class}) //2
	public Person savePersonWithRollBack(Person person){
		Person p =personRepository.save(person);

		if(person.getName().equals("汪云飞")){
			// 硬编码手动触发异常
			throw new IllegalArgumentException("汪云飞已存在，数据将回滚"); //3
		}
		return p;
	}

	// 使用@Transactional注解的noRollbackFor属性，指定特定异常时，数据回滚
	@Transactional(noRollbackFor={IllegalArgumentException.class}) //4
	public Person savePersonWithoutRollBack(Person person){
		Person p =personRepository.save(person);
		
		if(person.getName().equals("汪云飞")){
			throw new IllegalArgumentException("汪云飞虽已存在，数据将不会回滚");
		}
		return p;
	}
}

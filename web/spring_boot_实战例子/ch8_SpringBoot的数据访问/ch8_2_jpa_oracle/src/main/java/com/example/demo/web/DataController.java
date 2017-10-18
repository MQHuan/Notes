package com.example.demo.web;

import com.example.demo.dao.PersonRepository;
import com.example.demo.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DataController {

    // 1 Spring Data JPA自动注册的bean,可自动注入
    @Autowired
    PersonRepository personRepository;

    /**
     * 保存
     * save支持批量保存：<S extends T> Iterable<S> save(Iterable<S> entities);
     * <p>
     * 删除：
     * 删除支持使用id，对象以，批量删除及删除全部：
     * void delete(ID id);
     * void delete(T entity);
     * void delete(Iterable<? extends T> entities);
     * void deleteAll();
     */
    @RequestMapping("/save")
    public Person save(String name, String address, Integer age) {
        Person p = personRepository.save(new Person(null, name, age, address));
        return p;
    }


    /**
     * 测试 findByAddress
     */
    @RequestMapping("/q1")
    public List<Person> q1(String address) {
        List<Person> people = personRepository.findByAddress(address);
        return people;
    }

    /**
     * 测试 findByNameAndAddress
     */
    @RequestMapping("/q2")
    public Person q2(String name, String address) {
        Person people = personRepository.findByNameAndAddress(name, address);
        return people;
    }

    /**
     * 测试 withNameAndAddressQuery
     */
    @RequestMapping("/q3")
    public Person q3(String name, String address) {
        Person people = personRepository.withNameAndAddressQuery(name, address);
        return people;
    }

    /**
     * 测试 withNameAndAddressQuery
     */
    @RequestMapping("/q4")
    public Person q4(String name, String address) {
        Person people = personRepository.withNameAndAddressNamedQuery(name, address);
        return people;
    }

    /**
     * 排序测试
     */
    @RequestMapping("/sort")
    public List<Person> sort() {
        List<Person> people = personRepository.findAll(new Sort(Sort.Direction.ASC, "age"));
        return people;
    }

    /**
     * 测试分页
     */
    @RequestMapping("/page")
    public Page<Person> page() {
        Page<Person> pagePeople = personRepository.findAll(new PageRequest(1, 2));
        return pagePeople;
    }

    /**
     * 测试自定义模糊查询
     */
    @RequestMapping("/auto")
    public Page<Person> auto(Person person) {
        Page<Person> pagePeople = personRepository.findByAuto(person,
                new PageRequest(0, 10));
        return pagePeople;
    }
}

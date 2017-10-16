package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@SpringBootApplication
public class DemoApplication {

//	@RequestMapping(value = "/")
//	public String index(Model model) {
//		model.addAttribute("name", "Dear");
//		return "/index";
//	}
	@RequestMapping("/")
	public String helloHtml(Map<String,Object> map){

		map.put("hello","from TemplateController.helloHtml");
		return"/index";
	}
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}

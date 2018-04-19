package com.fairyonline.example.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fairyonline.example.entity.Example;
import com.fairyonline.example.service.ExampleServiceImpl;

@Controller
@Repository
@RequestMapping("/example")/*更改成对应的名字；类对应地址*/
public class ExampleControllerImpl {/*驼峰式命名*/
	@Resource
	private ExampleServiceImpl exampleServiceImple;

	@RequestMapping(value="/Add")/*方法对应地址*/
	public String Add() {/*驼峰式命名*/
		System.out.println("get add");
		Example example = new Example("exampleName");
		exampleServiceImple.Add(example);
		return "Example/Example";
	}
	
}

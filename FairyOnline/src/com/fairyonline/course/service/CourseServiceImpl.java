package com.fairyonline.course.service;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import com.fairyonline.course.dao.CourseDaoImpl;
import com.fairyonline.course.entity.Course;

@Service
public class CourseServiceImpl {
	@Resource
	private SessionFactory sessionFactory;
	@Resource
	private CourseDaoImpl cdi;
	
	//�γ̲�ѯ
	public List<Course> getList(){
		List<Course> list = cdi.getList();
		return list;	
	}
	//ģ���γ̲�ѯ
//	public List<Course> getCourseByPartName(String courseName){
//		Course list = cdi.getCourseByPartName(courseName);
//		return list;
//	}

}

package com.fairyonline.xiaoye.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
 
import com.fairyonline.xiaoye.entity.Fileses;

@Controller
@Repository	
@RequestMapping("/File")
public class FileseController {
//	@Resource
//	private Fileses file;
	
	//��ȡ�ļ�����
	@RequestMapping("/redFile")
	public String RedFile() {//��ȡ�ļ�  MultipartFile  file
	        String encoding = "UTF-8";  
	        File file = new File("F:\\Documents\\File\\coqytest.txt");  
	        Long filelength = file.length();  
	        System.out.println("file length : " + file.length());
	        byte[] filecontent = new byte[filelength.intValue()];  
	        try {  
	            FileInputStream in = new FileInputStream(file);  
	            in.read(filecontent);  
	            in.close();  
	        } catch (FileNotFoundException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	        try {  
	             String str = new String(filecontent, encoding);  
	             System.out.println(str);
	        } catch (UnsupportedEncodingException e) {  
	            System.err.println("The OS does not support " + encoding);  
	            e.printStackTrace();  
	            return null;  
	        }  
	       
		return "Xiaoye/Test";
	}
	//���ļ���д������
	@RequestMapping("/writeFile")
	public String WriteFile(@RequestParam String textarea) {
		System.out.println("get ReadFile controller");
		System.out.println("textarea  ��"+textarea);
		//���������� 
		FileOutputStream file = null; 
		try {
			//���������� ��ѡ��׷�ӡ���ʽ   д������
			file = new FileOutputStream("F:\\Documents\\File\\coqytest.txt",true);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		//����ת�����ֽ�
		byte[] b1 = textarea.getBytes();
		byte[] b2 = "\r\n".getBytes(); 
		try {
			//����д��
			file.write(b1);
			file.write(b2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			//�ر���
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Xiaoye/Test";
	}
	//��ȡ�ļ�������
	@RequestMapping("/saveFile")
	public String SaveFile(MultipartFile  file) {
		File newFile = new File("F:\\Documents\\File\\"+ "coqytest.txt");//�����ļ� 
        try {
			file.transferTo(newFile);//��ҳ�洫����ļ������浽�ոմ������ļ�
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// ���ڴ��е�����д�����
		return "Xiaoye/Test";
	}
	

}

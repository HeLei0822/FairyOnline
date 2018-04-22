package com.fairyonline.xiaoye.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="Side")
public class Side {
	/*��Ҫ����*///5
	private int id ;
	//���������ڵ� ˳���Ⱥ� ��û����������
	private int NodeOneId;//�����ӵĵ�һ���ڵ�
	private int NodeTwoId;//�����ӵĵڶ����ڵ�
	//���������ڵ� ˳���Ⱥ���ǰ�������ڵ����Ӧ
	private int OneNextSideId;//��һ���ڵ����һ���ߣ�����Ǵ˽ڵ�����һ���ߣ���Ϊ�ջ�Ϊ0
	private int TwoNextSideId;//�ڶ����ڵ����һ���ߣ�����Ǵ˽ڵ�����һ���ߣ���Ϊ�ջ�Ϊ0
	/*һ������*///6
	private String verNumber;//�汾�� 	version number
	private String NoName;//�ڵ�һname NodeOneName
	private int NoRelDegree;//�ڵ�һ ��ض�Related degree; degree of association
	private String NtName;//�ڵ��name NodeTwoName
	private int NtRelDegree;//�ڵ�� ��ض�Related degree; degree of association
	private String content;//��ϸ����
	
	/*���캯��*/
	//ȫ����
	public Side(int id, int nodeOneId, int nodeTwoId, int oneNextSideId, int twoNextSideId, String verNumber,
			String noName, int noRelDegree ,String ntName, int ntRelDegree, String content) {
		this.id = id;
		NodeOneId = nodeOneId;
		NodeTwoId = nodeTwoId;
		OneNextSideId = oneNextSideId;
		TwoNextSideId = twoNextSideId;
		this.verNumber = verNumber;
		NoName = noName;
		NtName = ntName;
		NoRelDegree = noRelDegree;
		NtRelDegree = ntRelDegree;
		this.content = content;
	}
	//��id ȫ����
	public Side(int nodeOneId, int nodeTwoId, int oneNextSideId, int twoNextSideId, String verNumber,
			String noName, int noRelDegree ,String ntName, int ntRelDegree, String content) {
		NodeOneId = nodeOneId;
		NodeTwoId = nodeTwoId;
		OneNextSideId = oneNextSideId;
		TwoNextSideId = twoNextSideId;
		this.verNumber = verNumber;
		NoName = noName;
		NtName = ntName;
		NoRelDegree = noRelDegree;
		NtRelDegree = ntRelDegree;
		this.content = content;
	}
	//һ�㹹��
	public Side(String verNumber, String noName,int noRelDegree ,String ntName, int ntRelDegree, String content) {
		OneNextSideId=0;
		TwoNextSideId=0;
		this.verNumber = verNumber;
		NoName = noName;
		NtName = ntName;
		NoRelDegree = noRelDegree;
		NtRelDegree = ntRelDegree;
		this.content = content;
	}
	//���Թ���
	public Side(String test) {
		NodeOneId = 1;
		NodeTwoId = 2;
		OneNextSideId = 0;
		TwoNextSideId = 0;
		this.verNumber = "1.0.0";
		NoName = "noName";
		NtName = "ntName";
		NoRelDegree = 5;
		NtRelDegree = 5;
		this.content = "һ��ʮ����ϸ�Ľ��ͣ�������Ȼ Ҳ��֪���ǲ��������ϸ����֮����ϸ�Ͷ��ˡ�some English words!To test";
	}
	//Ĭ�Ϲ���
	public Side() {}
	/*����ķ���*/
	//����node  name ����set nextside
	public void setNextSidId(Node node,Side nexSid) {
		if(this.NoName.equals(node.getName())) 
			this.setOneNextSideId(nexSid.getId());
		else 
			this.setTwoNextSideId(nexSid.getId());
	}
	/*set&get*/
	@Id//����id
	@GeneratedValue(generator="my_gen")
    @GenericGenerator(name="my_gen", strategy="increment")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNodeOneId() {
		return NodeOneId;
	}
	public void setNodeOneId(int nodeOneId) {
		NodeOneId = nodeOneId;
	}
	public int getNodeTwoId() {
		return NodeTwoId;
	}
	public void setNodeTwoId(int nodeTwoId) {
		NodeTwoId = nodeTwoId;
	}
	public int getOneNextSideId() {
		return OneNextSideId;
	}
	public void setOneNextSideId(int oneNextSideId) {
		OneNextSideId = oneNextSideId;
	}
	public int getTwoNextSideId() {
		return TwoNextSideId;
	}
	public void setTwoNextSideId(int twoNextSideId) {
		TwoNextSideId = twoNextSideId;
	}
	public String getVerNumber() {
		return verNumber;
	}
	public void setVerNumber(String verNumber) {
		this.verNumber = verNumber;
	}
	public String getNoName() {
		return NoName;
	}
	public void setNoName(String noName) {
		NoName = noName;
	}
	public String getNtName() {
		return NtName;
	}
	public void setNtName(String ntName) {
		NtName = ntName;
	}
	public int getNoRelDegree() {
		return NoRelDegree;
	}
	public void setNoRelDegree(int noRelDegree) {
		NoRelDegree = noRelDegree;
	}
	public int getNtRelDegree() {
		return NtRelDegree;
	}
	public void setNtRelDegree(int ntRelDegree) {
		NtRelDegree = ntRelDegree;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}

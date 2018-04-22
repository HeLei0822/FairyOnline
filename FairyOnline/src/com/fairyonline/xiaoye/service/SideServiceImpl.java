package com.fairyonline.xiaoye.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.fairyonline.xiaoye.dao.NodeDaoImpl;
import com.fairyonline.xiaoye.dao.SideDaoImpl;
import com.fairyonline.xiaoye.entity.Side;
import com.fairyonline.xiaoye.entity.Node;

@Service
@Repository
public class SideServiceImpl {
	@Resource
	private SessionFactory sessionFactory;
	@Resource
	private SideDaoImpl sideDaoImpl;
	@Resource
	private NodeDaoImpl nodeDaoImpl;
	
	/*������*/
	//ͨ��file ��ӱ�
	public void AddSideByFile(String fileUrl) {
		Session session = sessionFactory.openSession();
		//��ȡ������ļ�����	����һ���ļ����ݶ���String2ά����
		String[][] strArr = this.GetStrArr(fileUrl);
		//����ȡ��������  ת��Ϊ��������
		Side[] sidArr = this.GetSideArr(strArr);
		//�������еĶ�����в�ȫ �� ����
		this.ComSidAndSave(sidArr);
		session.close();
	}
	
	
	/*�ӷ���*/
	//�������еĶ�����в�ȫ �� ����
	public void ComSidAndSave(Side[] sidArr) {//complement  ����
		for(Side sid :sidArr) {
			//��ȡ�����ڵ����
			Node nod1 = nodeDaoImpl.getByName(sid.getNoName());
			Node nod2 = nodeDaoImpl.getByName(sid.getNtName());
			sid.setNodeOneId(nod1.getId());
			sid.setNodeTwoId(nod2.getId());
			//��Ҫ�����Ѿ���ȫ  ���д洢
			sideDaoImpl.save(sid);
			//�ж�����node�Ƿ��Ѿ�������һ���� 
			//����Ѿ�������һ������   �� ��ȡLastSide
			Boolean nod1side=false;
			Boolean nod2side=false;
			if(nod1.getFirstSideId()==0) {
				//node��û�б�  �˱�Ϊnode�ĵ�һ����
				//��Fsid Lsid �����ó� �˱�
				nod1side=false;
				nod1.setFirstSideId(sid.getId());
				nod1.setLastSideId(sid.getId());
				nodeDaoImpl.update(nod1);
			}else {
				//node�Ѿ�������һ���ߵ���
				nod1side = true;
				//��ȡnode���ڱ�ǵ� ListSide
				Side node1LSide = sideDaoImpl.getById(nod1.getLastSideId());
				//���˱ߵĶ�Ӧnode����һ����id  �޸�Ϊ���ڵ�sid
				node1LSide.setNextSidId(nod1, sid);
				//��node�����һ���߸�Ϊ���ڵ�sid
				nod1.setLastSideId(sid.getId());
				nodeDaoImpl.update(nod1);
				sideDaoImpl.update(node1LSide);
			}
			if(nod2.getFirstSideId()==0) {
				//node��û�б�  �˱�Ϊnode�ĵ�һ����
				//��Fsid Lsid �����ó� �˱�
				nod2side=false;
				nod2.setFirstSideId(sid.getId());
				nod2.setLastSideId(sid.getId());
				nodeDaoImpl.update(nod2);
			}else {
				//node�Ѿ�������һ���ߵ���
				nod2side = true;
				//��ȡnode���ڱ�ǵ� ListSide
				Side node2LSide = sideDaoImpl.getById(nod2.getLastSideId());
				//���˱ߵĶ�Ӧnode����һ����id  �޸�Ϊ���ڵ�sid
				node2LSide.setNextSidId(nod2, sid);
				//��node�����һ���߸�Ϊ���ڵ�sid
				nod2.setLastSideId(sid.getId());
				nodeDaoImpl.update(nod2);
				sideDaoImpl.update(node2LSide);
			}			
		}//for(Side sid :sidArr)
	}
	//����ȡ��������  ת��Ϊ��������
	public Side[] GetSideArr(String[][] strArr) {
		Side[] sidArr = new Side[strArr.length];
		for(int i=0;i<(strArr.length-1);i++) {
			sidArr[i] = new Side(
					strArr[i+1][0],//����
					strArr[i+1][1],//�ڵ�һ ����
					Integer.parseInt(strArr[i+1][2]),//��ض�
					strArr[i+1][3],//�ڵ������
					Integer.parseInt(strArr[i+1][4]),//��ض�
					strArr[i+1][5]);//��ϸ����
		}
//		String str="12";
//		int a =Integer.parseInt(str);
		return sidArr;
	}
	//��ȡ������ļ�����	����һ���ļ����ݶ���2ά����
	public String[][] GetStrArr(String fileUrl) {
		String encoding = "UTF-8"; 
		String sign;//sign �ָ���   �������ƺ�׺  ȷ���ָ�������
		if(fileUrl.substring(fileUrl.lastIndexOf(".")).equals(".csv"))
			sign=",";
		else
			sign="\t";
		File file = new File(fileUrl);
		Long filelength = file.length();  //��ȡ�ļ��ܳ���
		byte[] filecontent = new byte[filelength.intValue()];// �����ļ����ȵ��ֽ����� 
		try {  
            FileInputStream in = new FileInputStream(file);  //������һ���µ�ʲô����
            in.read(filecontent);  //��ȡ���� ���ո��´������ֽ�����(�ֽ����� ����ǳ��������: [B@325b51b)
            in.close();  //�رն�ȡ
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }
		String str;
		try {
			str = new String(filecontent, encoding);
			String[] strs = str.split("\r\n");
			String[][] strArr = new String[strs.length][];
			for(int i=0 ;i<strs.length;i++) {
	             strArr[i] = strs[i].split(sign);
            }
			return strArr;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 
	}
	
	
	
	
}





package com.fairyonline.user.dao;

import java.util.List;


import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.fairyonline.user.entity.User;
import com.fairyonline.user.entity.UserLogin;
import com.fairyonline.user.entity.UserLogin1;


@Repository
public class UserDaoImpl {
	@Resource
	private SessionFactory sessionFactory;
	
	public List<User> findAll(){
		Query q = this.sessionFactory.getCurrentSession().createQuery("from User");
		return q.list();
	}
	
	public List<UserLogin> allUserLogin(){
		Query query = this.sessionFactory.getCurrentSession().createQuery("from UserLogin");
		List<UserLogin> list = query.list();
		return list;
	}
	
	public void addUserLogin(UserLogin UserLogin) {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tra = session.beginTransaction();//��������
		session.save(UserLogin);
		System.out.println("save success");
		session.flush();
		tra.commit();
		System.out.println("out Dao");
	}
	
	public boolean addUser(User user) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(user);
        return true;
	}
	
	public UserLogin login(String UserName,String PassWord) {
		Query query = this.sessionFactory.getCurrentSession().createQuery("from UserLogin where UserName=? and PassWord=?");
		query.setParameter(0,UserName);
		query.setParameter(1,PassWord);
		UserLogin userLogin = (UserLogin)query.uniqueResult();
		if(userLogin!=null) {
			return userLogin;
		}else {
			System.out.println("userLogin Dao����");
			return null;
		}
	}
	
	public UserLogin findUserLogin(String UserName) {
		Query query = this.sessionFactory.getCurrentSession().createQuery("from UserLogin where UserName=?");
		query.setParameter(0,UserName);
		UserLogin userLogin = (UserLogin)query.uniqueResult();
		return userLogin;
	}
	
	public User findUserById(int ID) {
		Query query = this.sessionFactory.getCurrentSession().createQuery("from User where ID=?");
		query.setParameter(0,ID);
		User user = (User)query.uniqueResult();
		return user;
	}
	
	public boolean updateUser(User user) {
		Query query = this.sessionFactory.getCurrentSession().createQuery("update User set PetName=?,Img=?,Sex=?,TName=? where ID=?");
		query.setParameter(0,user.getPetName());
		query.setParameter(1,user.getImg());
		query.setParameter(2,user.getSex());
		query.setParameter(3,user.getTName());
		query.setParameter(4,user.getID());
		int i = query.executeUpdate();
		if(i>0) {
			System.out.println("updateUserDaoִ�гɹ�");
			return true;
		}else {
			System.out.println("updateUserDaoִ��ʧ��");
			return false;
		}
	}
	
	public User findUser(String UserName) {
		Query query = this.sessionFactory.getCurrentSession().createQuery("from User where UserName=?");
		query.setParameter(0,UserName);
		User user = (User)query.uniqueResult();
		return user;
	}

	public boolean addupUser(User User) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(User);
		return true;
	}
}

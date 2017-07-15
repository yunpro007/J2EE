package service.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import db.MyHibernateSessionFactory;
import entity.Employees;
import service.EmployeesDAO;

//学生业务逻辑类的实现类
public class EmployeesDAOImpl implements EmployeesDAO{

	@Override
	public List<Employees> queryAllEmployees() {
		// TODO Auto-generated method stub
		Transaction tx = null;
		List<Employees> list = null;
		String hql = "";
		try
		{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			hql = "from Employees";
			Query query = session.createQuery(hql);
			
			list = query.list();
			tx.commit();
			return list;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			tx.commit();
			return list;
		}
		finally
		{
			if(tx!=null)
			{
				tx = null;
			}
		}
	}

	@Override
	public Employees queryEmployeesByEid(String eid) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		Employees e = null;
		try
		{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			e = (Employees)session.get(Employees.class, eid);
			tx.commit();
			return e;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			tx.commit();
			return e;
		}
		finally
		{
			if(tx!=null)
			{
				tx = null;
			}
		}
	}

	@Override
	public boolean addEmployees(Employees e) {
		// TODO Auto-generated method stub
		e.setEid(getNewEid());
		Transaction tx = null;
		try
		{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			session.save(e);
			tx.commit();
			return true;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			tx.commit();
			return false;
		}
		finally
		{
			if(tx!=null)
			{
				tx = null;
			}
		}
	}

	@Override
	public boolean updateEmployees(Employees e) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		try
		{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			session.update(e);
			tx.commit();
			return true;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			tx.commit();
			return false;
		}
		finally
		{
			if(tx!=null)
			{
				tx = null;
			}
		}
	}

	@Override
	public boolean deleteEmployees(String eid) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		//String hql = "";
		try
		{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			Employees e = (Employees)session.get(Employees.class, eid);
			session.delete(e);
			tx.commit();
			return true;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			tx.commit();
			return false;
		}
		finally
		{
			if(tx!=null)
			{
				tx = null;
			}
		}
	}
	
	//生成学生的学号
	private String getNewEid()
	{
		Transaction tx = null;
		String hql = "";
		String eid = null;
		try
		{
			Session session = MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			//获得当前学生的最大编号
			hql = "select max(eid) from Employees";
			Query query = session.createQuery(hql);
			eid = (String)query.uniqueResult();
			if(eid==null||"".equals(eid))
			{
				//给一个默认的最大编号
				eid = "S0000001";
			}
			else
			{
				String temp = eid.substring(1);//取后七位
				int i =Integer.parseInt(temp);//转成数字
				i++;
				//在还原为字符串
				temp = String.valueOf(i);
				int len = temp.length();
				//凑够七位
				for(int j=0;j<7-len;j++)
				{
					temp="0"+temp;
				}
				eid = "S"+temp;
			}
			tx.commit();
			return eid;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			tx.commit();
			return null;
		}
		finally
		{
			if(tx!=null)
			{
				tx = null;
			}
		}
	}
	

}

package service.impl;

import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import entity.Employees;
import service.EmployeesDAO;

public class TestEmplyeesDAOImpl {
	
	@Test
	public void testQueryAllEmployees()
	{
		EmployeesDAO edao = new EmployeesDAOImpl();
		List<Employees> list = edao.queryAllEmployees();
		
		for(int i=0;i<list.size();i++)
		{
			System.out.println(list.get(i));
		}
	}
	/*@Test
	public void testGetNewEid()
	{
		EmployeesDAOImpl edao = new EmployeesDAOImpl();
		System.out.println(edao.getNewEid());
	}*/
	@Test
	public void testAddEmployees()
	{
		Employees e = new Employees();
		e.setEname("cassic");
		e.setGender("Ů");
		e.setBirthday(new Date());
		e.setAddress("xi'an");
		EmployeesDAO edao = new EmployeesDAOImpl();
		Assert.assertEquals(true, edao.addEmployees(e));
	}
	

}

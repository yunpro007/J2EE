package service;

import java.util.List;

import entity.Employees;

//学生的业务逻辑类接口
public interface EmployeesDAO {
	//查询所有学生资料
	public List<Employees> queryAllEmployees();
	//根据学生编号查询学生资料
	public Employees queryEmployeesByEid(String Eid);
	//添加学生资料
	public boolean addEmployees(Employees e);
	//修改学生资料
	public boolean updateEmployees(Employees e);
	//删除学生资料
	public boolean deleteEmployees(String eid);
	
	

}

package action;

import java.text.SimpleDateFormat;
import java.util.List;

import entity.Employees;
import service.EmployeesDAO;
import service.impl.EmployeesDAOImpl;

//学生Action类
public class EmployeesAction extends SuperAction{

	
	private static final long serialVersionUID = 1L;
	//查询所有学生的动作
	public String query()
	{
		EmployeesDAO edao = new EmployeesDAOImpl();
		List<Employees> list = edao.queryAllEmployees();
		//
		if(list!=null&&list.size()>0)
		{
			session.setAttribute("Employees_list", list);
			
		}
		return "query_success";
	}
	
	//删除学生动作
	public String delete()
	{
		EmployeesDAO edao = new EmployeesDAOImpl();
		String eid = request.getParameter("eid");
		edao.deleteEmployees(eid);//调用删除方法
		return "delete_success";
	}
	//修改学生资料
	public String modify()
	{
		//获得传递过来的学生编号
		String eid = request.getParameter("eid");
		EmployeesDAO edao = new EmployeesDAOImpl();
		Employees e = edao.queryEmployeesByEid(eid);
		//保存在会话中
		session.setAttribute("modify_employees", e);
		return  "modify_success";
	}
	public String add() throws Exception
	{
		Employees e = new Employees();
		
		e.setEname(request.getParameter("ename"));
		e.setGender(request.getParameter("gender"));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		e.setBirthday(sdf.parse(request.getParameter("birthday")));
		e.setAddress(request.getParameter("address"));
		EmployeesDAO edao = new EmployeesDAOImpl();
		edao.addEmployees(e);
		return "add_success";
	}
	//保存修改后的学生资料动作
	public String save() throws Exception
	{
		Employees e = new Employees();
		e.setEid(request.getParameter("eid"));
		e.setEname(request.getParameter("ename"));
		e.setGender(request.getParameter("gender"));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		e.setBirthday(sdf.parse(request.getParameter("birthday")));
		e.setAddress(request.getParameter("address"));
		EmployeesDAO edao = new EmployeesDAOImpl();
		edao.updateEmployees(e);
		return "save_success";
	}

}

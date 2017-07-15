package action;

import java.text.SimpleDateFormat;
import java.util.List;

import entity.Employees;
import service.EmployeesDAO;
import service.impl.EmployeesDAOImpl;

//ѧ��Action��
public class EmployeesAction extends SuperAction{

	
	private static final long serialVersionUID = 1L;
	//��ѯ����ѧ���Ķ���
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
	
	//ɾ��ѧ������
	public String delete()
	{
		EmployeesDAO edao = new EmployeesDAOImpl();
		String eid = request.getParameter("eid");
		edao.deleteEmployees(eid);//����ɾ������
		return "delete_success";
	}
	//�޸�ѧ������
	public String modify()
	{
		//��ô��ݹ�����ѧ�����
		String eid = request.getParameter("eid");
		EmployeesDAO edao = new EmployeesDAOImpl();
		Employees e = edao.queryEmployeesByEid(eid);
		//�����ڻỰ��
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
	//�����޸ĺ��ѧ�����϶���
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

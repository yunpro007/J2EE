package service;

import java.util.List;

import entity.Employees;

//ѧ����ҵ���߼���ӿ�
public interface EmployeesDAO {
	//��ѯ����ѧ������
	public List<Employees> queryAllEmployees();
	//����ѧ����Ų�ѯѧ������
	public Employees queryEmployeesByEid(String Eid);
	//���ѧ������
	public boolean addEmployees(Employees e);
	//�޸�ѧ������
	public boolean updateEmployees(Employees e);
	//ɾ��ѧ������
	public boolean deleteEmployees(String eid);
	
	

}

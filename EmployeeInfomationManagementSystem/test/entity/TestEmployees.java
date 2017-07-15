package entity;



import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;



public class TestEmployees {
	
	@Test
	public void testSchemaExport()
	{
		//�������ö���
		Configuration config = new Configuration().configure();
		//��������ע�����
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		//�����ữ��������
		SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);
		//����Session����
		Session session = sessionFactory.getCurrentSession();
		//����SchemaExport����
		SchemaExport export = new SchemaExport(config);
		
		export.create(true,true);
	}
	
	@Test
	public void testSaveEmployees()
	{
		//�������ö���
		Configuration config = new Configuration().configure();
		//��������ע�����
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		//�����ữ��������
		SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);
		//����Session����
		Session session = sessionFactory.getCurrentSession();
		//�����������
		Transaction tx = session.beginTransaction();
		
		Employees e1 = new Employees("s0000001","dennison","��",new Date(),"�ൺ����ѧ");
		Employees e2 = new Employees("s0000002","cassic","Ů",new Date(),"��������ѧ");
		Employees e3 = new Employees("s0000003","june","Ů",new Date(),"��������ѧ");
		
		session.save(e1);
		session.save(e2);
		session.save(e3);
		
		tx.commit();
		sessionFactory.close();
	}

}

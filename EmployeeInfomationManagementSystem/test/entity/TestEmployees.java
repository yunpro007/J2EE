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
		//创建配置对象
		Configuration config = new Configuration().configure();
		//创建服务注册对象
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		//创建会化工厂对象
		SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);
		//创建Session对象
		Session session = sessionFactory.getCurrentSession();
		//创建SchemaExport对象
		SchemaExport export = new SchemaExport(config);
		
		export.create(true,true);
	}
	
	@Test
	public void testSaveEmployees()
	{
		//创建配置对象
		Configuration config = new Configuration().configure();
		//创建服务注册对象
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		//创建会化工厂对象
		SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);
		//创建Session对象
		Session session = sessionFactory.getCurrentSession();
		//创建事务对象
		Transaction tx = session.beginTransaction();
		
		Employees e1 = new Employees("s0000001","dennison","男",new Date(),"青岛理工大学");
		Employees e2 = new Employees("s0000002","cassic","女",new Date(),"北京理工大学");
		Employees e3 = new Employees("s0000003","june","女",new Date(),"大连理工大学");
		
		session.save(e1);
		session.save(e2);
		session.save(e3);
		
		tx.commit();
		sessionFactory.close();
	}

}

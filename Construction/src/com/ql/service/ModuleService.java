package com.ql.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.ql.dao.ModuleDao;
import com.ql.model.Module;
/**
 * ��ȡleft.jsp��Ҫ��ʾ��ģ�����Ϣ
 * @author 
 * @version 1.0
 */
public class ModuleService {
	 private static final Logger log = Logger.getLogger(ModuleService.class);
	
	 private ModuleDao moduleDao = new ModuleDao() ;
	/**
	 * �����û����ͣ���ȡ��Ȩ�����ܲ鿴��ģ�顣���ݣ�ģ��� mno,mname,hyperlink
	 * ������ �� mname�� left.jsp��ʹ��
	 * @param utype
	 * @return
	 * @throws Exception
	 */
	 public List<Module> getModulesByUtype(Integer utype) throws Exception {
			List<Module> modules = null;
			String  sql = " SELECT  m.mno, m.mname, m.hyperlink "
					+" FROM construction.module  m , construction.authority a "
					+" WHERE  a.uType = ? "
					+" AND   m.mno = a.mno " ;
			if(utype != null){
				Object val = utype;
				log.debug(sql);
				log.debug("parameter: "+ utype);
				modules = moduleDao.queryList(sql, val);
				log.debug(modules.size());
			}
			return modules;
		}
	
}

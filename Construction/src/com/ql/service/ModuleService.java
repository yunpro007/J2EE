package com.ql.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.ql.dao.ModuleDao;
import com.ql.model.Module;
/**
 * 获取left.jsp中要显示的模块儿信息
 * @author 
 * @version 1.0
 */
public class ModuleService {
	 private static final Logger log = Logger.getLogger(ModuleService.class);
	
	 private ModuleDao moduleDao = new ModuleDao() ;
	/**
	 * 根据用户类型，获取其权限内能查看的模块。内容：模块儿 mno,mname,hyperlink
	 * 超链接 和 mname在 left.jsp中使用
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

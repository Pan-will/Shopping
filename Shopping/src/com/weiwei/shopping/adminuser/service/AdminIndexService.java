package com.weiwei.shopping.adminuser.service;

import org.springframework.transaction.annotation.Transactional;

import com.weiwei.shopping.adminuser.dao.AdminIndexDao;
import com.weiwei.shopping.adminuser.vo.Adminuser;

/**
 * 后台管理用户模块业务层代码
 * @author 潘唯
 */
/*业务层要加Transactional · 要进行事物的管理*/
@Transactional
public class AdminIndexService {
	/*注入AdminIndexDao,并提供Set方法*/
	private AdminIndexDao adminIndexDao;
	public void setAdminIndexDao(AdminIndexDao adminIndexDao) {
		this.adminIndexDao = adminIndexDao;
	}


	/*验证登录的业务层方法*/
	public Adminuser checkLogin(Adminuser adminuser) {
		return adminIndexDao.checkLogin(adminuser);
	}
	
}

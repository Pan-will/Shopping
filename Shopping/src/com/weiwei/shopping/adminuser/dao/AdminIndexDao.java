package com.weiwei.shopping.adminuser.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.weiwei.shopping.adminuser.vo.Adminuser;

/**
 * 后台用户模块持久层代码
 * @author 潘唯
 * 继承了HibernateDaoSupport类之后自动就有了sessionFactory，再去spring中注入
 */
public class AdminIndexDao extends HibernateDaoSupport{

	/*验证登录的持久层代码*/
	public Adminuser checkLogin(Adminuser adminuser) {
		String hql = "from Adminuser where username=? and password=?";
		List<Adminuser> list = this.getHibernateTemplate().find(hql, adminuser.getUsername(), adminuser.getPassword());
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

}

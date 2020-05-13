package com.weiwei.shopping.user.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.weiwei.shopping.user.vo.User;

/**
 * 用户模块持久层代码
 * @author 潘唯
 * 继承了HibernateDaoSupport类之后自动就有了sessionFactory，再去spring中注入
 */
@SuppressWarnings("unchecked")
public class UserDao extends HibernateDaoSupport{
	/*按用户名查询是否存在该用户*/
	public User FindByUsername(String username){
		String hql = "from User where username=?";
		/*利用Hibernate模板查询；
		 * find返回的是一个List集合*/
		List<User> list = this.getHibernateTemplate().find(hql, username);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	/*注册用户存入数据库*/
	public void save(User user) {
		this.getHibernateTemplate().save(user);
	}

	/*通过激活码查询用户是否存在*/
	public User FindByCode(String code) {
		String hql = "from User where code=?";
		/*利用Hibernate模板查询；
		 * find返回的是一个List集合*/
		List<User> list = this.getHibernateTemplate().find(hql, code);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	/*更新用户的状态并清空用户的激活码*/
	public void update(User existUser) {
		this.getHibernateTemplate().update(existUser);
	}

	/*检查用户登录的方法*/
	public User checkLogin(User user) {
		String sql = "from User where username=? and password=? and state=?";
		List<User> list = this.getHibernateTemplate().find(sql, user.getUsername(),user.getPassword(),1);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	/*根据用户id查询改用户的持久层代码*/
	public User findByUid(Integer uid) {
		return this.getHibernateTemplate().get(User.class, uid);
	}

	/*删除用户的持久层代码*/
	public void delete(User existUser) {
		this.getHibernateTemplate().delete(existUser);
	}

	/*查询用户总个数的持久层代码*/
	public int findCount() {
		String hql = "select count(*) from User";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	/*分页查询用户集合的持久层代码*/
	public List<User> findByPage(int begin, int limit) {
		String hql = "from User";
		List<User> list = this.getHibernateTemplate().execute(new com.weiwei.shopping.utils.PageHibernateCallback<User>(hql, null, begin, limit));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}
}

package com.weiwei.shopping.user.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.weiwei.shopping.user.dao.UserDao;
import com.weiwei.shopping.user.vo.User;
import com.weiwei.shopping.utils.MailUtils;
import com.weiwei.shopping.utils.PageBean;
import com.weiwei.shopping.utils.UUIDUtils;

/**
 * 用户模块业务层代码
 * @author 潘唯
 */
/*业务层要加Transactional · 要进行事物的管理*/
@Transactional
public class UserService {
	/*注入UserDao*/
	private UserDao userDao;
	//提供set方法
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	/*按用户名查询是否存在该用户*/
	public User FindByUsername(String username){
		return userDao.FindByUsername(username);
	}

	/*业务层完成用户注册的方法*/
	public void save(User user) {
		/*用户注册第一步：将数据保存到数据库*/
		user.setState(0);/*1:已激活；0：未激活*/
		String code = UUIDUtils.getUUID() + UUIDUtils.getUUID();
		user.setCode(code);
		userDao.save(user);
		/*用户注册第二步：发送激活邮件*/
		MailUtils.sendMail(user.getEmail(), code);
	}

	/*业务层根据激活码查询用户*/
	public User FindByCode(String code) {
		return userDao.FindByCode(code);
	}

	/*业务层更新用户状态并清空激活码的方法*/
	public void update(User existUser) {
		userDao.update(existUser);
	}

	/*用户登录的方法*/
	public User checkLogin(User user) {
		return userDao.checkLogin(user);
	}

	/*根据用户id查询改用户的业务层代码*/
	public User findByUid(Integer uid) {
		return userDao.findByUid(uid);
	}

	/*删除用户的业务层代码*/
	public void delete(User existUser) {
		userDao.delete(existUser);
	}

	/*分页查询所有用户的业务层代码*/
	public PageBean<User> findByPage(Integer page) {
		PageBean<User> pageBean = new PageBean<User>();
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		// 显示5个
		int limit = 4;
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount = 0;
		totalCount = userDao.findCount();
		pageBean.setTotalCount(totalCount);
		// 设置总页数
		int totalPage = 0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 设置每页显示数据集合:
		int begin = (page - 1)*limit;
		List<User> list = userDao.findByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
}

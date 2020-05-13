package com.weiwei.shopping.user.adminaction;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.weiwei.shopping.user.service.UserService;
import com.weiwei.shopping.user.vo.User;
import com.weiwei.shopping.utils.PageBean;

public class AdminUserAction extends ActionSupport implements ModelDriven<User> {
	/*模型驱动要使用的对象*/
	private User user = new User();
	public User getModel() {
		return user;
	}
	
	/*注入UserService*/
	private UserService userService;
	/*提供set方法*/
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	/*接受前台传来的page参数，并提供set方法*/
	private Integer page;
	public void setPage(Integer page) {
		this.page = page;
	}
	
	/*分页查询所有用户的Action*/
	public String findAll(){
		PageBean<User> pageBean = userService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	
	/*删除用户的Action*/
	public String delete(){
		//先根据uid查出用户来
		User existUser = userService.findByUid(user.getUid());
		userService.delete(existUser);
		return "deleteSuccess";
	}
	
	/*编辑用户信息的Action——只是做了查找用户和跳转页面的操作*/
	public String edit(){
		user = userService.findByUid(user.getUid());
		return "editSuccess";
	}
	
	/*修改用户信息的Action*/
	public String update(){
		userService.update(user);
		return "updateSuccess";
	}
	
}

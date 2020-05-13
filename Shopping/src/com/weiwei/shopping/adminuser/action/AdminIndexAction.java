package com.weiwei.shopping.adminuser.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.weiwei.shopping.adminuser.service.AdminIndexService;
import com.weiwei.shopping.adminuser.vo.Adminuser;

public class AdminIndexAction extends ActionSupport implements ModelDriven<Adminuser>{
	/*模型驱动类使用的对象*/
	private Adminuser adminuser = new Adminuser();
	public Adminuser getModel() {
		return adminuser;
	}
	/*注入AdminIndexService,并提供set方法*/
	private AdminIndexService adminIndexService;
	public void setAdminIndexService(AdminIndexService adminIndexService) {
		this.adminIndexService = adminIndexService;
	}
	
	/*跳转到登录页面*/
	public String loginPage(){
		return "loginPage";
	}

	/*登录的Action*/
	public String login(){
		Adminuser existUser = adminIndexService.checkLogin(adminuser);
		if(existUser == null){
			//登录失败
			this.addActionError("查无此人,登录失败！");
			return "loginFalse";
		} else{
			//登录成功
			//将用户的信息存到session中，在进行页面的跳转
			ServletActionContext.getRequest().getSession().setAttribute("existUser", existUser);
			return "loginSuccess";
		}
	}
	
	/*用户退出的方法*/
	public String quit(){
		//销毁session
		ServletActionContext.getRequest().getSession().invalidate();
		this.addActionMessage("成功退出系统！欢迎再次使用！");
		return "quit";
	}

}

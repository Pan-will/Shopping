package com.weiwei.shopping.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.weiwei.shopping.user.service.UserService;
import com.weiwei.shopping.user.vo.User;

@SuppressWarnings("serial")
public class UserAction extends ActionSupport implements ModelDriven<User>{
	/*模型驱动要使用的对象*/
	private User user = new User();
	public User getModel() {
		return user;
	}
	
	/*注入UserService，并提供set方法*/
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	/*接收表单传入的验证码，并提供set方法*/
	private String checkcode;
	
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	
	/**
	 * AJAX异步校验用户名
	 * @return String
	 * @throws IOException
	 */
	public String FindByName() throws IOException{
		/*调用service进行查询*/
		User existUser = userService.FindByUsername(user.getUsername());
		/*获取response对象，向页面输出信息*/
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		/*判断是否为空*/
		if(existUser != null){
			//用户名已经存在，不能注册
			/*有异常则向上抛出*/
			response.getWriter().println("<font color='red'>用户名已存在,不可用！</font>");
		} else{
			//用户民不存在，可以注册
			response.getWriter().println("<font color='green'>用户名可以使用！</font>");
		}
		/*AJAX操作，不需要页面跳转*/
		return NONE;
	}
	
	/*跳转到注册页面*/
	public String registPage(){
		return "registPage";
	}
	/*跳转到登录页面*/
	public String loginPage(){
		return "loginPage";
	}
	
	/*用户注册方法*/
	public String register(){
		/*判断验证码，验证码正确则提交，否则不提交*/
		//从session中获取正确的验证码
		String rightcode = (String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
		//与表单提交的验证码比较
		if(!checkcode.equalsIgnoreCase(rightcode)){
			//不相等
			this.addActionError("验证码输入错误！");
			return "checkcodeFalse";
		}
		userService.save(user);
		this.addActionMessage("注册成功！请前往您的邮箱激活账号！");
		return "msg";
	}
	
	/*用户激活的方法*/
	public String active(){
		//根据激活码查询用户是否存在
		User existUser = userService.FindByCode(user.getCode());
		if(existUser == null){
			//激活码错误，激活失败
			this.addActionError("激活失败：激活码错误或已过期！");
		} else{
			//激活成功
			existUser.setState(1);
			existUser.setCode(null);
			userService.update(existUser);
			this.addActionMessage("激活成功，请前往登录！");
		}
		return "msg";
	}
	
	/*用户登录的方法*/
	public String login(){
		User existUser = userService.checkLogin(user);
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
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
//		if(user==null){
//			System.out.println("用户已经被销毁，不存在\n\n\n\n\n\n\n\n\n\n\n\n");
//		}
		return "quit";
	}
}
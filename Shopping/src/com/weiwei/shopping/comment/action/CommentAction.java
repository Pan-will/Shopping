package com.weiwei.shopping.comment.action;

import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.weiwei.shopping.comment.service.CommentService;
import com.weiwei.shopping.comment.vo.Comment;
import com.weiwei.shopping.user.vo.User;
/**
 * 商品评价模块的Action代码
 * @author 潘唯
 * 添加商品评价。
 */
@SuppressWarnings("serial")
public class CommentAction extends ActionSupport implements ModelDriven<Comment>{
	/*用于接收数据的模型驱动对象*/
	private Comment comment = new Comment();
	public Comment getModel() {
		return comment;
	}
	
	/*注入CommentService*/
	private CommentService commentService;
	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}
	
	/*接收当前页数page，并提供set方法*/
//	private int page;
//	public void setPage(int page) {
//		this.page = page;
//	}
	
	/*接收页面传来的商品pid*/
//	private int pid;
//	public int getPid() {
//		return pid;
//	}
//	public void setPid(int pid) {
//		this.pid = pid;
//	}

	/*保存评价的Action*/
	public String save(){
		// 设置商品评价关联的客户
		User existUser = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("existUser");
		if (existUser == null) {
			this.addActionMessage("亲!您还没有登录!");
			return "msg";
		}
		comment.setUser(existUser);
		comment.setCdate(new Date());//评论时间为当前系统时间
		commentService.save(comment);
		this.addActionMessage("评论成功！");
		return "msg";
	}
}

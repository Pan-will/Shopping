package com.weiwei.shopping.category.adminaction;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.weiwei.shopping.category.service.CategoryService;
import com.weiwei.shopping.category.vo.Category;

public class AdminCategoryAction extends ActionSupport implements ModelDriven<Category>{
	/*模型驱动使用的对象*/
	private Category category = new Category();
	public Category getModel() {
		return category;
	}
	
	/*注入CategoryService*/
	private CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	/*查询所有一级分类的Action*/
	public String findAll(){
		// 调用Service查询所有一级分类
		List<Category> cList = categoryService.FindAll();
		// 通过值栈保存一级分类集合
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "findAll";
	}
	
	/*保存一级分类的Action*/
	public String save(){
		// 调用Service保存一级分类
		categoryService.save(category);
		// 页面跳转
		return "saveSuccess";
	}
	
	/*删除一级分类的Action*/
	public String delete(){
		//调用Service,查询出该一级分类
		category = categoryService.findByCid(category.getCid());
		//调用Service，删除该一级分类
		categoryService.delete(category);
		return "deleteSuccess";
	}
	
	/*编辑一级分类的Action——只是根据cid进行了一个查询操作*/
	public String edit(){
		// 接收cid,根据cid进行查询
		category = categoryService.findByCid(category.getCid());
		// 完成页面转向:将一级分类数据显示到页面上.
		return "editSuccess";
	}
	
	/*修改一级分类的方法*/
	public String update(){
		// 使用模型驱动接收前台提交数据
		categoryService.update(category);
		// 页面跳转
		return "updateSuccess";
	}
}

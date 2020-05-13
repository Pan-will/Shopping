package com.weiwei.shopping.categorysecond.adminaction;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.weiwei.shopping.category.service.CategoryService;
import com.weiwei.shopping.category.vo.Category;
import com.weiwei.shopping.categorysecond.service.CategorySecondService;
import com.weiwei.shopping.categorysecond.vo.CategorySecond;
import com.weiwei.shopping.utils.PageBean;

public class AdminCategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond> {
	/*模型驱动使用的对象*/
	private CategorySecond categorySecond = new CategorySecond();
	public CategorySecond getModel() {
		return categorySecond;
	}
	
	/*注入二级分类的CategorySecondService*/
	private CategorySecondService categorySecondService;
	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}
	
	/*注入一级分类的CategoryService*/
	private CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	/*接收前台传来的当前页page,并提供set方法*/
	private Integer page;
	public void setPage(Integer page) {
		this.page = page;
	}

	/*根据当前页查找所有二级分类的Action*/
	public String findAllByPage(){
		PageBean<CategorySecond> pageBean = categorySecondService.findAllByPage(page);
		//将pageBean的数据保存到值栈中
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		//页面跳转
		return "findAllByPage";
	}
	
	/*跳转到添加二级分类的页面*/
	public String addPage(){
		//先查询出一级分类 --> 注入一级分类service
		List<Category> cList = categoryService.FindAll();
		//将查询出来的数据放到值栈中
		ActionContext.getContext().getValueStack().set("cList",cList);
		//页面跳转
		return "addPage";
	}
	
	/*添加二级分类的的Action*/
	public String save(){
		categorySecondService.save(categorySecond);
		//页面跳转
		return "saveSuccess";
	}
	
	/*删除二级分类的Action*/
	public String delete(){
		//级联删除，需要先查询再删除，配置cascade
		categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
		categorySecondService.delete(categorySecond);
		//页面跳转
		return "deleteSuccess";
	}
	
	/*编辑二级分类的Action——只是根据csid进行了一个查询操作*/
	public String edit(){
		// 接收csid,根据csid进行查询
		categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
		//查询所有一级分类
		List<Category> cList = categoryService.FindAll();
		//将查出的集合放到值栈中
		ActionContext.getContext().getValueStack().set("cList", cList);
		// 完成页面转向:将二级分类数据显示到页面上.
		return "editSuccess";
	}
	
	/*修改二级分类的Action*/
	public String update(){
		// 使用模型驱动接收前台提交数据
		categorySecondService.update(categorySecond);
		// 页面跳转
		return "updateSuccess";
	}
	
	
}

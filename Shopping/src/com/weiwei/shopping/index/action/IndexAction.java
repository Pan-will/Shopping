package com.weiwei.shopping.index.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.weiwei.shopping.category.service.CategoryService;
import com.weiwei.shopping.category.vo.Category;
import com.weiwei.shopping.product.service.ProductService;
import com.weiwei.shopping.product.vo.Product;

/**
 * 首页访问的Action
 * @author 潘唯
 */
public class IndexAction extends ActionSupport{
	/*注入一级分类的service并提供set方法*/
	private CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	/*注入商品的service并提供set方法*/
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public String execute(){
		/*查询商品的一级分类*/
		List<Category> Clist = categoryService.FindAll();
		//将一级分类存入session中：因为很多页面都用到
		ActionContext.getContext().getSession().put("Clist", Clist);

		/*查询热门商品*/
		List<Product> HotPlist = productService.FindHot();
		//将热门商品放到值栈中
		ActionContext.getContext().getValueStack().set("HotPlist", HotPlist);
		
		/*查询最新商品*/
		List<Product> NewPList = productService.FindNew();
		//将最新商品放到值栈中
		ActionContext.getContext().getValueStack().set("NewPList", NewPList);
		
		return "index";
	}
}

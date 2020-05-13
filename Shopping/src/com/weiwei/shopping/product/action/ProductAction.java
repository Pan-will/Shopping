package com.weiwei.shopping.product.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.weiwei.shopping.comment.service.CommentService;
import com.weiwei.shopping.comment.vo.Comment;
import com.weiwei.shopping.product.service.ProductService;
import com.weiwei.shopping.product.vo.Product;
import com.weiwei.shopping.utils.PageBean;

/**
 * 商品模块的的Action类
 * @author 潘唯
 */
@SuppressWarnings("serial")
public class ProductAction extends ActionSupport implements ModelDriven<Product>{
	/*用于接收数据的模型驱动*/
	private Product product = new Product();
	//提供get方法
	public Product getModel(){
		return product;
	}
	
	/*注入商品的ProductService*/
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	/*注入CommentService*/
	private CommentService commentService;
	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}
	public CommentService getCommentService() {
		return commentService;
	}

	/*接收一级分类的编号cid，并提供set\get方法*/
	private Integer cid;
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public Integer getCid() {
		return cid;
	}

	/*接收当前页数page，并提供set方法*/
	private int page;
	public void setPage(int page) {
		this.page = page;
	}
	
	/*接收二级分类的csid,并提供set、get方法*/
	private Integer csid;
	public void setCsid(Integer csid) {
		this.csid = csid;
	}
	public Integer getCsid() {
		return csid;
	}
	
	/*根据一级分类的编号cid查询商品*/
	public String FindByCid(){
		/*查询商品的一级分类
		 * 因为已经查询过了，所以直接从session中获取就行了*/
//		List<Category> Clist = categoryService.FindAll();
		/*根据一级分类的id查询商品，带分页;参数：二级分类id，当前页。*/
		PageBean<Product> pageBean = productService.FindByPageCid(cid, page);
		/*将pageBean存入值栈*/
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "FindByCid";
	}

	/*根据二级分类的csid查询商品*/
	public String FindByCsid(){
		PageBean<Product> pageBean = productService.FindByPageCsid(csid, page);
		/*将pageBean存入值栈*/
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "FindByCsid";
	}

	/*根据商品的编号查询该商品的详细信息——商品评论页面*/
	public String findProductItem(){
		//调用Service的方法完成查询.
		product = productService.FindByPid(product.getPid());
		// 将查询到的商品放到值栈中，从而显示到页面
		ActionContext.getContext().getValueStack().set("product", product);
		return "findProduct";
	}
	
	/*根据商品的编号查询该商品的详细信息——商品详情页面*/
	public String FindByPid(){
		/*查询该商品的评论*/
		PageBean<Comment> pageBean = commentService.findPageByPid(product.getPid(), page);
		
		//调用Service的方法完成查询.
		product = productService.FindByPid(product.getPid());

		/*将pageBean存入值栈*/
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "FindByPid";
	}
	
	/*根据商品pid分页查询该商品所有好评*/
	public String findGoodByPid(){
		/*查询该商品的评论*/
		PageBean<Comment> pageBean = commentService.findGoodPageByPid(product.getPid(), page);
		
		//调用Service的方法完成查询.
		product = productService.FindByPid(product.getPid());
		
		/*将pageBean存入值栈*/
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findGoodByPid";
	}
	
	/*根据商品pid分页查询该商品所有差评*/
	public String findBadByPid(){
		/*查询该商品的评论*/
		PageBean<Comment> pageBean = commentService.findBadPageByPid(product.getPid(), page);
		
		//调用Service的方法完成查询.
		product = productService.FindByPid(product.getPid());
				
		/*将pageBean存入值栈*/
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findBadByPid";
	}
	
}

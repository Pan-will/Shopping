package com.weiwei.shopping.product.adminaction;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.weiwei.shopping.categorysecond.service.CategorySecondService;
import com.weiwei.shopping.categorysecond.vo.CategorySecond;
import com.weiwei.shopping.product.service.ProductService;
import com.weiwei.shopping.product.vo.Product;
import com.weiwei.shopping.utils.PageBean;
/**
 * 管理员后台管理商品的Action
 * @author 唯唯
 *
 */
public class AdminProductAction extends ActionSupport implements ModelDriven<Product> {
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
	
	/*注入二级分类的CategorySecondService*/
	private CategorySecondService categorySecondService;
	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}
	
	/*接收前台传来的page*/
	private Integer page;
	public void setPage(Integer page) {
		this.page = page;
	}
	/*接收文件上传需要的参数，提供set方法*/
	private File upload;//上传的文件，"upload"必须和jsp上的name属性值完全相同
	private String uploadFileName;//上传的文件名
	private String uploadContextType;//上传的图片类型
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public void setUploadContextType(String uploadContextType) {
		this.uploadContextType = uploadContextType;
	}

	/*分页查询所有商品的Action*/
	public String findAllByPage(){
		PageBean<Product> pageBean = productService.findAllByPage(page);
		//存到值栈中
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		//页面跳转
		return "findAllByPage";
	}
	
	/*跳转到添加商品的页面*/
	public String addPage(){
		//调用service查询所有二级分类，返回的是一个集合
		List<CategorySecond> csList = categorySecondService.findAll();
		//将查询结果放到值栈中
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "addPage";
	}
	
	/*添加商品的Action,调用service完成保存操作*/
	public String save() throws IOException{
		//设置添加的新商品的时间
		product.setPdate(new Date());
		//判断若文件不为空则上传
		if(upload != null){
			//图片要上传到的Tomcat服务器的路径
			//realPath = D:\apache-tomcat-7.0.68\webapps\Shopping\products
			String realPath = ServletActionContext.getServletContext().getRealPath("/products");
			System.out.println(uploadFileName);
			//创建一个文件:即是刚刚上传的服务器的图片,其中两条"//"：第一条是转移字符下划线，第二条是路径名中的下划线
			File diskFile = new File(realPath + "//" + uploadFileName);
			//文件上传,把前台选择的文件upload复制给上面创建的磁盘文件diskFile
			FileUtils.copyFile(upload, diskFile);
			product.setImage("products/" + uploadFileName);
		}
		//保存到数据库
		productService.save(product);
		//页面跳转
		return "saveSuccess";
	}
	
	/*获取一条随机字符串，用来给图片命名*/
    public String getRandomString(int length) { //length表示生成字符串的长度  
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";     
        Random random = new Random();     
        StringBuffer sb = new StringBuffer();     
        for (int i = 0; i < length; i++) {     
            int number = random.nextInt(base.length());     
            sb.append(base.charAt(number));     
        }     
        return sb.toString();     
     }   
    
	/*删除商品的Action*/
	public String delete(){
		product = productService.FindByPid(product.getPid());
		//删除上传的商品图片
		String path = product.getImage();
		//判断图片是否为空
		if(path != null){
			//获得磁盘绝对路径
			String realPath = ServletActionContext.getServletContext().getRealPath("/"+path);
			//创建一个file对象
			File file = new File(realPath);
			//调用文件对象的delete方法
			file.delete();
		}
		productService.delete(product);
		//页面跳转
		return "deleteSuccess";
	}
	
	/*进入编辑商品的页面Action*/
	/*编辑商品的Action——只是做了查询数据、跳转页面的操作*/
	public String edit(){
		//根据商品pid查询到该商品
		product = productService.FindByPid(product.getPid());
		//查询所有二级分类的集合
		List<CategorySecond> csList = categorySecondService.findAll();
		//将数据保存到页面 · 直接用模型驱动
		ActionContext.getContext().getValueStack().set("csList", csList);
		//页面跳转
		return "editSuccess";
	}
	
	/*更新商品的Action*/
	/*修改商品信息的Action*/
	public String update() throws IOException{
		//调用service完成保存操作
		product.setPdate(new Date());
		//判断若文件不为空则上传
		if(upload != null){
			//先删除原来的图片
			String path = product.getImage();
			File file = new File(ServletActionContext.getServletContext().getRealPath("/"+path));
			file.delete();
			//获得上传的绝对路径
			String realPath = ServletActionContext.getServletContext().getRealPath("/products");
			//创建一个文件
			File diskFile = new File(realPath + "//" + uploadFileName);
			//文件上传
			FileUtils.copyFile(upload, diskFile);
			product.setImage("products/" + uploadFileName);
		}
		//保存到数据库
		productService.update(product);
		//页面跳转
		return "updateSuccess";
	}
}

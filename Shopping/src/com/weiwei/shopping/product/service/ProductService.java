package com.weiwei.shopping.product.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.weiwei.shopping.product.dao.ProductDao;
import com.weiwei.shopping.product.vo.Product;
import com.weiwei.shopping.utils.PageBean;

/**
 * 商品的业务层代码
 * @author 潘唯
 * 先在Service类中注入DAO，再到spring中注入
 */
/*添加事务管理*/
@Transactional
public class ProductService {
	/*注入ProductDao*/
	private ProductDao productDao;
	//提供set方法
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	
	/*查询热门商品的方法*/
	public List<Product> FindHot() {
		return productDao.FindHot();
	}

	/*查询最新商品的方法*/
	public List<Product> FindNew() {
		return productDao.FindNew();
	}

	/*业务层根据商品id查询商品的方法*/
	public Product FindByPid(Integer pid) {
		return productDao.FindByPid(pid);
	
	}

	/*根据一级分类的id带分页查询商品*/
	public PageBean<Product> FindByPageCid(Integer cid, int page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		//设置当前页
		pageBean.setPage(page);
		//设置每页显示的记录数:8个商品；
		int limit = 8;
		pageBean.setLimit(limit);
		//设置总记录数
		int totalCount = 0;
		totalCount = productDao.FindCountByCid(cid);
		pageBean.setTotalCount(totalCount);
		//设置总页数
//		另外一种写法：Math.ceil(totalCount / limit);
		int totalPage = 0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		/*设置每页显示的数据集合*/
		//从哪页开始：
		int begin = (page - 1) * limit;
		List<Product> list = productDao.FindByPageCid(cid,begin,limit);
		pageBean.setList(list);
		
		return pageBean;
	}

	/*业务层根据二级分类的csid查询商品集合*/
	public PageBean<Product> FindByPageCsid(Integer csid, int page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		//设置当前页
		pageBean.setPage(page);
		//设置每页显示的记录数:8个商品；
		int limit = 8;
		pageBean.setLimit(limit);
		//设置总记录数
		int totalCount = 0;
		totalCount = productDao.FindCountByCsid(csid);
		pageBean.setTotalCount(totalCount);
		//设置总页数
//		另外一种写法：Math.ceil(totalCount / limit);
		int totalPage = 0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		/*设置每页显示的数据集合*/
		//从哪页开始：
		int begin = (page - 1) * limit;
		List<Product> list = productDao.FindByPageCsid(csid,begin,limit);
		pageBean.setList(list);
		
		return pageBean;
	}

	/*分页查询所有商品的业务层代码*/
	public PageBean<Product> findAllByPage(Integer page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		//设置当前页
		pageBean.setPage(page);
		//设置每页显示的记录数:6个商品；
		int limit = 6;
		pageBean.setLimit(limit);
		//设置总记录数
		int totalCount = 0;
		totalCount = productDao.findAllCount();
		pageBean.setTotalCount(totalCount);
		//设置总页数
//		另外一种写法：Math.ceil(totalCount / limit);
		int totalPage = 0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		/*设置每页显示的数据集合*/
		//从哪页开始：
		int begin = (page - 1) * limit;
		List<Product> list = productDao.findAll(begin, limit);
		pageBean.setList(list);
		
		return pageBean;
	}
	
	
	/*删除商品的业务层代码*/
	public void delete(Product product) {
		productDao.delete(product);
	}

	/*添加商品的业务层方法*/
	public void save(Product product) {
		productDao.save(product);
	}

	/*修改商品信息的业务层代码*/
	public void update(Product product) {
		productDao.update(product);
	}
}

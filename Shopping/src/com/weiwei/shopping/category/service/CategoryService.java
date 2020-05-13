package com.weiwei.shopping.category.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.weiwei.shopping.category.dao.CategoryDao;
import com.weiwei.shopping.category.vo.Category;

/**
 * 一级分类的业务层代码
 * @author 潘唯
 * 先在Service类中注入DAO，再到spring中注入
 */
/*添加事务管理*/
@Transactional
public class CategoryService {
	/*注入CategoryDao*/
	private CategoryDao categoryDao;
	//set方法
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	
	/*业务层查询所有一级分类的方法*/
	public List<Category> FindAll() {
		return categoryDao.FindAll();
	}

	/*添加一级分类的业务层方法*/
	public void save(Category category) {
		categoryDao.save(category);
	}

	/*根据cid查询一级分类的方法*/
	public Category findByCid(Integer cid) {
		return categoryDao.findByCid(cid);
	}
	
	/*删除一级分类的业务层方法*/
	public void delete(Category category) {
		categoryDao.delete(category);
	}

	/*更新一级分类的业务层方法*/
	public void update(Category category) {
		categoryDao.update(category);
	}

	
	
	
}

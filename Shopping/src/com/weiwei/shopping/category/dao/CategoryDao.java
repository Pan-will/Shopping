package com.weiwei.shopping.category.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.weiwei.shopping.category.vo.Category;

/**
 * 一级分类持久层代码
 * @author 潘唯
 * 继承了HibernateDaoSupport类之后自动就有了sessionFactory，再去spring中注入
 */
@SuppressWarnings("unchecked")
public class CategoryDao extends HibernateDaoSupport{

	/*持久层查询所有一级分类的方法*/
	public List<Category> FindAll() {
		String hql = "from Category";
		List<Category> list = this.getHibernateTemplate().find(hql);
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}

	/*添加一级分类的持久层方法*/
	public void save(Category category) {
		this.getHibernateTemplate().save(category);
	}

	/*查询一级分类的持久层代码*/
	public Category findByCid(Integer cid) {
		return this.getHibernateTemplate().get(Category.class, cid);
	}

	/*删除一级分类的持久层代码*/
	public void delete(Category category) {
		this.getHibernateTemplate().delete(category);
	}

	/*更新一级分类的持久层代码*/
	public void update(Category category) {
		this.getHibernateTemplate().update(category);
	}

}

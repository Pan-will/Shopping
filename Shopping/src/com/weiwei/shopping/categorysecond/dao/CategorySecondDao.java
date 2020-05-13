package com.weiwei.shopping.categorysecond.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.weiwei.shopping.categorysecond.vo.CategorySecond;
import com.weiwei.shopping.utils.PageHibernateCallback;

/**
 * 二级分类管理的持久层代码
 * @author 潘唯
 * 继承了HibernateDaoSupport类之后自动就有了sessionFactory，再去spring中注入
 */
@SuppressWarnings("unchecked")
public class CategorySecondDao extends HibernateDaoSupport{

	/*分页查询二级分类的总个数的持久层代码*/
	public Integer findCount() {
		String hql = "select count(*) from CategorySecond";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}

	/*查询显示商品的集合的持久层代码*/
	public List<CategorySecond> findPage(int begin, Integer limit) {
		String hql = "from CategorySecond order by csid desc";
		List<CategorySecond> list = this.getHibernateTemplate().execute(new PageHibernateCallback<CategorySecond>(hql, null , begin, limit));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}

	/*添加二级分类的持久层代码*/
	public void save(CategorySecond categorySecond) {
		this.getHibernateTemplate().save(categorySecond);
	}

	/*根据二级分类的csid查询该二级分类的持久层方法*/
	public CategorySecond findByCsid(Integer csid) {
		return this.getHibernateTemplate().get(CategorySecond.class, csid);
	}

	/*删除二级分类的持久层方法*/
	public void delete(CategorySecond categorySecond) {
		this.getHibernateTemplate().delete(categorySecond);
	}

	/*修改二级分类的持久层代码*/
	public void update(CategorySecond categorySecond) {
		this.getHibernateTemplate().update(categorySecond);
	}

	/*查询所有二级分类的持久层代码*/
	public List<CategorySecond> findAll() {
		String hql = "from CategorySecond";
		return this.getHibernateTemplate().find(hql);
	}
	
}

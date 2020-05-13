package com.weiwei.shopping.product.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.weiwei.shopping.product.vo.Product;
import com.weiwei.shopping.utils.PageHibernateCallback;

/**
 * 商品模块的持久层代码
 * @author 潘唯
 *继承了HibernateDaoSupport类之后自动就有了sessionFactory，再去spring中注入
 */
@SuppressWarnings("unchecked")
public class ProductDao extends HibernateDaoSupport{

	/*带分页的热门商品查询方法.0:不是热门商品  1:是热门商品*/
	public List<Product> FindHot() {
		//使用离线条件查询
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		//查询热门商品条件：is_hot=1
		criteria.add(Restrictions.eq("is_hot", 1));
		//倒序排序输出
		criteria.addOrder(Order.desc("pdate"));
		//执行查询:从第一条到第十条，即每页是条数据
		List<Product> list = this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}

	/*带分页的最新商品查询方法*/
	public List<Product> FindNew() {
		//使用离线条件查询
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		//按日期倒序排序输出
		criteria.addOrder(Order.desc("pdate"));
		//执行查询:从第一条到第十条，即每页是条数据
		List<Product> list = this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}

	/*持久层根据商品id查询商品的方法*/
	public Product FindByPid(Integer pid) {
		String hql = "from Product where pid=?";
		List<Product> list = this.getHibernateTemplate().find(hql, pid);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	/*根据一级分类的id查询商品的总个数*/
	public int FindCountByCid(Integer cid) {
		String hql = "select count(*) from Product p where p.categorySecond.category.cid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql, cid);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}

	/*根据一级分类的id查询显示商品的集合
	 * 多表多外键关联查询*/
	public List<Product> FindByPageCid(Integer cid, int begin, int limit) {
		//select p.* from category c,categorysecond cs,product p where c.cid=cs.cid and cs.csid=p.csid and cid=?;
		//select p from category c,categorysecond cs,product p where c.cid=cs.category.cid and cs.csid=p.categorysecond.csid and c.cid=?;
		
		String hql = "select p from Product p join p.categorySecond cs join cs.category c where c.cid=?";
		List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql,new Object[]{cid}, begin, limit));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}

	/*根据二级分类的csid查询商品的总个数*/
	public int FindCountByCsid(Integer csid) {
		String hql = "select count(*) from Product p where p.categorySecond.csid=?";
		List<Long> list = this.getHibernateTemplate().find(hql, csid);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}

	/*根据二级分类的csid查询显示商品的集合*/
	public List<Product> FindByPageCsid(Integer csid, int begin, int limit) {
		String hql = "select p from Product p join p.categorySecond cs where cs.csid=?";
		List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql,new Object[]{csid}, begin, limit));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}

	/*查询所有商品总记录数的持久层代码*/
	public int findAllCount() {
		String hql = "select count(*) from Product";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}

	/*查询所有商品数据集合的持久层代码*/
	public List<Product> findAll(int begin, int limit) {
		String hql = "from Product order by pdate desc";
		List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, null , begin, limit));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}

	/*删除商品的持久层代码*/
	public void delete(Product product) {
		this.getHibernateTemplate().delete(product);
	}

	/*添加商品的持久层方法*/
	public void save(Product product) {
		this.getHibernateTemplate().save(product);
	}

	/*修改商品信息的持久层代码*/
	public void update(Product product) {
		this.getHibernateTemplate().update(product);
	}

}

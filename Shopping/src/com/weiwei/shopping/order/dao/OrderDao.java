package com.weiwei.shopping.order.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.weiwei.shopping.order.vo.Order;
import com.weiwei.shopping.order.vo.OrderItem;
import com.weiwei.shopping.utils.PageHibernateCallback;
/**
 * 订单模块的持久层代码
 * @author 潘唯
 * 继承了HibernateDaoSupport类之后自动就有了sessionFactory，再去spring中注入
 */
@SuppressWarnings("unchecked")
public class OrderDao extends HibernateDaoSupport{

	/*保存订单的持久层代码*/
	public void save(Order order) {
		this.getHibernateTemplate().save(order);
	}

	/*持久层根据Uid查询该用户订单总数的方法*/
	public Integer FindCountByUid(Integer uid) {
		String hql = "select count(*) from Order o where o.user.uid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql, uid);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}

	/*持久层根据Uid查询该用户的订单集合 · 分页查询*/
	public List<Order> FindByPageUid(Integer uid, int begin, int limit) {
		String hql = "from Order o where o.user.uid=? order by ordertime desc";
		List<Order> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Order>(hql, new Object[]{uid}, begin, limit));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}

	/*根据订单编号查询该订单的持久层代码*/
	public Order FindByOid(Integer oid) {
		return this.getHibernateTemplate().get(Order.class, oid);
	}

	/*修改订单的持久层代码*/
	public void update(Order payorder) {
		this.getHibernateTemplate().update(payorder);
	}

	/*查询订单总数的持久层代码*/
	public Integer findAllCount() {
		String hql = "select count(*) from Order";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	/*分页查询显示订单的集合的持久层代码*/
	public List<Order> FindAllPage(int begin, int limit) {
		String hql = "from Order order by ordertime desc";
		List<Order> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<Order>(hql, null, begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	/*根据订单oid查询订单项的持久层代码*/
	public List<OrderItem> findOrderItem(Integer oid) {
		String hql = "from OrderItem oi where oi.order.oid = ?";
		List<OrderItem> list = this.getHibernateTemplate().find(hql, oid);
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	/*删除订单的持久层方法*/
	public void delete(Order order) {
		this.getHibernateTemplate().delete(order);
	}

	/*查询不同状态订单的总数*/
	public Integer findStateCount(Integer state) {
		String hql = "select count(*) from Order where state=?";
		List<Long> list = this.getHibernateTemplate().find(hql, state);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	/*分页查询显示不同状态订单的集合的持久层代码*/
	public List<Order> FindStatePage(Integer state, int begin, int limit) {
		String hql = "from Order o where o.state=? order by ordertime desc";
		List<Order> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<Order>(hql, new Object[]{state}, begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

}

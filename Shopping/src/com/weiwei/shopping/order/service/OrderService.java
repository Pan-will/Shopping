package com.weiwei.shopping.order.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.weiwei.shopping.order.dao.OrderDao;
import com.weiwei.shopping.order.vo.Order;
import com.weiwei.shopping.order.vo.OrderItem;
import com.weiwei.shopping.utils.PageBean;
/**
 * 订单模块：业务层代码
 * @author 潘唯
 */
@Transactional
public class OrderService {
	/*注入OrderDao*/
	private OrderDao orderDao;
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	/*保存订单的业务层代码*/
	public void save(Order order) {
		orderDao.save(order);
	}

	/*我的订单查询的业务层代码*/
	public PageBean<Order> FindByUid(Integer uid, Integer page) {
		PageBean<Order> pageBean = new PageBean<Order>();
		//设置当前页
		pageBean.setPage(page);
		//设置每页显示的记录数:2张订单；
		int limit = 2;
		pageBean.setLimit(limit);
		//设置总记录数
		Integer totalCount = 0;
		totalCount = orderDao.FindCountByUid(uid);
		pageBean.setTotalCount(totalCount);
		//设置总页数
        //另外一种写法：Math.ceil(totalCount / limit);
		Integer totalPage = 0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		/*设置每页显示的数据集合*/
		//从哪页开始：
		int begin = (page - 1) * limit;
		List<Order> list = orderDao.FindByPageUid(uid, begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	/*根据订单编号查询该订单的业务层方法*/
	public Order FindByOid(Integer oid) {
		return orderDao.FindByOid(oid);
	}

	/*修改订单的业务层代码*/
	public void update(Order payorder) {
		orderDao.update(payorder);
	}

	/*分页查询所有订单的业务层方法*/
	public PageBean<Order> findAll(Integer page) {
		PageBean<Order> pageBean = new PageBean<Order>();
		//设置当前页
		pageBean.setPage(page);
		//设置每页显示的记录数:2张订单；
		int limit = 5;
		pageBean.setLimit(limit);
		//设置总记录数
		Integer totalCount = 0;
		totalCount = orderDao.findAllCount();
		pageBean.setTotalCount(totalCount);
		//设置总页数
        //另外一种写法：Math.ceil(totalCount / limit);
		Integer totalPage = 0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		/*设置每页显示的数据集合*/
		//从哪页开始：
		int begin = (page - 1) * limit;
		List<Order> list = orderDao.FindAllPage(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	/*根据订单oid查询订单项的业务层代码*/
	public List<OrderItem> findOrderItem(Integer oid) {
		return orderDao.findOrderItem(oid);
	}

	/*删除订单的业务层方法*/
	public void delete(Order order) {
		orderDao.delete(order);
	}

	/*根据订单状态查找分页订单的业务层代码*/
	public PageBean<Order> findByState(Integer state, Integer page) {
		PageBean<Order> pageBean = new PageBean<Order>();
		//设置当前页
		pageBean.setPage(page);
		//设置每页显示的记录数:2张订单；
		int limit = 5;
		pageBean.setLimit(limit);
		//设置总记录数
		Integer totalCount = 0;
		totalCount = orderDao.findStateCount(state);
		pageBean.setTotalCount(totalCount);
		//设置总页数
        //另外一种写法：Math.ceil(totalCount / limit);
		Integer totalPage = 0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		/*设置每页显示的数据集合*/
		//从哪页开始：
		int begin = (page - 1) * limit;
		List<Order> list = orderDao.FindStatePage(state, begin, limit);
		pageBean.setList(list);
		return pageBean;
	}
	
}

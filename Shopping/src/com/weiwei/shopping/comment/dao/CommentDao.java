package com.weiwei.shopping.comment.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.weiwei.shopping.comment.vo.Comment;
import com.weiwei.shopping.utils.PageHibernateCallback;

/**
 * 商品评价模块的持久层代码
 * @author 潘唯
 * 继承了HibernateDaoSupport类之后自动就有了sessionFactory，再去spring中注入
 */
@SuppressWarnings("unchecked")
public class CommentDao extends HibernateDaoSupport{

	/*保存评价的持久层方法*/
	public void save(Comment comment) {
		this.getHibernateTemplate().save(comment);
	}

	/*根据商品的id查询该商品评价的总数量*/
	public int findCountByPid(int pid) {
		String hql = "select count(*) from Comment c where c.product.pid=?";
		List<Long> list = this.getHibernateTemplate().find(hql,pid);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}

	/*根据商品的id查询该商品的集合*/
	public List<Comment> findByPagePid(int pid, int begin, int limit) {
		String hql = "select c from Comment c join c.product p where p.pid=? order by c.cdate desc";
		List<Comment> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Comment>(hql,new Object[]{pid},begin,limit));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}

	/*根据商品的id查询该商品好评的总数量*/
	public int findGoodCount(Integer pid) {
		String hql = "select count(*) from Comment c where c.product.pid=? and c.type=1";
		List<Long> list = this.getHibernateTemplate().find(hql,pid);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}
	/*根据商品的id查询该商品好评的集合*/
	public List<Comment> findGoodByPagePid(int pid, int begin, int limit) {
		String hql = "select c from Comment c join c.product p where c.type=1 and p.pid=? order by c.cdate desc";
		List<Comment> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Comment>(hql,new Object[]{pid},begin,limit));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}
	
	/*根据商品的id查询该商品差评的总数量*/
	public int findBadCount(Integer pid) {
		String hql = "select count(*) from Comment c where c.product.pid=? and c.type=3";
		List<Long> list = this.getHibernateTemplate().find(hql,pid);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}
	/*根据商品的id查询该商品差评的集合*/
	public List<Comment> findBadByPagePid(int pid, int begin, int limit) {
		String hql = "select c from Comment c join c.product p where c.type=3 and p.pid=? order by c.cdate desc";
		List<Comment> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Comment>(hql,new Object[]{pid},begin,limit));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}

}

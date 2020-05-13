package com.weiwei.shopping.comment.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.weiwei.shopping.comment.dao.CommentDao;
import com.weiwei.shopping.comment.vo.Comment;
import com.weiwei.shopping.utils.PageBean;

/**
 * 商品评价模块的业务层代码
 * @author 潘唯
 *
 */
@Transactional
public class CommentService {
	/*注入CommentDao*/
	private CommentDao commentDao;
	public void setCommentDao(CommentDao commentDao) {
		this.commentDao = commentDao;
	}
	
	/*保存评价的业务层方法*/
	public void save(Comment comment) {
		commentDao.save(comment);
	}

	/*分页查询该商品所有评价的业务层方法*/
	public PageBean<Comment> findPageByPid(int pid, int page) {
		PageBean<Comment> pageBean = new PageBean<Comment>();
		//设置当前页
		pageBean.setPage(page);
		//设置每页显示的记录数:8条评论；
		int limit = 8;
		pageBean.setLimit(limit);
		//设置总记录数
		int totalCount = 0;
		totalCount = commentDao.findCountByPid(pid);
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
		List<Comment> list = commentDao.findByPagePid(pid,begin,limit);
		pageBean.setList(list);
		
		return pageBean;
	}

	/*根据商品pid分页查询该商品所有好评的业务层方法*/
	public PageBean<Comment> findGoodPageByPid(Integer pid, int page) {
		PageBean<Comment> pageBean = new PageBean<Comment>();
		//设置当前页
		pageBean.setPage(page);
		//设置每页显示的记录数:8条评论；
		int limit = 8;
		pageBean.setLimit(limit);
		//设置总记录数
		int totalCount = 0;
		totalCount = commentDao.findGoodCount(pid);
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
		List<Comment> list = commentDao.findGoodByPagePid(pid,begin,limit);
		pageBean.setList(list);
		
		return pageBean;
	}

	/*根据商品pid分页查询该商品所有差评的业务层方法*/
	public PageBean<Comment> findBadPageByPid(Integer pid, int page) {
		PageBean<Comment> pageBean = new PageBean<Comment>();
		//设置当前页
		pageBean.setPage(page);
		//设置每页显示的记录数:8条评论；
		int limit = 8;
		pageBean.setLimit(limit);
		//设置总记录数
		int totalCount = 0;
		totalCount = commentDao.findBadCount(pid);
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
		List<Comment> list = commentDao.findBadByPagePid(pid,begin,limit);
		pageBean.setList(list);
		
		return pageBean;
	}
	
}

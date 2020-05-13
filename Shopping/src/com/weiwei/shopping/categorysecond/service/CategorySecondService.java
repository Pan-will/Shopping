package com.weiwei.shopping.categorysecond.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.weiwei.shopping.categorysecond.dao.CategorySecondDao;
import com.weiwei.shopping.categorysecond.vo.CategorySecond;
import com.weiwei.shopping.utils.PageBean;

/**
 * 二级分类管理的业务层代码
 * @author 潘唯
 *
 */
/*添加事务管理*/
@Transactional
public class CategorySecondService {
	/*注入CategorySecondDao*/
	private CategorySecondDao categorySecondDao;
	public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
		this.categorySecondDao = categorySecondDao;
	}
	
	/*分页查询二级分类的业务层方法*/
	public PageBean<CategorySecond> findAllByPage(Integer page) {
		PageBean<CategorySecond> pageBean = new PageBean<CategorySecond>();
		pageBean.setPage(page);
		//设置每页显示的记录数
		Integer limit = 10;
		pageBean.setLimit(limit);
		//设置总条数
		Integer totalCount = categorySecondDao.findCount();
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
		List<CategorySecond> list = categorySecondDao.findPage(begin, limit);
		pageBean.setList(list);
		
		return pageBean;
	}

	/*添加二级分类的业务层方法*/
	public void save(CategorySecond categorySecond) {
		categorySecondDao.save(categorySecond);
		
	}

	/*根据二级分类的csid查询该二级分类的业务层方法*/
	public CategorySecond findByCsid(Integer csid) {
		return categorySecondDao.findByCsid(csid);
	}

	/*删除二级分类的业务层代码*/
	public void delete(CategorySecond categorySecond) {
		categorySecondDao.delete(categorySecond);
	}

	/*修改二级分类的业务层代码*/
	public void update(CategorySecond categorySecond) {
		categorySecondDao.update(categorySecond);
	}

	/*查询所有二级分类的业务层代码*/
	public List<CategorySecond> findAll() {
		return categorySecondDao.findAll();
	}
	
}

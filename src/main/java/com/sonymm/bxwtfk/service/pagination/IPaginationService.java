package com.sonymm.bxwtfk.service.pagination;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

/**
 * 分页查询
 */
public interface IPaginationService {
	/**
	 * 获取数据总数
	 * @param sql 
	 * @return 数据总数
	 * @throws Exception
	 */
	public Integer getDataCount(String sql) throws Exception;
	
	/**
	 * 获取分页
	 * @param sql
	 * @param pageRequest
	 * @return PageImpl
	 * @throws Exception
	 */
	public PageImpl getPageImpl(String sql,PageRequest pageRequest,Class javaBean) throws Exception;
	
	/**
	 * 获取分页
	 * @param sql
	 * @param pageNum
	 * @param pageSize 
	 * @param order 排序语句 例：draftTime DESC
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> executeQuery(String sql,
            int pageNum, int pageSize,String order) throws Exception;
	
	/**
	 * 获取分页数据
	 * @param sql
	 * @param pageRequest
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getPaginationData(String sql,
			PageRequest pageRequest) throws Exception;
	
	 /**
	   * 分页查询(Bean类型数据)
   * @param sql
   *  sql语句
   * @param pageRequest
   * @param javaBean
   * 分页的数据类型
   * @return 
   * List集合（Bean类型）
   * @throws Exception
   */
	public List getPaginationBeans(String sql, PageRequest pageRequest,Class javaBean) 
			throws Exception;
	 /**
	   * 分页查询(Bean类型数据)
 * @param sql
 *  sql语句
 * @param pageRequest
 * @param javaBean
 * 分页的数据类型
 * @return 
 *
 * @throws Exception
 */
	public List<Map<String,Object>> getPaginationBeans(String sql, PageRequest pageRequest) 
			throws Exception;
}

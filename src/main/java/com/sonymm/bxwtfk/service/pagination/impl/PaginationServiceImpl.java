package com.sonymm.bxwtfk.service.pagination.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.sonymm.bxwtfk.service.pagination.IPaginationService;
import com.sonymm.bxwtfk.util.Convert;

@Service
public class PaginationServiceImpl implements IPaginationService{
	@Autowired
    private JdbcTemplate jdbc;

    /**
     * 执行带分页的数据库查询
     * @param sql语句/对应表名
     * @param pageRequest
     * @return
     * List集合（map类型）
     * @throws Exception
     */
    public List<Map<String, Object>> getPaginationData(String sql,
            PageRequest pageRequest) throws Exception {
        StringBuffer dirSql = new StringBuffer();
        Sort sort = pageRequest.getSort(); //排序字段
        Integer pageNumber = pageRequest.getPageNumber(); //当前页数
        Integer pageSize = pageRequest.getPageSize(); //每页条数
        if (null != sort) {
            Iterator<Order> it = sort.iterator();
            //排序字段处理
            while (it.hasNext()) {
                Order order = it.next();
                dirSql.append(order.getProperty() + " "
                        + order.getDirection().name()+",");
            }
            dirSql =new StringBuffer( dirSql.substring(0, dirSql.length()-1));
        }else{
        	dirSql.append("RowNum");
        }
        return executeQuery(sql, pageNumber, pageSize, dirSql.toString());
    }

    /**
     * 执行带分页的数据库查询
     * @param sql sql语句/对应表名
     * @param pageNum 开始于
     * @param pageSize 每页个数
     * @param order 排序语句 例：draftTime DESC
     * @return
     * List集合（map类型）
     */
    public List<Map<String, Object>> executeQuery(String sql, int pageNum,
            int pageSize, String order) throws Exception {

        //因为是oracle只考虑oracle的分页。
        StringBuffer sqlStr = new StringBuffer();
        sqlStr.append("select * from (");
        sqlStr.append(" select t1.*,RowNum rn from ( ");
        sqlStr.append(" select * from ( ");
        sqlStr.append(sql);
        sqlStr.append(") order by " + order);
        sqlStr.append(")t1 where RowNum <= " + ((pageNum + 1) * pageSize));
        sqlStr.append(") where rn > " + (pageNum * pageSize));
        //sqlStr.append(" order by " + order);

        return jdbc.queryForList(sqlStr.toString());
    }

    /**
     * 查询到的数据总数
     * @param sql语句/对应表名 
     * @return
     * Integer
     * @throws Exception
     */
    public Integer getDataCount(String sql) throws Exception {
        return Integer.parseInt(jdbc
                .queryForList("select count(*) as count from (" + sql + ")")
                .get(0).get("count").toString());
    }

    /**
     * 获取分页数据
     * @param sql语句/对应表名
     *  sql语句
     * @param pageRequest
     * @param javaBean
     * 分页的数据类型
     * @return 
     * PageImpl
     * @throws Exception
     */
    public PageImpl getPageImpl(String sql, PageRequest pageRequest,
            Class javaBean) throws Exception {
        return new PageImpl(getPaginationBeans(sql, pageRequest, javaBean),
                pageRequest, getDataCount(sql));
    }

    /**
     * 分页查询(Bean类型数据)
    * @param sql语句/对应表名
    *  sql语句
    * @param pageRequest
    * @param javaBean
    * 分页的数据类型
    * @return 
    * List集合（Bean类型）
    * @throws Exception
    */
    public List getPaginationBeans(String sql, PageRequest pageRequest,
            Class javaBean) throws Exception {
        List<Map<String, Object>> map_list = getPaginationData(sql, pageRequest);
        List bean_list = new ArrayList();
        for (Map<String, Object> bean : map_list) {
            bean_list.add(Convert.map2bean(javaBean, bean));
        }
        return bean_list;
    }
    /**
     * 分页查询(Bean类型数据)
    * @param sql语句/对应表名
    *  sql语句
    * @param pageRequest
    * @param javaBean
    * 分页的数据类型
    * @return 
    * List集合（Bean类型）
    * @throws Exception
    */
    public List<Map<String,Object>> getPaginationBeans(String sql, PageRequest pageRequest
            ) throws Exception {
        List<Map<String, Object>> map_list = getPaginationData(sql, pageRequest);
//        List bean_list = new ArrayList();
//        for (Map<String, Object> bean : map_list) {
//            bean_list.add(Convert.map2bean(javaBean, bean));
//        }
        return map_list;
    }
}

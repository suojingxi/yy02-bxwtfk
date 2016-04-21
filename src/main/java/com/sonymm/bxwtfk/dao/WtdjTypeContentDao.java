package com.sonymm.bxwtfk.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sonymm.bxwtfk.bean.BXWTFK_WTDJTYPECONTENT;

/**
 * @ClassName: WtdjTypeContentDao
 * @Description: 问题单据类型内容表Dao
 * @author suojx
 * @date 2016年04月12日 下午22:56:47
*/
public interface WtdjTypeContentDao extends PagingAndSortingRepository<BXWTFK_WTDJTYPECONTENT, Serializable>, JpaSpecificationExecutor<BXWTFK_WTDJTYPECONTENT>{

}

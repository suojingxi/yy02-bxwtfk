package com.sonymm.bxwtfk.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sonymm.bxwtfk.bean.BXWTFK_SENDCONTENT;


/**
 * @ClassName: SendContentDao
 * @Description: 发送内容表Dao
 * @author suojx
 * @date 2016年04月14日 上午09:01:47
*/
public interface SendContentDao extends PagingAndSortingRepository<BXWTFK_SENDCONTENT, Serializable>, JpaSpecificationExecutor<BXWTFK_SENDCONTENT>{

}

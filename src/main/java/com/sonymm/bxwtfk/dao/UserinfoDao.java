package com.sonymm.bxwtfk.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sonymm.bxwtfk.bean.BXWTFK_USERINFO;

/**
 * @ClassName: UserinfoDao
 * @Description: 人员信息表Dao
 * @author suojx
 * @date 2016年04月12日 下午22:56:47
*/
public interface UserinfoDao extends PagingAndSortingRepository<BXWTFK_USERINFO, Serializable>, JpaSpecificationExecutor<BXWTFK_USERINFO>{

}

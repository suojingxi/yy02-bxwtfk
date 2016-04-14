package com.sonymm.bxwtfk.service;

import java.util.List;
import java.util.Map;

import com.sonymm.bxwtfk.bean.BXWTFK_USERINFO;

/**
 * @ClassName: IUserinfoService
 * @Description: 人员信息表 操作
 * @author suojx
 * @date 2016年04月12日 下午23:02:47
*/
public interface IUserinfoService {
	/**
     * 查询表中所有人员
     * @param 
     * @return List<BXWTFK_USERINFO>
     */
    public List<Map<String, Object>> getAllUserinfo() throws Exception;
    
    /**
     * 通过系统userid判断该人员是否在数据库中
     * @param userid
     * @return Map<String, Object> 返回获取的数据
     */
    public Map<String, Object> getUserinfo(String userid) throws Exception;
    
    /**
     * 通过系统userinfoid判断该人员是否在数据库中
     * @param userinfoid
     * @return Map<String, Object> 返回获取的数据
     */
    public Map<String, Object> getUserinfoById(String userinfoid) throws Exception;
    
    /**
     * 将当前登录人信息插入到数据库中
     * @param save_auth_map 
     * @return Map<String, Object> 返回插入的数据
     */
    public Map<String, Object> addUserinfo(Map<String, Object> save_auth_map) throws Exception;
}

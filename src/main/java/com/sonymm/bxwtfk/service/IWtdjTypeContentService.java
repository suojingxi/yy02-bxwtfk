package com.sonymm.bxwtfk.service;

/**
 * @ClassName: IWtdjTypeContentService
 * @Description: 问题单据类型内容表 操作
 * @author suojx
 * @date 2016年04月12日 下午23:02:47
*/
public interface IWtdjTypeContentService {
	/**
     * 根据问题类型代码获取问题类型名称
     * @param 
     * @return String
     */
	public String getContentNameByCode(String code) throws Exception;
}

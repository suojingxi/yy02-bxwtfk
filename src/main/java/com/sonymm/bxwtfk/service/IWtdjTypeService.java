package com.sonymm.bxwtfk.service;

/**
 * @ClassName: IWtdjTypeService
 * @Description: 问题单据类型表 操作
 * @author suojx
 * @date 2016年04月12日 下午23:02:47
*/
public interface IWtdjTypeService {
	/**
     * 根据问题类型代码获取问题类型名称
     * @param 
     * @return String
     */
	public String getNameByCode(String code) throws Exception;
	
	/**
     * 根据问题类型代码串获取问题类型名称串
     * @param 
     * @return String
     */
	public String getNamesByCodes(String codes) throws Exception;
}

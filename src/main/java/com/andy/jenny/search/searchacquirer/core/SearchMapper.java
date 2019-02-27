package com.andy.jenny.search.searchacquirer.core;

import java.util.List;
import java.util.Map;

public interface SearchMapper {
	
	/**
	 * 记录检索
	 * 
	 * @param map
	 *            检索条件
	 * @return 记录列表
	 */
	List<Map<String, Object>> listRecords(Map<?, ?> map);
	
	/**
     * 条件查询结果总数
     * @param map 检索条件
     * @return 记录列表
     */
    String listRecordsNum(Map<?, ?> map);
    
    /**
     * 获取详情
     * @param id
     * @return
     */
    Map<String, Object> getDetails(String id);
    
    /**
     * 数据库记录总数
     */
    String recordsNum();
}

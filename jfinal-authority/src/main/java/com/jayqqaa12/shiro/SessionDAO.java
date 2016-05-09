package com.jayqqaa12.shiro;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;

/***
 * 用redis 实现 分布式缓存
 * 
 * @author 12
 * 
 */
public class SessionDAO extends EnterpriseCacheSessionDAO
{

	public static  SessionDAO me;
	
	@Override
	public void setCacheManager(CacheManager cacheManager) {
		super.setCacheManager(cacheManager);
		
		me=this;
	}

	 
 
	
}

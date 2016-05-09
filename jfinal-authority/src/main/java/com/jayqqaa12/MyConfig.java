package com.jayqqaa12;

import com.jayqqaa12.exception.MyJsonExceptionIntercepter;
import com.jayqqaa12.jbase.jfinal.ext.JbaseConfig;
import com.jayqqaa12.model._MappingKit;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.Plugins;
import com.jfinal.core.JFinal;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.ext.interceptor.SessionInViewInterceptor;

/**
 * API引导式配置
 */
public class MyConfig extends JbaseConfig {

	/**
	 * 配置插件
	 */
	public void configPlugin(Plugins me) {

		addEhCachePlugin(me);
		addShiroPlugin(me);
		_MappingKit.mapping(addActiveRecordPlugin(me, addDruidPlugin(me)));

	}

	/**
	 * 配置全局拦截器
	 */
	public void configInterceptor(Interceptors me) {
		super.configInterceptor(me);

		me.add(new com.jayqqaa12.shiro.ShiroInterceptor());
		// 让 模版 可以使用session
		me.add(new SessionInViewInterceptor());
		
		me.add(new MyJsonExceptionIntercepter());
		// 对 api 接口进行 oauth2 认证。
		// me.add(new OauthIntercepter("/api/"));
	}

	/**
	 * 配置处理器
	 */
	public void configHandler(Handlers me) {
		super.configHandler(me);
		// xss 过滤
		// me.add(new XssHandler("s"));
		// 伪静态处理
		// me.add(new FakeStaticHandler());
		// 去掉 jsessionid 防止找不到action
		me.add(new com.jayqqaa12.shiro.SessionHandler());
		me.add(new ContextPathHandler());
		// me.add(new ACAOlHandler("*"));
	}

	/**
	 * 运行此 main 方法可以启动项目，此main方法可以放置在任意的Class类定义中，不一定要放于此
	 */
	public static void main(String[] args) {

		JFinal.start("src/main/webapp", 8888, "/", 6);
	}

}

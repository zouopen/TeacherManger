package com.evlisay.schoolservices.config;

import com.evlisay.schoolservices.Filter.ShiroLoginFilter;
import com.evlisay.schoolservices.constant.CookieConstant;
import com.evlisay.schoolservices.constant.ShiroConstant;
import com.evlisay.schoolservices.shiro.ShiroReaml;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: EvilSay
 * @Date: 2019/7/10 18:33
 */
@Configuration
public class ShiroConfig {
    private static final long MILLIS_PER_SECOND = 1000;
    private static final long MILLIS_PER_MINUTE = 60 * MILLIS_PER_SECOND;
    private static final long MILLIS_PER_HOUR = 60 * MILLIS_PER_MINUTE;


    @Bean
    public CacheManager cacheManager(){
        EhCacheManager cacheManager = new EhCacheManager();
        cacheManager.setCacheManagerConfigFile("classpath:config/ehcache.xml");
        return cacheManager;
    }
    @Bean
    public ShiroReaml getShiroReaml(){ return new ShiroReaml(); }



    /**
     * 自定义cookie管理器
     * @return
     */
    @Bean
    public SimpleCookie simpleCookie(){
        SimpleCookie simpleCookie = new SimpleCookie();
        simpleCookie.setName(CookieConstant.TOKEN);
        simpleCookie.setMaxAge(CookieConstant.EXPIRE);
        return simpleCookie;
    }
    /**
     * <p>session管理器</p>
     *
     * @return
     */
    @Bean("sessionManager")
    public DefaultWebSessionManager defaultSessionManager(){
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
//        全局会话超时时间
        sessionManager.setGlobalSessionTimeout(3 * MILLIS_PER_HOUR);
//        是否定时检查过期session
        sessionManager.setSessionValidationSchedulerEnabled(true);
//        是否开启删除无效的session对象
        sessionManager.setDeleteInvalidSessions(true);
//        设置session失效的扫描时间, 清理用户直接关闭浏览器造成的孤立会话 默认为 1个小时
//        设置该属性 就不需要设置 ExecutorServiceSessionValidationScheduler 底层也是默认自动调用ExecutorServiceSessionValidationScheduler
        sessionManager.setSessionValidationInterval(MILLIS_PER_HOUR);
//        设置会话ID
        sessionManager.setSessionIdCookie(simpleCookie());
//        取消URL后面的后缀
        sessionManager.setSessionIdUrlRewritingEnabled(true);

        return sessionManager;
    }
    /**
     * <p>安全管理器</p>
     * @return
     */
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(){

        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();

        manager.setRealm(getShiroReaml());

        manager.setCacheManager(cacheManager());

        manager.setSessionManager(defaultSessionManager());

        return manager;
    }
    @Bean
    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);

        shiroFilter.setSuccessUrl("/index");

        Map<String, Filter> filters = new HashMap<>();
        //匿名过滤器
        filters.put(ShiroConstant.anon, new AnonymousFilter());
        //表单过滤器
        filters.put(ShiroConstant.authc, new FormAuthenticationFilter());
        //注销过滤器
        filters.put(ShiroConstant.logout, new LogoutFilter());
        //角色权限过滤器
        filters.put(ShiroConstant.role, new RolesAuthorizationFilter());
        //用户过滤器
        filters.put(ShiroConstant.user, new UserFilter());
        //设置过滤器
        shiroFilter.setFilters(filters);

        Map<String, String> chains = new LinkedHashMap<>();
        /**
         * 登录不拦截
         */
        chains.put("/user/login",ShiroConstant.anon);

        chains.put("/user/checkcode",ShiroConstant.anon);

        chains.put("/**",ShiroConstant.authc);

        shiroFilter.setFilterChainDefinitionMap(chains);

        return shiroFilter;
    }

    /**
     * <p>保证实现了Shiro内部lifecycle函数的bean执行</p>
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
    //自定义拦截器
    @Bean
    public ShiroLoginFilter shiroLoginFilter (){return new ShiroLoginFilter();}
}

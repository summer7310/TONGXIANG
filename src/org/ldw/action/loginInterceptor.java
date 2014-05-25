package org.ldw.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/*
 * 一般页面拦截器
 */
public class loginInterceptor extends AbstractInterceptor{
	public String intercept(ActionInvocation actionInvocation) throws Exception
    {
            Map session = actionInvocation.getInvocationContext().getSession();
       
            if(null == session.get("userInfo")){
            	return "unlogin";
            }
            return actionInvocation.invoke();               
    }
}

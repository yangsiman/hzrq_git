package com.hzvanke.report.controller;

import com.hzvanke.report.common.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private HttpSession session;
    /**
     * 该方法将在请求处理之前进行调用，只有该方法返回true，才会继续执行后续的Interceptor和Controller，
     * 当返回值为true 时就会继续调用下一个Interceptor的preHandle 方法，
     * 如果已经是最后一个Interceptor的时候就会是调用当前请求的Controller方法；
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // 这里可以根据session的用户来判断角色的权限，根据权限来转发不同的页面
        if (session.getAttribute(AppUtil.SESSION_SYSUSER) == null) {
            //request.getRequestDispatcher("/index.jsp").forward(request, response);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter()
                    .write("<script>alert('您还未登陆或登陆已超时，请重新登陆！');top.location.href='/index.jsp'</script>");
            return false;
        }
        return true;
    }

    /**
     * 该方法将在请求处理之后，DispatcherServlet进行视图返回渲染之前进行调用，
     * 可以在这个方法中对Controller 处理之后的ModelAndView 对象进行操作。
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub

    }

    /**
     * 该方法也是需要当前对应的Interceptor的preHandle方法的返回值为true时才会执行，该方法将在整个请求结束之后，
     * 也就是在DispatcherServlet 渲染了对应的视图之后执行。用于进行资源清理。
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub

    }
}

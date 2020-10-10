package cn.qrq.springboot.component;

import cn.qrq.config.Result;
import com.google.gson.Gson;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//登录拦截器,没有登录的用户，就不能访问后台的主页
@Configuration
public class LoginHandllerInterceptor implements HandlerInterceptor{
    //目标执行之前的预检查
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setCharacterEncoding("utf-8");
        Object user = request.getSession().getAttribute("user");
        if (user == null){
//            未登录
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().println(new Gson().toJson(new Result(403, "请先登录", null)));
            return false;
        }else{
//            放行请求
            return true;
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }
}

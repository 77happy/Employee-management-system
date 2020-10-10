package cn.qrq.Controller;

import cn.qrq.config.Result;
import cn.qrq.springboot.param.LoginParam;
import com.sun.deploy.net.HttpRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

//处理登录请求
@RestController
@RequestMapping("user")
@Api("用户板块")
public class LoginController {

    @PostMapping(value = "/login")//处理的请求地址
    @ApiOperation("用户登录")
    public Result login(HttpServletRequest request, @RequestBody LoginParam loginParam){
        if("admin".equals(loginParam.getUserName())&&"123456".equals(loginParam.getPassWord())){
            request.getSession().setAttribute("user",loginParam);
            return new Result(200,"登录成功",null);
        }else {
            return new Result(500,"用户名或者密码错误",null);
        }

    }
@GetMapping("/getuser")
@ApiOperation("获取用户信息")
public Result getUser(HttpServletRequest request) {
    LoginParam loginParam=(LoginParam)request.getSession().getAttribute("user");
    if(null==loginParam){
        return new Result(500,"请先登录",null);
    }
    return new Result(200,"",loginParam);
 }
    @PostMapping("/exit")
    @ApiOperation("退出")
    public Result exit(HttpServletRequest request) {
        request.getSession().setAttribute("user",null);
        return new Result(200,"退出成功",null);
    }
}

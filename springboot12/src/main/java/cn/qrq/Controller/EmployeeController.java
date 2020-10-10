package cn.qrq.Controller;

import cn.qrq.Log.MyLog;
import cn.qrq.config.Result;
import cn.qrq.mapper.EmployeeMapper;
import cn.qrq.springboot.entities.Employee;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(description = "用户接口")
public class EmployeeController {
    @Autowired
    private EmployeeMapper userMapper;

    @MyLog(value = "查询记录")  //这里添加了AOP的自定义注解
    @ApiOperation(value = "获取所有用户信息" ,  notes = "返回所有用户信息")
    @RequestMapping(value = "/getUsers", method=RequestMethod.GET)
    public List<Employee> getUsers() {
        List<Employee> employees=userMapper.selectAll();
        return employees;
    }
    @MyLog(value = "新增记录")  //这里添加了AOP的自定义注解
    @ApiOperation(value = "新增用户" ,  notes = "注册")
    @RequestMapping(value = "/add", method=RequestMethod.POST)
    public Result save(@RequestBody Employee employee) {
        userMapper.insert(employee);
        return new Result(200,"插入成功！",null);
    }
//
//    @ApiOperation(value = "根据ID获取用户信息" ,  notes = "返回单个用户信息")
//    @RequestMapping(value = "/getUser", method=RequestMethod.GET)
//    public Employee getUser(Integer id) {
//        Employee user=userMapper.selectByPrimaryKey(id);
//        return user;
//    }
//

//
    @MyLog(value = "更新记录")  //这里添加了AOP的自定义注解
    @ApiOperation(value = "更新用户信息" ,  notes = "更改用户信息")
    @RequestMapping(value = "/update", method=RequestMethod.POST)
    public void update(@RequestBody  Employee employee) {
        userMapper.updateByPrimaryKey(employee);
    }

    @ApiOperation(value = "根据ID删除用户" ,  notes = "删除用户信息")
    @MyLog(value = "删除记录")  //这里添加了AOP的自定义注解
    @RequestMapping(value = "/delete/{id}", method=RequestMethod.DELETE)
    public void delete(@PathVariable("id") Integer id) {
        userMapper.deleteByPrimaryKey(id);
    }

}

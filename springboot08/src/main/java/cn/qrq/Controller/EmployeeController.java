package cn.qrq.Controller;

import cn.qrq.mapper.EmployeeMapper;
import cn.qrq.springboot.entities.Employee;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(description = "用户接口")
public class EmployeeController {
    @Autowired
    private EmployeeMapper userMapper;

    @ApiOperation(value = "获取所有用户信息" ,  notes = "返回所有用户信息")
    @RequestMapping(value = "/getUsers", method=RequestMethod.GET)
    public List<Employee> getUsers() {
        List<Employee> employees=userMapper.selectAll();
        return employees;
    }
    @ApiOperation(value = "新增用户" ,  notes = "注册")
    @RequestMapping(value = "/add", method=RequestMethod.POST)
    public void save(Employee employee) {
        userMapper.insert(employee);
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
//    @ApiOperation(value = "更新用户信息" ,  notes = "更改用户信息")
//    @RequestMapping(value = "update", method=RequestMethod.PUT)
//    public void update(User user) {
//        userMapper.updateByPrimaryKey(user);
//    }
//
//    @ApiOperation(value = "根据ID删除用户" ,  notes = "删除用户信息")
//    @RequestMapping(value = "/delete/{id}", method=RequestMethod.DELETE)
//    public void delete(@PathVariable("id") Integer id) {
//        userMapper.deleteByPrimaryKey(id);
//    }

}

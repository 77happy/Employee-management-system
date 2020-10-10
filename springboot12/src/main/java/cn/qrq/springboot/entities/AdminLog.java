package cn.qrq.springboot.entities;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class AdminLog implements Serializable {

    private Integer id;
    private String username; //用户名
    private String operation; //操作

    private Date createDate; //操作时间

}

package cn.qrq.config;

import lombok.Data;

@Data
public class Result {
    public Result(Integer code ,String describle,Object data){
        this.code=code;
        this.describle=describle;
        this.data=data;
    }
    private Integer code;
    private String describle;
    private Object data;
}

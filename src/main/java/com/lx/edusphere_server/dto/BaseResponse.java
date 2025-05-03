package com.lx.edusphere_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse {
    private String msg;
    private String code;
    
    public static BaseResponse success(String msg) {
        return new BaseResponse(msg, "success");
    }
    
    public static BaseResponse error(String msg) {
        return new BaseResponse(msg, "Error");
    }
} 
package com.lx.edusphere_server.mapper;

import com.lx.edusphere_server.entity.VerificationCode;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface VerificationCodeMapper {
    VerificationCode findByEmailAndPurpose(String code_email, String code_purpose);
    int saveVerificationCode(VerificationCode verificationCode);
    int updateVerificationCode(VerificationCode verificationCode);
}
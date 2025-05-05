package com.lx.edusphere_server.mapper;

import com.lx.edusphere_server.entity.VerificationCode;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface VerificationCodeMapper {
    int insert(VerificationCode verificationCode);
    int update(VerificationCode verificationCode);
    VerificationCode selectById(Long id);
    VerificationCode findByEmailAndPurpose(String email, String purpose);


    default Optional<VerificationCode> findByEmailAndPurposeOptional(String email, String purpose) {
        return Optional.ofNullable(findByEmailAndPurpose(email, purpose));
    }
    default VerificationCode save(VerificationCode verificationCode) {
        if (verificationCode.getId() == null) {
            insert(verificationCode);
        } else {
            update(verificationCode);
        }
        return verificationCode;
    }
}
package com.example.demo.controller;

import com.example.demo.common.code.model.CommonCode;
import com.example.demo.common.code.mapper.CommonCodeMapper;
import com.example.demo.common.code.enums.PaymentStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final CommonCodeMapper commonCodeMapper;

    // 전체 공통코드 조회 (DB 데이터)
    @GetMapping("/codes")
    public List<CommonCode> getAllCodes() {
        return commonCodeMapper.findAll();
    }

    // enum + DB 연동 결과 확인
    @GetMapping("/payment-status")
    public Map<String, Object> getPaymentStatus() {
        Map<String, Object> result = new LinkedHashMap<>();

        for (PaymentStatus status : PaymentStatus.values()) {
            Map<String, String> detail = new LinkedHashMap<>();
            detail.put("codeValue", status.getCodeValue());
            detail.put("description", status.getDescription());
            result.put(status.name(), detail);
        }

        return result;
    }
}

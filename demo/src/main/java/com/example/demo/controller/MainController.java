package com.example.demo.controller;

import com.example.demo.common.code.model.CommonCode;
import com.example.demo.common.code.mapper.CommonCodeMapper;
import com.example.demo.common.code.enums.PaymentStatus;
import com.example.demo.cte.CteCode;
import com.example.demo.cte.CteCodeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final CommonCodeMapper commonCodeMapper;
    private final CteCodeMapper cteCodeMapper;

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

    // CTE: 전체 카테고리 트리 조회
    @GetMapping("/categories")
    public List<CteCode> getAllCategories() {
        return cteCodeMapper.findAll();
    }

    // CTE: 특정 카테고리의 하위 카테고리 조회
    @GetMapping("/categories/{id}/descendants")
    public List<CteCode> getDescendants(@PathVariable("id") int id) {
        return cteCodeMapper.findDescendants(id);
    }

    // CTE: 특정 카테고리의 상위 카테고리 조회
    @GetMapping("/categories/{id}/ancestors")
    public List<CteCode> getAncestors(@PathVariable("id") int id) {
        return cteCodeMapper.findAncestors(id);
    }
}

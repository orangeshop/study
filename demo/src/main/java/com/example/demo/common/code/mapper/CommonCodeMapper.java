package com.example.demo.common.code.mapper;

import com.example.demo.common.code.model.CommonCode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommonCodeMapper {

    List<CommonCode> findAll();

    List<CommonCode> findByGroupCode(@Param("groupCode") String groupCode);
}

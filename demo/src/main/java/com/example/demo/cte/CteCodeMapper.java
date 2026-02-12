package com.example.demo.cte;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CteCodeMapper {

    List<CteCode> findAll();

    List<CteCode> findDescendants(@Param("categoryId") int categoryId);

    List<CteCode> findAncestors(@Param("categoryId") int categoryId);
}

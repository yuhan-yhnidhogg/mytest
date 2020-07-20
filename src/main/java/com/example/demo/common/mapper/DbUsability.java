package com.example.demo.common.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DbUsability {

    @Select("select 1 from dual")
    public String selectDual();
}

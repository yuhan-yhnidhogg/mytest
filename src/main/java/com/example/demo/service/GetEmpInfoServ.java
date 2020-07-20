package com.example.demo.service;

import com.example.demo.common.datasource.DataSourceType;
import com.example.demo.common.datasource.DynamicDataSourceContextHolder;
import com.example.demo.common.mapper.EmployeesMapper;
import com.example.demo.common.model.EmployeesModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GetEmpInfoServ {
    @Autowired
    private EmployeesMapper employeesMapper;

    public List<EmployeesModel> getAllEmployeesInfo(){
        return employeesMapper.selectAll();
    }

    public int getEmployeesNum() {
        DynamicDataSourceContextHolder.setDataSourceType(DataSourceType.DB2.name());
        return employeesMapper.countEmployees();
    }
}

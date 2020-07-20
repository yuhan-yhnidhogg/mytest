package com.example.demo.contorller;

import com.example.demo.common.datasource.DataSourceType;
import com.example.demo.common.datasource.DynamicDataSourceContextHolder;
import com.example.demo.common.mapper.DbUsability;
import com.example.demo.common.model.EmployeesModel;
import com.example.demo.service.GetEmpInfoServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private GetEmpInfoServ getEmpInfoServ;

    @RequestMapping("employee/getAllInfo")
    public List<EmployeesModel> getAllEmpInfo(){
        return getEmpInfoServ.getAllEmployeesInfo();
    }
    @RequestMapping("employee/cnt")
    public int getEmpNum(){
        return getEmpInfoServ.getEmployeesNum();
    }
    @Autowired
    private DbUsability dbUsability;
    @RequestMapping("employee/db")
    public String getDb(){
        return dbUsability.selectDual();

    }
}

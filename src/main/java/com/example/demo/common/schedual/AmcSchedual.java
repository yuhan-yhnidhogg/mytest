package com.example.demo.common.schedual;

import com.example.demo.common.datasource.DataSourceType;
import com.example.demo.common.datasource.DynamicDataSource;
import com.example.demo.common.datasource.DynamicDataSourceContextHolder;
import com.example.demo.common.mapper.DbUsability;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AmcSchedual {
    @Autowired
    private DbUsability dbUsability;
    @Scheduled(fixedRate = 30000)
    public void dbUsability(){
        String dbresult = dbUsability.selectDual();
        String dsType = DynamicDataSourceContextHolder.getDataSourceType();
        log.info("AmcSchedual#dbUsability() dstype:"+dsType);
        if (DataSourceType.DB1.name().equals(dsType)|| StringUtils.isBlank(dsType)){
            if("1".equals(dbresult)){
                log.info("AmcSchedual#dbUsability() DB1 connect success");
            }else{
                DynamicDataSourceContextHolder.setDataSourceType(DataSourceType.DB2.name(),1);
                dbresult = dbUsability.selectDual();
                if ("1".equals(dbresult)){
                    log.info("AmcSchedual#dbUsability() DB1 connect failure, DB2 connect success, ds change to DB2");
                    DynamicDataSourceContextHolder.setDataSourceType(DataSourceType.DB2.name());
                }else{
                    log.info("AmcSchedual#dbUsability() DB1 connect failure, DB2 connect failure, ds reset to DB1");
                    DynamicDataSourceContextHolder.resetDataSourceType();
                }
            }
        }else{
            DynamicDataSourceContextHolder.setDataSourceType(DataSourceType.DB1.name(),1);
            String dbresultMaster = dbUsability.selectDual();
            if ("1".equals(dbresultMaster)){
                log.info("AmcSchedual#dbUsability() DB1 connect success, reset to DB1");
                DynamicDataSourceContextHolder.resetDataSourceType();
            }else {
                if("1".equals(dbresult)){
                    log.info("AmcSchedual#dbUsability() DB1 connect failure, still to DB2");
                    DynamicDataSourceContextHolder.setDataSourceType(DataSourceType.DB2.name());
                }else {
                    log.info("AmcSchedual#dbUsability() DB1 connect failure, DB2 connect failure, ds reset to DB1");
                    DynamicDataSourceContextHolder.resetDataSourceType();
                }
            }
        }
    }
}

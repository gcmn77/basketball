package com.example.basketball.audit;

import com.example.basketball.domain.SysLog;
import com.example.basketball.repository.SysLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Component
public class AuditTrailListener {
    private static SysLogRepository sysLogRepository;

    @Autowired
    public void setSysLogRepository(SysLogRepository sysLogRepository) {
        this.sysLogRepository = sysLogRepository;
    }

    @PrePersist
    public void beforeCreate(Object object) {
        createSysLog(object, "CREATE");
    }

    @PreUpdate
    public void beforeUpdate(Object object)  {
        createSysLog(object, "UPDATE");
    }

    @PreRemove
    public void beforeRemove(Object object)  {
        createSysLog(object, "REMOVE");
    }

    public void createSysLog(Object object, String process) {
        SysLog sysLog = new SysLog();
        sysLog.setBody(object.toString());
        sysLog.setProcess(process);
        sysLog.setDomain(object.getClass().getSimpleName());
        sysLog.setModifiedAt(LocalDateTime.now());
        try {
            //There is no auth for initData. So ignore error for it.
            if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
                sysLog.setModifiedBy(SecurityContextHolder.getContext().getAuthentication().getName());
            }
        } catch (Exception e) {}
        sysLogRepository.save(sysLog);
    }
}

package com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.repository;
import java.time.LocalDateTime; import java.util.List; import org.springframework.data.jpa.repository.JpaRepository; import com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.entity.AuditLog;
public interface AuditLogRepository extends JpaRepository<AuditLog,Long>{List<AuditLog> findByActionContainingIgnoreCaseOrUserEmailContainingIgnoreCaseOrEntityTypeContainingIgnoreCase(String a,String u,String e); List<AuditLog> findByActionTimeBetween(LocalDateTime from,LocalDateTime to);}

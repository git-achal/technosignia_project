package com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name="audit_logs")
public class AuditLog {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 private String action;
 private String userEmail;
 private LocalDateTime actionTime;
 private String entityType;
 private Long entityId;
 private String details;
 public AuditLog(){}
 public AuditLog(String action,String userEmail,String entityType,Long entityId,String details){this.action=action;this.userEmail=userEmail;this.entityType=entityType;this.entityId=entityId;this.details=details;this.actionTime=LocalDateTime.now();}
 public Long getId(){return id;} public String getAction(){return action;} public String getUserEmail(){return userEmail;} public LocalDateTime getActionTime(){return actionTime;} public String getEntityType(){return entityType;} public Long getEntityId(){return entityId;} public String getDetails(){return details;}
 public void setId(Long v){id=v;} public void setAction(String v){action=v;} public void setUserEmail(String v){userEmail=v;} public void setActionTime(LocalDateTime v){actionTime=v;} public void setEntityType(String v){entityType=v;} public void setEntityId(Long v){entityId=v;} public void setDetails(String v){details=v;}
}

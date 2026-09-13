package com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity @Table(name="modification_requests")
public class ModificationRequest {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="contract_id") private Contract contract;
 @Lob private String requestedData; private String requestedBy; private String status="PENDING"; private LocalDateTime requestedAt; private LocalDateTime reviewedAt; private String reviewedBy;
 public ModificationRequest(){}
 public ModificationRequest(Contract c,String data,String by){contract=c;requestedData=data;requestedBy=by;requestedAt=LocalDateTime.now();}
 public Long getId(){return id;} public Contract getContract(){return contract;} public String getRequestedData(){return requestedData;} public String getRequestedBy(){return requestedBy;} public String getStatus(){return status;} public LocalDateTime getRequestedAt(){return requestedAt;} public LocalDateTime getReviewedAt(){return reviewedAt;} public String getReviewedBy(){return reviewedBy;}
 public void setStatus(String v){status=v;} public void setReviewedAt(LocalDateTime v){reviewedAt=v;} public void setReviewedBy(String v){reviewedBy=v;}
}

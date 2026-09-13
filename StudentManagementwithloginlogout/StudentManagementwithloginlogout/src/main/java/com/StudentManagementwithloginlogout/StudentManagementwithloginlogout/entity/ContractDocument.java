package com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity @Table(name="contract_documents")
public class ContractDocument {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="contract_id") private Contract contract;
 private String fileName; private String contentType; private long fileSize; private LocalDateTime uploadedAt; private String uploadedBy;
 @Lob @Basic(fetch=FetchType.LAZY) private byte[] data;
 public ContractDocument(){}
 public ContractDocument(Contract c,String n,String t,long s,byte[] d,String by){contract=c;fileName=n;contentType=t;fileSize=s;data=d;uploadedBy=by;uploadedAt=LocalDateTime.now();}
 public Long getId(){return id;} public Contract getContract(){return contract;} public String getFileName(){return fileName;} public String getContentType(){return contentType;} public long getFileSize(){return fileSize;} public LocalDateTime getUploadedAt(){return uploadedAt;} public String getUploadedBy(){return uploadedBy;} public byte[] getData(){return data;}
}

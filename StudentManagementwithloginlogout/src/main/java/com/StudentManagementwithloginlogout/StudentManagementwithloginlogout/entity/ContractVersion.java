package com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity @Table(name="contract_versions", uniqueConstraints=@UniqueConstraint(columnNames={"contract_id","version_number"}))
public class ContractVersion {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="contract_id") private Contract contract;
 private int versionNumber; private String contractName; private String description; private String partyName; private LocalDate startDate; private LocalDate endDate;
 @Enumerated(EnumType.STRING) private ContractStatus status; private String changedBy; private LocalDateTime changedAt;
 public ContractVersion(){}
 public ContractVersion(Contract c,int v,String by){contract=c;versionNumber=v;copy(c);changedBy=by;changedAt=LocalDateTime.now();}
 public void copy(Contract c){contractName=c.getContractName();description=c.getDescription();partyName=c.getPartyName();startDate=c.getStartDate();endDate=c.getEndDate();status=c.getStatus();}
 public Long getId(){return id;} public Contract getContract(){return contract;} public int getVersionNumber(){return versionNumber;} public String getContractName(){return contractName;} public String getDescription(){return description;} public String getPartyName(){return partyName;} public LocalDate getStartDate(){return startDate;} public LocalDate getEndDate(){return endDate;} public ContractStatus getStatus(){return status;} public String getChangedBy(){return changedBy;} public LocalDateTime getChangedAt(){return changedAt;}
 public void setId(Long v){id=v;} public void setContract(Contract v){contract=v;} public void setVersionNumber(int v){versionNumber=v;} public void setChangedBy(String v){changedBy=v;} public void setChangedAt(LocalDateTime v){changedAt=v;}
}

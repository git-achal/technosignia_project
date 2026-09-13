package com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.repository;
import java.util.List; import org.springframework.data.jpa.repository.JpaRepository; import com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.entity.ContractVersion;
public interface ContractVersionRepository extends JpaRepository<ContractVersion,Long>{List<ContractVersion> findByContractIdOrderByVersionNumberDesc(Long id); int countByContractId(Long id);}

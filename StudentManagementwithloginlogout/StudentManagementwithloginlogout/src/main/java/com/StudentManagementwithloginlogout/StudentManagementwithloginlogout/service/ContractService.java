package com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.service;

import java.util.List;

import com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.entity.Contract;

public interface ContractService {

    Contract addContract(Contract contract);

    List<Contract> getAllContracts();

    Contract getContractById(Long id);

    Contract updateContract(Long id, Contract contract);

    String deleteContract(Long id);
}

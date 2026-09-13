package com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.entity.Contract;
import com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.repository.ContractRepository;
import com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.service.ContractService;

@Service
public class ContractServiceImpl implements ContractService {

    @Autowired
    private ContractRepository contractRepository;

    @Override
    public Contract addContract(Contract contract) {

        return contractRepository.save(contract);
    }

    @Override
    public List<Contract> getAllContracts() {

        return contractRepository.findAll();
    }

    @Override
    public Contract getContractById(Long id) {

        return contractRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contract Not Found"));
    }

    @Override
    public Contract updateContract(Long id, Contract contract) {

        Contract existingContract = contractRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contract Not Found"));

        existingContract.setContractName(contract.getContractName());
        existingContract.setDescription(contract.getDescription());
        existingContract.setPartyName(contract.getPartyName());
        existingContract.setStartDate(contract.getStartDate());
        existingContract.setEndDate(contract.getEndDate());
        existingContract.setStatus(contract.getStatus());

        return contractRepository.save(existingContract);
    }

    @Override
    public String deleteContract(Long id) {

        Contract contract = contractRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contract Not Found"));

        contractRepository.delete(contract);

        return "Contract Deleted Successfully";
    }
}

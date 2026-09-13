package com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.version;
import java.util.List; import org.springframework.stereotype.Service; import org.springframework.beans.factory.annotation.Autowired; import com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.entity.*; import com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.repository.*; import jakarta.servlet.http.HttpSession;
@Service public class ContractVersionService { @Autowired ContractVersionRepository repo; @Autowired HttpSession session;
 public void snapshot(Contract c){Object u=session.getAttribute("loggedUser"); String by=u instanceof User?((User)u).getEmail():"SYSTEM"; int v=repo.countByContractId(c.getId())+1; repo.save(new ContractVersion(c,v,by));}
 public List<ContractVersion> history(Long id){return repo.findByContractIdOrderByVersionNumberDesc(id);}
}

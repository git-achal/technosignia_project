package com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.audit;
import java.time.LocalDateTime; import java.util.List; import org.springframework.stereotype.Service; import org.springframework.beans.factory.annotation.Autowired; import com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.entity.*; import com.StudentManagementwithloginlogout.StudentManagementwithloginlogout.repository.AuditLogRepository; import jakarta.servlet.http.HttpSession;
@Service public class AuditService { @Autowired AuditLogRepository repo; @Autowired HttpSession session;
 public void log(String action,String type,Long id,String details){Object u=session.getAttribute("loggedUser"); String email=u instanceof User ? ((User)u).getEmail() : "SYSTEM"; repo.save(new AuditLog(action,email,type,id,details));}
 public void logAs(String action,String email,String type,Long id,String details){repo.save(new AuditLog(action,email,type,id,details));}
 public List<AuditLog> search(String q){if(q==null||q.isBlank()) return repo.findAll(org.springframework.data.domain.Sort.by(org.springframework.data.domain.Sort.Direction.DESC,"actionTime")); return repo.findByActionContainingIgnoreCaseOrUserEmailContainingIgnoreCaseOrEntityTypeContainingIgnoreCase(q,q,q);}
 public List<AuditLog> all(){return repo.findAll(org.springframework.data.domain.Sort.by(org.springframework.data.domain.Sort.Direction.DESC,"actionTime"));}
}

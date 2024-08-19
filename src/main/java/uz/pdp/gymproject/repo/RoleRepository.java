package uz.pdp.gymproject.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.gymproject.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query(value = "select * from roles where role_name=? limit 1",nativeQuery = true)
    Role findByRoleName(String roleName);
}
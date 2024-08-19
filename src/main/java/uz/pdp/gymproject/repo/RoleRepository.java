package uz.pdp.gymproject.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.gymproject.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
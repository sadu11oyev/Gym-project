package uz.pdp.gymproject.dto;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import org.springframework.security.core.GrantedAuthority;
import uz.pdp.gymproject.entity.Role;

import java.util.Collection;
import java.util.List;

/**
 * DTO for {@link uz.pdp.gymproject.entity.User}
 */
public class UserDto {
     String firstName;
     String lastName;
     String phone;
     String email;
     String password;

}

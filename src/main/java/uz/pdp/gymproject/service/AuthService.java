package uz.pdp.gymproject.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import uz.pdp.gymproject.dto.LoginDto;
import uz.pdp.gymproject.dto.RegisterDto;
import uz.pdp.gymproject.entity.Attachment;
import uz.pdp.gymproject.entity.Role;
import uz.pdp.gymproject.entity.User;
import uz.pdp.gymproject.entity.enums.RoleName;
import uz.pdp.gymproject.mappers.UserLoginMapper;
import uz.pdp.gymproject.mappers.UserRegisterMapper;
import uz.pdp.gymproject.repo.AttachmentRepository;
import uz.pdp.gymproject.repo.RoleRepository;
import uz.pdp.gymproject.repo.UserRepository;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRegisterMapper userRegisterMapper;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final UserLoginMapper userLoginMapper;
    private final AuthenticationManager authenticationManager;
    private final AttachmentRepository attachmentRepository;

    public String register(RegisterDto registerDto) {
        User user = userRegisterMapper.toEntity(registerDto);
        Role roleUser = roleRepository.findByRoleName(RoleName.ROLE_USER.name());
        user.setRoles(List.of(roleUser));
        setDefaultPhotoToUser(user);
        userRepository.save(user);
        return user.getEmail();
    }

    @SneakyThrows
    private void setDefaultPhotoToUser(User user) {
        File file =new File("photo/default_user.png");
        byte[] photo = Files.readAllBytes(file.toPath());
        Attachment attachment = Attachment.builder()
                .fullImage(photo)
                .build();
        attachment.compressImage();
        attachmentRepository.save(attachment);
        user.setAttachment(attachment);
    }

    public String login(LoginDto loginDto) {
        User user = userLoginMapper.toEntity(loginDto);
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
            );
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid email or password", e);
        }
        return user.getEmail();
    }
}

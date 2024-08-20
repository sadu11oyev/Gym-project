package uz.pdp.gymproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.gymproject.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/trainee")
public class TraineeController {
    private final UserService userService;


}

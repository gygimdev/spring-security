package springsecurity.core.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import springsecurity.core.domain.Account;
import springsecurity.core.domain.AccountDto;
import springsecurity.core.service.UserService;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @GetMapping("/")
    public String myPage() {
        return "user/mypage";
    }

    @GetMapping("/users")
    public String createUser() {
        return "user/login/register";
    }

    @PostMapping("/users")
    public String createUser(@ModelAttribute AccountDto dto) {
        log.info("::: dto {}", dto.getPassword());

        Account newAccount = Account.builder()
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .username(dto.getUsername())
                .age(dto.getAge())
                .role(dto.getRole())
                .build();
        userService.createUser(newAccount);

        return "redirect:/";
    }
}

package springsecurity.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AccountDto {

    private String username;
    private String password;
    private String email;
    private String age;
    private String role;
}

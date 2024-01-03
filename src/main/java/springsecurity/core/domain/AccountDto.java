package springsecurity.core.domain;

import lombok.Getter;

@Getter
public class AccountDto {

    private String username;
    private String password;
    private String email;
    private String age;

    private String role;
}

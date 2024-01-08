package springsecurity.core.security;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/** 프로바이더 역할
 *
 */
@Slf4j
@Component
@AllArgsConstructor
public class CustomAccountProvider implements AuthenticationProvider {

    private final CustomUserDetailService customUserDetailService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("::: AuthenticationProvider - authenticate {}", "실행");
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        AccountContext accountContext = (AccountContext) customUserDetailService.loadUserByUsername(username);

        // 패스워드 검증
        if(!passwordEncoder.matches(password, accountContext.getPassword())) {
            throw new BadCredentialsException("BadCredentialException");
        }

        log.info("::: AuthenticationProvider - authenticate {}", "성공");

        // 두번째 생성자
        return new UsernamePasswordAuthenticationToken(accountContext.getAccount(), null, accountContext.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        log.info("::: AuthenticationProvider - support {}", "실행");
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}

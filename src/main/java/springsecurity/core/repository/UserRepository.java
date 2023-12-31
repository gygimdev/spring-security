package springsecurity.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springsecurity.core.domain.Account;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUsername(String username);
}

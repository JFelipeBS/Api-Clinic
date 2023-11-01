package med.voll.api.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import med.voll.api.domain.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserDetails findByLogin(String login);

}

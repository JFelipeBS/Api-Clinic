package med.voll.api.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import med.voll.api.domain.entities.UsersEntity;

public interface UserRepository extends JpaRepository<UsersEntity, Long> {

    UserDetails findByLogin(String login);

}

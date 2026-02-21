package in.nabin.springsecurity.repository;

import in.nabin.springsecurity.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser,Long>
{
    Optional<AppUser> findByUsername(String username);
}

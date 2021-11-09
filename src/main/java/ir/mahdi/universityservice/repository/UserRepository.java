package ir.mahdi.universityservice.repository;

import ir.mahdi.universityservice.domain.base.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @EntityGraph(attributePaths = {"roles", "roles.operations"})
    Optional<User> findUserByUsername(String username);


    <P> P findUserByUsername(String username, Class<P> clazz);

//    @Query(value = "select from User u where u.username = :userSearch.username and u.isConfirmed=userSearch.isConfirmed and u.is")
//    List<User> findByUserSearchDTO(UserSearchDTO userSearch);
}

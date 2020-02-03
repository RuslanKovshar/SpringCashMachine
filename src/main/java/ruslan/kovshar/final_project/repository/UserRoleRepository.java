package ruslan.kovshar.final_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ruslan.kovshar.final_project.entity.UserRole;
import ruslan.kovshar.final_project.enums.Roles;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole,Integer> {
    Optional<UserRole> findByRole(Roles role);
}

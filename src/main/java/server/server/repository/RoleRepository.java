package server.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.server.domain.RoleDao;
import server.server.domain.RoleName;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleDao, Long> {
    Optional<RoleDao> findByName(RoleName roleName);
}

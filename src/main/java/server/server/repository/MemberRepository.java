package server.server.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.server.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member , Long> {
    Optional<Member> findByUserId(String userId);
    Optional<Member> findByName(String name);

    Boolean existsByUserId(String userId);
}

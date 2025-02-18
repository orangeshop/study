package practice.jpa.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import practice.jpa.domain.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}

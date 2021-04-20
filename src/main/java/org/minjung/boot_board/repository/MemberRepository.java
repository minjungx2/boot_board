package org.minjung.boot_board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.minjung.boot_board.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String > {
}

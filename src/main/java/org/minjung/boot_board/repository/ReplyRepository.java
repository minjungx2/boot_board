package org.minjung.boot_board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.minjung.boot_board.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
}

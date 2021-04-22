package org.minjung.boot_board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.minjung.boot_board.entity.Reply;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    @Modifying
    @Query("delete from Reply r where r.board.bno = :bno")
    void deleteByBno(Long bno);

    @Query("select r from Reply r where r.board.bno = :bno order by r.rno asc")
    List<Reply> getBoardReply(Long bno);
}

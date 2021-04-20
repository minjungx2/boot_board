package org.minjung.boot_board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.minjung.boot_board.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
//    from뒤는 entity
//    @Query("select b, count(r) from Board b left join Reply r on r.board = b group by b")
//    Page<Object[]> getBoardWithReplyCount(Pageable pageable);

    //연관관계가 없으면 entity타입으로 명시
    @Query(value = "select b, w, count(r) from Board b inner join b.writer w left join Reply r on r.board = b group by b",
           countQuery = "select count(b) from Board b")
    Page<Object[]> getBoardWithReplyCount(Pageable pageable);
}

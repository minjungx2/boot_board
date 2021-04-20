package org.minjung.boot_board.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.minjung.boot_board.entity.Board;
import org.minjung.boot_board.entity.Reply;

import java.util.stream.IntStream;

@SpringBootTest
public class ReplyRepositoryTests {

    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void insetReplies(){
        IntStream.rangeClosed(1,300).forEach(i -> {
            long idx = (long)(Math.random() * 100) + 1;
            Board board = Board.builder().bno(idx).build();

            Reply reply = Reply.builder()
                    .replyer("replyer" + (i % 10))
                    .text("댓글..."+idx)
                    .board(board)
                    .build();

            replyRepository.save(reply);
        });
    }
}

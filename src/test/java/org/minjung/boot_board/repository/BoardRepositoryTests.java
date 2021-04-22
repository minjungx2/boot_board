package org.minjung.boot_board.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.minjung.boot_board.entity.Board;
import org.minjung.boot_board.entity.Member;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void insertBoards(){
        IntStream.rangeClosed(1,100).forEach(i -> {
            int idx = (int) (Math.random()*100) + 1;
            Member member = Member.builder().email("user"+idx+"@aaa.com").build();
            Board board = Board.builder()
                    .title("광고 신청합니다."+i)
                    .content("강남 매장들의 키오스크에 광고 보여주고싶습니다."+i)
                    .writer(member)
                    .build();
            boardRepository.save(board);
        });
    }

    @Test
    public void testRead(){
        Optional<Board> result = boardRepository.findById(100L);
        log.info(result);

        if(result.isPresent()){
            Board board = result.get();
            log.info("-----------------------------------------------");
            log.info(board);
        }
    }

    @Test
    public void testBoardWithReplyCount(){
        Pageable pageable = PageRequest.of(0,10, Sort.by("bno").descending());
        Page<Object[]> result = boardRepository.getBoardWithReplyCount(pageable);
        result.get().forEach(arr -> log.info(Arrays.toString(arr)));
    }

    @Test
    public void testReadWithCount(){
        Long bno = 99L;
        Object result = boardRepository.getBoardByBno(bno);
        log.info(result);

        Object[] arr = (Object[]) result;
        log.info(Arrays.toString(arr));
    }

    @Test
    public void testSearch1(){
        boardRepository.search1();
    }

    @Test
    public void testSearchPage(){
        Pageable pageable = PageRequest.of(0,10,Sort.by("bno").descending());
        String type = "tcw";
        String keyword = "10";

        Page<Object[]> result = boardRepository.searchPage(type,keyword,pageable);

        result.get().forEach(arr -> log.info(Arrays.toString(arr)));
    }
}

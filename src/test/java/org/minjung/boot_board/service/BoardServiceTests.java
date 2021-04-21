package org.minjung.boot_board.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.minjung.boot_board.dto.BoardDTO;
import org.minjung.boot_board.dto.PageRequestDTO;
import org.minjung.boot_board.dto.PageResultDTO;
import org.springframework.test.annotation.Commit;

import javax.transaction.Transactional;

@SpringBootTest
@Log4j2
public class BoardServiceTests {

    @Autowired
    private BoardService service;

    @Test
    public void testRegister(){

        BoardDTO dto = BoardDTO.builder()
                .title("광고 등록 원합니다")
                .content("광고 등록하고싶은데 금액은 어느 정도 하나요?")
                .writerEmail("user37@aaa.com")
                .build();
        Long bno = service.register(dto);
        log.info("bno: " + bno);
    }

    @Test
    public void testList(){

        PageRequestDTO pageRequestDTO = new PageRequestDTO();
        PageResultDTO<BoardDTO, Object[]> result = service.getList(pageRequestDTO);

        result.getDtoList().forEach(boardDTO -> log.info(boardDTO));
    }

    @Test
    public void testGet(){
        log.info(service.get(99L));
    }

    @Test
    public void testRemove(){
        Long bno = 96L;
        service.removeWtihReplies(bno);
    }

    @Commit
    @Transactional //test코드에서 transactional을 쓰면 rolled back 문제가 생기므로 @commit 써줌
    @Test
    public void testModify(){
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(100L)
                .title("광고 등록 원하는데 답변 빨리 부탁드려요")
                .content("연락 빨리 주세요 급합니다.")
                .build();
        service.modify(boardDTO);
    }
}

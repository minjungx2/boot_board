package org.minjung.boot_board.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.minjung.boot_board.dto.BoardDTO;
import org.minjung.boot_board.dto.PageRequestDTO;
import org.minjung.boot_board.dto.PageResultDTO;

@SpringBootTest
@Log4j2
public class BoardServiceTests {

    @Autowired
    private BoardService service;

    @Test
    public void testRegister(){

        BoardDTO dto = BoardDTO.builder()
                .title("테스트")
                .content("테스트내용")
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
}

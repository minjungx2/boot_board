package org.minjung.boot_board.service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.minjung.boot_board.dto.BoardDTO;
import org.minjung.boot_board.dto.PageRequestDTO;
import org.minjung.boot_board.dto.PageResultDTO;
import org.minjung.boot_board.entity.Board;
import org.minjung.boot_board.entity.Member;
import org.minjung.boot_board.repository.BoardRepository;

import java.util.function.Function;

@Service
@AllArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;

    @Override
    public Long register(BoardDTO dto) {

        Board board = dtoToEntity(dto);
        boardRepository.save(board);
        return board.getBno();
    }

    @Override
    public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {

        Pageable pageable = pageRequestDTO.getPageable(Sort.by("bno").descending());

        Page<Object[]> result = boardRepository.getBoardWithReplyCount(pageable);

        Function<Object[], BoardDTO> fn = (arr -> EntityToDTO(
                (Board) arr[0],
                (Member) arr[1],
                (Long) arr[2])
        );
        return new PageResultDTO<>(result,fn);
    }
}

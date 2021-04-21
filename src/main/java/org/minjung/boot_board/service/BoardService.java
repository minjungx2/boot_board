package org.minjung.boot_board.service;

import org.minjung.boot_board.dto.BoardDTO;
import org.minjung.boot_board.dto.PageRequestDTO;
import org.minjung.boot_board.dto.PageResultDTO;
import org.minjung.boot_board.entity.Board;
import org.minjung.boot_board.entity.Member;

public interface BoardService {

    Long register(BoardDTO dto);

    PageResultDTO<BoardDTO,Object[]> getList(PageRequestDTO pageRequestDTO);

    BoardDTO get(Long bno);

    void removeWtihReplies(Long bno);

    void modify(BoardDTO dto);

    default Board dtoToEntity(BoardDTO dto){
        Member writer = Member.builder()
                .email(dto.getWriterEmail())
                .build();

        Board board = Board.builder()
                .title(dto.getTitle())
                .writer(writer)
                .content(dto.getContent())
                .build();
        return board;
    }

    default BoardDTO entityToDTO(Board board,Member member, Long replyCount){

        BoardDTO boardDTO = BoardDTO.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .regDate(board.getRegDate())
                .modDate(board.getModDate())
                .writerEmail(member.getEmail())
                .writerName(member.getName())
                .replyCount(replyCount.intValue())
                .build();
        return boardDTO;
    }
}

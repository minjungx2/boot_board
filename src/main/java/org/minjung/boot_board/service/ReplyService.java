package org.minjung.boot_board.service;

import org.minjung.boot_board.dto.ReplyDTO;
import org.minjung.boot_board.entity.Board;
import org.minjung.boot_board.entity.Reply;

import java.util.List;

public interface ReplyService {

    Long register(ReplyDTO replyDTO);

    List<ReplyDTO> getList(Long bno);

    void modify(ReplyDTO replyDTO);

    void remove(Long rno);

    default Reply dtoToEntity(ReplyDTO replyDTO){

        //Board객체 생성
        Board board = Board.builder()
                .bno(replyDTO.getBno())
                .build();

        Reply reply = Reply.builder()
                .rno(replyDTO.getRno())
                .text(replyDTO.getText())
                .replyer(replyDTO.getReplyer())
                .board(board)
                .build();

        return reply;
    }

    default ReplyDTO entityToDTO(Reply reply){

        ReplyDTO dto = ReplyDTO.builder()
                .rno(reply.getRno())
                .text(reply.getText())
                .bno(reply.getBoard().getBno())
                .replyer(reply.getReplyer())
                .regDate(reply.getRegDate())
                .modDate(reply.getModDate())
                .build();

        return dto;
    }
}

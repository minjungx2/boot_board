package org.minjung.boot_board.service;

import lombok.RequiredArgsConstructor;
import org.minjung.boot_board.dto.ReplyDTO;
import org.minjung.boot_board.entity.Reply;
import org.minjung.boot_board.repository.ReplyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

    private final ReplyRepository replyRepository;

    @Override
    public Long register(ReplyDTO replyDTO) {

        Reply reply = dtoToEntity(replyDTO);

        replyRepository.save(reply);

        return reply.getRno();
    }

    @Override
    public List<ReplyDTO> getList(Long bno) {

        List<Reply> result =  replyRepository
                .getBoardReply(bno);

        return result.stream().map(reply -> entityToDTO(reply)).collect(Collectors.toList());
    }

    @Override
    public void modify(ReplyDTO replyDTO) {

        Reply reply = dtoToEntity(replyDTO);

        replyRepository.save(reply);

    }

    @Override
    public void remove(Long rno) {

        replyRepository.deleteById(rno);
    }

}

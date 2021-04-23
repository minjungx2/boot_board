package org.minjung.boot_board.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReplyDTO {

    private Long rno;
    private String text;
    private String replyer;
    private  Long bno;
    private LocalDateTime regDate,modDate;
}

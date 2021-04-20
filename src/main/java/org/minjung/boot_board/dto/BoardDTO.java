package org.minjung.boot_board.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {

    private Long bno;
    private String title, content, writerEmail, writerName;
    private LocalDateTime regDate, modDate;
    private int replyCount;
}

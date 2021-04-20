package org.minjung.boot_board.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass//entity로 만들어지지 않음
@EntityListeners(value = {AuditingEntityListener.class})//entity객체가 만들어지면 자동으로 감지
@Getter//entity는 getter위주
abstract class BaseEntity {

    @CreatedDate
    @Column(name="regdate", updatable = false)
    private LocalDateTime regDate;

    @LastModifiedDate
    @Column(name="moddate")
    public LocalDateTime modDate;
}

package org.minjung.boot_board.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString // 중요
public class Member extends BaseEntity{

    @Id
    private String email;
    private String password;
    private String name;

}

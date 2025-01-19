package org.casual_chess.cc_game.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("users")
public class UserEntity {

    @Id
    private Long userId;
    private String username;
}
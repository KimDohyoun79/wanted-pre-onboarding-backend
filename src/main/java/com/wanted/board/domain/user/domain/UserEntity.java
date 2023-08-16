package com.wanted.board.domain.user.domain;

import com.wanted.board.domain.user.dto.userRole.UserRole;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@DynamicInsert // when @ColumnDefault is null
@Table(name = "user_table")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(length = 100)
    private String userName;

    @Enumerated(EnumType.STRING)
    private UserRole role;
}

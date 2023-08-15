package com.wanted.board.domain.entity;


import com.wanted.board.domain.dto.userRole.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

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
    private String Email;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(length = 100)
    private String userName;

    @Enumerated(EnumType.STRING)
    private UserRole role;
}

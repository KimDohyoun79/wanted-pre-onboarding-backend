package com.wanted.board.domain.post.domain;

import com.wanted.board.domain.user.domain.UserEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "post_table")
public class PostEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") // 외래키
    private UserEntity userEntity;

    public void updatePost(String title, String body) {
        this.title = title;
        this.body = body;
    }
}

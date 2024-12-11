package org.example.apipractice.member.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.apipractice.comment.domain.Comment;
import org.example.apipractice.member.api.dto.request.MemberUpdateRequest;
import org.example.apipractice.post.domain.Post;
import org.example.apipractice.vote.domain.Vote;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String nickName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private LocalDateTime createAt = LocalDateTime.now();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vote> votes = new ArrayList<>();

    @Builder
    public Member(String nickName, String email, String password, int age, LocalDateTime createAt) {
        this.nickName = nickName;
        this.email = email;
        this.password = password;
        this.age = age;
        this.createAt = createAt;
    }

    public void update(MemberUpdateRequest memberUpdateRequest) {
        this.nickName = memberUpdateRequest.nickname();
        this.password = memberUpdateRequest.password();
        this.age = memberUpdateRequest.age();
    }
}

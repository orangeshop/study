package practice.jpa.domain.member.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class MemberDetail {

    @Id
    @GeneratedValue
    @Column(name = "member_detail_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;
}

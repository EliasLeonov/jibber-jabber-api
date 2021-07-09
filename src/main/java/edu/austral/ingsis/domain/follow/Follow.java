package edu.austral.ingsis.domain.follow;

import edu.austral.ingsis.domain.JJUser;
import edu.austral.ingsis.domain.dto.follow.FollowDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "follow")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private JJUser followingUser;
    @ManyToOne
    private JJUser followerUser;

    public FollowDto toDto(){
        return FollowDto
                .builder()
                .id(id)
                .followingUserId(followingUser.getId())
                .followerUserId(followerUser.getId())
                .build();
    }

}

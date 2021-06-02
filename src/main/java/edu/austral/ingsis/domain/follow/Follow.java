package edu.austral.ingsis.domain.follow;

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
    private String id;
    private String followingUserId;
    private String followerUserId;

    public FollowDto toDto(){
        return FollowDto
                .builder()
                .followingUserId(followingUserId)
                .followerUserId(followerUserId)
                .build();
    }

}

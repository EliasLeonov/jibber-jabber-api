package edu.austral.ingsis.domain.dto.follow;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FollowDto {
    private String id;
    private String followingUserId;
    private String followerUserId;
}

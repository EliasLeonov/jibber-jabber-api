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
    private Long id;
    private Long followingUserId;
    private Long followerUserId;
}

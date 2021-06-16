package edu.austral.ingsis.domain.dto.follow;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateFollowDto {
    private Long followUserId;
    private Long followerUserId;
}

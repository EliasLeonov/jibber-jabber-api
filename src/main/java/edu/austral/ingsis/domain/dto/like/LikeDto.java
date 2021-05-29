package edu.austral.ingsis.domain.dto.like;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeDto {
    private String id;
    private String postId;
    private String userId;
}

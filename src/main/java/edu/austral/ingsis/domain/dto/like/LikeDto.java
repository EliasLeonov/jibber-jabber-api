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
    private Long id;
    private Long postId;
    private Long userId;
}

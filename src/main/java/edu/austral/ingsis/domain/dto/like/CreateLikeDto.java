package edu.austral.ingsis.domain.dto.like;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateLikeDto {
    private Long postId;
    private Long userId;
}

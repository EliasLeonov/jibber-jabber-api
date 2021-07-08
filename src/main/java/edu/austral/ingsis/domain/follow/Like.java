package edu.austral.ingsis.domain.follow;

import edu.austral.ingsis.domain.dto.like.LikeDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "JJlike")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long postId;
    private Long userId;

    public LikeDto toDto(){
        return LikeDto
                .builder()
                .id(id)
                .postId(postId)
                .userId(userId)
                .build();
    }
}

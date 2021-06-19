package edu.austral.ingsis.domain.dto.post;

import edu.austral.ingsis.domain.dto.user.JJUserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDto {
    private Long id;
    private JJUserDto author;
    private String text;
    private Date timestamp;
    private Long likes;
    private Boolean isLiked;
}



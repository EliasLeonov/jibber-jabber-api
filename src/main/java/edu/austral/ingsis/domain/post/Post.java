package edu.austral.ingsis.domain.post;

import edu.austral.ingsis.dto.post.PostDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "post")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String text;

    public PostDto toDto(){
        return PostDto
                .builder()
                .id(id)
                .text(text)
                .build();
    }

}

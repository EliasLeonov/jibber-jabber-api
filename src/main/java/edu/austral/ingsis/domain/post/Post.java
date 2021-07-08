package edu.austral.ingsis.domain.post;

import edu.austral.ingsis.domain.JJUser;
import edu.austral.ingsis.domain.dto.post.PostDto;
import edu.austral.ingsis.domain.dto.user.JJUserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Date;

@Entity
@Table(name = "post")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post implements Comparable<Post> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private JJUser owner;

    @Column(updatable = false)
    private String text;

    @Column(updatable = false)
    private Date date;

    public PostDto toDto(){
        return PostDto
                .builder()
                .id(id)
                .timestamp(date)
                .author(owner.toDto())
                .text(text)
                .build();
    }

//    @Override
//    public int compare(Post o1, Post o2) {
//        return o1.getDate().compareTo(o2.getDate());
//    }

    @Override
    public int compareTo(Post o) {
        return (int) (getDate().getSeconds() - o.getDate().getSeconds());
    }
}

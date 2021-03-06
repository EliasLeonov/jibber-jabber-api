package edu.austral.ingsis.domain.dto.user;

import edu.austral.ingsis.domain.dto.follow.FollowDto;
import edu.austral.ingsis.domain.dto.follow.UserFollowData;
import edu.austral.ingsis.domain.dto.post.PostDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPrivateDataDto {
    private Long id;
    private String username;
    private String firstname;
    private String lastname;
    private String mail;
    private Set<PostDto> posts;
    private Set<UserFollowData> followers;
    private Set<UserFollowData> following;
}

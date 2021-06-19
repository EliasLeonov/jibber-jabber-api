package edu.austral.ingsis.domain.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JJUserDto {
    private Long id;
    private String username;
    private String firstname;
    private String lastname;
    private String mail;
}

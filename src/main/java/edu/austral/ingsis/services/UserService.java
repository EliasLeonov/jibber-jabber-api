package edu.austral.ingsis.services;

import edu.austral.ingsis.domain.JJUser;
import edu.austral.ingsis.domain.dto.user.JJUserDto;
import edu.austral.ingsis.domain.dto.user.UserPrivateDataDto;
import edu.austral.ingsis.domain.dto.user.UserPublicDataDto;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    public UserDetails loadUserByUsername(String username);
    public JJUser getById(Long id);
    public UserPublicDataDto getPublicData(String username);
    public UserPrivateDataDto getPrivateData();
    public JJUserDto getByUsername(String username);
}

package org.chatop.chatopback.service;

import lombok.RequiredArgsConstructor;
import org.chatop.chatopback.entity.User;
import org.chatop.chatopback.exception.CustomUsernameNotFoundException;
import org.chatop.chatopback.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Service class for loading user-specific data.
 * Implements the {@link UserDetailsService} interface provided by Spring Security.
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;


    /**
     * Loads the user from BDD by their email address.
     *
     * @param userEmail the email address of the user to be loaded
     * @return a fully populated {@link UserDetails} object
     * @throws CustomUsernameNotFoundException if the user could not be found by their email address
     */
    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(userEmail)
                .orElseThrow(() -> new CustomUsernameNotFoundException(userEmail));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .build();
    }
}

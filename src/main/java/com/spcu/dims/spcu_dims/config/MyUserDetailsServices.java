package com.spcu.dims.spcu_dims.config;

import com.spcu.dims.spcu_dims.models.User;
import com.spcu.dims.spcu_dims.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MyUserDetailsServices implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameCaseSensitive(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        if (user != null) {

            return new MyUserDetails(user) {
            };
        }

        throw new UsernameNotFoundException("User Not Found");
    }
}

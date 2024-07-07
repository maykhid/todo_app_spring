package com.example.todo_app;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
// import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.example.todo_app.user.User;
import com.example.todo_app.user.UserRepository;

@Configuration
public class UserConfig implements UserDetailsService{

       @Autowired
    private UserRepository userRepository;

    // @Bean
    // public UserDetailsService userDetailsService() {
    //     var user = User.withDefaultPasswordEncoder()
    //             .username("user")
    //             .password("password")
    //             // .roles("USER")
    //             .build();
        
    //     var admin = User.withDefaultPasswordEncoder()
    //             .username("admin")
    //             .password("admin")
    //             // .roles("ADMIN")
    //             .build();
        
    //     return new InMemoryUserDetailsManager(user, admin);
    // }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }
}

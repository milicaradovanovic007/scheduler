package com.milicaradovanovic.sap.security;

import com.milicaradovanovic.sap.entity.ScheduleUserEntity;
import com.milicaradovanovic.sap.repository.ScheduleUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private ScheduleUserRepository userRepository;
    private JwtProvider jwtProvider;


    @Autowired
    public UserDetailsServiceImpl(ScheduleUserRepository userRepository,
                                  JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        ScheduleUserEntity user = this.userRepository.findByEmail(s).orElseThrow(() ->
                new UsernameNotFoundException(String.format("User with email %s does not exist", s)));

        return new UserPrincipalImpl(user);
    }

    public Optional<UserPrincipalImpl> loadUserByJwtToken(String jwtToken) {
        if (this.jwtProvider.isValidToken(jwtToken)) {
            ScheduleUserEntity userEntity = new ScheduleUserEntity();
            userEntity.setEmail(this.jwtProvider.getEmail(jwtToken));
            userEntity.setId((int) this.jwtProvider.getUserId(jwtToken));
            return Optional.of(new UserPrincipalImpl(userEntity));
        }
        return Optional.empty();
    }
}

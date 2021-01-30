package com.milicaradovanovic.sap.security;

import com.milicaradovanovic.sap.entity.ScheduleUserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserPrincipalImpl implements UserDetails {

    private ScheduleUserEntity userEntity;

    public UserPrincipalImpl(ScheduleUserEntity userEntity) {
        super();
        this.userEntity = userEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptySet();
    }

    @Override
    public String getPassword() {
        return this.userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return this.userEntity.getEmail();
    }

    public long getUserId() {
        return this.userEntity.getId();
    }

    public ScheduleUserEntity getUserEntity() {
        return this.userEntity;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

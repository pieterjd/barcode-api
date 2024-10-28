package com.pieterjd.barcode.user;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;

import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "USERS")
public class User implements UserDetails {
    @Id
    @Column(name = "USERNAME")
    private String userName;
    @Column
    private String password;
    @Column
    private boolean enabled;

    @ElementCollection
    @CollectionTable(name = "AUTHORITIES", joinColumns = @JoinColumn(name = "USERNAME"))
    @Enumerated(EnumType.STRING)
    @Column(name = "AUTHORITY")
    @Builder.Default
    private Set<Authority> authorities = new HashSet<>();

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities.stream()
                .map(a -> new SimpleGrantedAuthority(a.name()))
                .collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

}

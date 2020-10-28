package com.jyami.commitersewha.security;

import com.jyami.commitersewha.domain.githubInfo.GithubInfo;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

/**
 * Created by jyami on 2020/10/12
 */
@ToString
public class GithubUserPrincipal implements OAuth2User, UserDetails {

    private Long id;
    private String email;
    private Map<String, Object> attributes;

    public GithubUserPrincipal(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    public static GithubUserPrincipal create(GithubInfo githubInfo) {
        return new GithubUserPrincipal(githubInfo.getInfoId(), githubInfo.getEmail());
    }

    public static GithubUserPrincipal create(GithubInfo githubInfo, Map<String, Object> attributes) {
        GithubUserPrincipal githubUserPrincipal = GithubUserPrincipal.create(githubInfo);
        githubUserPrincipal.setAttributes(attributes);
        return githubUserPrincipal;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getUsername() {
        return email;
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

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    private void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getName() {
        return String.valueOf(id);
    }
}

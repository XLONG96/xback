package com.xlong.back.service;

import com.xlong.back.entity.User;
import com.xlong.back.entity.UserPermission;
import com.xlong.back.repository.PermissionRepository;
import com.xlong.back.repository.UserRepository;
import com.xlong.back.entity.UserPermission;
import com.xlong.back.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private static final Logger logger = Logger.getLogger(CustomUserDetailsService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String name){
        User user = userRepository.findByUsername(name);

        if(user == null){
            throw new UsernameNotFoundException("用户名不存在");
        }

        logger.info("XXX User name " + user.getUsername());
        logger.info("XXX User password " + user.getPassword());

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                getAuthorities(user.getId())
        );
    }

    public Collection<? extends GrantedAuthority> getAuthorities(Integer userId) {
        List<UserPermission> authList = permissionRepository.findByUserId(userId);
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (UserPermission perm : authList) {
            authorities.add(new SimpleGrantedAuthority(perm.getPermission()));
        }

        logger.info("XXX User role " + authList.get(0).getPermission());
        return authorities;
    }
}

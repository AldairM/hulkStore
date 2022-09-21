/**
 * @author Aldair mosquera murillo
 */
package com.store.hulk.services.users;

import com.store.hulk.models.users.UserHulk;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Slf4j
public class UserRoleService  implements UserDetailsService {

    @Autowired
    private UserService service;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            log.info("User found in the databese {}",username);
            UserHulk user = service.findByUserName(username);
            Collection<GrantedAuthority> authorities = buildUserAuthority();
            return new User(user.getUserName(),user.getPassword(),authorities);
        }catch (UsernameNotFoundException e){
            log.error("User not fount error:{}",e.getMessage());
            throw new UsernameNotFoundException("User not fount");
        }
    }

    private Collection<GrantedAuthority> buildUserAuthority() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_ADMIN");
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(authority);
        return authorities;
    }
}

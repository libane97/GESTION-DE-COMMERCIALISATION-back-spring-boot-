package dj.djib.odo.odocom.service;

import dj.djib.odo.odocom.dao.IRole;
import dj.djib.odo.odocom.dao.IUser;
import dj.djib.odo.odocom.model.Role;
import dj.djib.odo.odocom.model.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserdetailsImpl implements UserDetailsService {

    @Autowired
    private IUser iUser;

    @Autowired
    private IRole iRole;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        user user = iUser.findByUsername(s);
        if (user != null){
            ArrayList<Role> l = new ArrayList<>();
            l.add(user.getRole());
            User u = new User(user.getUsername(),user.getPassword(),
                    true,true,
                    true,true,getAuthorities(l));
            return  u;
        }
        return null;
    }

    private Collection getAuthorities(List roles) {
        List authorities = new ArrayList();
        for(Object role : roles)
        {
            Role l = (Role)role;
            authorities.add(new SimpleGrantedAuthority(l.getName().name()));
        }
        return authorities ;
    }

}

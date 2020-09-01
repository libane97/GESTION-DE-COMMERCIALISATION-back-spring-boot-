package dj.djib.odo.odocom.controller;

import dj.djib.odo.odocom.config.JwtTokenUtil;
import dj.djib.odo.odocom.dao.IRole;
import dj.djib.odo.odocom.dao.IUser;
import dj.djib.odo.odocom.model.*;
import dj.djib.odo.odocom.service.UserdetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin("*")
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserdetailsImpl userDetailsService;
    @Autowired
    private IUser iUser;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IRole iRole;
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody JwtRequest authenticationRequest){
      final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        Authentication authentication = null;
         try {
             authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
             SecurityContextHolder.getContext().setAuthentication(authentication);
             String jwt = jwtTokenUtil.generateToken(userDetails);
             if(userDetails != null)
                    return ResponseEntity.ok(new ResponseJwt(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
                return ResponseEntity.ok(new ErrorResponse("INVALID_CREDENTIALS"));
         }catch (DisabledException e){
             return ResponseEntity.ok(new ErrorResponse("USER_DISABLED"));
         }catch (BadCredentialsException e) {
             return ResponseEntity.ok(new ErrorResponse("INVALID_CREDENTIALS"));
         }
    }

    @PreAuthorize("hasAuthority('ROLE_ADMINISTRATEUR')")
    @PostMapping("/adds")
    public @ResponseBody user adds(@RequestBody user user) throws Exception{
        if (user.getRole() != null){
            Optional<Role> role = iRole.findById(user.getRole().getId());
            user.setRole(role.get());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return  iUser.save(user);
    }
    @PreAuthorize("hasAuthority('ROLE_ADMINISTRATEUR')")
    @GetMapping("/updates/{id}")
    public boolean updates(@PathVariable Long id,@RequestBody user user){
        // Optional<Contact> contact = contactRespository.findById(id);
        user.setId(id);
        iUser.save(user);
        return true;
    }
    @PreAuthorize("hasAuthority('ROLE_ADMINISTRATEUR')")
    @GetMapping("/listUser")
    public ResponseEntity<?> getUser() {
        return ResponseEntity.ok(iUser.findAll());
    }
    @PreAuthorize("hasAuthority('ROLE_ADMINISTRATEUR')")
    @GetMapping("/deletes")
    public @ResponseBody boolean deletes(@RequestParam(name = "id") Long id){
        Optional<user> user = iUser.findById(id);
        if (user.isPresent()) {
            iUser.delete(user.get());
            return true;
        }
        return false;
    }

    @PreAuthorize("hasAuthority('ROLE_ADMINISTRATEUR')")
    @GetMapping("/SearchByEmail")
    public List<user> searchUserByEmail(@RequestParam(name = "email",defaultValue = "") String email)
    {
        return iUser.searchByEmail(email);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMINISTRATEUR')")
    @GetMapping("/SearchBytelephone")
    public List<user> searchUserBytelephone(@RequestParam(name = "telephone",defaultValue = "") String telephone)
    {
        return iUser.searchBytelephone("%"+telephone+"%");
    }
}

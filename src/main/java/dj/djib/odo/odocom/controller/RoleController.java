package dj.djib.odo.odocom.controller;

import dj.djib.odo.odocom.dao.IRole;
import dj.djib.odo.odocom.model.Categorie;
import dj.djib.odo.odocom.model.Produit;
import dj.djib.odo.odocom.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/Role")
public class RoleController {
    @Autowired
    private IRole iRole;
    @PreAuthorize("hasAuthority('ROLE_ADMINISTRATEUR')")
    @GetMapping("/listRole")
    public ResponseEntity<?> getRole() {
        return ResponseEntity.ok(iRole.findAll());
    }

    @PreAuthorize("hasAuthority('ROLE_ADMINISTRATEUR')")
    @PostMapping("/addRole")
    public @ResponseBody Role adds(@RequestBody Role role){
        return  iRole.save(role);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMINISTRATEUR')")
    @RequestMapping(value = "/Update/{id}",method= RequestMethod.PUT)
    public boolean Update(@PathVariable Long id,@RequestBody Role role){
        role.setId(id);
        iRole.save(role);
        return true;
    }

    @PreAuthorize("hasAuthority('ROLE_ADMINISTRATEUR')")
    @GetMapping("/deletes")
    public @ResponseBody boolean deletes(@RequestParam(name = "id") Long id){
        Optional<Role> role = iRole.findById(id);
        if (role.isPresent()) {
            iRole.delete(role.get());
            return true;
        }
        return false;
    }

}

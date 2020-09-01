package dj.djib.odo.odocom.controller;

import dj.djib.odo.odocom.dao.IFacture;
import dj.djib.odo.odocom.dao.IReglements;
import dj.djib.odo.odocom.dao.IUser;
import dj.djib.odo.odocom.model.Commande;
import dj.djib.odo.odocom.model.Facture;
import dj.djib.odo.odocom.model.Reglements;
import dj.djib.odo.odocom.model.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin("*")
@Controller
@RequestMapping("/Reglements")
public class ReglementsController {
     @Autowired
     private IReglements iReglements;
     @Autowired
     private IFacture ifacture;
     @Autowired
     private IUser iUser;
    @PreAuthorize("hasAuthority('ROLE_ADMINISTRATEUR')")
    @GetMapping("/listReglement")
    public ResponseEntity<?> getReglements() {
        return ResponseEntity.ok(iReglements.findAll());
    }
    @PreAuthorize("hasAuthority('ROLE_ADMINISTRATEUR')")
    @PostMapping("/addReglement")
    public @ResponseBody Reglements adds(@RequestBody Reglements reglements) throws Exception {
        if (reglements.getFacture() != null){
            Optional<Facture> facture = ifacture.findById(reglements.getFacture().getId());
            reglements.setFacture(facture.get());
        }
        if (reglements.getUser() != null){
            Optional<user> user = iUser.findById(reglements.getUser().getId());
            reglements.setUser(user.get());
        }
        return iReglements.save(reglements);
    }
}

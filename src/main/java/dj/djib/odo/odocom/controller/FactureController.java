package dj.djib.odo.odocom.controller;

import dj.djib.odo.odocom.dao.ICommande;
import dj.djib.odo.odocom.dao.IFacture;
import dj.djib.odo.odocom.model.Categorie;
import dj.djib.odo.odocom.model.Commande;
import dj.djib.odo.odocom.model.Facture;
import dj.djib.odo.odocom.model.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@CrossOrigin("*")
@RequestMapping("/Facture")
public class FactureController {
    @Autowired
    private IFacture iFacture;
    @Autowired
    private ICommande iCommande;
    @PreAuthorize("hasAuthority('ROLE_ADMINISTRATEUR')")
    @GetMapping("/listFacture")
    public ResponseEntity<?> getFacture() {
        return ResponseEntity.ok(iFacture.findAll());
    }
    @PreAuthorize("hasAuthority('ROLE_ADMINISTRATEUR')")
    @PostMapping("/addFacture")
    public @ResponseBody Facture adds(@RequestBody Facture facture) throws Exception {
        if (facture.getCommandes() != null){
            Optional<Commande> Commande = iCommande.findById(facture.getCommandes().getId());
            facture.setCommandes(Commande.get());
        }
        return iFacture.save(facture);
    }
}

package dj.djib.odo.odocom.controller;

import dj.djib.odo.odocom.dao.ICommande;
import dj.djib.odo.odocom.dao.IDetailsCommands;
import dj.djib.odo.odocom.dao.IProduit;
import dj.djib.odo.odocom.dao.IUser;
import dj.djib.odo.odocom.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
@CrossOrigin("*")
@RequestMapping("/command")
public class CommadeController {
    @Autowired
    private ICommande iCommande;
    @Autowired
    private IUser iUser;
    @Autowired
    private IProduit iProduit;
    @Autowired
    private IDetailsCommands iDetailsCommands;
    int total = 0;
    @PreAuthorize("hasAuthority('ROLE_ADMINISTRATEUR')")
    @PostMapping("/add")
    public @ResponseBody Commande add(@RequestBody Commande commande) {
        int x = 1;
        if (commande.getUser() != null){
             Optional<user>  user = iUser.findById(commande.getUser().getId());
            System.out.println(user);
             commande.setUser(user.get());
        }
       /* if (commande.getProduit() != null) {
              List<Produit> produits = commande.getProduit();
              List<Produit> produitList = new ArrayList<>();
              for (int i=0; i<produits.size(); i++){
                   Optional<Produit> produit = iProduit.findById((Long) produits.get(i).getId());
                   produitList.add(produit.get());
              }
              commande.setProduit(produitList);
        }*/
            Random random = new Random();
            commande.setNumero(random.nextInt(100000));

        return iCommande.save(commande);
    }
    @PreAuthorize("hasAuthority('ROLE_ADMINISTRATEUR')")
    @GetMapping("/listcommand")
    public ResponseEntity<?> liste() {
        return ResponseEntity.ok(iCommande.findAll());
    }

    @PreAuthorize("hasAuthority('ROLE_ADMINISTRATEUR')")
    @GetMapping("/update")
    public @ResponseBody Commande update(@RequestBody Commande commande){
        Random random = new Random();
        commande.setNumero(random.nextInt(100000));
        return iCommande.save(commande);
    }
    @PreAuthorize("hasAuthority('ROLE_ADMINISTRATEUR')")
    @GetMapping("/delete")
    public @ResponseBody boolean delete(@RequestParam(name = "id") Long id){
        Optional<Commande> commande = iCommande.findById(id);
        if (commande.isPresent()) {
            iCommande.delete(commande.get());
            return true;
        }
        return false;
    }

    @PreAuthorize("hasAuthority('ROLE_ADMINISTRATEUR') or hasAuthority('ROLE_CLIENT')")
    @GetMapping("/getCommandById/{id}")
    public ResponseEntity<?> getCommandById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(iCommande.findById(id));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMINISTRATEUR')")
    @GetMapping("/listUser")
    public ResponseEntity<?> listUser() {
        return ResponseEntity.ok(iUser.findAll());
    }

    @PreAuthorize("hasAuthority('ROLE_ADMINISTRATEUR')")
    @GetMapping("/SearchDateCommande")
    public List<Commande> searchDateCommande(@RequestParam(name = "datecommand",defaultValue = "") Date datecommand
            ,@RequestParam(name = "datecommandf",defaultValue = "") Date datecommandf)
    {
        return iCommande.SearchDateCommande(datecommand,datecommandf);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMINISTRATEUR')")
    @GetMapping("/FactureByCommand")
    public ResponseEntity<?> FactureByCommand(@RequestParam(name = "numero",defaultValue = "") int numero) {
        return ResponseEntity.ok(iCommande.SelectedFacture(numero));
    }

}

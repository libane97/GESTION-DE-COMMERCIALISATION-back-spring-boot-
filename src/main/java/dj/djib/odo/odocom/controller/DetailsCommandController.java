package dj.djib.odo.odocom.controller;

import dj.djib.odo.odocom.dao.ICommande;
import dj.djib.odo.odocom.dao.IDetailsCommands;
import dj.djib.odo.odocom.dao.IProduit;
import dj.djib.odo.odocom.model.Commande;
import dj.djib.odo.odocom.model.DetailsCommand;
import dj.djib.odo.odocom.model.Produit;
import dj.djib.odo.odocom.model.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Dcommand")
@CrossOrigin
public class DetailsCommandController {
    @Autowired
    private ICommande iCommande;
    @Autowired
    private IDetailsCommands iDetailsCommands;
    @Autowired
    private IProduit iProduit;

    @PreAuthorize("hasAuthority('ROLE_ADMINISTRATEUR')")
    @PostMapping("/add")
    public @ResponseBody DetailsCommand add(@RequestBody DetailsCommand detailsCommand) {
        if (detailsCommand.getCommandes() != null){
            Optional<Commande> commande = iCommande.findById(detailsCommand.getCommandes().getId());
            detailsCommand.setCommandes(commande.get());
            //  System.out.println(commande);
        }
        if (detailsCommand.getProduit() != null){
            Optional<Produit> produit = iProduit.findById(detailsCommand.getProduit().getId());
            detailsCommand.setProduit(produit.get());
            //  System.out.println(commande);
        }
        return iDetailsCommands.save(detailsCommand);
    }
    @PreAuthorize("hasAuthority('ROLE_ADMINISTRATEUR')")
    @GetMapping("/listDcommand")
    public ResponseEntity<?> liste() {
        return ResponseEntity.ok(iDetailsCommands.findAll());
    }

    @PreAuthorize("hasAuthority('ROLE_ADMINISTRATEUR')")
    @GetMapping("/delete")
    public @ResponseBody boolean delete(@RequestParam(name = "id") Long id){
        Optional<DetailsCommand> detailsCommand = iDetailsCommands.findById(id);
        if (detailsCommand.isPresent()) {
            iDetailsCommands.delete(detailsCommand.get());
            return true;
        }
        return false;
    }

    @PreAuthorize("hasAuthority('ROLE_ADMINISTRATEUR')")
    @GetMapping("/update")
    public @ResponseBody DetailsCommand update(@RequestBody DetailsCommand detailsCommand){
        return iDetailsCommands.save(detailsCommand);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMINISTRATEUR')")
    @GetMapping("/Search")
    public List<DetailsCommand> search(@RequestParam(name = "dateCommande",defaultValue = "") Date dateCommande)
    {
        return iDetailsCommands.search(dateCommande);
    }


}

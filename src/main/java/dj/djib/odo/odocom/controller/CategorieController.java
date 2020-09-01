package dj.djib.odo.odocom.controller;

import dj.djib.odo.odocom.dao.ICategorie;
import dj.djib.odo.odocom.model.Categorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin("*")
@RestController
public class CategorieController {
    @Autowired
    private ICategorie iCategorie;
    @PreAuthorize("hasAuthority('ROLE_ADMINISTRATEUR') or hasAuthority('ROLE_CLIENT')")
    @RequestMapping(value = "/Categorie/liste",method= RequestMethod.GET)
    public List<Categorie> getCategorie(){
        return iCategorie.findAll();
    }
    @PreAuthorize("hasAuthority('ROLE_ADMINISTRATEUR')")
    @PostMapping("/Categorie/add")
    public @ResponseBody Categorie add(@RequestBody Categorie categorie){
        return iCategorie.save(categorie);
    }
    @PreAuthorize("hasAuthority('ROLE_ADMINISTRATEUR')")
    @RequestMapping(value = "/Categorie/Update/{id}",method= RequestMethod.PUT)
    public boolean Update(@PathVariable Long id,@RequestBody Categorie categorie){
        categorie.setId(id);
        iCategorie.save(categorie);
        return true;
    }

    @PreAuthorize("hasAuthority('ROLE_ADMINISTRATEUR')")
    @GetMapping("/Categorie/Delete/{id}")
    public boolean Delete(@PathVariable Long id){
        Optional<Categorie> contact = iCategorie.findById(id);
        iCategorie.delete(contact.get());
        return true;
    }
    @PreAuthorize("hasAuthority('ROLE_ADMINISTRATEUR')")
    @RequestMapping(value = "/Categorie/Search",method=RequestMethod.GET)
    public List<Categorie> Search(@RequestParam(name = "mc",defaultValue = "") String mc)
    {
        return  iCategorie.search("%"+mc+"%");
    }
}

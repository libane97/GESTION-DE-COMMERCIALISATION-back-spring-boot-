package dj.djib.odo.odocom.controller;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import dj.djib.odo.odocom.dao.ICategorie;
import dj.djib.odo.odocom.dao.IProduit;
import dj.djib.odo.odocom.model.Categorie;
import dj.djib.odo.odocom.model.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@RequestMapping("/Product")
@CrossOrigin("*")
@RestController
public class ProduitController {
    @Autowired
    private IProduit iProduit;
    @Autowired
    private ICategorie iCategorie;
    @Autowired
    private  ServletContext servletContext;
    private static String updloadDir = "C://Users//liban//OneDrive//Bureau//photo";
   @PreAuthorize("hasAuthority('ROLE_ADMINISTRATEUR') or hasAuthority('ROLE_CLIENT')")
   @GetMapping("/list")
   public ResponseEntity<?> getProduit() {
       return ResponseEntity.ok(iProduit.findAll());
   }

    @PreAuthorize("hasAuthority('ROLE_ADMINISTRATEUR')")
    @PostMapping("/adds")
    public @ResponseBody Produit adds(@RequestBody Produit produit) throws Exception{
        if (produit.getCategorie() != null){
            Optional<Categorie> categorie = iCategorie.findById(produit.getCategorie().getId());
            produit.setCategorie(categorie.get());
        }
        return  iProduit.save(produit);
   }

    @PreAuthorize("hasAuthority('ROLE_ADMINISTRATEUR')")
    @GetMapping("/updates/{id}")
    public boolean updates(@PathVariable Long id,@RequestBody Produit produit){
        // Optional<Contact> contact = contactRespository.findById(id);
        produit.setId(id);
        iProduit.save(produit);
        return true;
    }

    @PreAuthorize("hasAuthority('ROLE_ADMINISTRATEUR')")
    @GetMapping("/deletes")
    public @ResponseBody boolean deletes(@RequestParam(name = "id") Long id){
        Optional<Produit> produit = iProduit.findById(id);
        if (produit.isPresent()) {
            iProduit.delete(produit.get());
            return true;
        }
        return false;
    }

    @PreAuthorize("hasAuthority('ROLE_ADMINISTRATEUR') or hasAuthority('ROLE_CLIENT')")
    @GetMapping("/search")
    public List<Produit> search(@RequestParam(name = "libelle",defaultValue = "") String libelle)
    {
        return iProduit.search("%"+libelle+"%");
    }
    @PreAuthorize("hasAuthority('ROLE_ADMINISTRATEUR') or hasAuthority('ROLE_CLIENT')")
    @GetMapping("/getProduitById/{id}")
    public ResponseEntity<?> getProduitById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(iProduit.findById(id));
    }
    @PreAuthorize("hasAuthority('ROLE_ADMINISTRATEUR')")
    @GetMapping("/SearchPrix")
    public List<Produit> searchPrix(@RequestParam(name = "prix",defaultValue = "") float prix)
    {
        return iProduit.searchPrix(prix);
    }
    @PreAuthorize("hasAuthority('ROLE_ADMINISTRATEUR')")
    @GetMapping(path = "/photoProduct/{id}",produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getPhotoName(@PathVariable("id") Long id) throws Exception{
        Produit p = iProduit.findById(id).get();
        return Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/imageprojet/productImage/"+p.getPhoto()));
    }
    @PreAuthorize("hasAuthority('ROLE_ADMINISTRATEUR')")
    @PostMapping(path = "/uploadPhoto/{id}")
    public void uploadPhoto(MultipartFile file, @PathVariable Long id) throws Exception{
        Produit p= iProduit.findById(id).get();
        p.setPhoto(file.getOriginalFilename());
        Files.write(Paths.get(System.getProperty("user.home")+"/imageprojet/productImage/"+p.getPhoto()),file.getBytes());
        iProduit.save(p);
    }

}

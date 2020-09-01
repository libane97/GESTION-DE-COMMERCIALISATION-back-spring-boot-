package dj.djib.odo.odocom.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Produit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String libelle;
    @Column
    private String description;
    @Column
    private int quantite ;
    @Column
    private float prix ;
    @Column
    private String photo;
    private boolean promotion;
    private boolean selected;
    private boolean available;
    @Transient
    private MultipartFile[] files;
    @ManyToOne
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;
   /* @JsonIgnore
    @ManyToMany(mappedBy = "produit")
    private List<Commande> commande;
*/
    @JsonIgnore
    @OneToMany(mappedBy = "produit")
    private List<DetailsCommand> detailsCommands;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public boolean isPromotion() {
        return promotion;
    }

    public void setPromotion(boolean promotion) {
        this.promotion = promotion;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public MultipartFile[] getFiles() {
        return files;
    }

    public void setFiles(MultipartFile[] files) {
        this.files = files;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

//    public List<Commande> getCommande() {
//        return commande;
//    }
//
//    public void setCommande(List<Commande> commande) {
//        this.commande = commande;
//    }

    public List<DetailsCommand> getDetailsCommands() {
        return detailsCommands;
    }

    public void setDetailsCommands(List<DetailsCommand> detailsCommands) {
        this.detailsCommands = detailsCommands;
    }
}

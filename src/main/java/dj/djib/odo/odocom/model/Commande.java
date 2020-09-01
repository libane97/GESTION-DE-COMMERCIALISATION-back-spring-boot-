package dj.djib.odo.odocom.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

@Entity
public class Commande implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date dateCommande;
    @Temporal(TemporalType.DATE)
    private Date dateCommandef;
    private int Numero;
    private boolean livraison;
    public int getNumero() {
        return Numero;
    }
    public void setNumero(int numero) {
        Numero = numero;
    }
//    @ManyToMany(cascade = {CascadeType.ALL})
//    @JoinTable(name = "command_produit",
//            joinColumns = @JoinColumn(name = "command_id"),
//            inverseJoinColumns = @JoinColumn(name = "produit_id"))
//    private List<Produit> produit;
//
//    public List<Produit> getProduit() {
//        return produit;
//    }
//
//    public void setProduit(List<Produit> produit) {
//        this.produit = produit;
//    }
    //@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private user user;
    @JsonIgnore
    @OneToMany(mappedBy = "commandes",cascade = {CascadeType.ALL})
    private List<Facture> factures;
    @JsonIgnore
    @OneToMany(mappedBy = "commandes",cascade = {CascadeType.ALL})
    private List<DetailsCommand> detailsCommands;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    public Date getDateCommandef() {
        return dateCommandef;
    }

    public void setDateCommandef(Date dateCommandef) {
        this.dateCommandef = dateCommandef;
    }

    public boolean isLivraison() {
        return livraison;
    }

    public void setLivraison(boolean livraison) {
        this.livraison = livraison;
    }

    public dj.djib.odo.odocom.model.user getUser() {
        return user;
    }

    public void setUser(dj.djib.odo.odocom.model.user user) {
        this.user = user;
    }

    public List<Facture> getFactures() {
        return factures;
    }

    public void setFactures(List<Facture> factures) {
        this.factures = factures;
    }

    public List<DetailsCommand> getDetailsCommands() {
        return detailsCommands;
    }

    public void setDetailsCommands(List<DetailsCommand> detailsCommands) {
        this.detailsCommands = detailsCommands;
    }
}

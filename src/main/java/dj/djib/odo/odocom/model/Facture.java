package dj.djib.odo.odocom.model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class Facture implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date DateDeFacture;
  //  @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "command_id")
    private Commande commandes;
    @JsonIgnore
    @OneToMany(mappedBy = "facture",cascade = {CascadeType.ALL})
    private List<Reglements> reglements;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateDeFacture() {
        return DateDeFacture;
    }

    public void setDateDeFacture(Date dateDeFacture) {
        DateDeFacture = dateDeFacture;
    }

    public Commande getCommandes() {
        return commandes;
    }

    public void setCommandes(Commande commandes) {
        this.commandes = commandes;
    }

    public List<Reglements> getReglements() {
        return reglements;
    }

    public void setReglements(List<Reglements> reglements) {
        this.reglements = reglements;
    }
}

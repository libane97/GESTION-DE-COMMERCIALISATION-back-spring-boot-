package dj.djib.odo.odocom.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Reglements implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date datereg;
    private float montantreg;
   // @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "facture_id")
    private Facture facture;
  //  @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private user user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDatereg() {
        return datereg;
    }

    public void setDatereg(Date datereg) {
        this.datereg = datereg;
    }

    public float getMontantreg() {
        return montantreg;
    }

    public void setMontantreg(float montantreg) {
        this.montantreg = montantreg;
    }

    public Facture getFacture() {
        return facture;
    }

    public void setFacture(Facture facture) {
        this.facture = facture;
    }

    public dj.djib.odo.odocom.model.user getUser() {
        return user;
    }

    public void setUser(dj.djib.odo.odocom.model.user user) {
        this.user = user;
    }
}

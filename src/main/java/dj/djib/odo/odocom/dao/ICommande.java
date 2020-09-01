package dj.djib.odo.odocom.dao;

import dj.djib.odo.odocom.model.Categorie;
import dj.djib.odo.odocom.model.DetailsCommand;
import dj.djib.odo.odocom.model.user;
import dj.djib.odo.odocom.model.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface ICommande extends JpaRepository<Commande,Long> {
    // public Commande findByDateCommande(String datecommand);
     @Query("SELECT c FROM  Commande c where c.dateCommande=:datecommand AND c.dateCommandef=:datecommandf")
     public List<Commande> SearchDateCommande(@Param("datecommand") Date datecommand,@Param("datecommandf") Date datecommandf);
     @Query("SELECT d FROM Commande c, DetailsCommand  d where c.id=d.commandes.id AND c.Numero=:numero")
     public List<DetailsCommand> SelectedFacture(@Param("numero") int numero);
}

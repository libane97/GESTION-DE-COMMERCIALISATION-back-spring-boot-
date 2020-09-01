package dj.djib.odo.odocom.dao;

import dj.djib.odo.odocom.model.DetailsCommand;
import dj.djib.odo.odocom.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface IDetailsCommands extends JpaRepository<DetailsCommand, Long> {
    @Query("SELECT d FROM  DetailsCommand d where d.commandes.dateCommande=:dateCommande")
     public List<DetailsCommand> search(@Param("dateCommande") Date dateCommande);
}

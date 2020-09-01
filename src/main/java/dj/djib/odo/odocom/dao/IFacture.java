package dj.djib.odo.odocom.dao;

import dj.djib.odo.odocom.model.Facture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFacture extends JpaRepository<Facture,Long> {
}

package dj.djib.odo.odocom.dao;

import dj.djib.odo.odocom.model.Reglements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReglements extends JpaRepository<Reglements,Long> {
}

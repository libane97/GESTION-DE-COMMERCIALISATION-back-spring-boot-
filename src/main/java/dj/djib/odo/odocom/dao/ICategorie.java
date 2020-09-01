package dj.djib.odo.odocom.dao;


import dj.djib.odo.odocom.model.Categorie;
import dj.djib.odo.odocom.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RepositoryRestResource
@CrossOrigin("*")
public interface ICategorie extends JpaRepository<Categorie,Long> {
    @Query("SELECT c FROM  Categorie c where c.libelle like :x")
    public List<Categorie> search(@Param("x") String mc);
    @RestResource(path = "/selectedLibelle")
    public List<Categorie> findByLibelle(@Param("mc") String mc);
}

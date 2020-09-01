package dj.djib.odo.odocom.dao;

import dj.djib.odo.odocom.model.Categorie;
import dj.djib.odo.odocom.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RepositoryRestResource
@CrossOrigin("*")
public interface IProduit extends JpaRepository<Produit,Long> {
    float prix = 0;
    public Produit findByLibelle(String libelle);
    public Produit findByPrix(float prix);
    @Query("SELECT p FROM  Produit p where p.libelle like :libelle")
    public List<Produit> search(@Param("libelle") String libelle);
    @Query("SELECT p FROM  Produit p where p.prix= :prix")
    public List<Produit> searchPrix(@Param("prix") float prix);
    @PreAuthorize("hasAuthority('ROLE_ADMINISTRATEUR') or hasAuthority('ROLE_CLIENT')")
    @RestResource(path = "/selectedProduct")
    public List<Produit> findBySelectedIsTrue();
}

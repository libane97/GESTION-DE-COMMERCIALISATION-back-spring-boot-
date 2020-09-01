package dj.djib.odo.odocom.dao;

import dj.djib.odo.odocom.model.Categorie;
import dj.djib.odo.odocom.model.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUser extends JpaRepository<user,Long> {
    public user findByUsername(String username);
    @Query("SELECT u FROM  user u where u.email  like :email")
    public List<user> searchByEmail(@Param("email") String email);
    @Query("SELECT u FROM  user u where u.telephone like :telephone")
    public List<user> searchBytelephone(@Param("telephone") String telephone);
}

package dj.djib.odo.odocom.dao;

import dj.djib.odo.odocom.model.Role;
import dj.djib.odo.odocom.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRole extends JpaRepository<Role,Long> {
    public Role findByName(RoleName roleName);
}

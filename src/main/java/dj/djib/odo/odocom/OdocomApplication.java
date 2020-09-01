package dj.djib.odo.odocom;

import dj.djib.odo.odocom.dao.ICategorie;
import dj.djib.odo.odocom.dao.IProduit;
import dj.djib.odo.odocom.model.Produit;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Random;

@SpringBootApplication
public class OdocomApplication implements CommandLineRunner{

    public static void main(String[] args) {
        SpringApplication.run(OdocomApplication.class, args);
    }

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ICategorie categoryRespository;
    @Autowired
    private IProduit productRespository;
    @Override
    public void run(String... args) throws Exception {
        System.out.println(passwordEncoder.encode("passer"));
   /*     Random random = new Random();
        categoryRespository.findAll().forEach(category -> {
            for (int i=0;i<2; i++){
                Produit p = new Produit();
                p.setLibelle(RandomString.make(18));
                p.setPrix(100+random.nextInt(100000));
                p.setQuantite(random.nextInt());
                p.setDescription(RandomString.make(18));
                p.setPhoto("unknown.png");
                p.setCategorie(category);
                productRespository.save(p);
            }
        });*/
    }

}

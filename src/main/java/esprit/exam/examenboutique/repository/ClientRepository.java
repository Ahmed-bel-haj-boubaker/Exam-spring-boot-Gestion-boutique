package esprit.exam.examenboutique.repository;

import esprit.exam.examenboutique.entities.Client;
import esprit.exam.examenboutique.entities.enums.Categorie;
import esprit.exam.examenboutique.entities.enums.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client,Long> {
    List<Client> findByBoutiquesId(Long idBoutique);

    List<Client>findByBoutiquesCategorie(Categorie categorie);

    int countClientsByGenre(Genre genre);
}

package esprit.exam.examenboutique.repository;

import esprit.exam.examenboutique.entities.Boutique;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoutiqueRepository extends JpaRepository<Boutique,Long> {
    List<Boutique>findBoutiquesByCentreCommercial_Id(Long idCentre);
}

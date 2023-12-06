package esprit.exam.examenboutique.service;

import esprit.exam.examenboutique.entities.Boutique;
import esprit.exam.examenboutique.entities.CentreCommercial;
import esprit.exam.examenboutique.entities.Client;
import esprit.exam.examenboutique.entities.enums.Categorie;

import java.util.List;

public interface IExamService {

    void ajouterCentre(CentreCommercial centreCommercial);
    void ajouterEtAffecterlisteBoutiques (List<Boutique> lb, Long idCentre);
    void ajouterEtAffecterClientBoutiques(Client client, List<Long> idBoutiques);
    List<Client> listeClients(Long idBoutique);
    List<Boutique> listeBoutiques(Long idCentre);
    List<Client> listeDeClientsParCategorie(Categorie categorie);
}

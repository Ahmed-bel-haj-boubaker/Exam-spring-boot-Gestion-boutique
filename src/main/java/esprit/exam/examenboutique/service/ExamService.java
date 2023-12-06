package esprit.exam.examenboutique.service;

import esprit.exam.examenboutique.entities.Boutique;
import esprit.exam.examenboutique.entities.CentreCommercial;
import esprit.exam.examenboutique.entities.Client;
import esprit.exam.examenboutique.entities.enums.Categorie;
import esprit.exam.examenboutique.repository.BoutiqueRepository;
import esprit.exam.examenboutique.repository.CentreCommercialRepository;
import esprit.exam.examenboutique.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ExamService implements IExamService{

    private final BoutiqueRepository boutiqueRepository;
    private final CentreCommercialRepository centreCommercialRepository;
    private final ClientRepository clientRepository;

    @Override
    public void ajouterCentre(CentreCommercial centreCommercial) {

     Boutique boutique =addBoutique(centreCommercial);
     boutique.setCentreCommercial(centreCommercial);
     centreCommercialRepository.save(centreCommercial);
    }
    private Boutique addBoutique(CentreCommercial centreCommercial){
        Set<Boutique> boutiques =centreCommercial.getBoutiques();
        for (Boutique boutique : boutiques){
            return boutiqueRepository.save(boutique);
        }
        return null;
    }
    @Override
    public void ajouterEtAffecterlisteBoutiques(List<Boutique> lb, Long idCentre) {
        CentreCommercial centreCommercial = centreCommercialRepository.findById(idCentre).orElse(null);
        for (Boutique boutique : lb){
            boutique.setCentreCommercial(centreCommercial);

        }
        boutiqueRepository.saveAll(lb);

    }

    @Override
    public void ajouterEtAffecterClientBoutiques(Client client, List<Long> idBoutiques) {
        clientRepository.save(client);
        List<Boutique> boutiques = boutiqueRepository.findAll();

        for (Boutique boutique : boutiques){
            for (Long id : idBoutiques){
                if (boutique.getId().equals(id)){
                    boutique.getClients().add(client);
                    clientRepository.save(client);
                }
            }
        }
    }

    @Override
    public List<Client> listeClients(Long idBoutique) {
     return clientRepository.findByBoutiquesId(idBoutique);
    }

    @Override
    public List<Boutique> listeBoutiques(Long idCentre) {
        return boutiqueRepository.findBoutiquesByCentreCommercial_Id(idCentre);
    }

    @Override
    public List<Client> listeDeClientsParCategorie(Categorie categorie) {
        return clientRepository.findByBoutiquesCategorie(categorie);
    }


}

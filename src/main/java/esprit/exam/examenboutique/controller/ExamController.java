package esprit.exam.examenboutique.controller;

import esprit.exam.examenboutique.entities.Boutique;
import esprit.exam.examenboutique.entities.CentreCommercial;
import esprit.exam.examenboutique.entities.Client;
import esprit.exam.examenboutique.entities.enums.Categorie;
import esprit.exam.examenboutique.entities.enums.Genre;
import esprit.exam.examenboutique.repository.ClientRepository;
import esprit.exam.examenboutique.service.IExamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("examBoutique")
public class ExamController {
    private final ClientRepository clientRepository;
    private final IExamService iExamService;

    @PostMapping("/add-centre")
    @ResponseBody
    public void ajouCentre(@RequestBody CentreCommercial centre) {
        iExamService.ajouterCentre(centre);
    }

    @PostMapping("/add-listeBoutique/{idCentre}")
    @ResponseBody
    void ajouterEtaffecterListeboutique(@RequestBody List<Boutique> lb, @PathVariable("idCentre") Long idCentre) {
        iExamService.ajouterEtAffecterlisteBoutiques(lb, idCentre);
    }

    @PostMapping("/add-Client")
    public void ajouterEtAffecterClientBoutiques(@RequestBody Client client, @RequestParam(value="idBoutiques") List<Long> idBoutiques) {
        iExamService.ajouterEtAffecterClientBoutiques(client, idBoutiques);
    }

    @GetMapping("/list-client/{idBoutique}")
    @ResponseBody
    List<Client> listedeClients(@PathVariable("idBoutique") Long idBoutique){
        return iExamService.listeClients(idBoutique);
    }
    @GetMapping("/liste-boutique/{idCentre}")
    @ResponseBody
    List<Boutique> listedeBoutiques(@PathVariable("idCentre") Long idCentre){
        return iExamService.listeBoutiques(idCentre);
    }
    @GetMapping("/liste-clientParCategorie/{categorie}")
    List<Client> listeDeClientsParCategorie(@PathVariable("categorie") Categorie categorie){
        return iExamService.listeDeClientsParCategorie(categorie);
    }

    @Scheduled(cron = "*/30 * * * * *")
    void nbreClientParGenre(){

        int nbrFeminin = clientRepository.countClientsByGenre(Genre.FEMININ);
        int nbrmasculin = clientRepository.countClientsByGenre(Genre.MASCULIN);
        log.info("nombre des client feminin :"+nbrFeminin);
        log.info("nombre des client masculin :"+nbrmasculin);
    }

}

package be.condorcet.projet.webservices;

import be.condorcet.projet.modele.Cours;
import be.condorcet.projet.services.InterfCoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*", allowedHeaders = "*",exposedHeaders = "*")
@RestController
@RequestMapping("/cours")
public class RestCours {
    @Autowired
    private InterfCoursService coursServiceImpl;


    //-------------------Retrouver le cours correspondant à un id donné--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Cours> getCours(@PathVariable(value = "id") int id)  throws Exception{
        System.out.println("recherche du cours d' id " + id);
        Cours cours = coursServiceImpl.read(id);
        return new ResponseEntity<>(cours, HttpStatus.OK);
    }

    //-------------------Retrouver les cours portant un nom donné--------------------------------------------------------
    @RequestMapping(value = "/matiere={matiere}", method = RequestMethod.GET)
    public ResponseEntity<List<Cours>> listCoursNom(@PathVariable(value="matiere") String matiere) throws Exception{
        System.out.println("recherche de "+matiere);
        List<Cours> lc;
        lc = coursServiceImpl.read(matiere);
        return new ResponseEntity<>(lc, HttpStatus.OK);
    }
    //-------------------Retrouver le cours correspondant à un triplet (nom,prénom,tel) unique donné--------------------------------------------------------
    @RequestMapping(value = "/{matiere}/{nbreheures}", method = RequestMethod.GET)
    public ResponseEntity<Cours> getCoursUnique(@PathVariable(value = "matiere") String matiere,
                                                  @PathVariable(value = "nbreheures") int nbreheures) throws Exception{
        System.out.println("recherche du cours "+matiere+" "+nbreheures+" heures");
        Cours cours = coursServiceImpl.read(matiere,nbreheures);
        return new ResponseEntity<>(cours, HttpStatus.OK);
    }
    //-------------------Créer un cours--------------------------------------------------------
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Cours> createCours(@RequestBody Cours cours) throws Exception {
        System.out.println("Création de Cours " + cours.getMatiere());
        coursServiceImpl.create(cours);
        return new ResponseEntity<>(cours, HttpStatus.OK);
    }

    //-------------------Mettre à jour un cours d'un id donné--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Cours> majCours(@PathVariable(value = "id") int id,@RequestBody Cours nouvcours) throws Exception{
        System.out.println("maj de cours id =  " + id);
        nouvcours.setId(id);
        Cours clact = coursServiceImpl.update(nouvcours);
        return new ResponseEntity<>(clact, HttpStatus.OK);
    }

    //-------------------Effacer un cours d'un id donné--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteCours(@PathVariable(value = "id") int id) throws Exception{
        System.out.println("effacement du cours d'id " + id);
        Cours cours = coursServiceImpl.read(id);
        coursServiceImpl.delete(cours);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //-------------------Retrouver tous les cours --------------------------------------------------------
    @RequestMapping(value =  "/all",method = RequestMethod.GET)
    public ResponseEntity<List<Cours>> listCours() throws Exception{
        System.out.println("recherche de tous les cours");
        return new ResponseEntity<>(coursServiceImpl.all(), HttpStatus.OK);
    }

     //-------------------Gérer les erreurs--------------------------------------------------------
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Void>  handleIOException(Exception ex) {
        System.out.println("erreur : "+ex.getMessage());
        return ResponseEntity.notFound().header("error",ex.getMessage()).build();
    }
}


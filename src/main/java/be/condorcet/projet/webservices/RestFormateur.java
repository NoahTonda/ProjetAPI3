package be.condorcet.projet.webservices;

import be.condorcet.projet.modele.Formateur;
import be.condorcet.projet.services.InterfFormateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*",exposedHeaders = "*")
@RestController
@RequestMapping("/formateur")
public class RestFormateur {
    @Autowired
    private InterfFormateurService formateurServiceImpl;


    //-------------------Retrouver le formateur correspondant à un id donné--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Formateur> getFormateur(@PathVariable(value = "id") int id)  throws Exception{
        System.out.println("recherche du formateur d' id " + id);
        Formateur formateur = formateurServiceImpl.read(id);
        return new ResponseEntity<>(formateur, HttpStatus.OK);
    }

    //-------------------Retrouver les formateur correspondant à un nom donné--------------------------------------------------------
    @RequestMapping(value = "/nom={nom}", method = RequestMethod.GET)
    public ResponseEntity<List<Formateur>> listFormateurNom(@PathVariable(value="nom") String mail) throws Exception{
        System.out.println("recherche de "+mail);
        List<Formateur> lf;
        lf = formateurServiceImpl.read(mail);
        return new ResponseEntity<>(lf, HttpStatus.OK);
    }
    //-------------------Retrouver le formateur correspondant à un triplet (nom,prénom,mail) unique donné--------------------------------------------------------
    @RequestMapping(value = "/{nom}/{prenom}/{mail}", method = RequestMethod.GET)
    public ResponseEntity<Formateur> getFormateurUnique(@PathVariable(value = "nom") String nom,
                                                        @PathVariable(value = "prenom") String prenom,
                                                        @PathVariable(value = "mail") String mail) throws Exception{
        System.out.println("recherche du formateur "+nom+" "+prenom+" "+mail);
        Formateur formateur = formateurServiceImpl.read(nom,prenom,mail);
        return new ResponseEntity<>(formateur, HttpStatus.OK);
    }
    //-------------------Créer un formateur--------------------------------------------------------
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Formateur> createFormateur(@RequestBody Formateur formateur) throws Exception {
        System.out.println("Création de Formateur " + formateur.getNom());
        formateurServiceImpl.create(formateur);
        return new ResponseEntity<>(formateur, HttpStatus.OK);
    }

    //-------------------Mettre à jour un formateur d'un id donné--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Formateur> majFormateur(@PathVariable(value = "id") int id,@RequestBody Formateur nouvformateur) throws Exception{
        System.out.println("maj de formateur id =  " + id);
        nouvformateur.setId(id);
        Formateur clact = formateurServiceImpl.update(nouvformateur);
        return new ResponseEntity<>(clact, HttpStatus.OK);
    }

    //-------------------Effacer un formateur d'un id donné--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteFormateur(@PathVariable(value = "id") int id) throws Exception{
        System.out.println("effacement du formateur d'id " + id);
        Formateur formateur = formateurServiceImpl.read(id);
        formateurServiceImpl.delete(formateur);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //-------------------Retrouver tous les formateur --------------------------------------------------------
    @RequestMapping(value =  "/all",method = RequestMethod.GET)
    public ResponseEntity<List<Formateur>> listFormateur() throws Exception{
        System.out.println("recherche de tous les formateur");
        return new ResponseEntity<>(formateurServiceImpl.all(), HttpStatus.OK);
    }

     //-------------------Gérer les erreurs--------------------------------------------------------
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Void>  handleIOException(Exception ex) {
        System.out.println("erreur : "+ex.getMessage());
        return ResponseEntity.notFound().header("error",ex.getMessage()).build();
    }
}


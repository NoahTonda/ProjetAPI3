package be.condorcet.projet.webservices;

import be.condorcet.projet.modele.Local;
import be.condorcet.projet.services.InterfLocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*",exposedHeaders = "*")
@RestController
@RequestMapping("/local")
public class RestLocal {
    @Autowired
    private InterfLocalService localServiceImpl;


    //-------------------Retrouver le local correspondant à un id donné--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Local> getLocal(@PathVariable(value = "id") int id)  throws Exception{
        System.out.println("recherche du local d' id " + id);
        Local local = localServiceImpl.read(id);
        return new ResponseEntity<>(local, HttpStatus.OK);
    }

    //-------------------Retrouver les local portant un nom donné--------------------------------------------------------
    @RequestMapping(value = "/sigle={sigle}", method = RequestMethod.GET)
    public ResponseEntity<List<Local>> listLocalNom(@PathVariable(value="sigle") String sigle) throws Exception{
        System.out.println("recherche de "+sigle);
        List<Local> ll;
        ll = localServiceImpl.read(sigle);
        return new ResponseEntity<>(ll, HttpStatus.OK);
    }
    //-------------------Retrouver le local correspondant à un triplet (nom,prénom,tel) unique donné--------------------------------------------------------
    @RequestMapping(value = "/{sigle}/{places}", method = RequestMethod.GET)
    public ResponseEntity<Local> getLocalUnique(@PathVariable(value = "sigle") String sigle,
                                                  @PathVariable(value = "places") int places) throws Exception{
        System.out.println("recherche du local "+sigle+" "+places+" places");
        Local local = localServiceImpl.read(sigle,places);
        return new ResponseEntity<>(local, HttpStatus.OK);
    }
    //-------------------Créer un local--------------------------------------------------------
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Local> createLocal(@RequestBody Local local) throws Exception {
        System.out.println("Création de local " + local.getSigle());
        localServiceImpl.create(local);
        return new ResponseEntity<>(local, HttpStatus.OK);
    }

    //-------------------Mettre à jour un local d'un id donné--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Local> majLocal(@PathVariable(value = "id") int id,@RequestBody Local nouvlocal) throws Exception{
        System.out.println("maj de local id =  " + id);
        nouvlocal.setId(id);
        Local clact = localServiceImpl.update(nouvlocal);
        return new ResponseEntity<>(clact, HttpStatus.OK);
    }

    //-------------------Effacer un local d'un id donné--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteLocal(@PathVariable(value = "id") int id) throws Exception{
        System.out.println("effacement du local d'id " + id);
        Local local = localServiceImpl.read(id);
        localServiceImpl.delete(local);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //-------------------Retrouver tous les local --------------------------------------------------------
    @RequestMapping(value =  "/all",method = RequestMethod.GET)
    public ResponseEntity<List<Local>> listLocal() throws Exception{
        System.out.println("recherche de tous les locaux");
        return new ResponseEntity<>(localServiceImpl.all(), HttpStatus.OK);
    }

     //-------------------Gérer les erreurs--------------------------------------------------------
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Void>  handleIOException(Exception ex) {
        System.out.println("erreur : "+ex.getMessage());
        return ResponseEntity.notFound().header("error",ex.getMessage()).build();
    }
}


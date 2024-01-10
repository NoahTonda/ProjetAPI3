/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.condorcet.projet.webservices;


import be.condorcet.projet.modele.Cours;
import be.condorcet.projet.modele.Formateur;
import be.condorcet.projet.modele.Local;
import be.condorcet.projet.modele.SessionCours;
import be.condorcet.projet.services.InterfCoursService;
import be.condorcet.projet.services.InterfFormateurService;
import be.condorcet.projet.services.InterfLocalService;
import be.condorcet.projet.services.InterfSessionCoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*",exposedHeaders = "*")
@RestController
@RequestMapping("/sessionCours")
public class RestSessionCours {

    @Autowired
    private InterfSessionCoursService sessionCoursServiceImpl;
    @Autowired
    private InterfCoursService coursServiceImpl;
    @Autowired
    private InterfFormateurService formateurServiceImpl;
    @Autowired
    private InterfLocalService localServiceImpl;

    //-------------------Retrouver la session de cours correspondant à un n° donné--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<SessionCours> getSessionCours(@PathVariable(value = "id") int id)  throws Exception{
        System.out.println("recherche de la session de cours n° " + id);
        SessionCours sc = sessionCoursServiceImpl.read(id);
        return new ResponseEntity<>(sc, HttpStatus.OK);
    }

    //-------------------Retrouver la session de cours correspondant à un n° donné--------------------------------------------------------
    @RequestMapping(value = "/idcours={id}", method = RequestMethod.GET)
    public ResponseEntity<List<SessionCours>> getSessionCoursCours(@PathVariable(value = "id") int id)  throws Exception{
        System.out.println("recherche des session de cours du cours d'id " + id);
        Cours c = coursServiceImpl.read(id);
        List<SessionCours> lsc = sessionCoursServiceImpl.getSessionsCoursCours(c);
        return new ResponseEntity<>(lsc, HttpStatus.OK);
    }

    //-------------------Retrouver la session de cours correspondant à un n° donné--------------------------------------------------------
    @RequestMapping(value = "/idformateur={id}", method = RequestMethod.GET)
    public ResponseEntity<List<SessionCours>> getSessionCoursFormateur(@PathVariable(value = "id") int id)  throws Exception{
        System.out.println("recherche des session de cours du formateur d'id " + id);
        Formateur f = formateurServiceImpl.read(id);
        List<SessionCours> lsc = sessionCoursServiceImpl.getSessionsCoursForm(f);
        return new ResponseEntity<>(lsc, HttpStatus.OK);
    }

    //-------------------Retrouver la session de cours correspondant à un n° donné--------------------------------------------------------
    @RequestMapping(value = "/idlocal={id}", method = RequestMethod.GET)
    public ResponseEntity<List<SessionCours>> getSessionCoursLocal(@PathVariable(value = "id") int id)  throws Exception{
        System.out.println("recherche des session de courss du local d'id " + id);
        Local l = localServiceImpl.read(id);
        List<SessionCours> lsc = sessionCoursServiceImpl.getSessionsCoursLocal(l);
        return new ResponseEntity<>(lsc, HttpStatus.OK);
    }

    //-------------------Retrouver les sessions de cours correspondant à une date donnée--------------------------------------------------------
    @RequestMapping(value = "/date={date}", method = RequestMethod.GET)
    public ResponseEntity<List<SessionCours>> getSessionCoursDate(@PathVariable(value = "date") Date date)  throws Exception{
        System.out.println("recherche des session de cours du " + date);
        List<SessionCours> lsc = sessionCoursServiceImpl.getSessionCoursDate(date);
        return new ResponseEntity<>(lsc, HttpStatus.OK);
    }

    //-------------------Créer une session de cours--------------------------------------------------------
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<SessionCours> createSessionCours(@RequestBody SessionCours sc) throws Exception {
        System.out.println("Création de la session de cours du cours" + sc.getCours());
        sessionCoursServiceImpl.create(sc);
        return new ResponseEntity<>(sc, HttpStatus.OK);
    }

    //-------------------Mettre à jour une session de cours d'un n° donné--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
     public ResponseEntity<SessionCours> majCours(@PathVariable(value = "id") int id,@RequestBody SessionCours nouvsc) throws Exception{
        System.out.println("maj de la commade n° " + id);
        nouvsc.setId(id);
        SessionCours scact = sessionCoursServiceImpl.update(nouvsc);
        return new ResponseEntity<>(scact, HttpStatus.OK);
    }

    //-------------------Effacer une session de cours d'un id donné--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteSessionCours(@PathVariable(value = "id") int id) throws Exception{
        System.out.println("effacement de la session de cours n°" + id);
        SessionCours sc = sessionCoursServiceImpl.read(id);
        sessionCoursServiceImpl.delete(sc);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //-------------------Retrouver toutes les sessions de cours --------------------------------------------------------
    @RequestMapping(value =  "/all",method = RequestMethod.GET)
    public ResponseEntity<List<SessionCours>> listSessionCours() throws Exception{
        System.out.println("recherche de toutes les sessions de cours");
        return new ResponseEntity<>(sessionCoursServiceImpl.all(), HttpStatus.OK);
    }


    //-------------------Gérer les erreurs--------------------------------------------------------
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Void>  handleIOException(Exception ex) {
        System.out.println("erreur : "+ex.getMessage());
        return ResponseEntity.notFound().header("error",ex.getMessage()).build();
    }
}

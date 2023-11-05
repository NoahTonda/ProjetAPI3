package be.condorcet.projet.services;

import be.condorcet.projet.modele.Cours;
import be.condorcet.projet.modele.Formateur;
import be.condorcet.projet.modele.Local;
import be.condorcet.projet.modele.SessionCours;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SessionCoursServiceImplTest {
    @Autowired
    private InterfSessionCoursService sessionCoursServiceImpl;
    @Autowired
    private InterfFormateurService formateurServiceImpl;
    @Autowired
    private InterfCoursService coursServiceImpl;
    @Autowired
    private InterfLocalService localServiceImpl;

    SessionCours sessionCours;
    Cours cours;
    Local local;
    Formateur formateur;
    Date debut=Date.valueOf(LocalDate.of(2023,11,4));
    Date fin=Date.valueOf(LocalDate.of(2023,12,4));

    @BeforeEach
    void setUp() {

        try{
            cours=new Cours("MatiereTest",100);
            coursServiceImpl.create(cours);
            System.out.println("création du cours : "+cours);
            formateur=new Formateur("MailTest","NomTest","PrenomTest");
            formateurServiceImpl.create(formateur);
            System.out.println("création du formateur : "+formateur);
            local=new Local("SigleTest",60);
            localServiceImpl.create(local);
            System.out.println("création du local : "+local);
            sessionCours = new SessionCours(null,debut ,fin,20, cours,formateur,local);
            sessionCoursServiceImpl.create(sessionCours);
            System.out.println("création du sessionCours : "+sessionCours);
        }
        catch (Exception e){
            System.out.println("erreur de création de la session de cours : "+sessionCours +" erreur : "+e);
        }
    }

    @AfterEach
    void tearDown() {
        try {
            if (sessionCours != null) {
                sessionCoursServiceImpl.delete(sessionCours);
                System.out.println("effacement de la session de cours : " + sessionCours);
            }
        } catch (Exception e) {
            System.out.println("erreur d'effacement de la session de cours :" + sessionCours + " erreur : " + e);
        }
        try {
            if (cours != null) {
                coursServiceImpl.delete(cours);
                System.out.println("effacement du cours : " + cours);
            }
        } catch (Exception e) {
            System.out.println("erreur d'effacement du cours :" + cours + " erreur : " + e);
        }
        try {
            if (formateur != null) {
                formateurServiceImpl.delete(formateur);
                System.out.println("effacement du formateur : " + formateur);
            }
        } catch (Exception e) {
            System.out.println("erreur d'effacement du formateur :" + formateur + " erreur : " + e);
        }
        try {
            if (local != null) {
                localServiceImpl.delete(local);
                System.out.println("effacement du local : " + local);
            }
        } catch (Exception e) {
            System.out.println("erreur d'effacement du local :" + local + " erreur : " + e);
        }

    }

    @Test
    void create() {
        assertNotEquals(0,sessionCours.getId(),"id sessionCours non incrémenté");
        assertEquals(debut,sessionCours.getDateDebut(),"DateDebutTest sessionCours non enregistré : "+sessionCours.getDateDebut()+ " au lieu de DateDebutTest");
        assertEquals(fin,sessionCours.getDateFin(),"DateFinTest sessionCours non enregistré : "+sessionCours.getDateFin()+ " au lieu de DateFinTest");
        assertEquals(20,sessionCours.getNbreInscrits(),"nombre inscrits sessionCours non enregistré : "+sessionCours.getNbreInscrits()+ " au lieu de 20");
        assertEquals("MatiereTest",sessionCours.getCours().getMatiere(),"Cours sessionCours non enregistré : "+sessionCours.getCours().getMatiere()+ " au lieu de MatiereTest");
        assertEquals("NomTest",sessionCours.getFormateur().getNom(),"Formateur sessionCours non enregistré : "+sessionCours.getFormateur().getNom()+ " au lieu de NomTest");
        assertEquals("SigleTest",sessionCours.getLocal().getSigle(),"Local sessionCours non enregistré : "+sessionCours.getLocal().getSigle()+ " au lieu de SigleTest");
    }


    @Test
    void read() {
        try{
            int idsessionCours=sessionCours.getId();
            SessionCours sessionCours2=sessionCoursServiceImpl.read(idsessionCours);
            assertEquals(debut,sessionCours.getDateDebut(),"Dates de début différentes "+"DateDebutTest"+"-"+sessionCours.getDateDebut()+ " au lieu de DateDebutTest");
            assertEquals(fin,sessionCours.getDateFin(),"Dates de fin différentes "+"DateFinTest"+"-"+sessionCours.getDateFin()+ " au lieu de DateFinTest");
            assertEquals(20,sessionCours.getNbreInscrits(),"nombre inscrits différents "+"20"+"-"+sessionCours.getNbreInscrits()+ " au lieu de 20");
            assertEquals("MatiereTest",sessionCours.getCours().getMatiere(),"Cours différents "+"MatiereTest"+"-"+sessionCours.getCours().getMatiere()+ " au lieu de MatiereTest");
            assertEquals("NomTest",sessionCours.getFormateur().getNom(),"Formateurs différents "+"NomTest"+"-"+sessionCours.getFormateur().getNom()+ " au lieu de NomTest");
            assertEquals("SigleTest",sessionCours.getLocal().getSigle(),"Locaux différents "+"SigleTest"+"-"+sessionCours.getLocal().getSigle()+ " au lieu de SigleTest");

        }
        catch (Exception e){
            fail("recherche infructueuse "+e);
        }
    }

    @Test
    void update() {
        try{
            Date debut2=Date.valueOf(LocalDate.of(2023,11,5));
            Date fin2=Date.valueOf(LocalDate.of(2023,12,5));
            sessionCours.setDateDebut(debut2);
            sessionCours.setDateFin(fin2);
            sessionCours.setNbreInscrits(40);
            sessionCours = sessionCoursServiceImpl.update(sessionCours);
            assertEquals(debut2,sessionCours.getDateDebut(),"Dates de début différentes "+debut2+"-"+sessionCours.getDateDebut()+ " au lieu de "+debut2);
            assertEquals(fin2,sessionCours.getDateFin(),"Dates de fin différentes "+fin2+"-"+sessionCours.getDateFin()+ " au lieu de "+fin2);
            assertEquals(40,sessionCours.getNbreInscrits(),"Prenoms différentes : "+40+"-"+sessionCours.getNbreInscrits()+ " au lieu de 40");
        }
        catch(Exception e){
            fail("erreur de mise à jour "+e);
        }
    }

    @Test
    void delete() {
        try{
            sessionCoursServiceImpl.delete(sessionCours);    Assertions.assertThrows(Exception.class, () -> {
                sessionCoursServiceImpl.read(sessionCours.getId());
            },"record non effacé");
        }
        catch(Exception e){
            fail("erreur d'effacement "+e);
        }
    }


    @Test
    void all(){
        try {
            List<SessionCours> lc = sessionCoursServiceImpl.all();
            assertNotEquals(0,lc.size(),"la liste ne contient aucun élément");
        }catch (Exception e){
            fail("erreur de recherche de toutes les session de cours "+e);
        }
    }

    @Test
    void affCollectionCours() {
        try {
            Collection<SessionCours> lsc = sessionCoursServiceImpl.getSessionsCoursCours(cours);
            boolean trouve = false;
            for (SessionCours sc : lsc) {
                if (sc.getId().equals(sessionCours.getId())) {
                    trouve = true;
                    break;
                }
            }
            assertTrue(trouve, "session absente de la liste du cours");
        } catch (Exception e) {
            fail("Erreur de recherche " + e);

        }
    }
    @Test
    void affCollectionFormateur() {
        try {
            Collection<SessionCours> lsc = sessionCoursServiceImpl.getSessionsCoursForm(formateur);
            boolean trouve = false;
            for (SessionCours sc : lsc) {
                if (sc.getId().equals(sessionCours.getId())) {
                    trouve = true;
                    break;
                }
            }
            assertTrue(trouve, "session absente de la liste du formateur");
        } catch (Exception e) {
            fail("Erreur de recherche " + e);

        }
    }
}
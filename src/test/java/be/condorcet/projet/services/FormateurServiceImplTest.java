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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest

class FormateurServiceImplTest {
    @Autowired
    private InterfCoursService coursServiceImpl;
    @Autowired
    private InterfFormateurService formateurServiceImpl;
    @Autowired
    private InterfSessionCoursService sessionCoursServiceImpl;
    @Autowired
    private InterfLocalService localServiceImpl;
    Formateur formateur;

    @BeforeEach
    void setUp() {

        try{
            formateur = new Formateur(null,"MailTest","NomTest", "PrenomTest",new ArrayList<>());
            formateurServiceImpl.create(formateur);
            System.out.println("création du formateur : "+formateur);
        }
        catch (Exception e){
            System.out.println("erreur de création du formateur : "+formateur +" erreur : "+e);
        }
    }

    @AfterEach
    void tearDown() {
        try {
            formateurServiceImpl.delete(formateur);
            System.out.println("effacement du formateur : "+formateur);
        }
        catch (Exception e){
            System.out.println("erreur d'effacement du formateur :"+formateur+" erreur : "+e);
        }
    }

    @Test
    void create() {
        assertNotEquals(0,formateur.getId(),"id formateur non incrémenté");
        assertEquals("MailTest",formateur.getMail(),"Mail formateur non enregistré : "+formateur.getMail()+ " au lieu de MailTest");
        assertEquals("NomTest",formateur.getNom(),"Nom formateur non enregistré : "+formateur.getNom()+ " au lieu de NomTest");
        assertEquals("PrenomTest",formateur.getPrenom(),"Prenom formateur non enregistré : "+formateur.getPrenom()+ " au lieu de PrenomTest");
    }


    @Test
    void read() {
        try{
            int idformateur=formateur.getId();
            Formateur formateur2=formateurServiceImpl.read(idformateur);
            assertEquals("MailTest",formateur2.getMail(),"Mails différentes "+"MailTest"+"-"+formateur2.getMail());
            assertEquals("NomTest",formateur2.getNom(),"Noms différentes "+"NomTest"+"-"+formateur2.getNom()+ " au lieu de NomTest");
            assertEquals("PrenomTest",formateur2.getPrenom(),"Prenoms différentes : "+"PrenomTest"+"-"+formateur2.getPrenom()+ " au lieu de PrenomTest");
        }
        catch (Exception e){
            fail("recherche infructueuse "+e);
        }
    }

    @Test
    void update() {
        try{
            formateur.setMail("MailTest2");
            formateur.setNom("NomTest2");
            formateur.setPrenom("PrenomTest2");
            formateur = formateurServiceImpl.update(formateur);
            assertEquals("MailTest2",formateur.getMail(),"Mails différents "+"MailTest2-"+formateur.getMail());
            assertEquals("NomTest2",formateur.getNom(),"Noms différentes "+"NomTest2"+"-"+formateur.getNom()+ " au lieu de NomTest");
            assertEquals("PrenomTest2",formateur.getPrenom(),"Prenoms différentes : "+"PrenomTest2"+"-"+formateur.getPrenom()+ " au lieu de PrenomTest");
        }
        catch(Exception e){
            fail("erreur de mise à jour "+e);
        }
    }
    @Test
    void rechNom() {
        List<Formateur> lf = formateurServiceImpl.read("NomTest");
        boolean trouve=false;
        for(Formateur f : lf){
            if(f.getNom().startsWith("NomTest"))  trouve=true;
            else fail("un record ne correspond pas , nom = "+f.getNom());
        }
        assertTrue(trouve,"record non trouvé dans la liste");
    }

    @Test
    void delete() {
        try{
            formateurServiceImpl.delete(formateur);    Assertions.assertThrows(Exception.class, () -> {
                formateurServiceImpl.read(formateur.getId());
            },"record non effacé");
        }
        catch(Exception e){
            fail("erreur d'effacement "+e);
        }
    }


    @Test
    void all(){
        try {
            List<Formateur> lc = formateurServiceImpl.all();
            assertNotEquals(0,lc.size(),"la liste ne contient aucun élément");
        }catch (Exception e){
            fail("erreur de recherche de tous les formateur "+e);
        }
    }

    @Test
    void delAvecSession() {
        try {
            Cours cours = new Cours("test",30);
            coursServiceImpl.create(cours);

            Local local = new Local("f30",20);
            localServiceImpl.create(local);

            SessionCours sc = new SessionCours(Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()), 10, cours, formateur, local);
            sessionCoursServiceImpl.create(sc);

            formateur.getSession().add(sc);
            formateurServiceImpl.update(formateur);

            Assertions.assertThrows(Exception.class, () -> {
                formateurServiceImpl.delete(formateur);
            }, "effacement réalisé malgré session liée");

            sessionCoursServiceImpl.delete(sc);

            coursServiceImpl.delete(cours);
            localServiceImpl.delete(local);
        } catch (Exception e) {
            fail("erreur de création de session" + e);
        }
    }
}
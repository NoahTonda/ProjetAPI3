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
class LocalServiceImplTest {

    @Autowired
    private InterfCoursService coursServiceImpl;
    @Autowired
    private InterfFormateurService formateurServiceImpl;
    @Autowired
    private InterfSessionCoursService sessionCoursServiceImpl;
    @Autowired
    private InterfLocalService localServiceImpl;
    Local local;

    @BeforeEach
    void setUp() {

        try{
            local = new Local(null,"SigleTest",100, new ArrayList<>());
            local=localServiceImpl.create(local);
            System.out.println("création du local : "+local);
        }
        catch (Exception e){
            System.out.println("erreur de création du local : "+local +" erreur : "+e);
        }
    }

    @AfterEach
    void tearDown() {
        try {
            localServiceImpl.delete(local);
            System.out.println("effacement du local : "+local);
        }
        catch (Exception e){
            System.out.println("erreur d'effacement du local :"+local+" erreur : "+e);
        }
    }

    @Test
    void create() {
        assertNotEquals(0,local.getId(),"id local non incrémenté");
        assertEquals("SigleTest",local.getSigle(),"Sigle local non enregistré : "+local.getSigle()+ " au lieu de SigleTest");
        assertEquals(100,local.getPlaces(),"Places local non enregistré : "+local.getPlaces()+ " au lieu de 100");
    }


    @Test
    void read() {
        try{
            int idlocal=local.getId();
            Local local2=localServiceImpl.read(idlocal);
            assertEquals("SigleTest",local2.getSigle(),"Sigles différents "+"SigleTest"+"-"+local2.getSigle());

        }
        catch (Exception e){
            fail("recherche infructueuse "+e);
        }
    }

    @Test
    void update() {
        try{
            local.setSigle("SigleTest2");
            local.setPlaces(150);
            local = localServiceImpl.update(local);
            assertEquals("SigleTest2",local.getSigle(),"Sigles différents "+"SigleTest2-"+local.getSigle());

        }
        catch(Exception e){
            fail("erreur de mise à jour "+e);
        }
    }

    @Test
    void delete() {
        try{
            localServiceImpl.delete(local);    Assertions.assertThrows(Exception.class, () -> {
                localServiceImpl.read(local.getId());
            },"record non effacé");
        }
        catch(Exception e){
            fail("erreur d'effacement "+e);
        }
    }


    @Test
    void all(){
        try {
            List<Local> lc = localServiceImpl.all();
            assertNotEquals(0,lc.size(),"la liste ne contient aucun élément");
        }catch (Exception e){
            fail("erreur de recherche de tous les local "+e);
        }
    }
    @Test
    void delAvecSession() {
        try {
            Cours cours = new Cours("test",30);
            coursServiceImpl.create(cours);

            Formateur formateur = new Formateur("test","test","test");
            formateurServiceImpl.create(formateur);

            SessionCours sc = new SessionCours(Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()), 10, cours, formateur, local);
            sessionCoursServiceImpl.create(sc);

            local.getSessions().add(sc);
            localServiceImpl.update(local);

            Assertions.assertThrows(Exception.class, () -> {
                localServiceImpl.delete(local);
            }, "effacement réalisé malgré session liée");

            sessionCoursServiceImpl.delete(sc);

            coursServiceImpl.delete(cours);
            formateurServiceImpl.delete(formateur);
        } catch (Exception e) {
            fail("erreur de création de session" + e);
        }
    }
}
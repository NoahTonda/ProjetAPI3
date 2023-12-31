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

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CoursServiceImplTest {
    @Autowired
    private InterfCoursService coursServiceImpl;
    @Autowired
    private InterfFormateurService formateurServiceImpl;
    @Autowired
    private InterfSessionCoursService sessionCoursServiceImpl;
    @Autowired
    private InterfLocalService localServiceImpl;
    Cours cours;

    @BeforeEach
    void setUp() {

        try{
            cours = new Cours(null,"MatiereTest",100, new ArrayList<>());
            coursServiceImpl.create(cours);
            System.out.println("création du cours : "+cours);
        }
        catch (Exception e){
            System.out.println("erreur de création du cours : "+cours +" erreur : "+e);
        }
    }

    @AfterEach
    void tearDown() {
        try {
            coursServiceImpl.delete(cours);
            System.out.println("effacement du cours : "+cours);
        }
        catch (Exception e){
            System.out.println("erreur d'effacement du cours :"+cours+" erreur : "+e);
        }
    }

    @Test
    void create() {
        assertNotEquals(0,cours.getId(),"id cours non incrémenté");
        assertEquals("MatiereTest",cours.getMatiere(),"matiere cours non enregistré : "+cours.getMatiere()+ " au lieu de MatiereTest");
    }


    @Test
    void read() {
        try{
            int idcours=cours.getId();
            Cours cours2=coursServiceImpl.read(idcours);
            assertEquals("MatiereTest",cours2.getMatiere(),"matières différentes "+"MatiereTest"+"-"+cours2.getMatiere());
        }
        catch (Exception e){
            fail("recherche infructueuse "+e);
        }
    }

    @Test
    void update() {
        try{
            cours.setMatiere("MatiereTest2");
            cours.setNbreHeures(200);
            cours = coursServiceImpl.update(cours);
            assertEquals("MatiereTest2",cours.getMatiere(),"matières différents "+"MatiereTest2-"+cours.getMatiere());
        }
        catch(Exception e){
            fail("erreur de mise à jour "+e);
        }
    }

    @Test
    void delete() {
        try{
            coursServiceImpl.delete(cours);    Assertions.assertThrows(Exception.class, () -> {
                coursServiceImpl.read(cours.getId());
            },"record non effacé");
        }
        catch(Exception e){
            fail("erreur d'effacement "+e);
        }
    }


    @Test
    void all(){
        try {
            List<Cours> lc = coursServiceImpl.all();
            assertNotEquals(0,lc.size(),"la liste ne contient aucun élément");
        }catch (Exception e){
            fail("erreur de recherche de tous les cours "+e);
        }
    }
    @Test
    void rechMatiere() {
        List<Cours> lc = coursServiceImpl.read("MatiereTest");
        boolean trouve=false;
        for(Cours c : lc){
            if(c.getMatiere().startsWith("MatiereTest"))  trouve=true;
            else fail("un record ne correspond pas , Matiere = "+c.getMatiere());
        }
        assertTrue(trouve,"record non trouvé dans la liste");
    }
    @Test
    void delAvecSession() {
        try {
            Formateur formateur = new Formateur("test","test","test");
            formateurServiceImpl.create(formateur);

            Local local = new Local("f30",20);
            localServiceImpl.create(local);

            SessionCours sc = new SessionCours(Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()), 10, cours, formateur, local);
            sessionCoursServiceImpl.create(sc);

            cours.getSession().add(sc);
            coursServiceImpl.update(cours);

            Assertions.assertThrows(Exception.class, () -> {
                coursServiceImpl.delete(cours);
            }, "effacement réalisé malgré session liée");

            sessionCoursServiceImpl.delete(sc);

            formateurServiceImpl.delete(formateur);
            localServiceImpl.delete(local);
        } catch (Exception e) {
            fail("erreur de création de session" + e);
        }
    }

}
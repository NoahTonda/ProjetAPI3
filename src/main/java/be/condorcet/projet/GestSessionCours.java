package be.condorcet.projet;

import be.condorcet.projet.modele.Cours;
import be.condorcet.projet.modele.Formateur;
import be.condorcet.projet.modele.SessionCours;
import be.condorcet.projet.services.InterfCoursService;
import be.condorcet.projet.services.InterfFormateurService;
import be.condorcet.projet.services.InterfSessionCoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sessioncours")

public class GestSessionCours {
    @Autowired
    private InterfFormateurService formateurServiceImpl;
    @Autowired
    private InterfCoursService coursServiceImpl;
    @Autowired
    private InterfSessionCoursService sessionCoursServiceImpl;
    @RequestMapping("/tous")
    public String affTous(Map<String, Object> model){
        System.out.println("recherche session cours");
        try {
            Collection<SessionCours> lsc= sessionCoursServiceImpl.all();
            model.put("mesSessions", lsc);
        } catch (Exception e) {
            System.out.println("----------erreur lors de la recherche-------- " + e);
            return "error";
        }
        return "affichagetousSessions";
    }

    @RequestMapping("/rechparform")
    public String readForm(@RequestParam int idformateur, Map<String, Object> model) {
        System.out.println("recherche du formateur n° " + idformateur);
        try {
            Formateur f = formateurServiceImpl.read(idformateur);
            List<SessionCours> lsc = sessionCoursServiceImpl.getSessionsCoursForm(f);
            model.put("monform",f);
            model.put("formsc", lsc);
        } catch (Exception e) {
            System.out.println("----------erreur lors de la recherche -------- " + e);
            model.put("error", e.getMessage());
            return "error";
        }
        return "affFormSess";
    }
    @RequestMapping("/rechparcours")
    public String readCours(@RequestParam int idcours, Map<String, Object> model) {
        System.out.println("recherche du cours n° " + idcours);
        try {
            Cours c = coursServiceImpl.read(idcours);
            List<SessionCours> lsc = sessionCoursServiceImpl.getSessionsCoursCours(c);
            model.put("moncours",c);
            model.put("formsc", lsc);
        } catch (Exception e) {
            System.out.println("----------erreur lors de la recherche -------- " + e);
            model.put("error", e.getMessage());
            return "error";
        }
        return "affCoursSess";
    }
    @RequestMapping("/delete")
    public String delete(@RequestParam int idSessionCours, Map<String, Object> model){
        System.out.println("recherche de la session n° "+idSessionCours);
        try {
            SessionCours sc = sessionCoursServiceImpl.read(idSessionCours);
            sessionCoursServiceImpl.delete(sc);
            model.put("delSession",sc);
        }catch (Exception e) {
            System.out.println("----------erreur lors de la recherche -------- " + e);
            model.put("error",e.getMessage());
            return "error";
        }
        return "delSession";
    }

}

package be.condorcet.projet;

import be.condorcet.projet.modele.Cours;
import be.condorcet.projet.modele.Formateur;
import be.condorcet.projet.services.InterfCoursService;
import be.condorcet.projet.services.InterfFormateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.Map;
@Controller
@RequestMapping("/cours")

public class GestCours {
    @Autowired
    //instanciation "automatique" par le framework avec les paramètres indiqués, il s'agit d'un singleton
    private InterfCoursService coursServiceImpl;
    @RequestMapping("/tous")
    public String affTous(Map<String, Object> model){
        System.out.println("recherche cours");
        try {
            Collection<Cours> lc= coursServiceImpl.all();
            model.put("mesCours", lc);
        } catch (Exception e) {
            System.out.println("----------erreur lors de la recherche-------- " + e);
            return "error";
        }
        return "affichagetousCours";
    }

    @RequestMapping("/create")
    public String create(@RequestParam String matiere, @RequestParam int nbreHeures, Map<String, Object> model){
        System.out.println("création de formateur");
        Cours c = new Cours(matiere,nbreHeures);
        try {
            coursServiceImpl.create(c);//mise à jour du formateur avec son id par l'environnement
            c = coursServiceImpl.read(c.getId());
            coursServiceImpl.update(c);
            model.put("nouvcours",c);
        } catch (Exception e) {
            System.out.println("----------erreur lors de la création-------- " + e);
            model.put("error",e.getMessage());
            return "error";
        }
        return "nouveauCours";
    }

    @RequestMapping("/selection")
    public String read(@RequestParam int idcours, Map<String, Object> model){
        System.out.println("recherche du cours n° "+idcours);
        try {
            Cours c = coursServiceImpl.read(idcours);
            model.put("moncours",c);
        }catch (Exception e) {
            System.out.println("----------erreur lors de la recherche -------- " + e);
            model.put("error",e.getMessage());
            return "error";
        }
        return "affCoursSess";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam int idcours, Map<String, Object> model){
        System.out.println("recherche du cours n° "+idcours);
        try {
            Cours c = coursServiceImpl.read(idcours);
            coursServiceImpl.delete(c);
            model.put("delCours",c);
        }catch (Exception e) {
            System.out.println("----------erreur lors de la recherche -------- " + e);
            model.put("error",e.getMessage());
            return "error";
        }
        return "delCours";
    }

}

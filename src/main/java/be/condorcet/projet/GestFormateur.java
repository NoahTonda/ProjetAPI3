package be.condorcet.projet;

import be.condorcet.projet.modele.Formateur;
import be.condorcet.projet.services.InterfFormateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/formateurs")
public class GestFormateur {

    @Autowired     //instanciation "automatique" par le framework avec les paramètres indiqués, il s'agit d'un singleton
    private InterfFormateurService formateurServiceImpl;
    @RequestMapping("/tous")
    public String affTous(Map<String, Object> model){
        System.out.println("recherche formateurs");
        try {
            Collection<Formateur> lf= formateurServiceImpl.all();
            model.put("mesFormateurs", lf);
        } catch (Exception e) {
            System.out.println("----------erreur lors de la recherche-------- " + e);
            return "error";
        }
        return "affichagetousFormateurs";
    }

    @RequestMapping("/create")
    public String create(@RequestParam String mail, @RequestParam String nom, @RequestParam String prenom, Map<String, Object> model){
        System.out.println("création de formateur");
        Formateur f = new Formateur(mail,nom,prenom);
        try {
            formateurServiceImpl.create(f);//mise à jour du formateur avec son id par l'environnement
            f = formateurServiceImpl.read(f.getId());
            formateurServiceImpl.update(f);
            model.put("nouvform",f);
        } catch (Exception e) {
            System.out.println("----------erreur lors de la création-------- " + e);
            model.put("error",e.getMessage());
            return "error";
        }
        return "nouveauFormateur";
    }

    @RequestMapping("/selectionid")
    public String read(@RequestParam int idformateur, Map<String, Object> model){
        System.out.println("recherche du formateur n° "+idformateur);
        try {
            Formateur f = formateurServiceImpl.read(idformateur);
            model.put("monform",f);
        }catch (Exception e) {
            System.out.println("----------erreur lors de la recherche -------- " + e);
            model.put("error",e.getMessage());
            return "error";
        }
        return "affFormSess";
    }
    @RequestMapping("/selectionnom")
    public String read(@RequestParam String nom, Map<String, Object> model){
        System.out.println("recherche du formateur n° "+nom);
        try {
            List<Formateur> f = formateurServiceImpl.read(nom);
            model.put("monform",f);
        }catch (Exception e) {
            System.out.println("----------erreur lors de la recherche -------- " + e);
            model.put("error",e.getMessage());
            return "error";
        }
        return "affFormSess";
    }
    @RequestMapping("/delete")
    public String delete(@RequestParam int idformateur, Map<String, Object> model){
        System.out.println("recherche du formateur n° "+idformateur);
        try {
            Formateur f = formateurServiceImpl.read(idformateur);
            formateurServiceImpl.delete(f);
            model.put("delForm",f);
        }catch (Exception e) {
            System.out.println("----------erreur lors de la recherche -------- " + e);
            model.put("error",e.getMessage());
            return "error";
        }
        return "delForm";
    }

}

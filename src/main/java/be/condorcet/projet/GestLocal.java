package be.condorcet.projet;

import be.condorcet.projet.modele.Local;
import be.condorcet.projet.services.InterfLocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.Map;

@Controller
@RequestMapping("/local")

public class GestLocal {
    @Autowired
    //instanciation "automatique" par le framework avec les paramètres indiqués, il s'agit d'un singleton
    private InterfLocalService localServiceImpl;

    @RequestMapping("/tous")
    public String affTous(Map<String, Object> model) {
        System.out.println("recherche local");
        try {
            Collection<Local> lc = localServiceImpl.all();
            model.put("mesLocaux", lc);
        } catch (Exception e) {
            System.out.println("----------erreur lors de la recherche-------- " + e);
            return "error";
        }
        return "affichagetousLocaux";
    }

    @RequestMapping("/create")
    public String create(@RequestParam String sigle, @RequestParam int places, Map<String, Object> model) {
        System.out.println("création de local");
        Local l = new Local(sigle, places);
        try {
            localServiceImpl.create(l);//mise à jour du formateur avec son id par l'environnement
            l = localServiceImpl.read(l.getId());
            localServiceImpl.update(l);
            model.put("nouvlocal", l);
        } catch (Exception e) {
            System.out.println("----------erreur lors de la création-------- " + e);
            model.put("error", e.getMessage());
            return "error";
        }
        return "nouveauLocal";
    }

    @RequestMapping("/selection")
    public String read(@RequestParam int idlocal, Map<String, Object> model) {
        System.out.println("recherche du local n° " + idlocal);
        try {
            Local l = localServiceImpl.read(idlocal);
            model.put("monlocal", l);
        } catch (Exception e) {
            System.out.println("----------erreur lors de la recherche -------- " + e);
            model.put("error", e.getMessage());
            return "error";
        }
        return "affLocalSess";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam int idlocal, Map<String, Object> model) {
        System.out.println("recherche du local n° " + idlocal);
        try {
            Local l = localServiceImpl.read(idlocal);
            localServiceImpl.delete(l);
            model.put("delLocal", l);
        } catch (Exception e) {
            System.out.println("----------erreur lors de la recherche -------- " + e);
            model.put("error", e.getMessage());
            return "error";
        }
        return "delLocal";
    }
}

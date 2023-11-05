package be.condorcet.demosb1;

import be.condorcet.demosb1.modele.Client;
import be.condorcet.demosb1.modele.ClientDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/clients")
public class GestClient {
    @Autowired     //instanciation "automatique" par le framework avec les paramètres indiqués, il s'agit d'un singleton
    ClientDAO clientDAO;
    @RequestMapping("/tous")
    public String affTous(Map<String, Object> model){
        System.out.println("recherche clients");
        List<Client> liste;
        try {
            liste= clientDAO.readall();
            model.put("mesClients", liste);
        } catch (Exception e) {
            System.out.println("----------erreur lors de la recherche-------- " + e);
            return "error";
        }
        return "affichagetousClients";
    }
}

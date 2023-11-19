package be.condorcet.projet.services;

import be.condorcet.projet.modele.Formateur;
import be.condorcet.projet.modele.SessionCours;
import be.condorcet.projet.repositories.FormateurRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional(rollbackOn = Exception.class)

public class FormateurServiceImpl implements InterfFormateurService{
    @Autowired
    private FormateurRepository formateurRepository;
    @Override
    public Formateur create(Formateur formateur) throws Exception {
        formateurRepository.save(formateur);
        return formateur;
    }

    @Override
    public Formateur read(Integer id) throws Exception {

        return formateurRepository.findById(id).get();
    }

    @Override
    public Formateur update(Formateur formateur) throws Exception {
        read(formateur.getId());
        formateurRepository.save(formateur);
        return formateur;
    }

    @Override
    public void delete(Formateur formateur) throws Exception {
        formateurRepository.deleteById(formateur.getId());
    }

    @Override
    public List<Formateur> all() throws Exception {
        return formateurRepository.findAll();
    }

    @Override
    public List<Formateur> read(String nom) {
        return formateurRepository.findByNomLike(nom+"%");
    }

    @Override
    public Formateur read(String nom, String prenom, String mail) {
        return formateurRepository.findByNomAndAndPrenomAndMail(nom,prenom,mail);
    }
}

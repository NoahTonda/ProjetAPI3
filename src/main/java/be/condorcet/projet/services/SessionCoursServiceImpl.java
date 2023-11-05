package be.condorcet.projet.services;

import be.condorcet.projet.modele.Cours;
import be.condorcet.projet.modele.Formateur;
import be.condorcet.projet.modele.SessionCours;
import be.condorcet.projet.repositories.SessionCoursRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional(rollbackOn = Exception.class)

public class SessionCoursServiceImpl implements InterfSessionCoursService{
    @Autowired
    private SessionCoursRepository sessionCoursRepository;
    @Override
    public SessionCours create(SessionCours sessionCours) throws Exception {
        sessionCoursRepository.save(sessionCours);
        return sessionCours;
    }

    @Override
    public SessionCours read(Integer id) throws Exception {
        return sessionCoursRepository.findById(id).get();
    }

    @Override
    public SessionCours update(SessionCours sessionCours) throws Exception {
        read(sessionCours.getId());
        sessionCoursRepository.save(sessionCours);
        return sessionCours;
    }

    @Override
    public void delete(SessionCours sessionCours) throws Exception {
        sessionCoursRepository.deleteById(sessionCours.getId());
    }

    @Override
    public List<SessionCours> all() throws Exception {
        return sessionCoursRepository.findAll();
    }

    @Override
    public List<SessionCours> getSessionsCoursForm(Formateur f) {
        List<SessionCours> lsc = sessionCoursRepository.findSessionCoursByFormateur(f);
        return lsc;
    }

    @Override
    public List<SessionCours> getSessionsCoursCours(Cours c) {
        List<SessionCours> lsc = sessionCoursRepository.findSessionCoursByCours(c);
        return lsc;
    }
}

package be.condorcet.projet.repositories;

import be.condorcet.projet.modele.Cours;
import be.condorcet.projet.modele.Formateur;
import be.condorcet.projet.modele.Local;
import be.condorcet.projet.modele.SessionCours;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionCoursRepository extends JpaRepository<SessionCours,Integer> {
    public List<SessionCours> findSessionCoursByFormateur(Formateur f);
    public List<SessionCours> findSessionCoursByCours(Cours c);

    List<SessionCours> findSessionCoursByLocal(Local l);
}

package be.condorcet.projet.services;

import be.condorcet.projet.modele.Cours;
import be.condorcet.projet.modele.Formateur;
import be.condorcet.projet.modele.SessionCours;

import java.util.List;

public interface InterfSessionCoursService extends InterfService<SessionCours>{
    public List<SessionCours> getSessionsCoursForm(Formateur f);
    public List<SessionCours> getSessionsCoursCours(Cours c);

}

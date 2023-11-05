package be.condorcet.projet.services;

import be.condorcet.projet.modele.Formateur;
import be.condorcet.projet.modele.SessionCours;

import java.util.List;

public interface InterfFormateurService extends InterfService<Formateur>{
    public List<Formateur> read(String nom);

}

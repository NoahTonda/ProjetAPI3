package be.condorcet.projet.services;

import be.condorcet.projet.modele.Cours;

import java.util.List;

public interface InterfCoursService extends InterfService<Cours>{
    public List<Cours> read(String matiere);

}

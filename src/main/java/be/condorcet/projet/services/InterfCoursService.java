package be.condorcet.projet.services;

import be.condorcet.projet.modele.Cours;

import java.util.List;

public interface InterfCoursService extends InterfService<Cours>{
    List<Cours> read(String matiere);
    Cours read(String matiere,int nbreheures);

}

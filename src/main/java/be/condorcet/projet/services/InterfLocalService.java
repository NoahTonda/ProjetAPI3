package be.condorcet.projet.services;

import be.condorcet.projet.modele.Local;

import java.util.List;

public interface InterfLocalService extends InterfService<Local>{
    List<Local> read(String sigle);
    Local read(String sigle, int places);
}

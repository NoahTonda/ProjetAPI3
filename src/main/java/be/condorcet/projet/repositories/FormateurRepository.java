package be.condorcet.projet.repositories;

import be.condorcet.projet.modele.Formateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FormateurRepository  extends JpaRepository<Formateur,Integer> {
    public List<Formateur> findByNomLike(String nom);

}

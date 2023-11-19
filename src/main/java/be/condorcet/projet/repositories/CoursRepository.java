package be.condorcet.projet.repositories;

import be.condorcet.projet.modele.Cours;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoursRepository extends JpaRepository<Cours,Integer> {
    public List<Cours> findByMatiereLike(String matiere);
    public List<Cours> findByMatiereAndNbreHeures(String matiere, int nbreheures);
}

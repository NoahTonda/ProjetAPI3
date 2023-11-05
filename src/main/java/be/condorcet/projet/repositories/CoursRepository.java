package be.condorcet.projet.repositories;

import be.condorcet.projet.modele.Cours;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoursRepository extends JpaRepository<Cours,Integer> {
}

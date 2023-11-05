package be.condorcet.projet.repositories;

import be.condorcet.projet.modele.Local;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalRepository extends JpaRepository<Local,Integer> {
}

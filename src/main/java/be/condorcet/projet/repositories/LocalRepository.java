package be.condorcet.projet.repositories;

import be.condorcet.projet.modele.Local;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocalRepository extends JpaRepository<Local,Integer> {
    List<Local> findBySigleLike(String sigle);
    Local findBySigleAndPlaces(String sigle,int places);

}

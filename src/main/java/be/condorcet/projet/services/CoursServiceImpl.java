package be.condorcet.projet.services;

import be.condorcet.projet.modele.Cours;
import be.condorcet.projet.repositories.CoursRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackOn = Exception.class)

public class CoursServiceImpl implements InterfCoursService{
    @Autowired
    private CoursRepository coursRepository;

    @Override
    public Cours create(Cours cours) throws Exception {
        coursRepository.save(cours);
        return cours;
    }

    @Override
    public Cours read(Integer id) throws Exception {
        Optional<Cours> optionalCours = coursRepository.findById(id);
        return optionalCours.get();
    }

    @Override
    public Cours update(Cours cours) throws Exception {
        read(cours.getId());
        coursRepository.save(cours);
        return cours;
    }

    @Override
    public void delete(Cours cours) throws Exception {
        coursRepository.deleteById(cours.getId());
    }

    @Override
    public List<Cours> all() throws Exception {
        return coursRepository.findAll();
    }
}

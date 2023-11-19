package be.condorcet.projet.services;

import be.condorcet.projet.modele.Local;
import be.condorcet.projet.repositories.LocalRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional(rollbackOn = Exception.class)

public class LocalServiceImpl implements InterfLocalService{
    @Autowired
    private LocalRepository localRepository;
    @Override
    public Local create(Local local) throws Exception {
        localRepository.save(local);
        return local;
    }

    @Override
    public Local read(Integer id) throws Exception {
        return localRepository.findById(id).get();
    }

    @Override
    public Local update(Local local) throws Exception {
        read(local.getId());
        localRepository.save(local);
        return local;
    }

    @Override
    public void delete(Local local) throws Exception {
        localRepository.deleteById(local.getId());
    }

    @Override
    public List<Local> all() throws Exception {
        return localRepository.findAll();
    }

    @Override
    public List<Local> read(String sigle) {
        return localRepository.findBySigleLike(sigle);
    }

    @Override
    public Local read(String sigle, int places) {
        return localRepository.findBySigleAndPlaces(sigle,places);
    }
}

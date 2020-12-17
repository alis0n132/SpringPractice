package com.football.footballChampion.services;

import com.football.footballChampion.entities.CardEntity;
import com.football.footballChampion.entities.MatchEntity;
import com.football.footballChampion.entities.PlayerEntity;
import com.football.footballChampion.repositorys.CardRepository;
import com.football.footballChampion.repositorys.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class MatchService {
    final MatchRepository matchRepo;

    @Autowired
    public MatchService(MatchRepository matchRepo) {
        this.matchRepo = matchRepo;
    }

    public List<MatchEntity> listAll(){
        List<MatchEntity> matchesList = (List<MatchEntity>) matchRepo.findAll();
        return matchesList;
    }

    public void save(MatchEntity match){
        matchRepo.save(match);
    }

    public void deleteById(Integer id){
        matchRepo.deleteById(id);
    }

    public MatchEntity findById(Integer id){
        MatchEntity matchEntity;
        try {
            matchEntity = matchRepo.findById(id).get();
        } catch (NoSuchElementException e) {
            matchEntity = null;
        }
        return matchEntity;
    }

}

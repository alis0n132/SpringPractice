package com.football.footballChampion.services;

import com.football.footballChampion.entities.ArbitreEntity;
import com.football.footballChampion.entities.TeamEntity;
import com.football.footballChampion.repositorys.ArbitreRepository;
import com.football.footballChampion.repositorys.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ArbitreService {
    final ArbitreRepository teamRepo;

    @Autowired
    public ArbitreService(ArbitreRepository teamRepo) {
        this.teamRepo = teamRepo;
    }

    public List<ArbitreEntity> listAll(){
        List<ArbitreEntity> playerList = (List<ArbitreEntity>) teamRepo.findAll();
        return playerList;
    }



}

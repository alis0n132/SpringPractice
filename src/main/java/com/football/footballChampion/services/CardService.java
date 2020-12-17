package com.football.footballChampion.services;

import com.football.footballChampion.entities.ArbitreEntity;
import com.football.footballChampion.entities.CardEntity;
import com.football.footballChampion.repositorys.ArbitreRepository;
import com.football.footballChampion.repositorys.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CardService {
    final CardRepository teamRepo;

    @Autowired
    public CardService(CardRepository teamRepo) {
        this.teamRepo = teamRepo;
    }

    public List<CardEntity> listAll(){
        List<CardEntity> playerList = (List<CardEntity>) teamRepo.findAll();
        return playerList;
    }


}

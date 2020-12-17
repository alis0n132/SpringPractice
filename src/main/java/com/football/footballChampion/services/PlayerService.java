package com.football.footballChampion.services;

import com.football.footballChampion.entities.PlayerEntity;
import com.football.footballChampion.repositorys.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class PlayerService {
    final PlayerRepository repo;

    @Autowired
    public PlayerService(PlayerRepository repo) {
        this.repo = repo;
    }

    public PlayerEntity getPlayerById(Integer id) {
        PlayerEntity playerEntity;
        try {
            playerEntity = repo.findById(id).get();
        } catch (NoSuchElementException e) {
            playerEntity = null;
        }
        return playerEntity;
    }

    public void save(PlayerEntity player) {
        repo.save(player);
    }

    public List<PlayerEntity> listAll() {
        List<PlayerEntity> playerList = (List<PlayerEntity>) repo.findAll();
        return playerList;
    }

    public void deleteById(Integer id){
        repo.deleteById(id);
    }

    public void savePlayer(PlayerEntity playerEntity){
        repo.save(playerEntity);
    }

}

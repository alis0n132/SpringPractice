package com.football.footballChampion.services;

import com.football.footballChampion.entities.ArbitreEntity;
import com.football.footballChampion.entities.GoalEntity;
import com.football.footballChampion.entities.PlayerEntity;
import com.football.footballChampion.repositorys.ArbitreRepository;
import com.football.footballChampion.repositorys.GoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class GoalService {
    final GoalRepository goalRepo;

    @Autowired
    public GoalService(GoalRepository goalRepo) {
        this.goalRepo = goalRepo;
    }

    public List<GoalEntity> listAll(){
        List<GoalEntity> goals = (List<GoalEntity>) goalRepo.findAll();
        return goals;
    }

    public void save(GoalEntity goal) {
        goalRepo.save(goal);
    }


}

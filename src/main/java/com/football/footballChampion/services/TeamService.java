package com.football.footballChampion.services;

import com.football.footballChampion.entities.PlayerEntity;
import com.football.footballChampion.entities.TeamEntity;
import com.football.footballChampion.repositorys.PlayerRepository;
import com.football.footballChampion.repositorys.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class TeamService {
    final TeamRepository teamRepo;

    @Value("${load.dir.path}")
    private String loadDirPath;

    @Autowired
    public TeamService(TeamRepository teamRepo) {
        this.teamRepo = teamRepo;
    }

    public TeamEntity getTeamById(Integer id) {
        TeamEntity teamEntity;
        try {
            teamEntity = teamRepo.findById(id).get();
        } catch (NoSuchElementException e) {
            teamEntity = null;
        }
        return teamEntity;
    }

    public List<TeamEntity> listAll(){
        List<TeamEntity> playerList = (List<TeamEntity>) teamRepo.findAll();
        return playerList;
    }

    public void deleteById(Integer id){
        TeamEntity team = teamRepo.findById(id).get();
        String imgName = team.getImage();
        File file = new File(loadDirPath + "/" + imgName);
        file.delete();
        teamRepo.deleteById(id);
    }

    public void save(TeamEntity team){
        teamRepo.save(team);
    }


}

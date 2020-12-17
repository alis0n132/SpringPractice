package com.football.footballChampion.repositorys;

import com.football.footballChampion.entities.TeamEntity;
import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<TeamEntity, Integer> {
}

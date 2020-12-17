package com.football.footballChampion.repositorys;

import com.football.footballChampion.entities.ArbitreEntity;
import com.football.footballChampion.entities.MatchEntity;
import org.springframework.data.repository.CrudRepository;

public interface MatchRepository extends CrudRepository<MatchEntity, Integer> {
}

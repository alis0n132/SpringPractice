package com.football.footballChampion.repositorys;

import com.football.footballChampion.entities.PlayerEntity;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<PlayerEntity, Integer> {

}

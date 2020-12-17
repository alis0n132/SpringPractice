package com.football.footballChampion.repositorys;

import com.football.footballChampion.entities.CardEntity;
import org.springframework.data.repository.CrudRepository;

public interface CardRepository extends CrudRepository<CardEntity, Integer> {
}

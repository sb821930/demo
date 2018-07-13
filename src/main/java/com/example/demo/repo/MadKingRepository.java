package com.example.demo.repo;

import com.example.demo.entity.MadKingEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MadKingRepository extends CrudRepository<MadKingEntity, Long> {

}

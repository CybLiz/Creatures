package org.example.creatures.repository;

import org.example.creatures.entity.Creature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreatureRepository extends JpaRepository<Creature, Integer> {
}

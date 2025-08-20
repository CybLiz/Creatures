package org.example.creatures.service;

import org.example.creatures.dto.CreatureReceiveDto;
import org.example.creatures.entity.Creature;
import org.example.creatures.exception.NotFoundException;
import org.example.creatures.repository.CreatureRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class CreatureService {

    private CreatureRepository creatureRepository;

    public CreatureService(CreatureRepository creatureRepository) {
        this.creatureRepository = creatureRepository;
    }

    public Creature createCreature(CreatureReceiveDto creatureReceiveDto) {
        Creature creature = creatureReceiveDto.dtoToEntity();
        return creatureRepository.save(creature);

    }

    public Creature getCreatureById(int id) {
        return creatureRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public List<Creature> getCreatures() {
        return creatureRepository.findAll().stream().toList();
    }

    public Creature updateCreature(int id, CreatureReceiveDto creatureReceiveDto) {
        Creature creatureFound = creatureRepository.findById(id).orElseThrow(NotFoundException::new);
        Creature getCreature = creatureReceiveDto.dtoToEntity();
        creatureFound.setName(getCreature.getName());
        creatureFound.setAge(getCreature.getAge());
        creatureFound.setWeight(getCreature.getWeight());
        creatureFound.setDangerous(getCreature.isDangerous());
        creatureFound.setType(getCreature.getType());
        return creatureRepository.save(creatureFound);
    }

    public void deleteCreature(int id) {
        creatureRepository.deleteById(id);
    }

    public Page<Creature> getPage(Pageable pageable) {
        return creatureRepository.findAll(pageable);

    }
}


package org.example.creatures.config;

import org.example.creatures.entity.Creature;
import org.example.creatures.repository.CreatureRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.util.Random;
import java.util.stream.IntStream;

public class DataLoader {

    @Bean
    CommandLineRunner initDatabase(CreatureRepository creatureRepository) {
        return args -> {
            Random random = new Random();

            IntStream.rangeClosed(1,50).forEach(i -> {
                Creature creature = new Creature();
                creature.setName("User -"+i);
                creature.setAge(random.nextInt(100) + 1);
                creature.setWeight(random.nextInt(200) + 1);
                creature.setDangerous(random.nextBoolean());

                Creature.CreatureType[] types = Creature.CreatureType.values();
                creature.setType(types[random.nextInt(types.length)]);
                creatureRepository.save(creature);
            });
        };
    }
}

package org.example.creatures.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.example.creatures.entity.Creature;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class CreatureReceiveDto {
    @NotNull(message = "Le nom ne peut pas être null")
    @Size(min = 3, max = 15, message = "Le nom doit contenir entre 3 et 15 caractères")
    private String name;

    @Positive(message = "L'âge doit être supérieur à 0")
    private int age;

    @Positive(message = "Le poids doit être supérieur à 0")
    private int weight;

    @NotNull(message = "Le champ 'dangerous' doit être défini")
    private Boolean dangerous;

    @NotNull(message = "Le type de créature doit être défini")
    private Creature.CreatureType type;


    public Creature dtoToEntity() {
        return Creature.builder()
                .name(name)
                .age(age)
                .weight(weight)
                .dangerous(dangerous)
                .type(type)
                .build();
    }
}



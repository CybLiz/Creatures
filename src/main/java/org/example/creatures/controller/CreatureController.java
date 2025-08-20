package org.example.creatures.controller;

import jakarta.validation.Valid;
import org.example.creatures.dto.CreatureReceiveDto;
import org.example.creatures.entity.Creature;
import org.example.creatures.service.CreatureService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/creatures")
public class CreatureController {

    private CreatureService creatureService;
    public CreatureController(CreatureService creatureService) {
        this.creatureService = creatureService;
    }

@GetMapping()
    public ResponseEntity<List<Creature>> findAll() {
        return ResponseEntity.ok(creatureService.getCreatures());
}

@GetMapping("/{id}")
    public ResponseEntity<Creature> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(creatureService.getCreatureById(id));
}
@PostMapping()
public ResponseEntity<Creature> save( @Valid @RequestBody CreatureReceiveDto creatureReceiveDto ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(creatureService.createCreature(creatureReceiveDto));
}

@PutMapping("/{id}")
    public ResponseEntity<Creature> update (@PathVariable Integer id, @RequestBody CreatureReceiveDto creatureReceiveDto) {
        return ResponseEntity.ok(creatureService.updateCreature(id, creatureReceiveDto));
}

@DeleteMapping("/{id}")
public ResponseEntity<String> delete (@PathVariable Integer id) {
        creatureService.deleteCreature(id);
        return ResponseEntity.ok(String.format("Creature with id %d deleted", id));
}
    @GetMapping("/page")
    public ResponseEntity<Page<Creature>> findPage(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(creatureService.getPage(PageRequest.of(page, size)));
    }

}

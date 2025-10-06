package ru.fitness.gym_management.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fitness.gym_management.dto.TrainerRequestDto;
import ru.fitness.gym_management.dto.TrainerResponceDto;
import ru.fitness.gym_management.service.TrainerService;

import java.util.List;

@RestController
@RequestMapping("/api/trainers")
@RequiredArgsConstructor
public class TrainerController {

    private final TrainerService trainerService;

    @GetMapping
    public ResponseEntity<List<TrainerResponceDto>> getAllTrainers(){
        return ResponseEntity.ok(trainerService.getAllTrainers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainerResponceDto> getTrainerById(@PathVariable Long id){
        return ResponseEntity.ok(trainerService.getTrainerById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<TrainerResponceDto> createTrainer(@Valid @RequestBody TrainerRequestDto requestDto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(trainerService.createTrainer(requestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainerResponceDto> updateTraner(
            @PathVariable Long id,
            @Valid @RequestBody TrainerRequestDto requestDto){
        return ResponseEntity.ok(trainerService.updateTrainer(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainer(@PathVariable Long id){
        trainerService.deleteTrainer(id);
        return ResponseEntity.noContent().build();
    }
}

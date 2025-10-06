package ru.fitness.gym_management.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.fitness.gym_management.dto.TrainerRequestDto;
import ru.fitness.gym_management.dto.TrainerResponceDto;
import ru.fitness.gym_management.entity.Trainer;
import ru.fitness.gym_management.mapper.TrainerMapper;
import ru.fitness.gym_management.repository.TrainerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainerService {

    private final TrainerRepository trainerRepository;
    private final TrainerMapper trainerMapper;

    public List<TrainerResponceDto> getAllTrainers(){
        return trainerRepository.findAll().stream()
                .map(trainerMapper::toDto)
                .toList();
    }

    public TrainerResponceDto getTrainerById(Long id){
        Trainer trainer = trainerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Тренер с id " + id + " не найден"));
        return trainerMapper.toDto(trainer);
    }

    public TrainerResponceDto createTrainer(TrainerRequestDto requestDto){
        Trainer trainer = trainerMapper.toEntity(requestDto);
        Trainer savedTrainer = trainerRepository.save(trainer);
        return trainerMapper.toDto(savedTrainer);
    }

    public TrainerResponceDto updateTrainer(Long id, TrainerRequestDto requestDto){
        Trainer existingTrainer = trainerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Тренер с id " + id + " не найден"));

        existingTrainer.setFirstName(requestDto.getFirstName());
        existingTrainer.setLastName(requestDto.getLastName());
        existingTrainer.setSpecialization(requestDto.getSpecialization());
        existingTrainer.setPhone(requestDto.getPhone());
        existingTrainer.setEmail(requestDto.getEmail());

        Trainer updateTrainer = trainerRepository.save(existingTrainer);
        return trainerMapper.toDto(updateTrainer);
    }

    public void deleteTrainer(Long id){
        if (!trainerRepository.existsById(id)){
            throw new RuntimeException("Тренер с id " + id + " не найден");
        }
        trainerRepository.deleteById(id);
    }
}

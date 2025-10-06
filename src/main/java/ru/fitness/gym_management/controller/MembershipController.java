package ru.fitness.gym_management.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fitness.gym_management.dto.MembershipRequestDto;
import ru.fitness.gym_management.dto.MembershipResponseDto;
import ru.fitness.gym_management.service.MembershipService;

import java.util.List;

@RestController
@RequestMapping("/api/memberships")
@RequiredArgsConstructor
public class MembershipController {

    private final MembershipService membershipService;

    @GetMapping
    public ResponseEntity<List<MembershipResponseDto>> getAllMemberships() {
        return ResponseEntity.ok(membershipService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MembershipResponseDto> getMembershipById(@PathVariable Long id) {
        return ResponseEntity.ok(membershipService.findById(id));
    }

    @PostMapping
    public ResponseEntity<MembershipResponseDto> createMembership(@Valid @RequestBody MembershipRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(membershipService.create(requestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MembershipResponseDto> updateMembership(
            @PathVariable Long id,
            @Valid @RequestBody MembershipRequestDto requestDto) {
        return ResponseEntity.ok(membershipService.update(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMembership(@PathVariable Long id) {
        membershipService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

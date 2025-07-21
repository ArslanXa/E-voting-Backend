package com.arslan.E_voting.Backend.Controller;

import com.arslan.E_voting.Backend.DTO.CreateElectionRequestDTO;
import com.arslan.E_voting.Backend.DTO.ElectionResponseDTO;
import com.arslan.E_voting.Backend.Service.ElectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/elections")
@RequiredArgsConstructor
public class ElectionController {

    private final ElectionService electionService;

    // 🏛️ 1. Create Election (Admin only)
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ElectionResponseDTO> createElection(@RequestBody CreateElectionRequestDTO dto) {
        return ResponseEntity.ok(electionService.createElection(dto));
    }

    // 📜 2. Get All Elections
    @GetMapping
    public ResponseEntity<List<ElectionResponseDTO>> getAllElections() {
        return ResponseEntity.ok(electionService.getAllElections());
    }

    // 🔍 3. Get Election by ID
    @GetMapping("/{id}")
    public ResponseEntity<ElectionResponseDTO> getElectionById(@PathVariable Long id) {
        return ResponseEntity.ok(electionService.getElectionById(id));
    }

    // 🛠️ 4. Update Election (Admin only)
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ElectionResponseDTO> updateElection(
            @PathVariable Long id,
            @RequestBody CreateElectionRequestDTO dto
    ) {
        return ResponseEntity.ok(electionService.updateElection(id, dto));
    }

    // ❌ 5. Delete Election (Admin only)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteElection(@PathVariable Long id) {
        electionService.deleteElection(id);
        return ResponseEntity.noContent().build();
    }
}

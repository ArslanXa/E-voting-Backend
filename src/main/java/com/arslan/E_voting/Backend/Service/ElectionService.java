package com.arslan.E_voting.Backend.Service;

import com.arslan.E_voting.Backend.DTO.CreateElectionRequestDTO;
import com.arslan.E_voting.Backend.DTO.ElectionResponseDTO;
import com.arslan.E_voting.Backend.Entity.Election;
import com.arslan.E_voting.Backend.Entity.Users;
import com.arslan.E_voting.Backend.Mapper.ElectionMapper;
import com.arslan.E_voting.Backend.Repository.ElectionRepository;
import com.arslan.E_voting.Backend.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ElectionService {

    private final ElectionRepository electionRepository;
    private final UserRepository userRepository;
    private final ElectionMapper electionMapper;

    public ElectionResponseDTO createElection(CreateElectionRequestDTO dto) {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Users adminUser = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("Admin user not found"));

        Election election = electionMapper.fromDto(dto);
        election.setCreatedBy(adminUser);
        return electionMapper.toDto(electionRepository.save(election));
    }

    public List<ElectionResponseDTO> getAllElections() {
        return electionRepository.findAll()
                .stream()
                .map(electionMapper::toDto)
                .collect(Collectors.toList());
    }

    public ElectionResponseDTO getElectionById(Long id) {
        return electionRepository.findById(id)
                .map(electionMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Election not found with ID: " + id));
    }

    public ElectionResponseDTO updateElection(Long id, CreateElectionRequestDTO dto) {
        Election existing = electionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Election not found with ID: " + id));

        existing.setTitle(dto.getTitle());
        existing.setDescription(dto.getDescription());
        existing.setStartDateTime(dto.getStartDateTime());
        existing.setEndDateTime(dto.getEndDateTime());
        existing.setEligibilityMinAge(dto.getEligibilityMinAge());
        existing.setEligibilityRegion(dto.getEligibilityRegion());

        return electionMapper.toDto(electionRepository.save(existing));
    }

    public void deleteElection(Long id) {
        electionRepository.deleteById(id);
    }
}

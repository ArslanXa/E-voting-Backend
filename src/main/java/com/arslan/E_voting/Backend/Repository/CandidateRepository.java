package com.arslan.E_voting.Backend.Repository;

import com.arslan.E_voting.Backend.Entity.Candidate;
import com.arslan.E_voting.Backend.Entity.Election;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    List<Candidate> findByElection(Election election);
}

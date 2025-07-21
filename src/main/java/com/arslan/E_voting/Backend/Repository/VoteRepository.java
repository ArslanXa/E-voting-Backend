package com.arslan.E_voting.Backend.Repository;

import com.arslan.E_voting.Backend.Entity.Vote;
import com.arslan.E_voting.Backend.Entity.Users;
import com.arslan.E_voting.Backend.Entity.Election;
import com.arslan.E_voting.Backend.Entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    Optional<Vote> findByUserAndElection(Users user, Election election);

    long countByCandidate(Candidate candidate);

    List<Vote> findByElection(Election election);
}

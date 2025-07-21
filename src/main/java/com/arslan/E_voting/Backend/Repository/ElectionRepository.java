package com.arslan.E_voting.Backend.Repository;

import com.arslan.E_voting.Backend.Entity.Election;
import com.arslan.E_voting.Backend.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ElectionRepository extends JpaRepository<Election, Long> {
    List<Election> findByCreatedBy(Users user);
}

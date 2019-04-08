package com.gandhi.contest.repository;

import com.gandhi.contest.model.Contestant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ContestantRepository extends JpaRepository<Contestant, UUID> {
}

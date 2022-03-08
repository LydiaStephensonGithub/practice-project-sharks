package com.example.sharks.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sharks.entity.Shark;

@Repository
public interface SharkRepo extends JpaRepository<Shark, Long> {

}

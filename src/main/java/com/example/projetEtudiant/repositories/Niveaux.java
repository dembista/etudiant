package com.example.projetEtudiant.repositories;

import com.example.projetEtudiant.model.Niveau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface Niveaux extends JpaRepository<Niveau,UUID> {
    Optional<Niveau> findById(UUID id);
}

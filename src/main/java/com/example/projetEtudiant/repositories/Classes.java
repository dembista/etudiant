package com.example.projetEtudiant.repositories;

import com.example.projetEtudiant.model.Classe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface Classes extends JpaRepository<Classe,UUID> {
    Optional<Classe> findById(UUID id);
}

package com.example.projetEtudiant.repositories;

import com.example.projetEtudiant.model.Filiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface Filieres extends JpaRepository<Filiere,UUID> {
    Optional<Filiere> findById(UUID id);
}

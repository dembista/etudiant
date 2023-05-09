package com.example.projetEtudiant.repositories;

import com.example.projetEtudiant.model.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface Etudiants extends JpaRepository<Etudiant,UUID> {
    Optional<Etudiant> findById(UUID id);
}

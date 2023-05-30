package com.example.projetEtudiant.repositories;

import com.example.projetEtudiant.model.Reglement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReglementRepositori extends JpaRepository<Reglement, UUID> {
}

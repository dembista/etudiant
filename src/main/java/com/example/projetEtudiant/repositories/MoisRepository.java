package com.example.projetEtudiant.repositories;

import com.example.projetEtudiant.model.Mois;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MoisRepository extends JpaRepository<Mois, UUID> {
}

package com.example.projetEtudiant.repositories;

import com.example.projetEtudiant.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface Administrateur extends JpaRepository<Admin, UUID> {
}

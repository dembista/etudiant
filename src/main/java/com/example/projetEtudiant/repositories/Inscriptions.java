package com.example.projetEtudiant.repositories;

import com.example.projetEtudiant.model.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface Inscriptions extends JpaRepository<Inscription,UUID> {

}

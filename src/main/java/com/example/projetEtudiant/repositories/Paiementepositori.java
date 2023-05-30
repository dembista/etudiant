package com.example.projetEtudiant.repositories;

import com.example.projetEtudiant.model.Inscription;
import com.example.projetEtudiant.model.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface Paiementepositori extends JpaRepository<Paiement, UUID> {
    //Optional<Paiement> findByPaiementByInscription(Inscription  inscription,String mois);
}

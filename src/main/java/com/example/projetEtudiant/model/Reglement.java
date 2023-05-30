package com.example.projetEtudiant.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reglement")
public class Reglement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, columnDefinition = "UUID default gen_random_uuid()")
    private UUID id;
    private String montant;
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "paiement", referencedColumnName = "id")
    private Paiement paiement;

    @ManyToOne
    @JoinColumn(name = "mois", referencedColumnName = "id")
    private Mois mois;
}

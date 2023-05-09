package com.example.projetEtudiant.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "inscription")
public class Inscription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, columnDefinition = "UUID default gen_random_uuid()")
    private UUID id;
    private LocalDate dateInscription;
    private LocalDate anneeScolaire;
    private LocalDate anneeScolaireFin;
    private Double initialDeposit;
    private int nbreMois;
    @Column(columnDefinition = "boolean default true")
    private boolean enCours;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "etudiant" ,referencedColumnName = "id")
    private Etudiant  etudiant;

    @ManyToOne
    @JoinColumn(name = "classe" ,referencedColumnName = "id")
    private Classe  classe;
}

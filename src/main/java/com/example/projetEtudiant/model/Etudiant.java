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
@Table(name = "etudiant")
public class Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, columnDefinition = "UUID default gen_random_uuid()")
    private UUID id;
    private String nom;
    private String prenom;
    private String matricule;


    private LocalDate dateNaissance;
    private String adresse;
    private String email;
    private String telephone;
    private String photo;
    private int age;
    private boolean fraisCompletes;
    private double solde;

}
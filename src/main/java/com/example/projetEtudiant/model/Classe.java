package com.example.projetEtudiant.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "classe")
@NoArgsConstructor
@AllArgsConstructor
public class Classe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, columnDefinition = "UUID default gen_random_uuid()")
    private UUID id;
    private String libelle;
    private String fraisInscription;
    private String mensualite;
    private String autreFrais;

    @ManyToOne
    @JoinColumn(name = "filiere" ,referencedColumnName = "id")
    private Filiere filiere;

    @ManyToOne
    @JoinColumn(name = "niveau" ,referencedColumnName = "id")
    private Niveau niveau;


}
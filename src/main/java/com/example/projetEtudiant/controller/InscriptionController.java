package com.example.projetEtudiant.controller;

import com.example.projetEtudiant.exception.BadRequestException;
import com.example.projetEtudiant.model.*;
import com.example.projetEtudiant.repositories.Inscriptions;
import com.example.projetEtudiant.service.InscriptionService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Controller
@Log
@RequestMapping("/api")
public class InscriptionController {
   // private static final String STRUCTUREID_REQUIRED = "structureId required" ;
    private final String INTERNAL_SERVER_ERROR = "Internal Server Error";
    @Autowired
    private InscriptionService inscriptionService;
    @Autowired
    private Inscriptions inscriptions;

    @PostMapping("/inscrire")
    public ResponseEntity<?> faireInscription(@RequestBody Inscription inscription) throws ParseException {

        try{
            Etudiant etudiant = null;
            LocalDate localDate = inscription.getEtudiant().getDateNaissance();
            LocalDate dateJour = LocalDate.now();
            LocalDate  anneeScolaire = inscription.getAnneeScolaire();



            if (inscription.getEtudiant().getId()==null) {
               etudiant = new Etudiant();
                int age = (int) ChronoUnit.YEARS.between(localDate,dateJour);
                if (age < 18)
                    throw  new BadRequestException("L'étudiant est trop jeune pour s'inscrire.");
                //inscription.setEtudiant(etudiant);
            }else
            {
                etudiant = inscriptionService.findByIdEtudiant(inscription.getEtudiant().getId());
                if (etudiant == null)
                    throw new RuntimeException("L'etudiant n'existe pas");
              /*  Optional<Inscription> inscriptionOptional = inscriptionService.getInscriptionByStudentIdAndAnneeScolaire(etudiant.getId(), anneeSolaire);
                if (inscriptionOptional.isPresent())
                    throw new BadRequestException( "Student can't have 2 registrations in same year");*/
                    inscription.setEtudiant(etudiant);
            }


            Classe  classe = inscriptionService.findByIdClasses(inscription.getClasse().getId());
            inscription.setClasse(classe);
            Optional<Inscription> existingInscription = inscriptionService.verifyEtudiantByAnneeByClasse(etudiant,classe,anneeScolaire);
            if (existingInscription.isPresent())
                throw new BadRequestException("L'étudiant est déjà inscrit dans cette classe pour l'année scolaire ");
           Double mensualite = Double.valueOf(classe.getMensualite());
            Double fraisInscription = Double.valueOf(classe.getFraisInscription());
            Double minimumDeposit = fraisInscription + mensualite;
            Double initialDeposit = inscription.getInitialDeposit();
            if (initialDeposit < minimumDeposit)
                throw new IllegalArgumentException("Le montant minimum a déposé est " + minimumDeposit);
            Inscription inscrire = inscriptionService.inscrire(inscription);
            inscriptionService.proceedPayments(inscrire, initialDeposit, minimumDeposit , mensualite);
            return ResponseEntity.status(HttpStatus.CREATED).body(inscrire);
        }catch(Exception e){
            throw e;
        }
    }
    @PostMapping("/ajoutEtudiant")
    public Etudiant ajoutEtudiant(@RequestBody Etudiant etudiant ) throws Exception {
        try{
            if(etudiant.getId()==null)throw new BadRequestException("L'etudiant n'existe pas");
            if (etudiant == null || etudiant.getAge() < 18)
                throw new BadRequestException("L'etudiant n'est pas majeur");
            return inscriptionService.ajoutEtudiant(etudiant);
        }catch(Exception e){
            throw e;
        }
    }
    @PostMapping("/ajoutClasse")
    public Classe ajoutClasse(@RequestBody Classe classe ){
        try{
            return inscriptionService.ajoutClasse(classe);
        }catch(Exception e){
            throw e;
        }
    }
    @PostMapping("/ajoutFiliere")
    public Filiere ajoutFiliere(@RequestBody Filiere filiere ){
        try{
            return inscriptionService.ajoutFiliere(filiere);
        }catch(Exception e){
            throw e;
        }
    }
    @PostMapping("/ajoutNiveau")
    public Niveau ajoutNiveau(@RequestBody Niveau niveau ){
        try{
            return inscriptionService.ajoutNiveau(niveau);
        }catch(Exception e){
            throw e;
        }
    }

    /*@PostMapping("/payer")
    public Paiement fairePaiement(@ModelAttribute("paiement")PaiementDto paiementDto){
        Paiement paiement = inscriptionService.fairePaiement(paiementDto.getPaiement());
        paiement.setInscription(paiementDto.getInscription());
        paiement.setAmount(paiementDto.getAmount());
        paiement.setMois(paiementDto.getMois());
        return inscriptionService.fairePaiement(paiement);
    }*/
}

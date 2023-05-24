package com.example.projetEtudiant.service;

import com.example.projetEtudiant.exception.BadRequestException;
import com.example.projetEtudiant.model.*;
import com.example.projetEtudiant.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Service
public class InscriptionService {
    @Autowired
    private Inscriptions insc;
    @Autowired
    private Filieres  fil;
    @Autowired
    private Etudiants etudiants;
    @Autowired
    private Niveaux niveaux;
    @Autowired
    private Classes classes;
    @Autowired
    private Administrateur administrateur;
    @Autowired
    private Paiementepositori paiementepositori;



    public Inscription inscrire(Inscription inscription){
        try {
            return insc.save(inscription);
        }catch(Exception e){
            throw e;
        }
    }
    public Etudiant ajoutEtudiant(Etudiant etudiant){
        try {
            return etudiants.save(etudiant);
        }catch(Exception e){
            throw e;
        }
    }
    public Classe ajoutClasse(Classe classe){
        try {
            return classes.save(classe);
        }catch(Exception e){
            throw e;
        }
    }
    public Filiere ajoutFiliere(Filiere filiere){
        try {
            return fil.save(filiere);
        }catch(Exception e){
            throw e;
        }
    }
    public Niveau ajoutNiveau(Niveau niveau){
        try {
            return niveaux.save(niveau);
        }catch(Exception e){
            throw e;
        }
    }
    public Etudiant findByIdEtudiant(UUID id){
        return etudiants.findById(id).orElse(null);
    }
    public Classe findByIdClasses(UUID id){
        return classes.findById(id).orElse(null);
    }
    public Filiere findByIdFiliere(UUID id){
        return fil.findById(id).orElse(null);
    }
    public Niveau findByIdNiveau(UUID id){
        return niveaux.findById(id).orElse(null);
    }

    public List<Niveau> listNiveau(){
        return niveaux.findAll();
    }
    public List<Classe> listClasse(){
        return classes.findAll();
    }
    public List<Filiere> listFiliere(){
        return fil.findAll();
    }

    @Transactional
   public void  proceedPayments(Inscription registration, Double initialDeposit, Double minimumDeposit , Double  mensualite) {

        List<Paiement> payments = new ArrayList<>();
        // Create payment for November
        Paiement paymentNov = new Paiement();
        paymentNov.setInscription(registration);
        paymentNov.setMois("November");
        paymentNov.setAmount(mensualite);
        payments.add(paymentNov);
        // Check amount after november payment
        Double remainingBalanceAfterNov = initialDeposit - minimumDeposit;
        if (remainingBalanceAfterNov >= mensualite) {
            // Check number of month with the remaining amount
            int remainingMonths = (int) Math.floor(remainingBalanceAfterNov / mensualite);
            // See if there is a remaining so i can add to student solde
            Double remainingAmount = remainingBalanceAfterNov % mensualite;
            // Incrémenter le solde de l'étudiant
            registration.getEtudiant().setSolde(registration.getEtudiant().getSolde() + remainingAmount);
            // Créer des paiements pour les mois restants
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.MONTH, 11); // Set month to november
            for (int i = 0; i < remainingMonths; i++) {
                Paiement payment = new Paiement();
                payment.setInscription(registration);
                calendar.add(Calendar.MONTH, i); // increment month after nov
                payment.setMois(new SimpleDateFormat("MMMM").format(calendar.getTime()));
                payment.setAmount(mensualite);
                payments.add(payment);
            }
        }
        // Save payments
        createPayments(payments);
    }
    @Transactional
    public void createPayments(List<Paiement> payments) {
        paiementepositori.saveAll(payments);
    }

    public Optional<Inscription> verifyEtudiantByAnneeByClasse(Etudiant etudiant, Classe classe, LocalDate anneeScolaire) throws BadRequestException {
        Optional<Inscription> existingInscription = insc.findByEtudiantAndClasseAndAnneeScolaire(etudiant,classe,anneeScolaire);
        if (existingInscription.isPresent()) {
            throw new BadRequestException("L'étudiant est déjà inscrit dans cette classe pour l'année scolaire ");
        }
        return existingInscription;
    }
}

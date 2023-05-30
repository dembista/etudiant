package com.example.projetEtudiant.fixtures;

import com.example.projetEtudiant.model.Mois;
import com.example.projetEtudiant.service.InscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class MoisFixture {
    @Autowired
    private InscriptionService inscriptionService;

    public void addDefaultMois() {
        List<Mois> mois = inscriptionService.getAllMois();
        if (mois == null || mois.size() <= 0) {
            Mois[] moisArray = {
                new Mois(null, "Novembre"),
                new Mois(null, "Decembre"),
                new Mois(null, "Janvier"),
                new Mois(null, "Fevrier"),
                new Mois(null, "Mars"),
                new Mois(null, "Avril"),
                new Mois(null, "Mai"),
                new Mois(null, "Juin"),
                new Mois(null, "Juillet"),
                    new Mois(null, "Aout"),
                    new Mois(null, "Septembre"),
                    new Mois(null, "Octobre")
            };

            inscriptionService.ajoutListMois(Arrays.asList(moisArray));
            System.out.println("all mois added successfully");
        }
    }
}

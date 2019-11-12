/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dickodb;

import com.dickodb.annotation.processors.DefaultProcessor;
import com.dickodb.annotation.processors.TableAnnotationProcessor;
import com.dickodb.persistance.AbstractRepository;
import com.dickodb.persistance.EntityManager;
import java.util.ArrayList;

/**
 *
 * @author genius
 */
public class DickoDB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

//        Etudiant etd = new Etudiant();
//        etd.setAge(26);
//        etd.setName("Koné");
//        etd.setPrenom("Ousséni");
//        
//        
//        EntityManager manager = EntityManager.getInstance();
//        
//        
//        manager.persist(etd);
//        manager.flush();


        System.out.println(DefaultProcessor.getIdName(Etudiant.class));

        AbstractRepository<Etudiant> repository = new AbstractRepository<>(Etudiant.class);
        Etudiant etd = repository.find(5);

        System.out.println(etd);

    }

}

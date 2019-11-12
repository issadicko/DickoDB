/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dickodb;

import com.dickodb.annotation.Column;
import com.dickodb.annotation.Id;
import com.dickodb.annotation.Table;

/**
 *
 * @author genius
 */
@Table(name = "etudiant")
public class Etudiant {
    
    @Id
    private long id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "prenom")
    private String prenom;
    
    @Column(name = "age")
    private int age;

    public Etudiant() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
    
    public String toString(){
        return id+" "+name+" "+prenom+" "+age;
    }
}

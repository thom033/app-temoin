package com.need;

import com.annotation.Range;
import com.annotation.Required;

public class Employe {
    @Required
    String nom;

    @Range(min = 18, max = 65)
    int age;

    public Employe() {
    }

    public Employe(String nom, int age) {
        this.nom = nom;
        this.age = age;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

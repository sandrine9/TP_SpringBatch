package com.example.dto;

public class ProductOutDto {
    private String nom;
    private String description;

    public ProductOutDto(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

    public ProductOutDto() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

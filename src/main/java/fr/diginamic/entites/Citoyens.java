package fr.diginamic.entites;

public class Citoyens {

    private String nom;
    private int quantite;
    private String role;

    public Citoyens(String nom, int quantite, String role) {
        this.nom = nom;
        this.quantite = quantite;
        this.role = role;
    }

    public void afficherDetails() {
        System.out.println("Citoyens: " + nom + ", Quantité: " + quantite + ", Role: " + role);
    }
}

package fr.diginamic;

import com.mongodb.client.MongoDatabase;
import fr.diginamic.gestion.GestionCitoyens;

public class Main {

    public static void main(String[] args) {

        // Connexion à MongoDB
        ConnexionMongoDB connexion = new ConnexionMongoDB("mongodb://localhost:27017", "royaume");
        MongoDatabase database = connexion.getDatabase();

        // Instanciation de GestionCitoyens
        GestionCitoyens gestionCitoyens = new GestionCitoyens(database);

        // Retirer 3 paysans avec une quantité de 50 et changer leur rôle
        gestionCitoyens.mettreAJourRoleParQuantiteLimite(50, "Construc", 3);

        // Afficher les citoyens après la mise à jour
        System.out.println("Après mise à jour des rôles :");
        gestionCitoyens.afficherCitoyens();
    }
}

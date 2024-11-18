package fr.diginamic.gestion;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class GestionCitoyens {

    private final MongoCollection<Document> collection;

    // Constructeur
    public GestionCitoyens(MongoDatabase database) {
        this.collection = database.getCollection("Citoyens");
    }

    // Afficher tous les citoyens
    public void afficherCitoyens() {
        try {
            System.out.println("Liste des citoyens :");
            for (Document citoyen : collection.find()) {
                System.out.println(citoyen.toJson());
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de l'affichage des citoyens : " + e.getMessage());
        }
    }

    // Retirer 3 paysans avec une quantité de 50 et leur attribuer un nouveau rôle
    public void mettreAJourRoleParQuantiteLimite(int quantiteInitiale, String nouveauRole, int nombreMax) {
        try {
            // Filtrer les citoyens par quantité initiale
            Document filtre = new Document("quantite", quantiteInitiale);

            // Récupérer les citoyens avec cette quantité (limitée à nombreMax)
            FindIterable<Document> citoyens = collection.find(filtre).limit(nombreMax);

            int count = 0;
            for (Document citoyen : citoyens) {
                if (count < nombreMax) {
                    // Supprimer le citoyen de la collection
                    String nom = citoyen.getString("nom");  // On récupère le nom pour cibler chaque citoyen
                    collection.deleteOne(new Document("nom", nom));

                    // Réinsérer le citoyen avec le nouveau rôle
                    Document citoyenMisAJour = new Document("nom", nom)
                            .append("quantite", quantiteInitiale)
                            .append("role", nouveauRole);

                    collection.insertOne(citoyenMisAJour);
                    System.out.println("Rôle mis à jour pour : " + nom + " -> Nouveau rôle : " + nouveauRole);
                    count++;
                }
            }

            if (count == 0) {
                System.out.println("Aucun citoyen trouvé avec la quantité spécifiée.");
            } else {
                System.out.println("Mise à jour des rôles terminée.");
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de la mise à jour des rôles : " + e.getMessage());
        }
    }
}

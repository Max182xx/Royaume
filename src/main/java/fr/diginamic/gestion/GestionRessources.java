package fr.diginamic.gestion;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class GestionRessources {

    private final MongoCollection<Document> collection;

    // Constructeur
    public GestionRessources(MongoDatabase database) {
        this.collection = database.getCollection("Ressources");
    }

    // Méthode pour ajouter une ressource
    public void ajouterRessource(String type, int quantite) {
        try {
            Document ressource = new Document("type", type).append("quantite", quantite);
            collection.insertOne(ressource);
            System.out.println("Ressource ajoutée : " + type + " avec quantité : " + quantite);
        } catch (Exception e) {
            System.err.println("Erreur lors de l'ajout de la ressource : " + e.getMessage());
        }
    }

    // Méthode pour afficher toutes les ressources
    public void afficherRessources() {
        try {
            for (Document ressource : collection.find()) {
                System.out.println(ressource.toJson());
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de l'affichage des ressources : " + e.getMessage());
        }
    }

    // Méthode pour mettre à jour la quantité d'une ressource
    public void mettreAJourRessource(String type, int nouvelleQuantite) {
        try {
            Document filtre = new Document("type", type);
            Document miseAJour = new Document("$set", new Document("quantite", nouvelleQuantite));
            collection.updateOne(filtre, miseAJour);
            System.out.println("Quantité mise à jour pour la ressource : " + type + " à " + nouvelleQuantite);
        } catch (Exception e) {
            System.err.println("Erreur lors de la mise à jour de la ressource : " + e.getMessage());
        }
    }

    // Méthode pour supprimer une ressource
    public void supprimerRessource(String type) {
        try {
            Document filtre = new Document("type", type);
            collection.deleteOne(filtre);
            System.out.println("Ressource supprimée : " + type);
        } catch (Exception e) {
            System.err.println("Erreur lors de la suppression de la ressource : " + e.getMessage());
        }
    }
}

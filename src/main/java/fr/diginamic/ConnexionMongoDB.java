package fr.diginamic;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class ConnexionMongoDB {
    private MongoClient mongoClient;
    private MongoDatabase database;

    // Constructeur par défaut qui établit une connexion avec localhost
    public ConnexionMongoDB() {
        this("mongodb://localhost:27017", "royaume");
    }

    // Constructeur qui accepte une URL de connexion et le nom de la base de données
    public ConnexionMongoDB(String url, String dbName) {
        try {
            mongoClient = MongoClients.create(url); // Création du client MongoDB
            database = mongoClient.getDatabase(dbName); // Sélection de la base de données
            System.out.println("Connexion réussie à la base de données : " + database.getName());
        } catch (Exception e) {
            System.err.println("Erreur lors de la connexion à MongoDB : " + e.getMessage());
        }
    }

    // Méthode pour obtenir la base de données
    public MongoDatabase getDatabase() {
        if (database == null) {
            throw new IllegalStateException("La connexion à la base de données n'a pas été initialisée correctement.");
        }
        return database;
    }

    // Méthode pour fermer la connexion
    public void fermerConnexion() {
        if (mongoClient != null) {
            mongoClient.close();
            System.out.println("Connexion fermée.");
        }
    }
}

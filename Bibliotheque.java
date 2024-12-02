package sn.isep.Bibliotheque;

import java.util.ArrayList;

public class Bibliotheque {

    // Classe abstraite Article
    public static abstract class Article {
        private int id;
        private String titre;
        private String statut; // "Disponible" ou "Emprunté"

        public Article(int id, String titre, String statut) {
            this.id = id;
            this.titre = titre;
            this.statut = statut;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitre() {
            return titre;
        }

        public void setTitre(String titre) {
            this.titre = titre;
        }

        public String getStatut() {
            return statut;
        }

        public void setStatut(String statut) {
            this.statut = statut;
        }

        @Override
        public String toString() {
            return "ID: " + id + ", Titre: " + titre + ", Statut: " + statut;
        }
    }

    // Classe Livre
    public static class Livre extends Article {
        private String auteur;
        private int isbn;

        public Livre(int id, String titre, String statut, String auteur, int isbn) {
            super(id, titre, statut);
            this.auteur = auteur;
            this.isbn = isbn;
        }

        public String getAuteur() {
            return auteur;
        }

        public void setAuteur(String auteur) {
            this.auteur = auteur;
        }

        public int getIsbn() {
            return isbn;
        }

        public void setIsbn(int isbn) {
            this.isbn = isbn;
        }

        @Override
        public String toString() {
            return super.toString() + ", Auteur: " + auteur + ", ISBN: " + isbn;
        }
    }

    // Classe CD
    public static class CD extends Article {
        private int duree; // en minutes

        public CD(int id, String titre, String statut, int duree) {
            super(id, titre, statut);
            this.duree = duree;
        }

        public int getDuree() {
            return duree;
        }

        public void setDuree(int duree) {
            this.duree = duree;
        }

        @Override
        public String toString() {
            return super.toString() + ", Durée: " + duree + " minutes";
        }
    }

    // Classe DVD
    public static class DVD extends Article {
        private int duree; // en minutes

        public DVD(int id, String titre, String statut, int duree) {
            super(id, titre, statut);
            this.duree = duree;
        }

        public int getDuree() {
            return duree;
        }

        public void setDuree(int duree) {
            this.duree = duree;
        }

        @Override
        public String toString() {
            return super.toString() + ", Durée: " + duree + " minutes";
        }
    }

    // Classe Membre
    public static class Membre {
        private int id;
        private String prenom;
        private String nom;

        public Membre(int id, String prenom, String nom) {
            this.id = id;
            this.prenom = prenom;
            this.nom = nom;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPrenom() {
            return prenom;
        }

        public void setPrenom(String prenom) {
            this.prenom = prenom;
        }

        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        @Override
        public String toString() {
            return "ID: " + id + ", Prénom: " + prenom + ", Nom: " + nom;
        }
    }

    // Classe Compte
    public static class Compte {
        private int id;
        private Membre membre;
        private ArrayList<Article> emprunts;

        public Compte(int id, Membre membre) {
            this.id = id;
            this.membre = membre;
            this.emprunts = new ArrayList<>();
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Membre getMembre() {
            return membre;
        }

        public void setMembre(Membre membre) {
            this.membre = membre;
        }

        public void emprunter(Article article) {
            emprunts.add(article);
            article.setStatut("Emprunté");
        }

        public void retourner(Article article) {
            emprunts.remove(article);
            article.setStatut("Disponible");
        }

        public ArrayList<Article> getEmprunts() {
            return emprunts;
        }

        @Override
        public String toString() {
            return "Compte ID: " + id + ", Membre: " + membre.toString() + ", Emprunts: " + emprunts.size();
        }
    }

    // Classe Bibliotheque pour gérer les articles et membres
    private ArrayList<Article> articles;
    private ArrayList<Membre> membres;

    public Bibliotheque() {
        articles = new ArrayList<>();
        membres = new ArrayList<>();
    }

    public void ajouterArticle(Article article) {
        articles.add(article);
    }

    public void supprimerArticle(int id) {
        articles.removeIf(article -> article.getId() == id);
    }

    public void modifierArticle(int id, String nouveauTitre, String nouveauStatut) {
        Article article = rechercherArticle(id);
        if (article != null) {
            article.setTitre(nouveauTitre);
            article.setStatut(nouveauStatut);
        }
    }

    public void ajouterMembre(Membre membre) {
        membres.add(membre);
    }

    public void supprimerMembre(int id) {
        membres.removeIf(membre -> membre.getId() == id);
    }

    public void modifierMembre(int id, String nouveauPrenom, String nouveauNom) {
        Membre membre = rechercherMembre(id);
        if (membre != null) {
            membre.setPrenom(nouveauPrenom);
            membre.setNom(nouveauNom);
        }
    }

    public ArrayList<Article> listerArticles() {
        return articles;
    }

    public ArrayList<Membre> listerMembres() {
        return membres;
    }

    public Article rechercherArticle(int id) {
        for (Article article : articles) {
            if (article.getId() == id) {
                return article;
            }
        }
        return null;
    }

    public Membre rechercherMembre(int id) {
        for (Membre membre : membres) {
            if (membre.getId() == id) {
                return membre;
            }
        }
        return null;
    }

    public ArrayList<Article> listerArticlesDisponibles() {
        ArrayList<Article> disponibles = new ArrayList<>();
        for (Article article : articles) {
            if (article.getStatut().equals("Disponible")) {
                disponibles.add(article);
            }
        }
        return disponibles;
    }
}

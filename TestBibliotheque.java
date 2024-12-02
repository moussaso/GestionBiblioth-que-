package sn.isep.Bibliotheque;

public class TestBibliotheque {
    public static void main(String[] args) {
        BibliothequeManager manager = new BibliothequeManager();

        // Ajout des articles
        manager.ajouterArticle(new Livre(1, "Les Vautours", "Disponible", "David DIOP", 123456));
        manager.ajouterArticle(new CD(2, "Histoire de Senegal", "Disponible", 120));
        manager.ajouterArticle(new DVD(3, "Inception", "Disponible", 148));

        // Ajout des membres
        Membre membre1 = new Membre(1, "Fanta", " BINTA BA");
        manager.ajouterMembre(membre1);

        // Création d'un compte
        Compte compte1 = new Compte(1, membre1);

        // Emprunter un livre
        Article livre = manager.rechercherArticle(1);
        if (livre != null && livre.getStatut().equals("Disponible")) {
            compte1.emprunter(livre);
            System.out.println("Emprunté : " + livre);
        }

        // Modifier un article
        manager.modifierArticle(1, "Les Vautours Modifié", "Emprunté");
        System.out.println("David DIOP modifié : " + livre);

        // Lister les articles disponibles
        System.out.println("Articles disponibles : " + manager.listerArticlesDisponibles());

        // Supprimer un article
        manager.supprimerArticle(2);
        System.out.println("Articles après suppression : " + manager.listerArticles());

        // Modifier un membre
        manager.modifierMembre(1, "Alice", "Dupont");
        System.out.println("Membre modifié : " + membre1);

        // Supprimer un membre
        manager.supprimerMembre(1);
        System.out.println("Membres après suppression : " + manager.listerMembres());
    }
}

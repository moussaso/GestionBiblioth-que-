-- Création des tables principales et sous-classes

-- Table des Articles (classe principale)
CREATE TABLE Articles (
    id_article INT PRIMARY KEY AUTO_INCREMENT,
    titre VARCHAR(255) NOT NULL,
    type ENUM('Livre', 'CD', 'DVD') NOT NULL,
    statut ENUM('Disponible', 'Emprunté') NOT NULL
);

-- Table des Livres (sous-classe de Articles)
CREATE TABLE Livres (
    id_livre INT PRIMARY KEY,
    auteur VARCHAR(255) NOT NULL,
    isbn INT UNIQUE NOT NULL,
    FOREIGN KEY (id_livre) REFERENCES Articles(id_article) ON DELETE CASCADE
);

-- Table des CDs (sous-classe de Articles)
CREATE TABLE CDs (
    id_cd INT PRIMARY KEY,
    duree INT NOT NULL, -- Durée en minutes
    FOREIGN KEY (id_cd) REFERENCES Articles(id_article) ON DELETE CASCADE
);

-- Table des DVDs (sous-classe de Articles)
CREATE TABLE DVDs (
    id_dvd INT PRIMARY KEY,
    duree INT NOT NULL, -- Durée en minutes
    FOREIGN KEY (id_dvd) REFERENCES Articles(id_article) ON DELETE CASCADE
);

-- Table des Membres
CREATE TABLE Membres (
    id_membre INT PRIMARY KEY AUTO_INCREMENT,
    prenom VARCHAR(50) NOT NULL,
    nom VARCHAR(50) NOT NULL,
    adresse TEXT NOT NULL,
    telephone VARCHAR(15),
    email VARCHAR(100) UNIQUE
);

-- Table des Comptes
CREATE TABLE Comptes (
    id_compte INT PRIMARY KEY AUTO_INCREMENT,
    date_creation DATE NOT NULL,
    id_membre INT NOT NULL,
    FOREIGN KEY (id_membre) REFERENCES Membres(id_membre) ON DELETE CASCADE
);

-- Table des Emprunts
CREATE TABLE Emprunts (
    id_emprunt INT PRIMARY KEY AUTO_INCREMENT,
    id_article INT NOT NULL,
    id_compte INT NOT NULL,
    date_emprunt DATE NOT NULL,
    date_retour DATE,
    FOREIGN KEY (id_article) REFERENCES Articles(id_article) ON DELETE CASCADE,
    FOREIGN KEY (id_compte) REFERENCES Comptes(id_compte) ON DELETE CASCADE
);

-- Table Historique (emprunts passés)
CREATE TABLE Historique (
    id_historique INT PRIMARY KEY AUTO_INCREMENT,
    id_article INT NOT NULL,
    id_compte INT NOT NULL,
    date_emprunt DATE NOT NULL,
    date_retour DATE NOT NULL,
    FOREIGN KEY (id_article) REFERENCES Articles(id_article) ON DELETE CASCADE,
    FOREIGN KEY (id_compte) REFERENCES Comptes(id_compte) ON DELETE CASCADE
);

-- Table des Statistiques (Rapports d'activité)
CREATE TABLE Statistiques (
    id_statistique INT PRIMARY KEY AUTO_INCREMENT,
    rapport_type ENUM('Articles Empruntés', 'Articles Disponibles', 'Membres Inscrits') NOT NULL,
    details TEXT NOT NULL,
    date_rapport DATE NOT NULL
);

-- Table des Impressions (suivi des impressions)
CREATE TABLE Impressions (
    id_impression INT PRIMARY KEY AUTO_INCREMENT,
    id_statistique INT NOT NULL,
    date_impression DATE NOT NULL,
    FOREIGN KEY (id_statistique) REFERENCES Statistiques(id_statistique) ON DELETE CASCADE
);

-- Insertion de données dans les tables

-- Insérer des Articles
INSERT INTO Articles (titre, type, statut)
VALUES 
    ('Les Vautours', 'Livre', 'Disponible'),
    ('Java Programming', 'Livre', 'Emprunté'),
    ('Greatest Hits', 'CD', 'Disponible'),
    ('Inception', 'DVD', 'Disponible');

-- Insérer des Livres
INSERT INTO Livres (id_livre, auteur, isbn)
VALUES 
    (1, 'David Diop', 123456789),
    (2, 'Cheick Anta Diop', 987654321);

-- Insérer des CDs
INSERT INTO CDs (id_cd, duree)
VALUES 
    (3, 120); -- 120 minutes

-- Insérer des DVDs
INSERT INTO DVDs (id_dvd, duree)
VALUES 
    (4, 148); -- 148 minutes

-- Insérer des Membres
INSERT INTO Membres (prenom, nom, adresse, telephone, email)
VALUES 
    ('Fanta', 'BA', '10 rue de DAKAR, 75001 DAKAR', '0102030405', 'fantaba@gmail.com'),
    ('Moussa', 'SOW', '15 rue de PIKINE, 69000 DAKAR', '0607080910', 'moussasow@gmail.com');

-- Insérer des Comptes
INSERT INTO Comptes (date_creation, id_membre)
VALUES 
    ('2023-01-01', 1),
    ('2023-06-15', 2);

-- Insérer des Emprunts
INSERT INTO Emprunts (id_article, id_compte, date_emprunt)
VALUES 
    (2, 1, '2023-11-01'),
    (4, 2, '2023-11-10');

-- Insérer dans Historique (articles déjà retournés)
INSERT INTO Historique (id_article, id_compte, date_emprunt, date_retour)
VALUES 
    (1, 1, '2023-10-01', '2023-10-10'),
    (3, 2, '2023-10-05', '2023-10-15');

-- Insérer des Statistiques
INSERT INTO Statistiques (rapport_type, details, date_rapport)
VALUES 
    ('Articles Empruntés', '10 articles empruntés en Novembre', '2023-11-30'),
    ('Articles Disponibles', '50 articles disponibles dans le catalogue', '2023-11-30');

-- Insérer des Impressions
INSERT INTO Impressions (id_statistique, date_impression)
VALUES 
    (1, '2023-12-01'),
    (2, '2023-12-01');

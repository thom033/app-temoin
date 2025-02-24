CREATE TABLE avion (
    id SERIAL PRIMARY KEY,
    nom_avion VARCHAR(255) NOT NULL,
    nbr_siege_business INTEGER NOT NULL CHECK (nbr_siege_business >= 0),
    nbr_siege_eco INTEGER NOT NULL CHECK (nbr_siege_eco >= 0),
    date_fabrication DATE NOT NULL
);

CREATE TABLE ville (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL UNIQUE
);


CREATE TABLE vol (
    id SERIAL PRIMARY KEY,
    id_avion INTEGER NOT NULL REFERENCES avion(id) ON DELETE CASCADE,
    id_ville_depart INTEGER NOT NULL REFERENCES ville(id) ON DELETE CASCADE,
    id_ville_arrivee INTEGER NOT NULL REFERENCES ville(id) ON DELETE CASCADE,
    date_depart TIMESTAMP NOT NULL CHECK (date_depart > NOW()), -- Empêche de créer un vol dans le passé
    duree_vol INTERVAL NOT NULL CHECK (duree_vol > INTERVAL '0 seconds'), -- La durée doit être positive
    CHECK (id_ville_depart <> id_ville_arrivee), -- Empêche un vol avec la même ville de départ et d'arrivée
    UNIQUE (id_avion, date_depart) -- Un avion ne peut pas faire deux vols au même moment
);


CREATE TABLE utilisateur (
    id SERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    mdp VARCHAR(255) NOT NULL
);

CREATE TABLE type_siege (
    id SERIAL PRIMARY KEY,
    type VARCHAR(50) NOT NULL CHECK (type IN ('eco', 'business'))
);

CREATE TABLE siege (
    id SERIAL PRIMARY KEY,
    id_avion INTEGER NOT NULL REFERENCES avion(id) ON DELETE CASCADE,
    id_type_siege INTEGER NOT NULL REFERENCES type_siege(id) ON DELETE CASCADE,
    numero VARCHAR(10) NOT NULL, -- Numéro du siège (ex: A1, B3)
    UNIQUE (id_vol, numero) -- Empêche les doublons de numéros de siège pour un même vol
);

CREATE TABLE type_etat_reservation (
    id SERIAL PRIMARY KEY,
    etat VARCHAR(50) NOT NULL CHECK (etat IN ('annulé', 'ok', 'fini'))
);


CREATE TABLE reservation (
    id_reservation SERIAL PRIMARY KEY,
    code_reservation VARCHAR(50) NOT NULL UNIQUE, -- Unicité du code de réservation
    id_vol INTEGER NOT NULL REFERENCES vol(id) ON DELETE CASCADE,
    id_siege INTEGER NOT NULL REFERENCES siege(id) ON DELETE CASCADE,
    date_reservation TIMESTAMP NOT NULL DEFAULT NOW(),
    etat_reservation INTEGER NOT NULL REFERENCES type_etat_reservation(id) ON DELETE CASCADE,
    UNIQUE (id_siege) -- Empêche la double réservation d'un même siège sur un vol
);


CREATE TABLE historique_etat_reservation (
    id SERIAL PRIMARY KEY,
    id_reservation INTEGER NOT NULL REFERENCES reservation(id) ON DELETE CASCADE,
    id_type INTEGER NOT NULL REFERENCES type_etat_reservation(id) ON DELETE CASCADE,
    date_modification TIMESTAMP NOT NULL DEFAULT NOW(),
    description TEXT
);

CREATE TABLE promotion_vol (
    id SERIAL PRIMARY KEY,
    id_vol INTEGER NOT NULL REFERENCES vol(id) ON DELETE CASCADE,
    id_siege INTEGER NOT NULL REFERENCES siege(id) ON DELETE CASCADE, 
    pourcentage_reduction INTEGER NOT NULL CHECK (pourcentage_reduction BETWEEN 0 AND 100)
);



CREATE TABLE config_reservation (
    id SERIAL PRIMARY KEY,
    heure_validite_avant_vol INTEGER NOT NULL,  -- Nombre d'heures avant le vol où la réservation reste valide
    heure_marge_annulation INTEGER NOT NULL,   -- Nombre d'heures avant le vol où l'annulation est autorisée
    date_insertion TIMESTAMP NOT NULL DEFAULT NOW()  -- Date d'insertion de la configuration
);



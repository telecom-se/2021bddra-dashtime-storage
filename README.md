# 2021bddra-equipe1-storage

## Membres de l'équipe
Grégoire Biron
Ruben Feliciano
Thomas Gagnaire
Tom-Pierre Brégnac

## Tâches
Création du système de stockage d'une base de données

## Principe
Nous stockons des données de capteur thermiques. Ces derniers fournissent 3 informations différentes. Leur id, la température mesurée et un timestamp.
Nous avons créer une structure de données : une collection de séries de données. Une collection est une table de hashage. Une série est un tableau chaîné. Voir schéma ci-dessous.

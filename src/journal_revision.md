# template journal

# 📝 [Titre de la leçon / exercice]
**Date :**  
**Technologie / Framework :** Java / Spring Boot / Autre  
**Concept clé :** Abstraction, Héritage, Template Method, REST API, Sécurité, etc.

---

## 1️⃣ Contexte fonctionnel / métier
Le systeme doit suivre et analyser la répartition du cash dans un réseau d’ATMs a travers toutes les agences en utilisant 
un tableau 2D.
Le systeme doit avoir 4 branches chacune avec 3 ATMs

---

## 2️⃣ Objectifs techniques
- Implementer les methodes loadCash(String branch, String atm, int amount) qui met en place les montants pour chaque ATM
- Implementer la methode printGrid() qui affiche cahque branche par table ATM sous forme de grid.
- Creer la methode branchTotal(branch) qui affiche le montant total de tous les ATMs d'une agence.
- Creer la methode findLowCash(threshold) qui affiche les ATM en dessous du seuil.

---

## 3️⃣ Analyse / Planification
- Structure principale : 
  - double[][] atmCash = new double[4][3];
- Mapping mental (IMPORTANT)
  | Branche       | ATM              |
  | ------------- | ---------------- |
  | atmCash[0][0] | Branch A - ATM 1 |
  | atmCash[0][1] | Branch A - ATM 2 |
  | atmCash[0][2] | Branch A - ATM 3 |

- Contraintes importantes
  - Indices valides (éviter erreurs)
  - Pas de valeurs négatives
  - Parcours complet du tableau pour certaines méthodes

---

## 4️⃣ Implémentation / Étapes
1. Étape 1 : Creer la methode loadCash()
- Algorithm: 
  -  Vérifier que branch et atm sont valides
  - Vérifier que amount >= 0
  - Assigner atmCash[branch][atm] = amount
2. Étape 2 :  printGrid() : parcourir le tableau 2D
- Algorithm : 
   -  Pour chaque branche (ligne) : Afficher le nom de la branche
   - Pour chaque ATM (colonne) : Afficher le montant correspondant
   - Passer à la ligne suivante
3. Étape 3 : branchTotal()
- Algorithm:
  - Initialiser total = 0
  - Pour chaque ATM de la branche donnée
  - Ajouter le montant au total
  - Retourner total
4. Étape 4 : findLowCash
- Algorithm
  - Pour chaque branche
  - Pour chaque ATM dans cette branche
  - Si le montant < threshold
  - Afficher la position (branche + ATM)


> Note ici toute difficulté rencontrée ou détail important.

---

## 5️⃣ Problèmes rencontrés et solutions
| Problème | Cause | Solution |
|----------|-------|---------|
| Exemple : authenticate() toujours false | ssn vide mal initialisé | Vérification avant assignation |

---

## 6️⃣ Tests effectués
- Cas normal / standard
- Cas limite / boundary
- Cas d’erreur / exceptions
- Cas spécifique métier

---

## 7️⃣ Leçons apprises
- Concepts maîtrisés
- Patterns appliqués
- Bonnes pratiques observées
- Points techniques à retenir

---

## 8️⃣ Améliorations possibles
- Optimisation / refactoring
- Tests supplémentaires
- Sécurité, validation, logs, monitoring

---

## 9️⃣ Résumé personnel
> Une phrase ou un paragraphe résumant ce que tu retiens de cette leçon ou exercice.

---

## 🔹 Section hebdomadaire (facultative)
À la fin de chaque semaine, synthétise :
- Concepts maîtrisés cette semaine
- Difficultés majeures
- Victoires ou réussites
- Ce que tu dois améliorer pour la semaine suivante

---
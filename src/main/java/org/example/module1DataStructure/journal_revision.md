# 📝 Section 1 — Core Banking Storage (Arrays & ArrayList)
**Date :** 2025  
**Technologie / Framework :** Java 21 / Maven / JUnit 5 / SLF4J + Logback  
**Concept clé :** Arrays (1D, 2D), ArrayList, tableaux parallèles, validation d'indices, TDD

---

## 1️⃣ Contexte fonctionnel / métier

Une coopérative de crédit (credit union) a besoin de :
- **Exercice 1.1** — Un registre fixe de 10 comptes clients avec noms et soldes (tableaux parallèles)
- **Exercice 1.2** — Un système d'onboarding dynamique où le nombre de clients est inconnu à l'avance (ArrayList)
- **Exercice 1.3** — Un suivi du cash dans un réseau de 4 agences × 3 ATMs chacune (tableau 2D)

---

## 2️⃣ Objectifs techniques

### Exercice 1.1 — CreditUnionLedger (Arrays parallèles)
- `register(index, name, balance)` — enregistrer un compte à un index donné
- `totalDeposits()` — calculer la somme de tous les soldes
- `highestBalanceName()` / `highestBalanceAmount()` — trouver le compte le plus riche
- `printLedger()` — afficher tous les comptes (retourne `String[]` pour testabilité)

### Exercice 1.2 — CustomerOnboarding (ArrayList)
- `addCustomer(name)` — ajouter un client (avec vérification de doublons)
- `removeCustomer(name)` — retirer un client
- `isRegistered(name)` — vérifier si un client existe
- `printQueue()` — afficher la file d'attente

### Exercice 1.3 — ATMGrid (Tableau 2D)
- `loadCash(branch, atm, amount)` — charger du cash dans un ATM spécifique
- `printGrid()` — afficher la grille branche × ATM (retourne `String[][]`)
- `branchTotal(branch)` — total du cash d'une agence
- `findLowCash(threshold)` — lister les ATMs en dessous du seuil (retourne `String[]`)

---

## 3️⃣ Analyse / Planification

### Exercice 1.1 — Structure
- `String[] accountHolders = new String[10]`
- `double[] balances = new double[10]`
- `int accountCount` — compteur d'enregistrements

### Exercice 1.2 — Structure
- `ArrayList<String> customerNames` — taille dynamique

### Exercice 1.3 — Structure
- `double[][] atmCash = new double[4][3]`
- Mapping mental :

| Index         | Signification    |
| ------------- | ---------------- |
| atmCash[0][0] | Branch A - ATM 1 |
| atmCash[0][1] | Branch A - ATM 2 |
| atmCash[0][2] | Branch A - ATM 3 |
| atmCash[1][0] | Branch B - ATM 1 |
| ...           | ...              |
| atmCash[3][2] | Branch D - ATM 3 |

### Contraintes communes
- Validation des indices (éviter `ArrayIndexOutOfBoundsException`)
- Pas de valeurs négatives
- Retourner des valeurs testables (pas `void` + `System.out.println`)

---

## 4️⃣ Implémentation / Étapes

### Exercice 1.1 — CreditUnionLedger
1. `register()` : valider index (0–9), valider balance >= 0, assigner aux tableaux, incrémenter `accountCount`
2. `totalDeposits()` : boucle sur `MAX_ACCOUNTS`, accumuler `balances[i]`, logger le total
3. `highestBalanceName()` / `highestBalanceAmount()` : parcourir pour trouver `maxIndex`, retourner `null` si aucun compte
4. `printLedger()` : retourner `String[accountCount]`, formater chaque entrée `[i] nom $montant`

### Exercice 1.2 — CustomerOnboarding
1. `addCustomer()` : valider null/vide, vérifier doublon avec `contains()`, ajouter
2. `removeCustomer()` : valider null/vide, `remove()` retourne boolean
3. `isRegistered()` : valider null/vide, `contains()`
4. `printQueue()` : boucle avec index, logger chaque client

### Exercice 1.3 — ATMGrid
1. `loadCash()` : valider branch (0–3) et atm (0–2), valider amount >= 0, assigner
2. `printGrid()` : `StringBuilder` pour header et lignes, retourner `String[4][3]`
3. `branchTotal()` : valider branch, boucle sur ATMs, accumuler total
4. `findLowCash()` : tableau temporaire `temp[12]`, `System.arraycopy` pour trimmer, retourner `String[count]`

---

## 5️⃣ Problèmes rencontrés et solutions

| Problème | Cause | Solution |
|----------|-------|---------|
| `register()` continuait après index invalide | `return` manquant après le `LOGGER.warn` | Ajouter `return` dans le bloc `if` de validation |
| `getAccountHolder()` retournait `null` au lieu de la valeur | Retournait hardcodé `null` au lieu de `accountHolders[index]` | Corriger le return |
| `highestBalanceName()` retournait `"No account registered"` au lieu de `null` | Mauvaise gestion du cas vide | Retourner `null` quand `accountHolders[maxIndex] == null` |
| `Cannot resolve method 'log()'` avec `java.util.logging` | Pas de surcharge `log(Level, String)` sans paramètre | Migration vers SLF4J (`LOGGER.info()`, `LOGGER.warn()`) |
| SonarQube : "Invoke method(s) only conditionally" | `String.format()` exécuté même si le log level est désactivé | Entourer avec `if (LOGGER.isInfoEnabled())` |
| SonarQube : "Use StringBuilder instead" | Concaténation `+=` dans une boucle | Remplacer par `StringBuilder.append()` |
| SonarQube : "String contains no format specifiers" | `LOGGER.warn("msg", unusedArg)` — argument non utilisé | Ajouter `{}` dans le message ou retirer l'argument |
| `ArrayIndexOutOfBoundsException` dans `getCash()` | Pas de validation d'indices dans le getter | Ajouter la même validation que `loadCash()` |
| `accountCount` non déclaré | Variable utilisée dans `printLedger()` mais jamais créée | Ajouter le champ `private int accountCount = 0` et incrémenter dans `register()` |
| JUnit 6.1.0-M1 introuvable | Version inexistante | Corriger vers JUnit Jupiter 5.10.2 |
| SLF4J alpha version | Version instable `2.1.0-alpha1` | Corriger vers `2.0.13` + ajouter `logback-classic 1.5.6` |

---

## 6️⃣ Tests effectués

### Exercice 1.1 — CreditUnionLedgerTest (14 tests)
- **Register Tests** : enregistrement simple, multiple, 10 comptes camerounais, index invalide, slot vide
- **Highest Balance Tests** : plus haut solde (nom + montant), premier index, dernier index, ledger vide
- **Total Deposits Tests** : somme avec 10 comptes réalistes (12,525,000 FCFA)
- **Print Ledger Tests** : vérification longueur, noms, montants formatés

### Exercice 1.2 — CustomerOnboardingTest (4 tests)
- Ajout de client, suppression, vérification d'inscription, affichage de la queue

### Exercice 1.3 — ATMGridTest (7 tests)
- Chargement normal, montant négatif, index invalide, affichage grille, total branche, ATMs sous seuil, aucun sous seuil

### Approche TDD
- Tests écrits en premier (failing tests)
- Implémentation minimale pour faire passer les tests
- Refactoring (helpers `registerAllAccounts()`, `registerFourAccounts()`, constantes `NAMES[]`, `BALANCES[]`)

---

## 7️⃣ Leçons apprises

### Concepts maîtrisés
- **Tableaux parallèles** : `String[]` + `double[]` synchronisés par index
- **Tableau 2D** : modélisation d'une grille branche × ATM
- **ArrayList vs Array** : ArrayList quand la taille est inconnue, Array quand elle est fixe
- **`System.arraycopy()`** : copie efficace pour trimmer un tableau temporaire

### Patterns appliqués
- **TDD** : Red → Green → Refactor
- **DRY** : helpers privés dans les tests, constantes partagées
- **Testabilité** : retourner des valeurs (`String[]`, `String[][]`) au lieu de `void` + print
- **Guard clauses** : validation en début de méthode avec `return` anticipé

### Bonnes pratiques observées
- **SLF4J** au lieu de `java.util.logging` — API plus propre, placeholders `{}`
- **`isInfoEnabled()`** avant `String.format()` dans les logs — performance
- **`StringBuilder`** au lieu de `+=` dans les boucles — éviter la création d'objets
- **Delta `0.01`** dans `assertEquals` pour les `double` — précision flottante
- **Noms camerounais** dans les données de test — personnalisation du contexte

### Points techniques à retenir
- `LOGGER.warn("msg: {}", value)` — pas besoin de `new Object[]{}` avec SLF4J
- `String.format("%,.2f", amount)` — formatage monétaire avec séparateur de milliers
- `System.arraycopy(src, 0, dest, 0, count)` — plus performant qu'une boucle manuelle
- JaCoCo pour la couverture de tests : rapport HTML dans `target/site/jacoco/index.html`

---

## 8️⃣ Améliorations possibles

### Refactoring
- Extraire la logique de recherche du max (`highestBalanceName` / `highestBalanceAmount`) en une seule méthode privée `findMaxIndex()`
- Ajouter un getter `getAccountCount()` pour exposer le nombre de comptes

### Tests supplémentaires
- Test `printLedger()` avec ledger vide (0 comptes)
- Test `totalDeposits()` avec ledger vide
- Test `register()` avec balance négative
- Test `addCustomer()` avec doublon
- Test `branchTotal()` avec index invalide

### Qualité
- Ajouter validation `name != null` dans `CreditUnionLedger.register()`
- Rendre `customerNames` privé dans `CustomerOnboarding` (actuellement package-private)
- Ajouter `@DisplayName` aux tests de `CustomerOnboardingTest`

---

## 9️⃣ Résumé personnel

> La Section 1 m'a permis de maîtriser les structures de stockage fondamentales en Java (Array, ArrayList, Array 2D) dans un contexte bancaire réaliste. L'approche TDD m'a forcé à penser aux cas limites dès le départ. La migration de `java.util.logging` vers SLF4J et la résolution des warnings SonarQube m'ont appris les bonnes pratiques de logging en production. Le point clé : **toujours retourner des valeurs testables** au lieu de faire des `void` + `System.out.println`.

---

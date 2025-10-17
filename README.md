# kata-bowling-spec-kit

Ce dépôt contient :

- Une suite de tests JUnit complète pour le calcul du score de bowling :
  `src/test/java/fr/geromeavecung/bowling/CompleteBowlingCalculatorTest.java`.
- Une implémentation simple/complète du calculateur et du parseur :
  - `src/main/java/fr/geromeavecung/bowling/BowlingCalculator.java`
  - `src/main/java/fr/geromeavecung/bowling/Frames.java`
  - `src/main/java/fr/geromeavecung/bowling/Frame.java`
  - `src/main/java/fr/geromeavecung/bowling/FrameType.java`

Format d'entrée attendu par `Frames(String)`
- Tokens séparés par des espaces représentant les frames 1..9, et le 10ᵉ frame peut être concaténé (exemples ci‑dessous).
- Notations acceptées :
  - `X` pour strike (10 quilles)
  - `5/` pour spare (la seconde valeur est calculée pour atteindre 10)
  - `34` pour 3 puis 4 quilles
  - `-` pour 0 (miss)
- Exemples de jeux :
  - Gutter game (tous zéros) : `- - - - - - - - - -`
  - All ones : `11 11 11 11 11 11 11 11 11 11`
  - Perfect game : `X X X X X X X X X XXX` (les deux derniers `X` sont les bonus du 10ᵉ)
  - Spare in tenth with bonus : `- - - - - - - - - 5/5`
  - Strike in tenth with two bonus rolls : `- - - - - - - - - X34`

Comment exécuter les tests (Windows / cmd.exe)

Ouvrez un terminal dans la racine du projet puis lancez :

```cmd
.\mvnw.cmd test
```

Rapports de test :
- Résultats Surefire : `target/surefire-reports/` (fichiers XML et TXT)

Notes et prochaines étapes possibles :
- Ajouter des tests paramétrés ou des cas supplémentaires.
- Rendre le parseur plus tolérant (espaces optionnels, minuscules, autres séparateurs).
- Extraire une API publique claire si vous souhaitez exposer le service via un controller REST.

https://codingdojo.org/kata/Bowling/
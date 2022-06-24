# DAO

Die main liegt in: https://github.com/Noah4ever/DAO/blob/a8f163a1b7845f8ae557c85408878fe4e1093ebc/src/Kaufvertrag/presentationLayer/Programm.java

Kaufvertrag erstellung

## Ablauf des Programms:
1.	Der Benutzer kann zwischen XML und SQLite wählen.
2.	Die ausgewählte Datei wird eingelesen.
3.	Mit „0“ kann der Benutzer eine Ebene zurück gehen.
4.	Der Benutzer kann zwischen: Vertragspartner, Ware und Ausgabe wählen.
###	Vertragspartner:
  -	Der Benutzer kann zwischen: Neu erstellen und Bearbeiten wählen.
  -	Neu erstellen:
    -	Der Benutzer wird aufgefordert Vorname, Nachname, Ausweisnummer, Straße, Hausnummer, PLZ und Ort einzugeben.
    -	Falls es nicht schon zwei Vertragspartner gibt wird er hinzugefügt.
  -	Bearbeiten:
    -	Der Benutzer kann zwischen: Vorname, Nachname, Ausweisnummer, Straße, Hausnummer, PLZ, Ort und „Delete“ wählen.
    -	Wenn er etwas verändern möchte, wird er aufgefordert seine Auswahl zu überschreiben.
    -	Bei „Delete“ wird die Ware gelöscht.

![image](https://user-images.githubusercontent.com/66632359/175576209-ed9d9ee0-49f5-4696-98b1-cc6d9fe9495e.png)

###	Ware:
  -	Der Benutzer kann zwischen: Neu erstellen und Bearbeiten wählen:
  -	Neu erstellen:
    -	Der Benutzer wird aufgefordert Bezeichnung, Preis, Beschreibung, Besonderheiten und Mängel einzugeben.
    -	Falls es nicht eine Ware gibt wird sie hinzugefügt.
  -	Bearbeiten:
    -	Der Benutzer kann zwischen: Bezeichnung, Preis, Beschreibung, Besonderheiten und Mängel und „Delete“ wählen.
    -	Wenn er etwas verändern möchte, wird er aufgefordert seine Auswahl zu überschreiben.
    -	Bei „Delete“ wird die Ware gelöscht.

![image](https://user-images.githubusercontent.com/66632359/175576449-c99080b6-3e39-4102-aa46-ab7ffb3baf15.png)

###	Ausgabe:
  -	Die Vertragspartner sowie die Ware werden in Tabellenform ausgegeben.
![image](https://user-images.githubusercontent.com/66632359/175576084-aa8dae49-62bf-456f-ab4d-191d79feddb4.png)


![image](https://user-images.githubusercontent.com/66632359/175573630-c7c30b83-bf2e-454b-b2e9-e9b6301189f6.png)

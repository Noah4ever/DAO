# DAO

Kaufvertrag erstellung

Ablauf des Programms:
1.	Der Benutzer kann zwischen XML und SQLite wählen.
2.	Die ausgewählte Datei wird eingelesen.
3.	Mit „0“ kann der Benutzer eine Ebene zurück gehen.
4.	Der Benutzer kann zwischen: Vertragspartner, Ware und Ausgabe wählen.
5.	Vertragspartner:
  -	Der Benutzer kann zwischen: Neu erstellen und Bearbeiten wählen.
  -	Neu erstellen:
    -	Der Benutzer wird aufgefordert Vorname, Nachname, Ausweisnummer, Straße, Hausnummer, PLZ und Ort einzugeben.
    -	Falls es nicht schon zwei Vertragspartner gibt wird er hinzugefügt.
  -	Bearbeiten:
    -	Der Benutzer kann zwischen: Vorname, Nachname, Ausweisnummer, Straße, Hausnummer, PLZ, Ort und „Delete“ wählen.
    -	Wenn er etwas verändern möchte, wird er aufgefordert seine Auswahl zu überschreiben.
    -	Bei „Delete“ wird die Ware gelöscht.
6.	Ware:
  -	Der Benutzer kann zwischen: Neu erstellen und Bearbeiten wählen:
  -	Neu erstellen:
    -	Der Benutzer wird aufgefordert Bezeichnung, Preis, Beschreibung, Besonderheiten und Mängel einzugeben.
    -	Falls es nicht eine Ware gibt wird sie hinzugefügt.
  -	Bearbeiten:
    -	Der Benutzer kann zwischen: Bezeichnung, Preis, Beschreibung, Besonderheiten und Mängel und „Delete“ wählen.
    -	Wenn er etwas verändern möchte, wird er aufgefordert seine Auswahl zu überschreiben.
    -	Bei „Delete“ wird die Ware gelöscht.
7.	Ausgabe:
  -	Die Vertragspartner sowie die Ware werden in Tabellenform ausgegeben.

![image](https://user-images.githubusercontent.com/66632359/175573630-c7c30b83-bf2e-454b-b2e9-e9b6301189f6.png)

SQLite format 3   @     "                                                             " .S`6 ��>�GGA                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         E�//�GtablesEr//�+tablesqlb_temp_table_2sqlb_temp_table_2CREATE TABLE "sqlb_temp_table_2" 6++�otableVertragspartnerVertragspartnerCREATE TABLE "Vertragspartner" (
	"id"	INTEGER DEFAULT 'id',
	"vorname"	TEXT,
	"nachname"	TEXT,
	"ausweisNr"	REAL,
	"adresseId"	INTEGER, "strasse"	TEXT,
	FOREIGN KEY("adresseId") REFERENCES "Adresse"("id"),
	PRIMARY KEY("id" AUTOINCREMENT)
)� ##�GtableKaufvertragKaufvertragCREATE TABLE "Kaufvertrag" (
	"id"	INTEGER,
	"kaeuferId"	INTEGER,
	"verkaeuferId"	INTEGER,
	"wareId"	INTEGER,
	"zahlungsmodalitaeten"	TEXT,
	FOREIGN KEY("wareId") REFERENCES "Ware"("id"),
	FOREIGN KEY("verkaeuferId") REFERENCES "Vertragspartner"("id"),
	FOREIGN KEY("kaeuferId") REFERENCES "Vertragspartner"("id"),
	PRIMARY KEY("id" AUTOINCREMENT)
)H ++�Ot��	�{	++�-tableVertragspartnerVertragspartnerCREATE TABLE "Vertragspartner" (
	"id"	INTEGER DEFAULT 'id',
	"vorname"	TEXT,
	"nachname"	TEXT,
	"ausweisNr"	REAL,
	"strasse"	TEXT,
	"plz"	TEXT,
	"ort"	TEXT,
	"hausNr"	TEXT,
	PRIMARY KEY("id" AUTOINCREMENT)
)      ��tableAdresseAdresseCREATE TABLE "Adresse" (
	"id"	INTEGER,
	"strasse"	TEXT,
	"hausNr"	TEXT,
	"plz"	TEXT,
	"ort"	TEXT,
	PRIMARY KEY("id" AUTOINCREMENT)
)P++Ytablesqlite_sequencesqlite_sequenceCREATE TABLE sqlite_sequence(name,seq) �?�atableWareWareCREATE TABLE "Ware" (
	"id"	INTEGER,
	"bezeichnung"	TEXT,
	"beschreibung"	TEXT,
	"preis"	TEXT,
	"besonderheiten"	TEXT,
	"maengel"	TEXT,
	PRIMARY KEY("id" AUTOINCREMENT)
)   � ��                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       pw57StockLang und Hart! Perfekt zum   HM> );LaptopWie neu100.0[Silber,  neu][Kratzer,  Akku kaputt]   � ���                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            � sqlb_temp_table_3  Ware+	Vertragspartner      �                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                strasse3dsafsdf                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            � c c�                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     5 	 #%NoahFieringL1213BZY192Am Osterholz12345Syke1   +#JuliusEwertL7812USM191Kornhaus189	 !#JuliusSchneevertL7812USM191Kornhaus18217Bremen77
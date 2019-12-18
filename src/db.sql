create table schueler(
	SID serial not null primary key,
	Vorname varchar,
	Nachname varchar
);

create table buch(
	BID serial not null primary key,
	Sachgebiet varchar,
	Buchtitel varchar,
	A_nachname varchar,
	A_vorname varchar,
	Verlag varchar,
	Erscheinungsjahr date
);

create table ausleihen(
	SID int not null REFERENCES schueler(SID),
	BID int not null REFERENCES buch(BID),
	Datum date,
	primary key(SID, BID)
);



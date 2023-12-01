create table geschl (g_id int constraint geschlecht primary key, g_bez char(15));

insert into geschl values
(0, 'Unbekannt'),
(1, 'Weiblich'),
(2, 'Männlich'),
(3, 'Diverse');

create table abteil (a_id int constraint abteilung primary key, a_bez char(15));

insert into abteil values
(0, 'Ohne Abteilung'),
(1, 'Verwaltung'),
(2, 'Produktion'),
(3, 'Vertrieb'),
(4, 'IT'),
(5, 'Logistik'),
(6, 'Lager'),
(7, 'Beschaffung');

create table personal (
	tbl_id	int		identity(1,1)		constraint personal_i primary key,
	p_id	int not null unique,
	nname	varchar(20) not null,
	vname	varchar(20) not null,
	geb		date,
	p_abt	int constraint fk_p_abteilung foreign key references abteil(a_id) default 0,
	P_geschl int constraint fk_p_geschlecht foreign key references geschl(g_id) default 0)

insert into personal (p_id, nname, vname) values
(1, 'Sarkus', 'Möder'),
(2, 'Mangela', 'Erkel'),
(3, 'Fistof', 'Chrorster'),
(4, 'Lonika', 'Mevinski'),
(5, 'Listian', 'Chrindner'),
(6, 'Bannalena', 'Aerbock'),
(7, 'Poris', 'Bistorius');

drop table personal

select p_id ID, nname Nachname, vname Vorname, g.g_bez Geschlecht, a.a_bez Abteilung
from personal p left join geschl g on p_geschl=g_id left join abteil a on p_abt = a_id

insert into personal (p_id, nname, vname) values
(1, 'Sarkus', 'Möder')

SELECT count(*) FROM information_schema.tables
WHERE table_name = 'personal'

update personal set nname = 'Changed' where p_id=5

delete from personal where p_id = 1;
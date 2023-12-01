package ab.model;

public class SQL_Data {
    @SuppressWarnings("unused")
    static final String connectionURL_local = "jdbc:sqlserver://localhost:1433;"
            + "databaseName=db_test;user=dba;password=dba";

    static final String connectionURL = "jdbc:sqlserver://10.140.130.17:1433;"
            + "databaseName=DBA_Test_AB;user=dba5;password=dba5;";


    static final String createTableGeschlecht = "create table geschl (g_id int constraint geschlecht primary key, g_bez char(15));";
    static final String fillTableGeschlecht = "insert into geschl values\n" +
            "(0, 'Unbekannt'),\n" +
            "(1, 'Weiblich'),\n" +
            "(2, 'Männlich'),\n" +
            "(3, 'Diverse');";
    static final String dropTableGeschlecht = "drop table geschl";

    static final String createTableAbteilung = "create table abteil (a_id int constraint abteilung primary key, a_bez char(15));";
    static final String fillTableAbteilung = "insert into abteil values\n" +
            "(0, 'Ohne Abteilung'),\n" +
            "(1, 'Verwaltung'),\n" +
            "(2, 'Produktion'),\n" +
            "(3, 'Vertrieb'),\n" +
            "(4, 'IT'),\n" +
            "(5, 'Logistik'),\n" +
            "(6, 'Lager'),\n" +
            "(7, 'Beschaffung');";
    static final String dropTableAbteilung = "drop table abteil";

    static final String createTablePersonal = "create table personal (" +
            " tbl_id int  identity(1,1)  constraint personal_id primary key," +
            " p_id int not null unique," +
            " nname varchar(20) not null," +
            " vname varchar(20) not null," +
            " geb  date," +
            " p_abt int constraint fk_p_abteilung foreign key references abteil(a_id) default 0," +
            " p_geschl int constraint fk_p_geschlecht foreign key references geschl(g_id) default 0)";
    static final String fillTablePersonal = "insert into personal (p_id, nname, vname) values\n" +
            "(1, 'Sarkus', 'Möder'),\n" +
            "(2, 'Mangela', 'Erkel'),\n" +
            "(3, 'Fistof', 'Chrorster'),\n" +
            "(4, 'Lonika', 'Mevinski'),\n" +
            "(5, 'Listian', 'Chrindner'),\n" +
            "(6, 'Bannalena', 'Aerbock'),\n" +
            "(7, 'Poris', 'Bistorius');";
    static final String dropTablePersonal = "drop table personal";

    static final String selectAllPersonal = "select p_id ID, nname Nachname, vname Vorname, g.g_bez Geschlecht, a.a_bez Abteilung\n"
            + "from personal p left join geschl g on p_geschl=g_id left join abteil a on p_abt = a_id";


    String[][] persons = {{"Wahnsinn", "Hella", "1001"},
            {"Donner", "Loki", "9678"},
            {"Bartsch, Jorgo", "50"}};
}

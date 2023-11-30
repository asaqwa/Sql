package ab.model;

public class SQL_Data {
    static final String connectionURL = "jdbc:sqlserver://localhost:1433;"
            + "databaseName=db_test;user=dba;password=dba";
    static final String createTablePersonal = "create table personal (id int primary key, name varchar(30) not null)";
    static final String dropTablePersonal = "drop table personal";

}

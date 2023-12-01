package ab.model;

import java.sql.*;

import static ab.model.SQL_Data.*;

/*

FI27A-Prof
TN anlegen
TN suchen(info)
TN bearbeiten *
TN löschen *

*/

public class DB_Manager implements AutoCloseable {
    private final Connection con;
    private final Statement stmt;

    public DB_Manager(String connectionUrl) throws SQLException, ClassNotFoundException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        this.con = DriverManager.getConnection(connectionUrl);
        stmt = con.createStatement();
    }

    public static void main(String[] args) throws Exception {
        try (DB_Manager inst = new DB_Manager(connectionURL)) {
            inst.initDB();
            printTable(inst.stmt.executeQuery(selectAllPersonal));

            inst.changeValue("nname", 1, "changed");
            inst.changeValue("p_geschl", 1, 1);
            inst.changeValue("p_abt", 1, 1);


            System.out.println();

            printTable(inst.stmt.executeQuery(selectAllPersonal));

            inst.removePerson(1);

            printTable(inst.stmt.executeQuery(selectAllPersonal));
        }
    }

    public void initDB() throws SQLException {
        dropDB();
        stmt.executeUpdate(createTableAbteilung);
        stmt.executeUpdate(fillTableAbteilung);
        stmt.executeUpdate(createTableGeschlecht);
        stmt.executeUpdate(fillTableGeschlecht);
        stmt.executeUpdate(createTablePersonal);
        stmt.executeUpdate(fillTablePersonal);
    }

    public void dropDB() throws SQLException {
        DatabaseMetaData db_mt = con.getMetaData();
        if (hasTable("personal")) stmt.executeUpdate(dropTablePersonal);
        if (hasTable("geschl")) stmt.executeUpdate(dropTableGeschlecht);
        if (hasTable("abteil")) stmt.executeUpdate(dropTableAbteilung);

    }

    private boolean hasTable(String tableName) throws SQLException {
        return con.getMetaData().getTables(null, null, tableName, new String[] {"TABLE"}).next();
    }

    /**
     * @param  newValue type depends on the type of column being changed
     */
    public void changeValue(String colName, int p_id, String newValue) throws SQLException {
        stmt.executeUpdate("update personal set "
                + colName +  " = '" + newValue + "' where p_id=" + p_id);
    }

    /**
     * @param  newValue type depends on the type of column being changed
     */
    public void changeValue(String colName, int p_id, int newValue) throws SQLException {
        stmt.executeUpdate("update personal set "
                + colName +  " = " + newValue + " where p_id=" + p_id);

    }

    public void addPerson(int p_id, String nname, String vname, long geb, int geschl, int abt) throws SQLException {
        stmt.executeUpdate("insert into personal (p_id, nname, vname) values\n" +
                "(1, 'Sarkus', 'Möder')");
    }

    public void removePerson(int p_id) throws SQLException {
        stmt.executeUpdate("delete from personal where p_id = "+p_id);
    }

    static void printTable(ResultSet rs) throws SQLException {
        ResultSetMetaData rsmt = rs.getMetaData();
        int colCount = rsmt.getColumnCount();
        int[] colSize = new int[colCount];
        for (int i = 0; i<colCount; ) {
            colSize[i] = rsmt.getColumnDisplaySize(++i);
        }

        for (int i = 0; i<colCount; ) {
            System.out.printf("%-" + colSize[i] + "s", rsmt.getColumnName(++i));
        }
        System.out.println();

        while (rs.next()) {
            for (int i = 0; i<colCount; ) {
                System.out.printf("%-" + colSize[i] + "s", rs.getString(++i));
                Object ob = rs.getString(i);
            }
            System.out.println();
        }

    }


    @Override
    public void close() throws Exception {
        if (con!=null) con.close();
    }
}
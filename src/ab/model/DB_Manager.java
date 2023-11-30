package ab.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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




    public DB_Manager(String connectionUrl) throws SQLException {
        this.con = DriverManager.getConnection(connectionUrl);
        stmt = con.createStatement();
    }



    public static void main(String[] args) throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        try (DB_Manager inst = new DB_Manager(connectionURL)) {

            String[][] persons = {{"Wahnsinn", "Hella", "1001"},
                    {"Donner", "Loki", "9678"},
                    {"Bartsch, Jorgo", "50"}};

            inst.stmt.executeUpdate(createTablePersonal);

//            inst.dropTable("person");


        }
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



    private static void fillColOrt(Connection con, ResultSet rs) throws SQLException {
        PreparedStatement prStmt = con.prepareStatement("update person1 set Ort = ? where id = ?");
        StringBuilder results = new StringBuilder("Results: ");
        while (rs.next()) {
            prStmt.setString(1, getRandomOrt());
            prStmt.setInt(2, rs.getInt(1));
            int res =  prStmt.executeUpdate();
            results.append(res).append(", ");
        }
        System.out.println(results.toString().substring(0, results.length()-2));
    }



    private static String getRandomOrt() {
        String[] orte = {"Berlin", "München", "Nürnberg", "Ansbach", "Hamburg", "Münster", "Erlangen", "Osnabrück", "Heidenheim", "Ingolstadt"};
        return orte[(int) (Math.random()*10)];
    }



    private static void executeUserCommand(Statement stmt, String printAll) throws IOException, SQLException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String query = "";
            while (true) {
                String line = reader.readLine();
                if (line.isEmpty() || "exit".equalsIgnoreCase(line)) break;
                if ("retry".equalsIgnoreCase(line)) {
                    query = "";
                    continue;
                }
                query += (line + " ");
            }

            query = query.trim();

            if (query.isEmpty()) return;

            if (query.toLowerCase().startsWith("select")) {
                printTable(stmt.executeQuery(query));
            } else {
                int res = stmt.executeUpdate(query);
                System.out.println("Result of the user-command: " + res);
                printTable(stmt.executeQuery(printAll));
            }
        }
    }



    @Override
    public void close() throws Exception {
        if (con!=null) con.close();
    }
}
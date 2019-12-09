package Database;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DBConnector {

    private Connection CONNECTION = null;
    private boolean connected = false;

    public DBConnector() {

    }

    public boolean isConnected(){
        return this.connected;
    }
    
    public boolean connect() {

     
        try {
            Class.forName("org.postgresql.Driver");//"org.postgresql.Driver"
            if (CONNECTION == null) {
                CONNECTION = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/bibliothekverwaltung", "postgres", "Fungog04");//"jdbc:postgresql://127.0.0.1:5432/Weine" - postgres - "Fungog04"
            }
            connected = true;
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public Connection getConnetion() {
        return this.CONNECTION;
    }

    public int executeUpdate(String sql) {

        try {
            Statement statement = CONNECTION.createStatement();
            return statement.executeUpdate(sql);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return -1;
    }

    public String printResultSet(ResultSet answer) {

        String ausgabe = "";

        try {
            for (int i = 1; i <= answer.getMetaData().getColumnCount(); i++) {
                ausgabe += (answer.getMetaData().getColumnName(i) + "\t|");
            }
            ausgabe += ("\n--------------------------\n");

            while (answer.next()) {

                for (int i = 1; i <= answer.getMetaData().getColumnCount(); i++) {
                    ausgabe += (answer.getString(i) + "\t|");
                }
                ausgabe += "\n";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.println(ausgabe);
        return ausgabe;
    }

    
    public ResultSet executeQuery(String sql) {
        try {
            Statement statement = CONNECTION.createStatement();
            ResultSet answer = statement.executeQuery(sql);
            return answer;
        } catch (Exception ex) {
             JOptionPane.showMessageDialog(null,"Fehler beim Ausführen der Query\n"+"Überprüfe die Syntax", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
        return null;
    }

    public void close() {
        try {
            if (CONNECTION != null) {
                connected = false;
                CONNECTION.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}

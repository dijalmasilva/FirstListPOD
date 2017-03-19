package br.com.dijalmasilva;

import java.sql.*;

/**
 * Created by <a href="http://dijalmasilva.github.io/" target="_blank">Dijalma Silva</a> on 12/03/17 - 18:28
 */
public class ConnectionDataBase {

    private Connection postgres = null;
    private Connection mysql = null;

    private void connectPostgres() throws SQLException {
        this.postgres = DriverManager.getConnection("jdbc:postgresql://localhost:5432/question4pod", "postgres", "123456");
    }

    private void connectMySql() throws SQLException {
        this.mysql = DriverManager.getConnection("jdbc:mysql://localhost/question4pod", "dijalma", "dijalma123");
    }

    public boolean insertPostgres(String msg) throws SQLException {
        if (postgres == null){
            connectPostgres();
        }

        PreparedStatement statement = postgres.prepareStatement("INSERT INTO message (msg) VALUES (?)");
        statement.setString(1, msg);
        boolean execute = statement.execute();
        statement.close();
        return execute;
    }

    public boolean insertMySql(String msg) throws SQLException {
        if (mysql == null){
            connectMySql();
        }

        PreparedStatement statement = mysql.prepareStatement("INSERT INTO message (msg) VALUES (?)");
        statement.setString(1, msg);
        boolean execute = statement.execute();
        statement.close();
        return execute;
    }
}

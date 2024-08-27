package de.revivemc.buildffa.storage.sql;

import org.bukkit.Bukkit;

import java.sql.*;

public class MySQL {
    private String HOST = "127.0.0.1";
    private String DATABASE = "mcserver";
    private String USER = "root";
    private String PASSWORD = "password";
    private Connection con;

    public MySQL(String host, String database, String user, String password)
    {
        this.HOST = host;
        this.DATABASE = database;
        this.USER = user;
        this.PASSWORD = password;

        connect();
    }

    public void connect()
    {
        try
        {
            this.con = DriverManager.getConnection("jdbc:mysql://" + this.HOST + ":3306/" + this.DATABASE + "?autoReconnect=true", this.USER, this.PASSWORD);
            Bukkit.getConsoleSender().sendMessage("Connected to MySQL!");
        }
        catch (SQLException e)
        {
            Bukkit.getConsoleSender().sendMessage("Errow while connecting to MySQL! Error: " + e.getMessage());
        }
    }

    public void close()
    {
        try
        {
            if (this.con != null)
            {
                this.con.close();
                Bukkit.getConsoleSender().sendMessage("Connection to MySQL was stopped!");
            }
        }
        catch (SQLException e)
        {
            Bukkit.getConsoleSender().sendMessage("Error while stopping MySQL! Error: " + e.getMessage());
        }
    }

    public void update(String sql)
    {
        try
        {
            this.con.createStatement().executeUpdate(sql);
        }
        catch (SQLException localSQLException) {
        }
    }

    public ResultSet query(String qry) {
        ResultSet rs = null;
        try
        {
            Statement st = this.con.createStatement();
            rs = st.executeQuery(qry);
        }
        catch (SQLException e)
        {
            connect();
            System.err.println(e);
        }
        return rs;
    }
}

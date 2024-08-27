package de.revivemc.buildffa.storage.map;

import de.revivemc.buildffa.BuildFFA;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapModule {

    public String getAllMapsAsString() {
        String string = "";

        try {
            final ResultSet resultSet = BuildFFA.mysql.query("SELECT * FROM Maps");
            while (resultSet.next()) {
                string = resultSet.getString("MapName");
            }
        }catch (SQLException ex) {
            ex.printStackTrace();
        }

        return string;
    }
}

package db;


import javafx.scene.image.Image;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class PostgresHelper {

    private Connection conn;
    private String host;
    private String dbName;
    private String user;
    private String pass;

    //we don't like this constructor
    protected PostgresHelper() {}

    public PostgresHelper(String host, String dbName, String user, String pass) {
        this.host = host;
        this.dbName = dbName;
        this.user = user;
        this.pass = pass;
    }

    public ResultSet execQuery(String query) throws SQLException {
        return this.conn.createStatement().executeQuery(query);
    }

    public int insert(String table, Map<String,Object> values) throws SQLException {

        StringBuilder columns = new StringBuilder();
        StringBuilder vals = new StringBuilder();

        for (String col : values.keySet()) {
            columns.append(col).append(",");

            if (values.get(col) instanceof String) {
                vals.append("'").append(values.get(col)).append("', ");
            }
            else vals.append(values.get(col)).append(",");
        }


        columns.setLength(columns.length()-1);
        vals.setLength(vals.length()-2);

        String query = String.format("INSERT INTO %s (%s) VALUES (%s);", table,
                columns.toString() , vals.toString());
        System.out.println(query);
        return this.conn.createStatement().executeUpdate(query);
    }
    //===
    public String[] select(String queryDate,String querycity_country) throws SQLException {
        ResultSet rs = null;
        try {
            rs = execQuery("SELECT * FROM weatherinfo WHERE date='"+queryDate+"'"+"AND city_country='"+querycity_country+"';");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        String[] result = new String[8];
        while(rs.next()) {
            result[0] = rs.getString(1);
            result[1] = rs.getString(2);
            result[2] = rs.getString(3);
            result[3] = rs.getString(4);
            result[4] = rs.getString(5);
            result[5] = rs.getString(6);
            result[6] = rs.getString(7);
            result[7] = rs.getString(8);
        }


        return result;
    }
    //===
    public boolean connect() throws SQLException, ClassNotFoundException {
        if (host.isEmpty() || dbName.isEmpty() || user.isEmpty() || pass.isEmpty()) {
            throw new SQLException("Database credentials missing");
        }

        Class.forName("org.postgresql.Driver");
        this.conn = DriverManager.getConnection(
                this.host + this.dbName,
                this.user, this.pass);
        return true;
    }

    public Map prepare( String city_country,
                        String date,
                        String description,
                        String humidity,
                        String pressure,
                        String temp,
                        String degree,
                        String speed){
        Map vals = new HashMap();
        vals.put("city_country",city_country);
        vals.put("date",date);
        vals.put("description", description);
        vals.put("humidity", humidity);
        vals.put("pressure", pressure);
        vals.put("temp", temp);
        vals.put("deg", degree);
        vals.put("speed", speed);

        return vals;
    }

}
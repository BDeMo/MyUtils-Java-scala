package Mytraining.DataBase;

import java.sql.*;

public class Connect {
    public static void main(String[] args) {
        Connection con;
        String driver="com.mysql.jdbc.Driver";
        String url="jdbc:mysql://localhost:3303/mydb";
        String user="samj";
        String password="";
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
            if (!con.isClosed()) {
                System.out.println("connectclose");
            }
            Statement statement = con.createStatement();
            String sql = "select * from demo;";//�ҵı���home
            ResultSet resultSet = statement.executeQuery(sql);
            String name;
            while (resultSet.next()) {
                name = resultSet.getString("name");
                System.out.println("name:" + name);
            }
            resultSet.close();
            con.close();
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException");
            e.printStackTrace();

        } catch (SQLException e) {
            System.out.println("SQLException");
            e.printStackTrace();
        }
    }
}

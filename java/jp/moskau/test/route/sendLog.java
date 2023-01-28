package jp.moskau.test.route;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

class sendLog extends Thread{
    public void run() {
        try (
        Connection sql_con = DriverManager.getConnection(
                "jdbc:mysql://localhost/main?useSSL=false",
                "web",
                "Verkhovna_Rada"
        );

        )
        {

            PreparedStatement script = sql_con.prepareStatement("INSERT INTO access_log (ip, access_at) VALUES ('" + ipStorage.ip + "', cast(now() as datetime))");
            script.execute();

            script.close();



        } catch (SQLException e) {
            System.out.println("logging failure.");
            e.printStackTrace();
        }



    }
}

package com.github.x7fffffff;

import org.junit.Test;

import java.io.InputStream;
import java.sql.*;

import static org.junit.Assert.*;

public class MainTest {


    @Test
    public void test() throws Exception {
        final String db = "jdbc:hsqldb:mem::mymemdb";
        final String user = "SA";
        final String password = "";
        try (final Connection connection = DriverManager.getConnection(db, user, password);
             final Statement statement = connection.createStatement();
             final InputStream is = Main.class.getClassLoader().getResourceAsStream("Test.java");
        ) {
            //final JavaVisitor psmCode = Main.getPSMCode(is);
            // final String sql = psmCode.toString();
            final String sql1 = "SET SCHEMA PUBLIC;\n"+
                    "    CREATE FUNCTION func2(x int) RETURNS int\n" +
                            "    BEGIN ATOMIC\n" +
                            "            return x * x + 1;\n" +
                            "    END";
            statement.executeUpdate(sql1);

            final String sql2 =     "   CREATE FUNCTION func1(x int) RETURNS int\n" +

                    "    BEGIN ATOMIC\n" +
                    "    DECLARE " + "y int;\n" +
                    "          SET y =  PUBLIC.func2(x);\n" +
                    "            return x * x;\n" +
                    "    END\n" +
                    "\n"
                   ;
            statement.executeUpdate(sql2);
           /* System.out.println(sql);
            statement.executeUpdate(sql);*/
            try (final CallableStatement callableStatement = connection.prepareCall("{Call func1(?)}")) {
                callableStatement.setInt(1, 2);
                final ResultSet rs = callableStatement.executeQuery();
                if (rs.next()) {
                    System.out.println("result=" + rs.getInt(1));
                }
            }

        }
    }
}
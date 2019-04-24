package com.myServer.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnector {
	 public  static  void  main ( String []  args )  { 
	        Connection  conn  =  null ; 
	        Statement  stmt  =  null ; 
	        ResultSet  rset  =  null ;

	        // 연결 문자열 
	        String url = "jdbc:postgresql://127.0.0.1:5432/myData" ; 
	        String user = "postgres"; 
	        String password="0000" ;

	        try { 
	            // PostgreSQL에 연결 
	            conn = DriverManager.getConnection(url, user, password);

	            // 자동 커밋 OFF
	            conn . setAutoCommit(false);

	            // SELECT 문의 실행 
	            stmt = conn.createStatement(); 
	            String sql = "SELECT 1"; 
	            rset = stmt.executeQuery(sql);

	            // SELECT 결과의 수신 
	            while ( rset . next ()) { 
	                String  col  =  rset . getString ( 1 ); 
	                System.out.println(col); 
	            }

	            // INSERT 문 실행 
	            sql  =  "INSERT INTO jdbc_test VALUES (1, 'AAA')" ; 
	            stmt . executeUpdate ( sql ); 
	            conn . commit (); 
	        } 
	        catch  (SQLException  e ) { 
	            e . printStackTrace (); 
	        } 
	        finally  { 
	            try  { 
	                if ( rset != null ) rset . close (); 
	                if ( stmt != null ) stmt . close ();
	                if ( conn != null ) conn . close (); 
	            } 
	            catch  ( SQLException  e ) { 
	                e . printStackTrace (); 
	            }

	        } 
	    } 
}

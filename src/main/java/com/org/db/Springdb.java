package com.org.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository

public class Springdb
{
	@Autowired
    public JdbcTemplate jdbcTemplate;
	
	public void getData()
	{
String SQL = "select * from scb_spliter.temp_table";

        PreparedStatement pstmt = null;
		
				
                try 
                {
                	Connection connection = jdbcTemplate.getDataSource().getConnection();
					pstmt = connection.prepareStatement(SQL);
					ResultSet rs = pstmt.executeQuery();
					while(rs.next())
					{
						System.out.println(rs.getString("id"));
					}
				}
                catch (SQLException e) 
                {
					e.printStackTrace();
				}
	}
	
}

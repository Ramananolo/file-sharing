package com.file.share.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
@AllArgsConstructor
public class DbConnector {
    private DbProperties dbProperties;

    public Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(
                dbProperties.getUrl(),
                dbProperties.getUser(),
                dbProperties.getPassword()
        );
        connection.setAutoCommit(false);
        return connection;
    }

}
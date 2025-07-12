package com.file.share.repository;

import com.file.share.config.DbConnector;
import com.file.share.repository.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class UserRepository {
    private final DbConnector dbConnector;

    public User findByEmail(String email){
        String query = "SELECT * FROM users WHERE email = ?";
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement preparedStatement= connection.prepareStatement(query)){
             preparedStatement.setString(1,email);
             ResultSet resultSet = preparedStatement.executeQuery();

             if (resultSet.next()){
                 return new User(
                        (UUID) resultSet.getObject("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password")
                 );
             }
             return null;
        } catch (SQLException e){
            throw new RuntimeException();
        }
    }

    public void save(User user) {
        String query = "INSERT INTO users (email,password) VALUES (?,?)";
        try (PreparedStatement statement = dbConnector.getConnection().prepareStatement(query)) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.executeUpdate();

        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public boolean existByEmail(String email){
        String query = "SELECT 1 FROM users WHERE email = ?";
        try (PreparedStatement statement = dbConnector.getConnection().prepareStatement(query)) {
            statement.setString(1,email);
            try(ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }

        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}

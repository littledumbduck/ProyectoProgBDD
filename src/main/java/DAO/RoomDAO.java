package DAO;

import entities.Room;
import utilities.ConexionMySQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomDAO {
    private Room room;

    public RoomDAO(Room room) {
        this.room = room;
    }

    public void addRoom() throws SQLException {
        ConexionMySQL sql = new ConexionMySQL();
        String statement = "INSERT INTO `room`(`roomNumber`, `roomfloor`, `roomType`, `price`, `status`) " +
                "VALUES (?,?,?,?,?)";

        try (PreparedStatement pstmt = sql.executeStatement(statement)) {
            pstmt.setInt(1, this.room.getRoomNumber());
            pstmt.setInt(2, this.room.getRoomFloor());
            pstmt.setString(3, this.room.getRoomType());
            pstmt.setDouble(4, this.room.getPrice());
            pstmt.setString(5, String.valueOf(this.room.getStatus()));

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateRoom() throws SQLException {
        ConexionMySQL sql = new ConexionMySQL();
        String statement = "UPDATE `room` SET `roomNumber`='?',`roomfloor`='?',`roomType`='?'," +
                "`price`='?',`status`='?' WHERE roomNumber = ?";

        try (PreparedStatement pstmt = sql.executeStatement(statement)) {
            pstmt.setInt(1, this.room.getRoomNumber());
            pstmt.setInt(2, this.room.getRoomFloor());
            pstmt.setString(3, this.room.getRoomType());
            pstmt.setDouble(4, this.room.getPrice());
            pstmt.setString(5, String.valueOf(this.room.getStatus()));
            pstmt.setInt(6, this.room.getRoomNumber());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteRoom() throws SQLException {
        ConexionMySQL sql = new ConexionMySQL();
        String statement = "DELETE FROM `room` WHERE roomNumber = ?";

        try (PreparedStatement pstmt = sql.executeStatement(statement)) {
            pstmt.setInt(1, this.room.getRoomNumber());

            pstmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet searchRoom() throws SQLException {
        ConexionMySQL sql = new ConexionMySQL();
        String statement = "SELECT * FROM room WHERE roomNumber = ?";
        ResultSet rs = null;

        try (PreparedStatement pstmt = sql.executeStatement(statement)) {
            pstmt.setInt(1, this.room.getRoomNumber());

            rs = pstmt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;

    }

}

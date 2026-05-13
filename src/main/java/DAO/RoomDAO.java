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

    public RoomDAO(){
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
        }

    }

    public void updateRoom() throws SQLException {
        ConexionMySQL sql = new ConexionMySQL();
        String statement = "UPDATE `room` SET `roomNumber`=?,`roomfloor`=?,`roomType`=?," +
                "`price`=?,`status`=? WHERE roomNumber = ?";

        try (PreparedStatement pstmt = sql.executeStatement(statement)) {
            pstmt.setInt(1, this.room.getRoomNumber());
            pstmt.setInt(2, this.room.getRoomFloor());
            pstmt.setString(3, this.room.getRoomType());
            pstmt.setDouble(4, this.room.getPrice());
            pstmt.setString(5, String.valueOf(this.room.getStatus()));
            pstmt.setInt(6, this.room.getRoomNumber());

            pstmt.executeUpdate();

        }

    }

    public void deleteRoom() throws SQLException {
        ConexionMySQL sql = new ConexionMySQL();
        String statement = "DELETE FROM `room` WHERE roomNumber = ?";

        try (PreparedStatement pstmt = sql.executeStatement(statement)) {
            pstmt.setInt(1, this.room.getRoomNumber());

            pstmt.executeUpdate();

        }
    }

    public ResultSet searchRoom(int roomId) throws SQLException {
        ConexionMySQL sql = new ConexionMySQL();
        String statement = "SELECT * FROM room WHERE roomNumber = ?";
        PreparedStatement pstmt = sql.executeStatement(statement);
        pstmt.setInt(1, roomId);
        return pstmt.executeQuery();
    }

    public ResultSet getAllRooms() throws SQLException {
        ConexionMySQL sql = new ConexionMySQL();
        String statement = "SELECT * FROM room";
        java.sql.PreparedStatement pstmt = sql.executeStatement(statement);
        return pstmt.executeQuery();    }
}

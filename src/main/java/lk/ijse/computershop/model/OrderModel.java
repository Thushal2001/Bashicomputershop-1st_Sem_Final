package lk.ijse.computershop.model;

import lk.ijse.computershop.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderModel {

    public static String getNextOrderId() throws SQLException {
        String sql = "SELECT orderId FROM Orders ORDER BY orderId DESC LIMIT 1";
        ResultSet resultSet = CrudUtil.execute(sql);

        if (resultSet.next()) {
            return splitOrderId(resultSet.getString(1));
        }
        return splitOrderId(null);
    }

    private static String splitOrderId(String currentId) {
        if (currentId != null) {
            String[] strings = currentId.split("O");
            int id = Integer.parseInt(strings[1]);
            id++;
            return "O" + String.format("%02d", id);
        }
        return "O01";
    }

    public static boolean save(String orderId, String customerId) throws SQLException {
        String sql = "INSERT INTO Orders(orderId, customerId) VALUES(?, ?)";
        Integer affectedRows = CrudUtil.execute(sql, orderId, customerId);
        return affectedRows > 0;
    }

    public static List<String> loadIds() throws SQLException {
        String sql = "SELECT orderId FROM orders ORDER BY orderId ASC";
        ResultSet resultSet = CrudUtil.execute(sql);

        List<String> data = new ArrayList<>();
        while (resultSet.next()) {
            data.add(resultSet.getString(1));
        }
        return data;
    }
}

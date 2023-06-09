package lk.ijse.computershop.model;

import lk.ijse.computershop.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustombuildsModel {

    public static String getNextBuildCode() throws SQLException {
        String sql = "SELECT code FROM custombuilds ORDER BY code DESC LIMIT 1";
        ResultSet resultSet = CrudUtil.execute(sql);

        if (resultSet.next()) {
            return splitBuildCode(resultSet.getString(1));
        }
        return splitBuildCode(null);
    }

    private static String splitBuildCode(String currentId) {
        if (currentId != null) {
            String[] strings = currentId.split("B");
            int id = Integer.parseInt(strings[1]);
            id++;
            return "B" + String.format("%02d", id);
        }
        return "B01";
    }

    public static boolean save(String buildCode, String customerId, String employeeId) throws SQLException {
        String sql = "INSERT INTO custombuilds(code, customerId, employeeId) VALUES(?, ? ,?)";
        Integer affectedRows = CrudUtil.execute(sql, buildCode, customerId, employeeId);
        return affectedRows > 0;
    }
}

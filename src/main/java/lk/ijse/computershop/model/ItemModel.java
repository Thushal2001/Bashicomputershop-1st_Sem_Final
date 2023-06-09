package lk.ijse.computershop.model;

import lk.ijse.computershop.dto.Custombuilds;
import lk.ijse.computershop.dto.Order;
import lk.ijse.computershop.dto.Item;
import lk.ijse.computershop.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemModel {

    public static int save(Item item) throws SQLException {
        String sql = "INSERT INTO item VALUES (?,?,?,?)";
        return CrudUtil.execute(
                sql,
                item.getCode(),
                item.getDescription(),
                item.getUnitPrice(),
                item.getQtyOnHand()
        );
    }

    public static Item search(String code) throws SQLException {
        String sql = "SELECT * FROM item WHERE code=?";

        ResultSet resultSet = CrudUtil.execute(sql, code);
        if (resultSet.next()) {
            return new Item(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getInt(4)
            );
        }
        return null;
    }

    public static int update(Item item) throws SQLException {
        String sql = "UPDATE item SET description=? , unitPrice=? , qtyOnHand=? WHERE code=?";

        return CrudUtil.execute(
                sql,
                item.getDescription(),
                item.getUnitPrice(),
                item.getQtyOnHand(),
                item.getCode()
        );
    }

    public static int delete(String code) throws SQLException {
        String sql = "DELETE FROM item WHERE code=?";
        return CrudUtil.execute(sql, code);
    }

    public static List<Item> getAll() throws SQLException {

        List<Item> itemList = new ArrayList<>();
        String sql = "SELECT * FROM item";
        ResultSet resultSet = CrudUtil.execute(sql);

        while (resultSet.next()) {
            Item item = new Item(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getInt(4)
            );
            itemList.add(item);
        }
        return itemList;
    }

    public static String getNextItemCode() throws SQLException {
        String sql = "SELECT code FROM item ORDER BY code DESC LIMIT 1";

        ResultSet resultSet = CrudUtil.execute(sql);
        if (resultSet.next()) {
            return splitItemCode(resultSet.getString(1));
        }
        return splitItemCode(null);
    }

    private static String splitItemCode(String currentId) {
        if (currentId != null) {
            String[] strings = currentId.split("P");
            int id = Integer.parseInt(strings[1]);
            id++;
            return "P" + String.format("%02d", id);
        }
        return "P01";
    }

    public static List<String> loadCodes() throws SQLException {
        String sql = "SELECT code FROM item  ORDER BY code ASC";
        ResultSet resultSet = CrudUtil.execute(sql);

        List<String> data = new ArrayList<>();
        while (resultSet.next()) {
            data.add(resultSet.getString(1));
        }
        return data;
    }

    public static Item searchById(String itemCode) throws SQLException {
        String sql = "SELECT * FROM item WHERE code = ?";
        ResultSet resultSet = CrudUtil.execute(sql, itemCode);

        if (resultSet.next()) {
            return new Item(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getInt(4)
            );
        }
        return null;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////    Orders
    public static boolean updateQty(List<Order> orderList) throws SQLException {
        for (Order orderDetails : orderList) {
            if (!updateQty(orderDetails)) {
                return false;
            }
        }
        return true;
    }

    private static boolean updateQty(Order orderDetails) throws SQLException {
        String sql = "UPDATE item SET qtyOnHand = (qtyOnHand - ?) WHERE code = ?";
        Integer affectedRows = CrudUtil.execute(sql, orderDetails.getQty(), orderDetails.getCode());
        return affectedRows > 0;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////    Custom Build
    public static boolean updateBuildQty(List<Custombuilds> buildsList) throws SQLException {
        for (Custombuilds custombuilds : buildsList) {
            if (!updateBuildQty(custombuilds)) {
                return false;
            }
        }
        return true;
    }

    private static boolean updateBuildQty(Custombuilds buildsList) throws SQLException {
        String sql = "UPDATE item SET qtyOnHand = (qtyOnHand - ?) WHERE code = ?";
        Integer affectedRows = CrudUtil.execute(sql, buildsList.getQty(), buildsList.getCode());
        return affectedRows > 0;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////    Suppliers
    public static boolean updateSupplyQty(String itemCode,String supplyQty) throws SQLException {
        String sql = "UPDATE item SET qtyOnHand = (qtyOnHand + ?) WHERE code = ?";
        Integer affectedRows = CrudUtil.execute(sql, supplyQty, itemCode);
        return affectedRows > 0;
    }
}

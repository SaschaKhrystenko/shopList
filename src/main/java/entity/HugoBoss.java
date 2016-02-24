package entity;

import DAO.BaseDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HugoBoss extends BaseDAO implements Shop,Runnable{
    String shopName;
    private static HugoBoss instance;
    private HugoBoss(){

    }

    public static synchronized HugoBoss getInstance(){
        if(instance == null){
            instance = new HugoBoss();

        }
        return instance;

    }

    public String getShopName() {
        return shopName;
    }

    public int getShopId(String shopName){

      int shop_id = 0;

        Connection connection = getConnection();
        try {

            PreparedStatement preStet =connection.prepareStatement("SELECT id FROM shop WHERE name = ?");
            preStet.setString(1, shopName);
            ResultSet resultSet = preStet.executeQuery();

           while (resultSet.next()) {
                shop_id = resultSet.getInt("id");
           }

            preStet.close();
            resultSet.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return shop_id;
    }


    public List getAllShopData (int shop_id){

        List<Object>shopList = new ArrayList<Object>();



        Connection connection = getConnection();
        try {

            PreparedStatement preStat =connection.prepareStatement("SELECT shop.name, category.name, product.title, product.price, product.status \n" +
                    "  FROM shop JOIN category ON shop.id = category.shop_id JOIN product ON\n" +
                    "  category.id = product.category_id WHERE shop.id = ?;");

            preStat.setInt(1, shop_id);
            ResultSet resultSet = preStat.executeQuery();

            while (resultSet.next()){
                String shopName = resultSet.getString(1);
                String categoryName = resultSet.getString(2);
                String productTitle = resultSet.getString(3);
                Double productPrice = resultSet.getDouble(4);
                String productStatus = resultSet.getString(5);

                shopList.add(shopName);
                shopList.add(categoryName);
                shopList.add(productTitle);
                shopList.add(productPrice);
                shopList.add(productStatus);
            }

            preStat.close();
            resultSet.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return shopList;
    }

    public void setProduct(int shop_id,String categoryName, String title, double price, String status) {
        Connection connection = getConnection();
        try {
            PreparedStatement prStat = connection.prepareStatement("INSERT INTO product (category_id,title, price, status) VALUES \n"+
                    "((SELECT category.id FROM  category WHERE category.name = ? AND category.shop_id = ?),?,?,?)");
            prStat.setString(1, categoryName);
            prStat.setInt(2, shop_id);
            prStat.setString(3, title);
            prStat.setDouble(4, price);
            prStat.setString(5, status);
            prStat.execute();

            prStat.close();
            connection.close();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public List<Product> getAllProductsShopByCategory(int shop_id,String categoryName) {
        List<Product> productsList = new ArrayList<Product>();

        Connection connection = getConnection();
        try {

            PreparedStatement preStet = connection.prepareStatement("SELECT * FROM product WHERE product.category_id IN \n"+
                    "(SELECT category.id FROM category WHERE shop_id = ? AND category.name = ?)");
            preStet.setInt(1,shop_id);
            preStet.setString(2, categoryName);
            ResultSet resultSet = preStet.executeQuery();

            while ((resultSet.next())) {

                String title  = resultSet.getString("title");
                double price = resultSet.getDouble("price");
                String status  = resultSet.getString("status");
                Product myProduct = new Product(title,price,status);
                productsList.add(myProduct);
            }
            resultSet.close();
            preStet.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return productsList;
    }

    public void changePrice(int shop_id,String status, double price) {

        Connection connection = getConnection();
        try {
            PreparedStatement prStat = connection.prepareStatement("UPDATE product SET price = price*?+price WHERE product.status = ? \n"+
                    "AND product.category_id IN (SELECT category.id FROM category WHERE category.shop_id = ?)");
            prStat.setDouble(1, price);
            prStat.setString(2, status);
            prStat.setInt(3, shop_id);
            prStat.executeUpdate();

            prStat.close();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void changeStatus(int shop_id,String categoryName, String status) {

        Connection connection = getConnection();
        try {
            PreparedStatement prStat = connection.prepareStatement("UPDATE product SET status = (?) WHERE category_id IN (SELECT category.id FROM category WHERE shop_id = (?) AND category.name = (?))");
            prStat.setString(1, status);
            prStat.setInt(2, shop_id);
            prStat.setString(3, categoryName);
            prStat.executeUpdate();

            prStat.close();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void run() {

    }
}

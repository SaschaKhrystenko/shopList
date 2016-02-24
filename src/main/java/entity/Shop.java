package entity;

import java.util.List;


public interface Shop {
    public int getShopId(String shopName);
    public List getAllShopData (int shop_id);
    public void setProduct(int shop_id, String categoryName, String title, double price, String status);
    public List<Product> getAllProductsShopByCategory(int shop_id, String categoryName);
    public void changePrice(int shop_id,String status, double price);
    public void changeStatus(int shop_id,String categoryName, String status);
}

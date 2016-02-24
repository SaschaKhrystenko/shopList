package entity;


public class Category {
    private  int id;
    private int shop_id;
    private  String name;

    public Category(int id, int shop_id, String name) {
        this.id = id;
        this.shop_id = shop_id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

      public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

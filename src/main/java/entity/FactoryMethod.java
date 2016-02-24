package entity;


public class FactoryMethod implements ShopFactory {

    Shop concreteShop;
    public Shop createShop(String shopName) {
        if (shopName.equalsIgnoreCase("Hugo Boss")) {
            concreteShop = HugoBoss.getInstance();
        } else if (shopName.equalsIgnoreCase("Armany")) {
            concreteShop = Armany.getInstance();
        }
        return concreteShop;
    }
}

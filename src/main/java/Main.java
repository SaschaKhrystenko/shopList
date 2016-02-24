import entity.*;

import java.util.List;


public class Main {

    public static void main(String[] args) {

        ShopFactory shopCreator = new FactoryMethod();
        Shop shop = shopCreator.createShop("Hugo Boss");


        int shop_id = 0;
        if(shop instanceof HugoBoss){
            shop_id = shop.getShopId("Hugo Boss");

        }
        else if (shop instanceof Armany){
            shop_id = shop.getShopId("Armany");
        }

        // ��������� ���� ������� �� �������� ���������� �� ���������
        List<Product> myHugoProducts = shop.getAllProductsShopByCategory(shop_id,"summer 2016");
        for(Product prop: myHugoProducts){
            System.out.println(prop.getTitle());
            System.out.println(prop.getPrice());
            System.out.println(prop.getStatus());
        }

        // ���������� ������ ��������
       shop.setProduct(shop_id,"summer 2016","socks",50,"expected");

        //� �����-�� �� ��������� �������� ������� ���� ������� �� �Absent�
        shop.changeStatus(shop_id,"summer 2016","absent");

        //�������� �������, �� ���������� ���������, �������� ������ �� �Expected�.
        shop.changeStatus(shop_id,"winter 2016","expected");

        //�� �������, ��� �������� ��������� ���� �� 20%;
        shop.changePrice(shop_id,"avaliable",0.2);


        //����� ���� ������� �� �������� � �����������
        int count =0;
        List myShop= shop.getAllShopData(shop_id);
        for( Object prop: myShop){
            System.out.println(prop);
        }

        }





}
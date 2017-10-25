package Base;

import java.util.Date;

public class Product {
    private String ProductID;
    private String ProductName;
    private int SafeStock;
    private Date LastPurchaseDate=null;
    private Date LastDeliveryDate=null;
    private int Quantity;
    private float SuggestBuyPrice;
    private float SuggestSalePrice;
    public Product(){

    }
    public Product(String productID, String productname) {
        this.ProductID = productID;
        this.ProductName = productname;
    }

    public Product(String productID,String productName, int safeStock,float suggestBuyPrice, float suggestSalePrice) {
        ProductID=productID;
        ProductName = productName;
        SafeStock = safeStock;
        SuggestBuyPrice = suggestBuyPrice;
        SuggestSalePrice = suggestSalePrice;
    }

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String productID) {
        ProductID = productID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public int getSafeStock() {
        return SafeStock;
    }

    public void setSafeStock(int safeStock) {
        SafeStock = safeStock;
    }

    public Date getLastPurchaseDate() {
        return LastPurchaseDate;
    }

    public void setLastPurchaseDate(Date lastPurchaseDate) {
        LastPurchaseDate = lastPurchaseDate;
    }

    public Date getLastDeliveryDate() {
        return LastDeliveryDate;
    }

    public void setLastDeliveryDate(Date lastDeliveryDate) {
        LastDeliveryDate = lastDeliveryDate;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public float getSuggestBuyPrice() {
        return SuggestBuyPrice;
    }

    public void setSuggestBuyPrice(float suggestBuyPrice) {
        SuggestBuyPrice = suggestBuyPrice;
    }

    public float getSuggestSalePrice() {
        return SuggestSalePrice;
    }

    public void setSuggestSalePrice(float suggestSalePrice) {
        SuggestSalePrice = suggestSalePrice;
    }
    @Override
    public boolean equals(Object obj) {
       Product product=(Product)obj;
       if (product.getProductID().equals(this.getProductID())){
           return true;
       }
       else return false;
    }
}

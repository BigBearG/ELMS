package Utils;

import Base.Product;
import DB.DBHelper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductManager {
    private static String sql = null;
    private static DBHelper db = null;
    private static ResultSet ret = null;

    public ProductManager(){
            ArrayList<Product> products=new ArrayList<Product>();
            products=getALLList();
    }
    public Object[][] listToArr(ArrayList<Product> products){
        Object[][] arrProduct=new Object[49][8];
        Object[] o=products.toArray();
        for (int i=0;i<o.length;i++){
            arrProduct[i][0]=((Product)o[i]).getProductID();
            arrProduct[i][1]=((Product)o[i]).getProductName();
            arrProduct[i][2]=((Product)o[i]).getSafeStock();
            arrProduct[i][3]=((Product)o[i]).getLastPurchaseDate();
            arrProduct[i][4]=((Product)o[i]).getLastDeliveryDate();
            arrProduct[i][5]=((Product)o[i]).getQuantity();
            arrProduct[i][6]=((Product)o[i]).getSuggestBuyPrice();
            arrProduct[i][7]=((Product)o[i]).getSuggestSalePrice();
        }
        return arrProduct;
    }
    public ArrayList<Product> getALLList() {
        ArrayList<Product> productss=new ArrayList<Product>();
        sql = "select *from Product";//SQL语句
        db = new DBHelper(sql);//创建DBHelper对象
        try {
            ret = db.pst.executeQuery();//执行语句，得到结果集
            while (ret.next()) {
                Product product=new Product();
                product.setProductID(ret.getString(1));
                product.setProductName(ret.getString(2));
                product.setSafeStock(ret.getInt(3));
                product.setLastPurchaseDate(ret.getDate(4));
                product.setLastDeliveryDate(ret.getDate(5));
                product.setQuantity(ret.getInt(6));
                product.setSuggestBuyPrice(ret.getInt(7));
                product.setSuggestSalePrice(ret.getInt(8));
                productss.add(product);
            }
            return productss;

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                ret.close();
                db.close();//关闭连接
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    public int add(Product product){
        String sql="insert into Product (ProductID,ProductName,SafeStock,LastPurchaseDate," +
                "LastDeliveryDate,Quantity,SuggestionBuyPrice,SuggestionSalePrice) values(?,?,?,?,?,?,?,?)";
        db = new DBHelper(sql);//创建DBHelper对象
        int i = 0;
        try {
            db.pst.setString(1,product.getProductID());
            db.pst.setString(2,product.getProductName());
            db.pst.setInt(3,product.getSafeStock());
            db.pst.setDate(4, (Date)product.getLastPurchaseDate());
            db.pst.setDate(5, (Date) product.getLastDeliveryDate());
            db.pst.setInt(6,product.getQuantity());
            db.pst.setFloat(7,product.getSuggestBuyPrice());
            db.pst.setFloat(8,product.getSuggestSalePrice());
            i=db.pst.executeUpdate();
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            db.close();//关闭连接
        }
        return i;
    }
    public boolean deleteById(String id){
        String sql = "delete from Product where ProductID = ?";
        boolean reslut=false;
        db = new DBHelper(sql);//创建DBHelper对象
        try {
            db.pst.setString(1,id);
            reslut=db.pst.execute();
            return reslut;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            db.close();//关闭连接
        }
        return false;

    }
    public Product findById(String id){
        String sql="select *from Product where ProductID=?";
        db = new DBHelper(sql);//创建DBHelper对象
        try {
            db.pst.setString(1,id);
            ret=db.pst.executeQuery();
            if (ret.next()){
                Product product=new Product();
                product.setProductID(ret.getString(1));
                product.setProductName(ret.getString(2));
                product.setSafeStock(ret.getInt(3));
                product.setLastPurchaseDate(ret.getDate(4));
                product.setLastDeliveryDate(ret.getDate(5));
                product.setQuantity(ret.getInt(6));
                product.setSuggestBuyPrice(ret.getInt(7));
                product.setSuggestSalePrice(ret.getInt(8));
                return product;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                ret.close();
                db.close();//关闭连接
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    public int updateById(String id,Product product){
        String sql= "update Product set ProductName=?,SafeStock=?,LastPurchaseDate=?,LastDeliveryDate=?,Quantity=?,SuggestionBuyPrice=?,SuggestionSalePrice=?"
                + "where ProductID=?";
        int i=0;
        db = new DBHelper(sql);//创建DBHelper对象
        try {
            db.pst.setString(1,product.getProductName());
            db.pst.setInt(2,product.getSafeStock());
            db.pst.setDate(3, (Date)product.getLastPurchaseDate());
            db.pst.setDate(4, (Date) product.getLastDeliveryDate());
            db.pst.setInt(5,product.getQuantity());
            db.pst.setFloat(6,product.getSuggestBuyPrice());
            db.pst.setFloat(7,product.getSuggestSalePrice());
            db.pst.setString(8,id);
            i=db.pst.executeUpdate();
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            db.close();//关闭连接
        }
        return i;
    }
}

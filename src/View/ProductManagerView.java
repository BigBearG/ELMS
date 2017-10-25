package View;

import Base.Product;
import Utils.ProductManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ProductManagerView extends JFrame implements ActionListener, MouseListener {
    JTable table;
    String[] title={"商品编号","商品名称","安全存量","最后进货日期","最后送货日期","当前数量","建议购买价","建议销售价"};
    ProductManager psm=new ProductManager();
    Object[][] ps=psm.listToArr(psm.getALLList());
    JTextField tfnumber,tfname,tfsafenu,tfsbprince,tfssprince;
    JButton btadd,btdelete,btmodify,btfind,btsave,btexit;
    Color mcolor=new Color(255,0,0);
    public ProductManagerView(){
        setBounds(200,200,850,300);
        setLocationRelativeTo(null);
        init();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private void init(){
        JScrollPane p1;
        JPanel p2,p3;
        JLabel lbwamess,lb1,lb2,lb3,lb4,lb5;
        table=new JTable(ps,title);
        table.addMouseListener(this);
        p1=new JScrollPane(table);
        p2=new JPanel();
        lb1=new JLabel("编号");
        lb1.setForeground(mcolor);
        tfnumber=new JTextField(10);
        lb2=new JLabel("名称");
        lb2.setForeground(mcolor);
        tfname=new JTextField(10);
        lb3=new JLabel("安全存量");
        lb3.setForeground(mcolor);
        tfsafenu=new JTextField(10);
        lb4=new JLabel("建议购买价");
        tfsbprince=new JTextField(10);
        lb5=new JLabel("建议销售价");
        tfssprince=new JTextField(10);
        p2.add(lb1);
        p2.add(tfnumber);
        p2.add(lb2);
        p2.add(tfname);
        p2.add(lb3);
        p2.add(tfsafenu);
        p2.add(lb4);
        p2.add(tfsbprince);
        p2.add(lb5);
        p2.add(tfssprince);


        p3=new JPanel();
        lbwamess=new JLabel("红色为必选内容");
        lbwamess.setForeground(mcolor);
        btadd=new JButton("新增");
        btadd.addActionListener(this);
        btdelete=new JButton("删除");
        btdelete.addActionListener(this);
        btmodify=new JButton("修改");
        btmodify.addActionListener(this);
        btfind=new JButton("查找");
        btfind.addActionListener(this);
        btsave=new JButton("保存");
        btsave.addActionListener(this);
        btexit=new JButton("退出");
        btexit.addActionListener(this);
        p3.add(lbwamess);
        p3.add(btadd);
        p3.add(btdelete);
        p3.add(btmodify);
        p3.add(btsave);
        p3.add(btfind);
        p3.add(btexit);
        Box box=Box.createVerticalBox();
        box.add(p1);
        box.add(p2);
        box.add(p3);
        add(box);
        validate();

    }
    private void setTable(){
        DefaultTableModel dtm=new DefaultTableModel();
        dtm.setDataVector(ps,title);
        table.setModel(dtm);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String no=tfnumber.getText();
        String name=tfname.getText();
        if (tfsafenu.getText().equals("")){
            int safeprince=0;
        }
        if (tfssprince.getText().equals("")){

        }
        if (tfsbprince.getText().equals("")){


        }
        try{
            int safeprince= Integer.parseInt(tfsafenu.getText());
            float sbprince= Float.parseFloat(tfsbprince.getText());
            float ssprince= Float.parseFloat(tfssprince.getText());

        if (e.getSource()==btadd){
            if (tfnumber.getText().equals("")||tfname.getText().equals("")||tfsafenu.getText().equals("")){
                JOptionPane.showMessageDialog(this,"红为必选内容","错误操作",JOptionPane.ERROR_MESSAGE);
                return;
            }
            System.out.println("点击新增按钮");
            Product p=new Product(no,name);
            p.setSafeStock(safeprince);
            p.setSuggestBuyPrice(sbprince);
            p.setSuggestSalePrice(ssprince);
            psm.add(p);
            psm.getALLList();
            ps=psm.listToArr(psm.getALLList());
            setTable();
        }else if (e.getSource()==btdelete){
            System.out.println("点击删除按钮");
            psm.deleteById(no);
            ps=psm.listToArr(psm.getALLList());
            setTable();
        }else if (e.getSource()==btmodify){
            System.out.println("点击修改按钮");
            tfnumber.setEditable(false);

        }else if (e.getSource()==btsave){
            psm.updateById(no,new Product(no,name,safeprince,sbprince,ssprince));
            ps=psm.listToArr(psm.getALLList());
            setTable();
            tfnumber.setEditable(true);
        }else if (e.getSource()==btfind){

            System.out.println("点击查找按钮");
            Product index=psm.findById(no);
            if (index!=null){
                JOptionPane.showMessageDialog(this,""+index.toString(),"查找成功",JOptionPane.ERROR_MESSAGE);
            }else {
                JOptionPane.showMessageDialog(this,"这个娃走了","查找失败",JOptionPane.ERROR_MESSAGE);
            }

        }else if (e.getSource()==btdelete){
            ProductManagerView.this.dispose();
        }
        }catch (Exception e1){

        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(table.getValueAt(table.getSelectedRow(),0)!=null)
        {
            String number = (String) table.getValueAt(table.getSelectedRow(),0);
            tfnumber.setText(number);
            String name=(String) table.getValueAt(table.getSelectedRow(),1);
            tfname.setText(name);
            Object safenumber = table.getValueAt(table.getSelectedRow(),2);
            tfsafenu.setText(safenumber+"");
            Object sbprince = table.getValueAt(table.getSelectedRow(),6);
            tfsbprince.setText(sbprince+"");
            Object ssprince = table.getValueAt(table.getSelectedRow(),7);
            tfssprince.setText(ssprince+"");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

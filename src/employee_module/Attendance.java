/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employee_module;

import com.sun.glass.events.KeyEvent;
import java.awt.Color;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import mycode.Dbconnect;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Dell
 */
public class Attendance extends javax.swing.JInternalFrame {

    Connection con=null;
    PreparedStatement pst=null;
    ResultSet rs=null;
    String path="C:\\Users\\Dell\\Documents\\NetBeansProjects\\Employee_Module\\src\\Images\\picture.png";
    /**
     * Creates new form Attendance
     */
    public Attendance() {
        initComponents();
          
        //connect to database
        con=Dbconnect.connect();
        String cdate=new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime());
        String ctime=new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
        //curr_day.setText(cdate);
       // curr_time.setText(ctime);
        
         getContentPane().setBackground(new Color(90, 120, 183));
      //   jPanel1.setOpaque(false);
        jPanel2.setOpaque(false);
        
        
        //load tables
        tableload2();
        tableload(cdate);
    }
    
    public void tableload(String cdate)
    {
        
        try{
            String sql="SELECT a.Empid,e.Name,e.Job,a.Indate,a.Outdate,a.In_time,a.Out_time,a.Status,a.Oth from attendance a,empdetails e where a.empid=e.empid and a.Indate ='"+cdate+"'";
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            
            Atten.setModel(DbUtils.resultSetToTableModel(rs));
            
        }
        catch(Exception e)
        {
            System.out.println("Error in getting data from db");
            System.out.println(e.getMessage());
        }
    }
    public void tableload2()
    {
        try{
                     String sql="SELECT Empid,Name,NIC,Gender from empdetails";
                     pst=con.prepareStatement(sql);
                     rs=pst.executeQuery();
                     EmpDe.setModel(DbUtils.resultSetToTableModel(rs));                                        
                }
                catch(Exception e)
                {
                     System.out.println("Error in searching at att");
                     System.out.println(e.getMessage());
                }
        
        
    }
    
    public int empAstatus(String id)
    {                
               
        try{
          
           String sql = "select 1 from attendance where empid = '"+id+"' and status=1";
           pst=con.prepareStatement(sql);
           rs=pst.executeQuery();
           
           if(rs.next())
           {
               return 1;
           }
           else
               return 0;
           
         }
        catch(Exception e)
        {
            System.out.println("Error in checking emp status "+e);
            return -1;
        }                      
    }
 
    public boolean checkId(String id) {
        
        try{
            String sql = "Select 1 from empdetails where empid = ?";  
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            return rs.next();
            
        }
        catch (Exception e)
        {
            System.out.println("Error in checking empID "+e);
        }
        return false;
        
    }
    public ImageIcon ResiziseImage (String ImagePath){
        ImageIcon myimage=new ImageIcon(ImagePath);
        Image img=myimage.getImage();
        Image newimg=img.getScaledInstance(pic.getWidth(),pic.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image=new ImageIcon(newimg);
        return image;
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        search_txt = new javax.swing.JTextField();
        search_btn = new javax.swing.JButton();
        time_in_btn = new javax.swing.JButton();
        time_out_btn = new javax.swing.JButton();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        pic = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Atten = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        EmpDe = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setBackground(new java.awt.Color(204, 204, 255));
        setClosable(true);
        setPreferredSize(new java.awt.Dimension(1060, 750));

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Enter Employee ID");

        search_txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                search_txtKeyTyped(evt);
            }
        });

        search_btn.setBackground(new java.awt.Color(68, 145, 162));
        search_btn.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        search_btn.setForeground(new java.awt.Color(255, 255, 255));
        search_btn.setText("Search");
        search_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_btnActionPerformed(evt);
            }
        });

        time_in_btn.setBackground(new java.awt.Color(68, 145, 162));
        time_in_btn.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        time_in_btn.setForeground(new java.awt.Color(255, 255, 255));
        time_in_btn.setText("Time IN");
        time_in_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                time_in_btnActionPerformed(evt);
            }
        });

        time_out_btn.setBackground(new java.awt.Color(68, 145, 162));
        time_out_btn.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        time_out_btn.setForeground(new java.awt.Color(255, 255, 255));
        time_out_btn.setText("Time OUT");
        time_out_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                time_out_btnActionPerformed(evt);
            }
        });

        jDesktopPane1.setLayer(pic, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pic, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                .addContainerGap())
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(pic, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(time_in_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(time_out_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(search_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(jLabel3))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(search_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(search_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(search_btn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(time_in_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(time_out_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25))))
        );

        Atten.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        Atten.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        Atten.setName("Employee Details"); // NOI18N
        jScrollPane1.setViewportView(Atten);

        EmpDe.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        EmpDe.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        EmpDe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(EmpDe);

        jButton1.setBackground(new java.awt.Color(68, 145, 162));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Daily Attendance");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(68, 145, 162));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Monthly Attendance");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(140, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void search_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_btnActionPerformed
        if(search_txt.getText().equals("")){
               JOptionPane.showMessageDialog(this,"Please enter Employee ID");
               return;
        }
        time_in_btn.setEnabled(true); 
        time_out_btn.setEnabled(true);
        
        String id=search_txt.getText();
        int checkatt=empAstatus(id);
        boolean empIdValid=checkId(id);
        
        
        if(empIdValid==true){
            try{
                     String sql="SELECT Empid,Name,NIC,Gender from empdetails where empid='"+id+"'";
                     pst=con.prepareStatement(sql);
                     rs=pst.executeQuery();
                     EmpDe.setModel(DbUtils.resultSetToTableModel(rs));                                        
            }
            catch(Exception e)
            {
                     System.out.println("Error in searching at att");
                     System.out.println(e.getMessage());
            }
            
            try{
                String p="Select image from empdetails where empid='"+id+"'";
                pst=con.prepareStatement(p);
                rs=pst.executeQuery();
                if(rs.next())
                {
                    String pat=rs.getString("image");
                    String mpath=pat.replace("@","\\");
                    pic.setIcon(ResiziseImage(mpath));
                    path=mpath; 
                }
                
            }
            catch(Exception e)
            {
                System.out.println("Error in displaying pic in att"+e);
            }
            
            
            
            if(checkatt==1)
            {
                time_in_btn.setEnabled(false);   
            }
            else{
                time_out_btn.setEnabled(false);
                
            }
        }
        else
        {   
            time_in_btn.setEnabled(false);
            time_out_btn.setEnabled(false);
            
            JOptionPane.showMessageDialog(null,"Invalid Employee ID","Alert",JOptionPane.WARNING_MESSAGE);
            search_txt.setText("");
            tableload2();
        }
    }//GEN-LAST:event_search_btnActionPerformed

    private void time_in_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_time_in_btnActionPerformed
        if(search_txt.getText().equals("")){
               JOptionPane.showMessageDialog(this,"Please enter Employee Id");
               
               tableload2();
               return;
        }
        String id=search_txt.getText();                     
        String cdate=new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime());
        String ctime=new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
       
       
        int checkatt=empAstatus(id);
        boolean empIdValid=checkId(id);
        
        try{
           String t="select *from attendance where empid='"+id+"' and Indate='"+cdate+"'";
           pst=con.prepareStatement(t);
           rs=pst.executeQuery();           
           if(rs.next())
           {    
               JOptionPane.showMessageDialog(null,"Attendance Already marked","Alert",JOptionPane.WARNING_MESSAGE);
               search_txt.setText("");
               path="C:\\Users\\Dell\\Documents\\NetBeansProjects\\Employee_Module\\src\\Images\\picture.png";
               pic.setIcon(ResiziseImage(path));
               tableload2();
               return ;
           }
            
        }
        catch(Exception e)
        {
            System.out.println("Error in checking avaliability"+e);
        }
        
        
        
        try{              
        if((checkatt!=1)&&(empIdValid==true)){
           int status=1;
           String q="INSERT INTO attendance (empid,Indate,in_time,status) values ('"+id+"','"+cdate+"','"+ctime+"','"+status+"')";
          
           pst=con.prepareStatement(q);
           pst.execute();
           JOptionPane.showMessageDialog(this,"Successfully Timed IN ");
           path="C:\\Users\\Dell\\Documents\\NetBeansProjects\\Employee_Module\\src\\Images\\picture.png";
           pic.setIcon(ResiziseImage(path));
           
           //load table
            tableload(cdate);
            time_in_btn.setEnabled(false);
            search_txt.setText("");
            
        }
        else
        {
           time_in_btn.setEnabled(false);
           JOptionPane.showMessageDialog(null,"Invalid Employee ID","Alert",JOptionPane.WARNING_MESSAGE);
           search_txt.setText("");
           path="C:\\Users\\Dell\\Documents\\NetBeansProjects\\Employee_Module\\src\\Images\\picture.png";
           pic.setIcon(ResiziseImage(path));
        }
           
        
        }
        catch(Exception e)
        {
            System.out.println("Error in inserting to att time in"+e);
        }
        
        
        tableload2();
        // TODO add your handling code here:
    }//GEN-LAST:event_time_in_btnActionPerformed

    private void time_out_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_time_out_btnActionPerformed
       if(search_txt.getText().equals("")){
               JOptionPane.showMessageDialog(this,"Please enter Employee Id");
               tableload2();
               return;
        }
        
        String id=search_txt.getText();
               
        String cdate=new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime());
        String ctime=new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
        int status=0;
        
        String sql="UPDATE attendance SET Outdate='"+cdate+"',Out_time='"+ ctime+"' where empid='"+id+"' and status=1" ;
        
        try{
        pst=con.prepareStatement(sql);
        pst.execute();
        JOptionPane.showMessageDialog(this,"Successfully TIMED OUT");
        
        //load table
        tableload(cdate);
        time_out_btn.setEnabled(false);
        
        
        }
        catch(Exception e)
        {
            System.out.println("error in time out");
            System.out.println(e.getMessage());
        }
        try{
            String q="SELECT Indate,Outdate,In_time,Out_time from attendance where empid = '"+id+"' and status=1";
            pst=con.prepareStatement(q);
            rs=pst.executeQuery();
            
            if(rs.next())
            {
               String indate=rs.getString("Indate");
               String outdate=rs.getString("Outdate");
               String intime =rs.getString("In_time");
               String outtime =rs.getString("Out_time");
               int  totalHours=0;                                                      
               try {
                    String date11 = indate;
                    String time1 =intime;
                    String date22 = outdate;
                    String time2 =outtime;

                    String formatt = "MM/dd/yyyy HH:mm:ss";

                    SimpleDateFormat sdf = new SimpleDateFormat(formatt);

                    Date dateObj1 = sdf.parse(date11 + " " + time1);
                    Date dateObj2 = sdf.parse(date22 + " " + time2);
                   
                    long difff = dateObj2.getTime() - dateObj1.getTime();
                    double diffInHours = difff / ((double) 1000 * 60 * 60);
                    totalHours=(int)diffInHours;
                    System.out.println(diffInHours);
                                     

                } catch (Exception e) {
                    e.printStackTrace();
                }
                               
                long oth=0;
                if(totalHours>8)
                {
                  oth=totalHours-8;
                }
                               
                
                String q2="UPDATE attendance set Oth='"+oth+"',status='"+ status+"' where empid='"+id+"' and status =1";
                pst=con.prepareStatement(q2);
                pst.execute();
                tableload(cdate);
                                                                                        
            }
        }
        catch(Exception e)
        {
            System.out.println("Error in otr"+e);
        }

        search_txt.setText("");
        path="C:\\Users\\Dell\\Documents\\NetBeansProjects\\Employee_Module\\src\\Images\\picture.png";
        pic.setIcon(ResiziseImage(path));
        tableload2();
        // TODO add your handling code here:
    }//GEN-LAST:event_time_out_btnActionPerformed

    private void search_txtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_search_txtKeyTyped
        char c=evt.getKeyChar();
         
         if(!(Character.isDigit(c))||(c==KeyEvent.VK_BACKSPACE) || (c== KeyEvent.VK_DELETE))
         {
             evt.consume();
         }
    }//GEN-LAST:event_search_txtKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        String cdate=new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime());
        HashMap para =new HashMap();
        para.put("Indate",cdate);
        ReportView r=new ReportView("C:\\Users\\Dell\\Desktop\\ireportTest\\att\\att_day.jasper",para);
        r.setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        if(search_txt.getText().equals("")){
               JOptionPane.showMessageDialog(this,"Please enter Employee ID");
               return;
        }
        
        HashMap para =new HashMap();
        if(search_txt.getText().length()!=0)
        {
            para.put("empid",search_txt.getText());
            ReportView r=new ReportView("C:\\Users\\Dell\\Desktop\\ireportTest\\attall\\att_all.jasper",para);
            r.setVisible(true);
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Atten;
    private javax.swing.JTable EmpDe;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel pic;
    private javax.swing.JButton search_btn;
    private javax.swing.JTextField search_txt;
    private javax.swing.JButton time_in_btn;
    private javax.swing.JButton time_out_btn;
    // End of variables declaration//GEN-END:variables
}

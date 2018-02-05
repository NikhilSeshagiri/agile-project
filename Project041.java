package project04;

import java.sql.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Project041
{   
    public static void main(String[] args) throws IOException, ParseException, SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {        
       String gname=null,sname=null,mname=null,sex=null,oph=null,rph=null,ph=null,email=null,famc=null,fams=null,dateStr=null,dateStr1=null;
       int count=0,id=1;
       Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
       Connection con = DriverManager.getConnection("JDBC:ODBC:gedcom","","");
       String query="INSERT INTO gedcom"+ "(ID,Indi,GName,SName,MName,Sex,BDate,DDate,OPh,RPh,Ph,Email,Famc,Fams)" + "VALUES" +"(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
       PreparedStatement pstmt = con.prepareStatement(query);
       try (BufferedReader in = new BufferedReader(new FileReader("D:/My-Family-21-Feb-2015.ged")))
       {            
            String line;
	    while ((line = in.readLine()) != null )
	    {	
                
                String[] lineInput = lineSplit(line);
		count++;
                
		if(count>13)
                {                       
                    if(lineInput[1].startsWith("@F")){
                        System.out.println("-----------");
                        break;
                    
                    }
                    
                    if(lineInput[0].equals("0"))
                    {                                    	
                        //id=Integer.parseInt(lineInput[1]);
                        System.out.println(id);
                        lineInput=lineSplit(in.readLine()); 
                    }                                   
                    if(lineInput[1].equals("NAME") )
                    {                                         
                        lineInput=lineSplit(in.readLine());                                         
                    }
                    if(lineInput[1].equals("GIVN") )
                    {                                         
                        gname=lineInput[2];
                        System.out.println(gname); 
                        lineInput=lineSplit(in.readLine());
                    }
                    if(lineInput[1].equals("SURN") )
                    {  
                        sname=lineInput[2];
                        System.out.println(sname);
                        lineInput=lineSplit(in.readLine());
                    }
                    if(lineInput[1].equals("_MARNM") )
                    {                                         
                        mname=lineInput[2];
                        System.out.println(mname);
                        lineInput=lineSplit(in.readLine());
                    }
                    if(lineInput[1].equals("SEX") )
                    { 
                        sex=lineInput[2];
                        System.out.println(sex);
                        lineInput=lineSplit(in.readLine());
                    }
                    if(lineInput[1].equals("BIRT") )
                    {
                        lineInput=lineSplit(in.readLine());
                        dateStr=getMonths(lineInput[3])+"/"+lineInput[2]+"/"+lineInput[4];
                        DateFormat df=new SimpleDateFormat("mm/dd/yyyy");
                  	    Date date =df.parse(dateStr);
                        System.out.println(dateStr);
                        System.out.println(date);
                        lineInput=lineSplit(in.readLine());
                    }
                    if(lineInput[1].equals("DEAT") )
                    {                                                                                 
                        lineInput=lineSplit(in.readLine());
                        dateStr1=getMonths(lineInput[3])+"/"+lineInput[2]+"/"+lineInput[4];
                        DateFormat df=new SimpleDateFormat("mm/dd/yyyy");
                  	    Date date =df.parse(dateStr1);
                        System.out.println(dateStr1);
                        System.out.println(date);
                        lineInput=lineSplit(in.readLine());
                    }
                    if(lineInput[1].equals("OCCU") )
                    {                                         
                        lineInput=lineSplit(in.readLine());
                        oph=lineInput[2];
                        System.out.println(oph);
                        lineInput=lineSplit(in.readLine());
                    }
                    if(lineInput[1].equals("RESI") )
                    {
                        lineInput=lineSplit(in.readLine());
                        rph=lineInput[2];
                        System.out.println(rph);
                        lineInput=lineSplit(in.readLine());
                    }
                    if(lineInput[1].equals("PHON") )
                    {                                         
                        ph=lineInput[2];
                        System.out.println(ph);
                        lineInput=lineSplit(in.readLine());
                    }
                    if(lineInput[1].equals("EMAIL") )
                    {                                         
                        email=lineInput[2];
                        System.out.println(email);
                        lineInput=lineSplit(in.readLine());
                    }
                    if(lineInput[1].equals("FAMS") )
                    {   
                        fams=lineInput[2];
                        lineInput=lineSplit(in.readLine());
                        if(lineInput[1].equals("FAMS") || lineInput[1].equals("FAMC") )
                        {
                            if(lineInput[1].equals("FAMS") )
                            {                                      
                                fams=fams + "," +lineInput[2];
                                System.out.println(fams);
                                lineInput=lineSplit(in.readLine());
                            }
                            if(lineInput[1].equals("FAMC") )
                            {                                      
                                famc=lineInput[2];
                                System.out.println(famc);
                            }
                        }  
                                               
                    }
                    if(lineInput[1].equals("FAMC") )
                    {                                         
                        famc=lineInput[2];
                        System.out.println(famc);
                    }
                    
                    pstmt.setInt(1, count-13);
                    pstmt.setString(2,"@I"+(id)+"@");
                    pstmt.setString(3,gname);
                    pstmt.setString(4,sname);
                    pstmt.setString(5,mname);
                    pstmt.setString(6,sex);
                    pstmt.setString(7,dateStr);
                    pstmt.setString(8,dateStr1);
                    pstmt.setString(9,oph);
                    pstmt.setString(10,rph);
                    pstmt.setString(11,ph);
                    pstmt.setString(12,email);
                    pstmt.setString(13,famc);
                    pstmt.setString(14,fams);
                    pstmt.executeUpdate();
                    
                    
                    gname=null;
                    sname=null;
                    mname=null;
                    sex=null;
                    oph=null;
                    rph=null;
                    ph=null;
                    id++;
                    email=null;
                    famc=null;
                    fams=null;
                    dateStr=null;
                    dateStr1=null;
                }
            }
                    pstmt.setInt(1, count-13);
                    pstmt.setString(2,"@I"+(id)+"@");
                    pstmt.setString(3,gname);
                    pstmt.setString(4,sname);
                    pstmt.setString(5,mname);
                    pstmt.setString(6,sex);
                    pstmt.setString(7,dateStr);
                    pstmt.setString(8,dateStr1);
                    pstmt.setString(9,oph);
                    pstmt.setString(10,rph);
                    pstmt.setString(11,ph);
                    pstmt.setString(12,email);
                    pstmt.setString(13,famc);
                    pstmt.setString(14,fams);
                    pstmt.executeUpdate();
       }catch(Exception e)
       {
        
       }
       
   }
   public static String[] lineSplit(String s)
   {
	String[] lineInput = s.split(" ");
	return lineInput;
   }
  public static String getMonths(String mon)
   {
       String i=null;
       switch(mon){
           case "JAN":
               i="1";
               break;
           case "FEB":
               i="2";
               break;
           case "MAR":
               i="3";
               break;
           case "APR":
               i="4";
               break;
           case "MAY":
               i="5";
               break;
           case "JUN":
               i="6";
               break;
           case "JUL":
               i="7";
               break;
           case "AUG":
               i="8";
               break;
           case "SEP":
               i="9";
               break;
           case "OCT":
               i="10";
               break;
           case "NOV":
               i="11";
               break;
           case "DEC":
               i="12";
               break;
           
       }
          return i;
   } 
}
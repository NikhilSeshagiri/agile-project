package project04;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class NewClass 
{
	public static void main(String[] arg) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, NullPointerException, ParseException
	{
		
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
	    Connection con = DriverManager.getConnection("JDBC:ODBC:gedcom","","");
	    Statement st=con.createStatement();
	    String query="select * from gedcom";
	    ResultSet rs1=st.executeQuery(query);
	    DiffSex(rs1,rs1);
	    ResultSet rs3=st.executeQuery(query);
            Email(rs3);
            ResultSet rs5=st.executeQuery(query);
	    DeathBirth(rs5);
	    ResultSet rs6=st.executeQuery(query);
	    BirthCurrent(rs6);
	    ResultSet rs7=st.executeQuery(query);
	    LeapDate(rs7);
	           
	}
        public static void DeathBirth(ResultSet rs) throws SQLException
	{
            System.out.println("---------------Death Date should be greater then Birth Date Varification-----------------------");
                
			while(rs.next())
			{
				Date date1=rs.getDate(7);
				Date date2=rs.getDate(8);
				
				if(date2==null)
				{
					System.out.println("pass");
				}
				else
				{
					if(date1.after(date2))
						System.out.println("fail");
					else
						System.out.println("pass"); 
				}
				
			}
	}
	public static void BirthCurrent(ResultSet rs1) throws SQLException,ParseException
	{
            System.out.println("---------------Birth Date Varification-----------------------");
                
		DateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
		Date date = new Date();
		String getdate=dateFormat.format(date);
		Date date1=dateFormat.parse(getdate);
		while(rs1.next())
		{
			Date date2=rs1.getDate(7);
			if(date2.after(date1))
				System.out.println("ID "+ rs1.getString("ID")+ " Date is not correct");
			else
				System.out.println("ID "+ rs1.getString("ID")+ " Date is correct"); 
		}
		
	}
	public static void LeapDate(ResultSet rs2)throws SQLException
	{
           // System.out.println("---------------Leap Year Varification-----------------------");
                
		while(rs2.next())
		{
			Date date1=rs2.getDate(7);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date1);
			int year=cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH);
			int day=cal.get(Calendar.DATE);
			if(year%4==0)
			{
				if(month==1)
				{
					
					if(day<=29)
						System.out.println("pass");
					else
						System.out.println("fail");
					
				}
			}
                }

}
	
	public static void DiffSex(ResultSet rs1,ResultSet rs2) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, NullPointerException
	{
		System.out.println("---------------Two different sex in marriage Varification-----------------------");
                
		
                   
                       while(rs1.next()){
                           try{
                           while(rs2.next()){
                               System.out.println("ID "+ rs1.getString("ID")+ "Valid sex in marriage");
                                if(rs1.getString("Fams").equals(rs2.getString("Fams"))){
                                    if(!(rs1.getString("Sex").equals(rs2.getString("Sex")))){
                                        System.out.println("id "+rs1.getString("ID")+" valid ");
                                    }else{
                                        System.out.println("id "+rs1.getString("ID")+" invalid ");
                                    }
                                }
                        
                            }
                    }	
                   
                    catch(Exception e)
                    {
                        continue;                         
                    }
		
                System.out.println();
	}
        }
	
	public static void Email(ResultSet rs1) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, NullPointerException
	{
		System.out.println("---------------Email Id Varification-----------------------");
		while(rs1.next())
		{
                    try{
                        if(rs1.getString("Email").contains(".")){
                            System.out.println("id "+rs1.getString("ID")+" Valid Email-id");
			}else{
                            System.out.println("id "+rs1.getString("ID")+" Invalid Email-id");
			}
                    }catch(Exception e){
                        continue;		
                    }
		}	
	}
        
}
	


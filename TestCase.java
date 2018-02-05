package project04;

import java.sql.*;


public class TestCase 
{
	public static void main(String[] arg) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, NullPointerException
	{
		
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
	    Connection con = DriverManager.getConnection("JDBC:ODBC:gedcom","","");
	    Statement st=con.createStatement();
	    String query="select * from gedcom";
	    ResultSet rs1=st.executeQuery(query);
	    PhNo(rs1);
	    
	    ResultSet rs2=st.executeQuery(query);
	    Email(rs2);
	    
	    ResultSet rs3=st.executeQuery(query);
	    WorkPersonalPh(rs3);
	    
	    ResultSet rs4=st.executeQuery(query);
	    ResiPersonalPh(rs4);
	    
	    ResultSet rs5=st.executeQuery(query);
	    ResiWorkPh(rs5);
	    
	    ResultSet rs6=st.executeQuery(query);
	    IsNameValid(rs6);
	    
	    ResultSet rs7=st.executeQuery(query);
	    IsNameNotNull(rs7);
	    
            
	}
	
	public static void PhNo(ResultSet rs) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, NullPointerException
	{
		System.out.println("---------------Phone Number Varification-----------------------");
                
		while(rs.next())
		{
                  //System.out.println(rs.first());
                   try{
                        
                        if((rs.getString("Ph").length())>15){
                            System.out.println("id "+rs.getString("ID")+" invalid Phone Number");
                        }else{
                            System.out.println("id "+rs.getString("ID")+" valid Phone Number");
                        }
                      
                    }		
                    catch(Exception e)
                    {
                        continue;                         
                    }
		}
                System.out.println();
		
	}
	public static void Email(ResultSet rs) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, NullPointerException
	{
		System.out.println("---------------Email Id Varification-----------------------");
		while(rs.next())
		{
                    try{
                        if(rs.getString("Email").contains("@")){
                            System.out.println("id "+rs.getString("ID")+" Valid Email-id");
			}else{
                            System.out.println("id "+rs.getString("ID")+" Invalid Email-id");
			}
                    }catch(Exception e){
                        continue;		
                    }
		}		
	}
	public static void  WorkPersonalPh(ResultSet rs) throws SQLException
	{
		System.out.println("-----------------------Work and Personal Phone number verification--------------------------");
		while(rs.next())
		{
                    try{
			 if(rs.getString("Ph").equals(rs.getString("OPh"))){
                            System.out.println("id "+rs.getString("ID")+" invalid Phone Number");
                         }else{
                            System.out.println("id "+rs.getString("ID")+" valid Phone Number");
                         }
                    }catch(Exception e){
                        continue;		
                    }
		}
	}
	
	public static void  ResiPersonalPh(ResultSet rs) throws SQLException
	{
		System.out.println("-------------------------------Resident and Personal Phone number verification---------------------------------");
		while(rs.next())
		{
                    try{
			 if(rs.getString("RPh").equals(rs.getString("Ph"))){
                            System.out.println("id "+rs.getString("ID")+" invalid Phone Number");
                         }else{
                            System.out.println("id "+rs.getString("ID")+" valid Phone Number");
                         }
                    }catch(Exception e){
                        continue;		
                    }
		}
	}
	
	public static void  ResiWorkPh(ResultSet rs) throws SQLException
	{
		System.out.println("---------------------------------Resident and Work Phone number verification---------------------------------");
		while(rs.next())
		{
                    try{
			 if(rs.getString("RPh").equals(rs.getString("OPh"))){
                            System.out.println("id "+rs.getString("ID")+" invalid Phone Number");
                         }else{
                            System.out.println("id "+rs.getString("ID")+" valid Phone Number");
                         }
                    }catch(Exception e){
                        continue;		
                    }
		}
	}
	
	public static void IsNameValid(ResultSet rs) throws SQLException
	{       
                System.out.println("---------------------------------Given Name should be character     verification---------------------------------");
		while(rs.next())
		{
                    try{
			if(rs.getString("GName").matches("[a-zA-Z]+")){
                            System.out.println("id "+rs.getString("ID")+" Valid Name");
                        }else{
                            System.out.println("id "+rs.getString("ID")+" Invalid Name");
                        }
                    }catch(Exception e){
                        continue;		
                    }
		}
	}
	
	public static void IsNameNotNull(ResultSet rs) throws SQLException
	{
                System.out.println("---------------------------------Given Name should not be null verification---------------------------------");
		while(rs.next())
		{
                    try{
			if(rs.getString("GName").equals(null)){
                            System.out.println("id "+rs.getString("ID")+" Invalid Name");
                        }else{
                            System.out.println("id "+rs.getString("ID")+" Valid Name");
                        }
                    }catch(Exception e){
                        continue;		
                    }
		}
	}
}

/*
Program description: This program takes the username and the password of a Basketball team fan, 
and gives this fan the team's players statistics according to the user's Username, Password and priviledge level.

By 
Mohammad Hijazi
Adedamola Shomoye
Jeff Stolzenberg 
Jose Guerrero 
Simon Ling


//Data Base Structure
USERS_INFO:
+------------------------------------+
| UserId     | Password | Priviledge |
+------------+----------+------------+
|Bulls_Fan   |   123    |      1     |
|Cavs_Fan    |   345    |      2     |
|Warriors_Fan|   678    |      3     |
|admin		  |  246	|	   4	 |	
+------------------------------------+

STATISTICS (default):

----------+---------------+-------------+---------+---------+---------------+----------------+---------+-------+
FirstName |	LastName  |	Team			|  Number |   Age   |	ThrePoints  |	  FreeThrow  |	Points | Priv  |
----------+---------------+-------------+---------+---------+---------------+----------------+---------+-------+
Jimmy	  |  Butler	  |    Bulls    |   21	  |   26    |   0.328	    |       0.832    	|  1399   	| 1    |
Denzel	  |  Valentine	  |    Bulls    |   45	  |   22    |	0.444	    |       0.853    |	595    |	1  |
Lebron	  |  James	  |     Cavs    |   23	  |   31    |	0.309	    |       0.731    |	1920   |		2  |
Kyrie	  |  Irving	  |    Cavs     |   2	  |   24    |	0.321	    |       0.885    |	1041   |		2  |
Stephen	  |  Curry	  |    Warriors |   30	  |   28    |	0.454	    |       0.908    |	2375   | 	3 	   |
Klay	  |  Thompson	  |    Warriors |   11	  |   25    |	0.424	    |       0.854    |	1771   | 	3  |
----------+---------------+-------------+---------+---------+---------------+----------------+---------+-------+

*/


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class FinalProject 
{

	public static void main(String[] args) 
        {       //Establish database connection
		String connectionString = "jdbc:hsqldb:testdb,sa,";
		Connection connection = null;
		try {
			Class.forName("org.hsqldb.jdbcDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection(connectionString);
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		FinalProject f = new FinalProject();
		
		f.dropTables(connection);
                
        f.createStats(connection);
        
        f.createUsers(connection);
        
        
        boolean leave = false;
        //always come back to home until user leaves
        while(!leave){
	        System.out.printf(" %n%n %s %n %s %n %s %n %s %n" ,"This is NBA Team Stats DB", "To view stats Enter 1 ", "To Add player stats to Team enter 2","Exit enter 3");
	        Boolean validInput = false;
	        String selection;
	    	Scanner in = new Scanner(System.in);   
	    	//get user selection and proceed if valid
	        while(!validInput){
			      selection = in.nextLine();
			      switch(selection){
			      case "1":
			    	  validInput = true;
			    	  f.viewStats(connection);
			    	  break;
			      case "2":
			    	  System.out.println("Enter admin name:");
			    	  String uname = in.nextLine();
			    	  System.out.println("Enter admin password");
			    	  String pass = in.nextLine();
			    	  if(f.checkAdmin(connection, uname.toUpperCase(), pass)){
			    		 validInput = true;
			    		f.addPlayer(connection);
			    		break;
			    	  }
			      case "3":
			    	  leave = true;
			    	  validInput = true;
			    	  in.close();
			    	  break;
			    	default:
			    		  System.out.println("invalid Input");
			      }
	        }
        }
        
        //user left, close connection
        f.closeConnection(connection);
        
      //main
      }
	
	/**
	 * start clean
	 * @param connection
	 */
	private void dropTables(Connection connection) {
		String sql; 
		Statement s;
		// DROP STATISTICS TABLE
					sql = "DROP TABLE IF EXISTS STATISTICS;";
					try {
						s = connection.createStatement();
						s.executeQuery(sql);
					} catch (SQLException e) {
						e.printStackTrace();
					}
			        // DROP USERS_INFO TABLE
					sql = "DROP TABLE IF EXISTS USERS_INFO;";
					try {
						s = connection.createStatement();
						s.executeQuery(sql);
					} catch (SQLException e) {
						e.printStackTrace();
			}		

		}
	
	/**
	 * add player stats to stats table
	 * @param connection
	 */
	private void createStats(Connection connection){
		Statement s;
		String sql;
		// CREATE TABLE STATISTICS
				sql = "CREATE TABLE STATISTICS (FirstName VARCHAR(24), LastName VARCHAR(24),Team VARCHAR(24), Number INT, Age INT, ThreePoint FLOAT, FreeThrow FLOAT, Points INT, Priv INT);";
				try {
					s = connection.createStatement();
					ResultSet rs = s.executeQuery(sql);
				} catch (SQLException e) {
					e.printStackTrace();
				}
		                
		                
		                
		                // INSERT Row1 INTO STATS table
				try {
					sql = "INSERT INTO STATISTICS VALUES('Jimmy', 'Butler','Bulls', 21, 26, 0.328, 0.832, 1399, 1);";
					s = connection.createStatement();
					ResultSet rs = s.executeQuery(sql);
				} catch (SQLException e) {
					e.printStackTrace();
				}
		                
		                // INSERT Row2 INTO STATS table
				try {
					sql = "INSERT INTO STATISTICS VALUES('Denzel', 'Valentine','Bulls', 45, 22, 0.444, 0.853, 595, 1);";
					s = connection.createStatement();
					ResultSet rs = s.executeQuery(sql);
				} catch (SQLException e) {
					e.printStackTrace();
				}
		                // INSERT Row3 INTO STATS table
				try {
					sql = "INSERT INTO STATISTICS VALUES('Lebron', 'James','Cavs', 23, 31, 0.309, 0.731, 1920, 2);";
					s = connection.createStatement();
					ResultSet rs = s.executeQuery(sql);
				} catch (SQLException e) {
					e.printStackTrace();
		                        
				}
		                
		                 // INSERT Row4 INTO STATS table
				try {
					sql = "INSERT INTO STATISTICS VALUES('Kyrie', 'Irving','Cavs', 2, 24, 0.321, 0.885, 1041, 2);";
					s = connection.createStatement();
					ResultSet rs = s.executeQuery(sql);
				} catch (SQLException e) {
					e.printStackTrace();
		                        
				}
		                
		                 // INSERT Row5 INTO STATS table
				try {
					sql = "INSERT INTO STATISTICS VALUES('Stephen', 'Curry','Warriors', 30, 28, 0.454, 0.908, 2375, 3);";
					s = connection.createStatement();
					ResultSet rs = s.executeQuery(sql);
				} catch (SQLException e) {
					e.printStackTrace();                    
				}
		                
		                // INSERT Row6 INTO STATS table
				try {
					sql = "INSERT INTO STATISTICS VALUES('Klay', 'Thompson','Warriors', 11, 25, 0.424, 0.854, 1771, 3);";
					s = connection.createStatement();
					ResultSet rs = s.executeQuery(sql);
				} catch (SQLException e) {
					e.printStackTrace();
		                        
				}
	}
	
	/**
	 * add users  to users table
	 * @param connection
	 */
	private void createUsers(Connection connection){
		String sql;
		Statement s;
		sql = "CREATE TABLE USERS_INFO (UserID VARCHAR(24), Password INT, Priv INT);";
		try {
			s = connection.createStatement();
			ResultSet rs = s.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		//admin can add to table
		try {
			sql = "INSERT INTO USERS_INFO VALUES('ADMIN', 246, 4);";
			s = connection.createStatement();
			ResultSet rs = s.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
                
                // INSERT INTO USERS_INFO table
		try {
			sql = "INSERT INTO USERS_INFO VALUES('BULLS_FAN', 123, 1);";
			s = connection.createStatement();
			ResultSet rs = s.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
                
                // INSERT INTO USERS_INFO table (more)
		try {
			sql = "INSERT INTO USERS_INFO VALUES('CAVS_FAN', 456, 2);";
			s = connection.createStatement();
			ResultSet rs = s.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
                
                // INSERT INTO USERS_INFO table (more)
		try {
			sql = "INSERT INTO USERS_INFO VALUES('WARRIORS_FAN', 789, 3);";
			s = connection.createStatement();
			ResultSet rs = s.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}      
	}
	
	/**
	 * view the stats of players in a team, username and pass required
	 * @param connection
	 */
	private void viewStats(Connection connection){
		String sql;
		Statement s;
		//variable to hold username and password and bool to check if to reenter credentials
	      String userId;
	      String pass;
	      Boolean wrongPass = true;
	      while(wrongPass){              
		      Scanner in = new Scanner(System.in);       
		      //get credentials from user
		      System.out.print("Enter your User ID: ");
		      userId = in.nextLine();
		       
		      System.out.print("Enter your Password: ");
		      pass = in.nextLine();
		
		      try {
		    	  //pull user password and privilege in db from entered username
		      	sql = "SELECT Priv, Password FROM USERS_INFO WHERE UserID = " + "'" + userId.toUpperCase()+"'";
		      	s = connection.createStatement();
		      	ResultSet rs = s.executeQuery(sql);
		      	while(rs.next()){
		      		String cpass = rs.getString(2).toString();
		      		//check if right password
		      		if (cpass.equals(pass)){
		      			//update boolean
		      			wrongPass = false;
		      			//pull up user statistics
		      			sql  = "SELECT * FROM STATISTICS WHERE Priv = " + "'" + rs.getString(1) +"'";
		      			s = connection.createStatement();
		      			ResultSet pstats = s.executeQuery(sql);
		      			
		      			//print out stats
		      			System.out.printf("%s %s %s %n","Your Priviledge level is",rs.getString(1)," These are the stats you can view"); 
		      			System.out.printf("%n%s%n","Firstname   LastName    Team    Number    Age         TP      FT         Points");
		      			//display all players
		      			while(pstats.next()){
		      				System.out.printf("%-12s%-12s%-9s%-9s%-9s%-9s%-13s%-15s%n",pstats.getString(1),pstats.getString(2),pstats.getString(3),pstats.getString(4),pstats.getString(5),pstats.getString(6),pstats.getString(7),pstats.getString(8));                    
		      			}	
		      		}
		      		//inform user of wrong credentials
		      		else {
		      			System.out.println("Wrong Username/Password combination /n Try again:");
		      		}
		      	}
		      }
		      catch (SQLException e) {
		      	e.printStackTrace();
		      } 
	      }     
		
	}
	
	/**
	 * close the connection when done
	 * @param connection
	 */
	private void closeConnection(Connection connection){
		//try to database when done
	      try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	/**
	 * asks the user for player info and tries to add to table if valid
	 * @param connection
	 */
	private void addPlayer(Connection connection){
		
		Statement s;
		String sql;
		
    	Scanner in = new Scanner(System.in);       
		System.out.println("Enter the Player's Firstname: ");
		String fname = in.nextLine();
		
		System.out.println("Enter the Player's Lastname: ");
		String lname = in.nextLine();
		
		System.out.println("Enter the Player's Team: (Bulls, Cavs or Warriors)");
		String team = in.nextLine();
		
		System.out.println("The following must be valid numbers!");
		
		System.out.println("Enter the Player's Number: ");
		int pnum = in.nextInt();
		
		System.out.println("Enter the Player's Age : ");
		int pAge = in.nextInt();
		
		in.nextLine();
		System.out.println("Enter the Player's TP: ");
		String pTP = in.nextLine();

		System.out.println("Enter the Player's FT: ");
		String pFT = in.nextLine();
		
		System.out.println("Enter the Player's Points: ");
		int pPoints = in.nextInt();
		
		ResultSet rs;
		
		//in.close();
		
		//for unknown priv/team
		int priv = 0;
			//otherwise find team priv
			sql = "SELECT Priv FROM STATISTICS WHERE Team = " + "'" + team +"'";
	      	try {
				s = connection.createStatement();
		      	rs = s.executeQuery(sql);
		      	while(rs.next()){
		      		priv = rs.getInt(1);
		      	}
		    
			} catch (SQLException e1) {
				e1.printStackTrace();
			}	
		try {
			//concatenate and add user info to stats
			sql = "INSERT INTO STATISTICS VALUES('" + fname + "', '" + lname + "','" + team + "', " + pnum + ", " + pAge + ", " + pTP + ", " + pFT + ", " + pPoints +"," + priv + ");";
			s = connection.createStatement();
			rs = s.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 *check if admin creds are correct
	 * @param connection
	 * @param uname
	 * @param pass
	 * @return boolean true if creds are correct
	 */
	private Boolean checkAdmin(Connection connection, String uname, String pass){
			Statement s;
			String cpass;
			String sql = "SELECT Password FROM USERS_INFO WHERE UserID = " + "'" + uname.toUpperCase()+"'";
			try {
				s=connection.createStatement();
				ResultSet rs = s.executeQuery(sql);
				while(rs.next()){
					cpass = rs.getString(1).toString();
				
	      		//check if right password
	      		if (cpass.equals(pass)){
					return true;
	      			}
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		return false;
	}
		
}
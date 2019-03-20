package Assignment;

//Import Statements from the Java API
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

//Main method
public class RunnerQueries
{
	//Setting connection, user & password to the database
	public String url = "jdbc:mysql://localhost:3306/competition?autoReconnect=true&useSSL=false"; //Connects to the database automatically
	public String user = "root";
	public String password = "Passpass";
	
	//Prepared Statements
	private PreparedStatement AddRunnerSt;
	private PreparedStatement RemoveRunnerSt;
	private PreparedStatement UpdateRunnerSt;
	private PreparedStatement GetAllRunnersSt;
	public ResultSet rp;
	private Connection c;
	
	//Method to connect to the database
	public Connection getConnection() 
	{
		//Try to connect to database if suscessfull connection is connected
		try
		{
			c = DriverManager.getConnection(url, user, password);
		}
		//Catches exceptions - connection was not connected message will display
		catch(SQLException ex)
		{
			JOptionPane.showMessageDialog(null, "Connection is not established!!");
		}
		return c;
	}//End method for connection
	
	//Adding Method
	public void AddRunner(String name, int age, String category) throws SQLException 
	{	
		//try connection to the database, prepared statement setting values for adding runner and executing prepared statement.
		try
		{
			getConnection();
			AddRunnerSt = c.prepareStatement("INSERT INTO runner(runnerName,runnerAge,runningCategory)values (?,?,?)");
			AddRunnerSt.setString(1, name);
	        AddRunnerSt.setInt(2, age);
	        AddRunnerSt.setString(3, category);
	        AddRunnerSt.executeUpdate();
	        
	        //Displaying message to say runner was added
	        JOptionPane.showMessageDialog(null, "Runner was registered!");
		}
		//catches exceptions
		catch (SQLException e) 
		{
			e.printStackTrace();
		}	
	}//End Adding Method
	
	//Deleting Method
	public void RemoveRunner(String runnerName) throws SQLException
	{	
		//try connection to the database, prepared statement setting values for removing runner and executing prepared statement.
		try
		{
			getConnection();
			RemoveRunnerSt = c.prepareStatement("DELETE FROM runner WHERE runnerName = ?");	
			RemoveRunnerSt.setString(1, runnerName);
			RemoveRunnerSt.executeUpdate();
			
			//Displaying message to say runner was removed
			JOptionPane.showMessageDialog(null, "Runner was removed!");
		} 
		//catches exceptions
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}//End Removing Method
	
	//Updating Method
	public void UpdateRunner(String runnerName, String runningCategory)
	{
		//try connection to the database, prepared statement setting values for updating runner and executing prepared statement.
		try 
		{
			getConnection();
			UpdateRunnerSt = c.prepareStatement("UPDATE runner SET runningCategory = ? WHERE runnerName = ?");
			UpdateRunnerSt.setString(2, runnerName);
			UpdateRunnerSt.setString(1, runningCategory);
			UpdateRunnerSt.executeUpdate();
			
			//Display message to say runner was updated
			JOptionPane.showMessageDialog(null, "Runner was updated!");
		} 
		//catches exceptions
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}//End of Updating Method
	
	//Select All Method
	public ResultSet GetAllRunners() throws SQLException 
	{
		//try connection to the database, prepared statement setting values & executing prepared statement.
		try 
		{
			getConnection();
			GetAllRunnersSt = c.prepareStatement("select * from runner");
			rp = GetAllRunnersSt.executeQuery();
		}
		//catches exceptions
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		//returning the resultset
		return rp;
	}//End Select All Method
	
	//End Connection Method
	public void closeConnection() throws SQLException
	{
		c.close();
	}

	//New RunnerQueries Method
	public static void main(String[] args) 
	{
		new RunnerQueries();
	}
}//End Main Method
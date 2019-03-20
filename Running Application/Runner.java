package Assignment;

//Main method
public class Runner
{
	
	//Setting Variables
	private String runnerName;
	private int runnerAge;
	private String runningCategory;
	
	//The Constructor with parameters
	public Runner(String name, String category, int age)
	{
		runnerName = name;
		runnerAge = age;
		runningCategory = category;
	}
	
	//Setters & Getters
	public String getrunnerName()
	{
		return runnerName;
	}
	
	public void setrunnerName(String runnerName)
	{
		this.runnerName = runnerName;
	}
	
	public int setrunnerAge()
	{
		return runnerAge;
	}
		
	public void setrunnerAge(int runnerAge)
	{
		this.runnerAge = runnerAge;
	}
	
	public String getrunningCategory()
	{
		return runningCategory;
	}
	
	public void setrunnerCategory(String runningCategory)
	{
		this.runningCategory = runningCategory;
	}

	//Setting @Override method
	@Override
	public String toString()
	{
		return "Name: " + runnerName +   " , " + runnerAge + " , " + runningCategory + " , ";
	}
		
}//End main method
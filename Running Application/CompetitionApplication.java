package Assignment;

//Import Statements from the Java API
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

//Main method
public class CompetitionApplication extends JFrame 
{

	private static final long serialVersionUID = 1L;

	// Setting Variables
	JTabbedPane pane = new JTabbedPane();
	JFrame frame = new JFrame();
	private JPanel p, t, p1, t1, p2, t2, p3, t3;
	public JTextArea area;
	private JTextField f1, f2, f3, f4, f5, f6;
	private JButton b1, b2, b3, b4;

	//Setting r1 as new RunnerQueries
	RunnerQueries r1 = new RunnerQueries();

	//The Constructor method
	public CompetitionApplication()
	{
		//Setting Frame
		super("Running App");
		this.setLayout(new BorderLayout());
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(700, 600);
		
		
		//Layouts
		this.add(p = new JPanel(), BorderLayout.CENTER);
		p.setLayout(new FlowLayout());
		this.add(t = new JPanel(new FlowLayout (FlowLayout.CENTER)));
		this.add(p1 = new JPanel(), BorderLayout.CENTER);
		p1.setLayout(new FlowLayout());
		this.add(t1 = new JPanel(new FlowLayout (FlowLayout.CENTER)));
		this.add(p2 = new JPanel(), BorderLayout.CENTER);
		p2.setLayout(new FlowLayout());
		this.add(t2 = new JPanel(new FlowLayout (FlowLayout.CENTER)));
		this.add(p3 = new JPanel(), BorderLayout.CENTER);
		p3.setLayout(new FlowLayout());
		this.add(t3 = new JPanel(new FlowLayout (FlowLayout.CENTER)));
		
		//Grid Layouts
		JPanel gp = new JPanel(new GridLayout(2,1));
		JPanel gp1 = new JPanel(new GridLayout(2,1));
		JPanel gp2 = new JPanel(new GridLayout(2,1));
		JPanel gp3 = new JPanel(new GridLayout(2,1));
		
	
		//Adding Runner GUI Components
		JPanel p = new JPanel();
		t.add(new JLabel("Name: "));
		t.add(f1 = new JTextField(15));
		t.add(new JLabel("Age: "));
		t.add(f2 = new JTextField(5));
		t.add(new JLabel("Category: "));
		t.add(f3 = new JTextField(10));
		t.add(b1 = new JButton("Add Runner"));
		setVisible(true);
		
		//Adding Grid Layouts
		gp.add(p);
		gp.add(t);
		add(gp, BorderLayout.SOUTH);
		
		//Removing Runner GUI Components
		JPanel p1 = new JPanel();
		t1.add(new JLabel("Name: "));
		t1.add(f4 = new JTextField(15));
		t1.add(b2 = new JButton("Remove Runner"));
		setVisible(true);

		//Adding Grid Layouts
		gp1.add(p1);
		gp1.add(t1);
		add(gp1, BorderLayout.SOUTH);
		
		//Update Runner GUI Components
		JPanel p2 = new JPanel();
		t2.add(new JLabel("Name: "));
		t2.add(f5 = new JTextField(15));
		t2.add(new JLabel("Category: "));
		t2.add(f6 = new JTextField(15));
		t2.add(b3 = new JButton("Update Runner"));
		setVisible(true);

		//Adding Grid Layouts
		gp2.add(p2);
		gp2.add(t2);
		add(gp2, BorderLayout.SOUTH);
		
		//Select All Runner's GUI Components
		JPanel p3 = new JPanel();
		p3.add(area = new JTextArea(30, 50));
		area.setEditable(false);
		t3.add(b4 = new JButton("Select All Runner's"));
		setVisible(true);
		
		//Adding Grid Layouts
		gp3.add(p3);
		gp3.add(t3);
		add(gp3, BorderLayout.SOUTH);
		
		//Adding panels to tabs
		pane.addTab("Add Runner", gp);
		pane.addTab("Remove Runner", gp1);
		pane.addTab("Update Runner", gp2);
		pane.addTab("Select All Runner's", gp3);
		this.add(pane);
		setVisible(true);
		
		//Add Runner Button Event
		b1.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				//Setting name, age & category to the database
				String name = f1.getText();
				int age = Integer.parseInt(f2.getText());
				String category = f3.getText();
				//If the name, age or category are blank an error will display.
				if(name.equals("") || age == 0 || category.equals(""))
				{
					
					JOptionPane.showMessageDialog(null, "You cant leave empty fields...", "error", JOptionPane.ERROR_MESSAGE);				
				}
				else
				{
					//Try add the runner to the database
					try 
					{
						r1.AddRunner(name, age, category);
					} 
					//Catches exceptions
					catch (SQLException e) 
					{
						e.printStackTrace();
					}
					//Clearing the textfields
					f1.setText("");
					f2.setText("");
					f3.setText("");
				}
			}
		}); //End method of Adding Runner
		
		//Remove Runner Button Event
		b2.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				//Setting the name
				String name = f4.getText();
				//If name is blank an error will display
				if(name.equals(""))
				{
					
					JOptionPane.showMessageDialog(null, "You cant leave empty fields...", "error", JOptionPane.ERROR_MESSAGE);				}
				
				else
				{
					//Try removing the runner to the database
					try 
					{
						r1.RemoveRunner(name);
					} 
					//Catches exceptions
					catch (SQLException e) 
					{
						e.printStackTrace();
					}
					//Clearing the textfield
					f4.setText("");
				}
			}
		}); //End method of Removing Runner
		
		//Update Runner Button Event
		b3.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				//Setting the name & category
				String name = f5.getText();
				String category = f6.getText();
				//If the name or category are blank an error will display.
				if(name.equals("") || category.equals(""))
				{
					
					JOptionPane.showMessageDialog(null, "You cant leave empty fields...", "error", JOptionPane.ERROR_MESSAGE);				}
				
				//Update the category by the name of the runner & clear textfileds
				else
				{
					r1.UpdateRunner(name, category);
					f5.setText("");
					f6.setText("");
				}
			}
		});//End method for Updating Runner
		
		//Select All Button Event
		b4.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{	
				//Setting variables
				String displayText ="";
				ResultSet rp;
				//Try passing resultset as new RunnerQueries
				try 
				{
					rp = r1.GetAllRunners();
					//While getting details from the database and sending to the variable displayText
					while(rp.next())
					{
						String name = rp.getString("runnerName");
						int age = rp.getInt("runnerAge");
						String category = rp.getString("runningCategory");
						displayText += name+ ", " +age+ ", " +category+ ".\n";
					}
				} 
				//Catches exceptions
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
				//Setting displayText to the Textarea to display the results
				area.setText(displayText);
			}
		});//End method for Select All Runners
		
		
	}//End constructor method

	//Method for new CompetitionApplication
	public static void main(String[] args) 
	{
		new CompetitionApplication();
	}

}//End Main Method
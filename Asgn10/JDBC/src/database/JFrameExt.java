package database;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

public class JFrameExt extends JFrame
{
	private static final long serialVersionUID = 1L;
	JPanel buttons = new JPanel();
	JPanel info = new JPanel();
	JPanel e = new JPanel();
	
	JButton add = new JButton("Add");
	JButton remove = new JButton("Remove");
	JButton update = new JButton("Update");
	JButton find = new JButton("Find");
	JButton findAll = new JButton("Find All");
	JButton clear = new JButton("Clear");
	
	public static JTextArea jta = new JTextArea (11, 40);
	
	JScrollPane jscrMessage = new JScrollPane (jta,
			JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	
	JLabel eID = new JLabel("Employee ID: ");
	JLabel fN = new JLabel("First Name: ");
	JLabel lN = new JLabel("Last Name: ");
	JLabel sl = new JLabel("Salary: ");
	JLabel dID = new JLabel("Department ID: ");
	
	JTextField employeeID = new JTextField(15);
	JTextField firstName = new JTextField(15);
	JTextField lastName = new JTextField(15);
	JTextField salary = new JTextField(15);
	JTextField deptID = new JTextField(15);
	
	private Connection connection = null;
	private Statement psmtFindAll=null;
	private PreparedStatement psmtFind=null;
	private PreparedStatement psmtAdd=null;
	private PreparedStatement psmtRemove=null;
	private PreparedStatement psmtUpdate=null;
	
	public JFrameExt()
	{
		super("Employee Application");
		
		buttons.add(add);
		buttons.add(remove);
		buttons.add(update);
		buttons.add(find);
		buttons.add(findAll);
		buttons.add(clear);
		
		add(buttons, BorderLayout.NORTH);
		
		info.add(jscrMessage);
		add(info);
		
		e.setLayout(new GridLayout(5,1));
		
		e.add(eID);
		e.add(employeeID);
		e.add(fN);
		e.add(firstName);
		e.add(lN);
		e.add(lastName);
		e.add(sl);
		e.add(salary);
		e.add(dID);
		e.add(deptID);
		
		add(e, BorderLayout.SOUTH);
		
		setSize(500, 380);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
	    try
	    {
	      jbInit();
	    }
	    catch(Exception e)
	    {
	      e.printStackTrace();
	    }
	
		
		 
		 clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				jta.setText("");
				employeeID.setText("");
				firstName.setText("");
				lastName.setText("");
				salary.setText("");
				deptID.setText("");
			}
        });
	
		find.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					psmtFind.setString(1, employeeID.getText());
				} catch (SQLException e) {
					e.printStackTrace();
				}
				ResultSet rs = null;
				try {
					rs = psmtFind.executeQuery();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				String f, l;
				int sal, dep;
				try {
					if(rs.next())
					{
						f = rs.getString(2);
						l = rs.getString(3);
						sal = rs.getInt(4);
						dep = rs.getInt(5);
						
						jta.append("Employee Found\n");
						jta.append("Name: " + f + " " + l + "\n");
						jta.append("Salary: " + sal + "\n");
						jta.append("Department ID: " + dep + "\n");
					}
					else
					{
						jta.append("ERROR!\nEmployee Record not found");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		findAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				//execute psmtFindAll PreparedStatement object.
			    ResultSet rs = null;
				try {
					rs = psmtFindAll.executeQuery("SELECT * FROM EmpTbl");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				try {
					jta.append("ID       First     Last                Salary   Department\n");
					jta.append("-----------------------------------------------------------------------\n");
					while(rs.next())
					{
						String i = rs.getString("EmployeeID");
						String f = rs.getString("firstName");
						String l = rs.getString("lastName");
						int s = rs.getInt("Salary");
						int d = rs.getInt("DepartmentID");
						jta.append(i + "        " + f + "     " + l + "     " + s + " " + d + "\n");
					}
				} catch (SQLException e) {
					e.printStackTrace();
					jta.append("Employee record empty\n");
				}
				jta.append("\n");
			}
		});
		remove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					psmtRemove.setString (1, employeeID.getText());
				} catch (SQLException e) {
					e.printStackTrace();
				}
				jta.append("Attempting to remove employee with ID " + employeeID.getText() + "\n");
				try {
					psmtRemove.execute();
					jta.append("Employee removed successfully\n\n");
				} catch (SQLException e1) {
					e1.printStackTrace();
					jta.append("Error, employee not found\n\n");
				}
			}
		});
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					psmtAdd.setString(1, employeeID.getText());
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					psmtAdd.setString(2, firstName.getText());
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					psmtAdd.setString(3, lastName.getText());
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					psmtAdd.setInt(4, Integer.parseInt(salary.getText()));
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					psmtAdd.setInt(5, Integer.parseInt(deptID.getText()));
				} catch (SQLException e) {
					e.printStackTrace();
				}
				jta.append("Attempting to add employee " + firstName.getText() + " " + lastName.getText() + "\n");
				try {
					psmtAdd.executeUpdate();
					jta.append("Employee added successfully\n\n");
				} catch (SQLException e) {
					e.printStackTrace();
					jta.append("Error in adding employee\n\n");
				}
			}
		});
		update.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				try{
					psmtUpdate.setString(5, employeeID.getText());
					psmtUpdate.setString(1, firstName.getText());
					psmtUpdate.setString(2, lastName.getText());
					psmtUpdate.setInt(3, Integer.parseInt(salary.getText()));
					psmtUpdate.setInt(4, Integer.parseInt(deptID.getText()));
				} catch(SQLException e){
					e.printStackTrace();
				}

				try {
					psmtUpdate.executeUpdate();
					jta.append("Employee Found\n");
					jta.append("Updating information, update complete\n\n");
				} catch (SQLException e) {
					e.printStackTrace();
					jta.append("ERROR!\nEmployee not found\n\n");
				}
			}
		});
		 
	
	}
	
	  private void jbInit() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException
	  {
		  //Load the database driver
		  String driverUrl = "com.mysql.jdbc.Driver";
		  Class.forName (driverUrl);
		 
		  //Create Connection
		  String database = "jdbc:mysql://localhost/EmpDB";
		  connection = DriverManager.getConnection(database);
		 
		  String dbUrl = "SELECT * FROM EmpTbl";
		  psmtFindAll = connection.createStatement();
		 
		  //PreparedStatement object for getting a single record from the table.
		  dbUrl = "SELECT * FROM EmpTbl WHERE EmployeeID = ?";
		  psmtFind = connection.prepareStatement(dbUrl);
		 
		  //PreparedStatement object for inserting a record in a table.
		  dbUrl = "INSERT INTO EmpTbl (EmployeeID, FirstName, LastName, Salary, DepartmentID) " +
		                                              "values (?, ?, ?, ?, ?)";
		  psmtAdd = connection.prepareStatement(dbUrl);
		 
		  //Prepared Statement for deleting a record
		  dbUrl = "DELETE FROM EmpTbl WHERE EmployeeID = ?";
		  psmtRemove = connection.prepareStatement(dbUrl);
		 
		  //Prepared statement for updating a record.
		  dbUrl = "UPDATE EmpTbl SET FirstName = ?,LastName = ?,Salary = ?,DepartmentID = ? WHERE EmployeeID = ?";
		  psmtUpdate = connection.prepareStatement(dbUrl);
	  }
}
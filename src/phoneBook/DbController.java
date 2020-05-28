package phoneBook;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;



public class DbController implements Initializable { 
	DB_Access db = new DB_Access();
	
	   @FXML
	   private Button myButton;
	   @FXML
	   private Button mySearch;
	   @FXML
	   private Button addContet;
	   @FXML
	   private Button deletButton;
	   @FXML
	   private Button modButton;
	  
	   @FXML
	   private TextArea myText;
	   
	   @FXML
	   private TextField modifyText;
	   @FXML
	   private TextField srchText;
	   @FXML
	   private TextField nameText;
	   @FXML
	   private TextField numText;
	   @FXML
	   private TextField deletText; 
	   @Override
	   public void initialize(URL location, ResourceBundle resources) {
	 
	       // TODO (don't really need to do anything here).
	      
	   }
	   
	   public void showContect(ActionEvent event) {
	      String s = new String();
	       ArrayList<String> r = db.getAllData();
	             	 
	        	 for (int i = 0 ; i<r.size(); i++) { 
	        		s += (r.get(i)); 
	        		myText.setText(s+"\n");
	        	 }
	   }
	   
	   public void  searchData(ActionEvent event) {
		   
		   String out = new String();
		   ArrayList<String> s = db.searchContect(srchText.getText());
		   
		   if(!s.isEmpty()== true) {
			   for (int i = 0; i<s.size(); i++) {
				   
				   out += (s.get(i));
				   myText.setText(out+"\n");
				   
			   }
		   }
		   
		   else {
			   
			   myText.setText("no result found plz search by name or full number");
		   
		   }
		   
	   }
	   public void addContect(ActionEvent event) {
		   Contect c = null;
		   String name = nameText.getText();
		   String number = numText.getText();
		   if (number.matches("[0-9]+") && number.length() == 10) { 
		   c = new Contect (-1, name, number);
		   db.insertContect(c);
		   //myText.setText(nameText.getText() + numText.getText() +"Successfully Added");
		   String s = new String();
	       ArrayList<String> r = db.getAllData();
	             	 
	        	 for (int i = 0 ; i<r.size(); i++) { 
	        		s += (r.get(i)); 
	        		myText.setText(s+"\n");
	        	 }
		   }
		   else {
			   myText.setText("please Enter proper value");
		   }
	   }
	   
	   public void deletContect(ActionEvent event) {
		   int id = Integer.parseInt(deletText.getText()); 
		   db.deletContect(id);
		   myText.setText("Successfully Added");
		   
		   String s = new String();
	       
	       ArrayList<String> r = db.getAllData();
	             	 
	        	 for (int i = 0 ; i<r.size(); i++) {
	        		 
	        		 
	        		s += (r.get(i)); 
	        		myText.setText(s+"\n");
	        		 
	        	 }

		   
	   }
	   public void modifyContect(ActionEvent event) {
		   Contect c = null;
		   String name = nameText.getText();
		   String number = numText.getText();
		   int id =Integer.parseInt(deletText.getText());
			 c = new Contect (id, name, number);
			db.modifyContect(id, c);
			String s = new String();
		       
		       ArrayList<String> r = db.getAllData();
		             	 
		        	 for (int i = 0 ; i<r.size(); i++) {
		        		 
		        		 
		        		s += (r.get(i)); 
		        		myText.setText(s+"\n");
		        		 
		        	 }
	   }

}

package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.animation.RotateTransition;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class controller implements Initializable {
	 	@FXML
	 	private ComboBox<String> Alg;

	 	@FXML
	 	private ComboBox<String> S;
	 
	 	@FXML
	 	private ComboBox<String> d;

	 	@FXML
	 	private Circle c1;

	 	@FXML
	 	private Circle c2;

	 	@FXML
	 	private Circle c3;
	
	 	@FXML
	 	private Button play;
	 	
	 	@FXML 
	 	private TextArea output;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
    	Algorithms.getCity();
    	Algorithms.readNodes();
    	setData();
    }
    
    
    public void play(ActionEvent event) throws IOException {
    	setR();
    	output.clear();
    	
    	String source = null;
		String destination = null;
		String Algorithm = null;
		
		try {
		source = S.getValue().toString();}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "empty node in start");
	    }
		
		try {
			destination = d.getValue().toString();}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "empty node in destination");
			}
		
		try {
			Algorithm = Alg.getValue().toString();}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "empty node in algorithm");
			}
		
		//System.out.print(source+" "+destination+" "+Algorithm);
		
		ArrayList<String> out1 = Algorithms.DFS(source, destination);
		ArrayList<String> out2 = Algorithms.BFS(source, destination);
		ArrayList<Object> out3 = Algorithms.aStarAlg(source, destination);
		ArrayList<Object> out4 = Algorithms.uniCOST(source, destination);
		ArrayList<Object> out5 = Algorithms.aStarAlgH(source, destination);
		
		if (Algorithm == "DFS") {
			output.appendText("from "+source+" to "+destination+" using "+Algorithm+" we got the results:\n");
			output.appendText("\n");
			output.appendText("\n");
			output.appendText("path :"+out1.get(0)+"\n");
			output.appendText("\n");
			output.appendText("\n");
			//System.out.println("xoxoxoxoxooxoxoxoxoxoxoxoxooxoxoxooxoxoxoxooxoxoxooxoxoxoxoxooxoxoxoxooxoxoxooxoxox");
			output.appendText("expanded nodes:"+out1.get(1)+"\n");
		}
		else if (Algorithm == "BFS") {
			output.appendText("from "+source+" to "+destination+" using "+Algorithm+" we got the results:\n");			
			output.appendText("\n");
			output.appendText("\n");
			output.appendText("path :"+out2.get(0));
			output.appendText("\n");
			output.appendText("\n");
			//System.out.println("xoxoxoxoxooxoxoxoxoxoxoxoxooxoxoxooxoxoxoxooxoxoxooxoxoxoxoxooxoxoxoxooxoxoxooxoxox");
			output.appendText("expanded nodes:"+out2.get(1));
		}
		else if (Algorithm == "A*") {
			output.appendText("from "+source+" to "+destination+" using "+Algorithm+" we got the results:\n");			output.appendText("\n");
			output.appendText("\n");
			output.appendText("path :"+out3.get(1));
			output.appendText("\n");
			output.appendText("\n");
			//System.out.println("xoxoxoxoxooxoxoxoxoxoxoxoxooxoxoxooxoxoxoxooxoxoxooxoxoxoxoxooxoxoxoxooxoxoxooxoxox");
			output.appendText("expanded nodes:"+out3.get(2));
			output.appendText("\n");
			output.appendText("\n");
			output.appendText("cost:"+out3.get(0));
		}
		
		else if (Algorithm == "uniform cost") {
			output.appendText("from "+source+" to "+destination+" using "+Algorithm+" we got the results:\n");			
			output.appendText("\n");
			output.appendText("\n");
			output.appendText("path :"+out4.get(1));
			output.appendText("\n");
			output.appendText("\n");
			//System.out.println("xoxoxoxoxooxoxoxoxoxoxoxoxooxoxoxooxoxoxoxooxoxoxooxoxoxoxoxooxoxoxoxooxoxoxooxoxox");
			output.appendText("expanded nodes:"+out4.get(2));
			output.appendText("\n");
			output.appendText("\n");
			output.appendText("cost:"+out4.get(0));
		}
		
		else if (Algorithm == "A* with new H") {
			output.appendText("from "+source+" to "+destination+" using "+Algorithm+" we got the results:\n");			
			output.appendText("\n");
			output.appendText("\n");
			output.appendText("path :"+out5.get(1));
			output.appendText("\n");
			output.appendText("\n");
			//System.out.println("xoxoxoxoxooxoxoxoxoxoxoxoxooxoxoxooxoxoxoxooxoxoxooxoxoxoxoxooxoxoxoxooxoxoxooxoxox");
			output.appendText("expanded nodes:"+out5.get(2));
			output.appendText("\n");
			output.appendText("\n");
			output.appendText("cost:"+out5.get(0));
		}
		
		
		
		output.setEditable(false);
		//output.getScrollLeft();
		//output.getScrollTop();
    }
    
    public void Circle(ActionEvent event) throws IOException {
    	setR();
    }
    
	private void setData() {
		output.setText("");
		Alg.setItems(FXCollections.observableArrayList("BFS","DFS","uniform cost","A*","A* with new H"));
		S.setItems(FXCollections.observableArrayList("Qalqilia", "Nablus", "Tulkarm", "Hebron", "Gaza", "Akko", "Jenin", "Haifa", "Yafo", "Nahariyya", "Karmiel", "Tiberias", "Tubas", "Salfit", "Ramallah", "Jerico", "Jerusalem", "Bethlehem", "Bir-sabe"));
		d.setItems(FXCollections.observableArrayList("Qalqilia", "Nablus", "Tulkarm", "Hebron", "Gaza", "Akko", "Jenin", "Haifa", "Yafo", "Nahariyya", "Karmiel", "Tiberias", "Tubas", "Salfit", "Ramallah", "Jerico", "Jerusalem", "Bethlehem", "Bir-sabe"));
	}
	
    private void setR() {
    	setRotate(c1,true,360,1);
        setRotate(c2,true,360,1);
        setRotate(c3,true,360,1);
    	
    }
    
    private void setRotate(Circle c,boolean reverse,int angle,int duration) {
        RotateTransition rt = new RotateTransition(Duration.seconds(duration),c);
        rt.setAutoReverse(reverse);
        rt.setByAngle(angle);
        rt.setDelay(Duration.seconds(0));
        rt.setRate(3);
        rt.setCycleCount(1);
        rt.play();

    }

}
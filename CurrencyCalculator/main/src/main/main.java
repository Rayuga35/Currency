package main;

import java.awt.event.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Formatter;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField; 

public class main {
	
	static boolean string_check(String string_check)
	{
		for(int i=0;i<string_check.length();i++)
		{
			if(Character.isDigit(string_check.charAt(i)) == false)
			{
				return false;
			}
			
		}
		return true;
		
	}
	
	
	public static String send(String cpy)
	{
		
		String array[] =  cpy.split(" ");
    	
		return array[1];
		
	}
	
	public static void main (String[]args) {
			
		try {
	
        String label_string = "";
		
	    JFrame frame = new JFrame("Currency Converter");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(300,320);
	   
		
	    
		JTextField text = new JTextField(label_string);
		text.setBounds(50, 30, 170, 30);
		text.setVisible(true);
		frame.add(text);
			 
	    
		String data[] = {"India INR","America USD","Japan JPY","Euro EUR","NewZealand NZD","Singapore SGD","England GBP","Canada CAD","Swedish SEK","Chinese CNY"};
		JComboBox<Object> currency1 = new JComboBox<Object>(data);
		currency1.setBounds(70, 80, 125, 30);
		frame.add(currency1);
		
		JComboBox<Object> currency2 = new JComboBox<Object>(data);
		currency2.setBounds(70, 130, 125, 30);
		frame.add(currency2);
		
		JLabel label1 = new JLabel("Result:");
	 	label1.setBounds(50,180,100,30);
	 	label1.setVisible(true);
	 	frame.add(label1);
	 	
	 	JLabel label2 = new JLabel();
	 	label2.setBounds(100,180,100,30);
	 	label2.setVisible(true);
	 	frame.add(label2);
	 	 	
		JButton button  = new JButton("Convert");
		button.setBounds(75,220,120,30); 
		
		button.addActionListener(new ActionListener(){  
		    public void actionPerformed(ActionEvent e){  
		        
		    	
		    	String string_input = text.getText();
		        boolean text_input = main.string_check(string_input); 
		        if(text_input == false)
		        {
		           label2.setText("Error ? Input");
		        }

		  	
				String cc =  (String)currency1.getItemAt(currency1.getSelectedIndex());
				String cc1 = (String)currency2.getItemAt(currency2.getSelectedIndex());
			    
			    String currency_selected_1 = main.send(cc);
			    String currency_selected_2 = main.send(cc1);
			    
				ArrayList<String> list = new ArrayList<String>();
				ArrayList<String> list_other = new ArrayList<String>();
				
				String line = "";
				
				try {
					
				URL url = new URL("https://api.exchangerate-api.com/v4/latest/" +currency_selected_1);
				HttpURLConnection con = (HttpURLConnection)url.openConnection();
				
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				
				
				while((line = in.readLine()) != null)
				{
				    list.add(line.toString()); 
				}
				
				String str[] = list.get(0).split(",");
				 
				 list.clear();
				 
				 for(String s : str)
				 {
					 list.add(s);
				 }
				
				 
				 for(int i=7;i<list.size();i++)
				 {
					list_other.add(list.get(i)); 
				 }
				 
				 String find = currency_selected_2;
				  
				 int position = 0;
				 for(int i = 0; i<list_other.size();i++)
				 {
					 String store = list_other.get(i);
					 if(store.contains(find))
					 {
						 position = i;
						 
					 }
					 
				 }
				 
				 String copy = list_other.get(position);
				 String split[] = copy.split(":");
				 Double rate = Double.parseDouble(split[1]);
				 
				 
				double input1 = Double.parseDouble((String)text.getText());
     			
     			Formatter formatter = new Formatter();
     	        formatter.format("%.4f", input1*rate);
     			
     	      
     	        
     	        
     	       if(currency_selected_1.equals(currency_selected_2))
				{
     	    	    
					label2.setText(String.valueOf(input1));
				
				}
     	        else
     	       {
     	    	 
     	    	  label2.setText(String.valueOf(formatter)); 
     	       }
     	        
				
				}catch(Exception e2)
				{
					System.out.println("Error Occur at Converting");
				}
			
				
		    }  
		    });     
		
		
		frame.add(button);
		frame.setLayout(null);
		frame.setVisible(true);	
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
	
		}catch(Exception e1)
		{
		
			System.out.println("Error Occur at JFrame");
			
		}


	}

}

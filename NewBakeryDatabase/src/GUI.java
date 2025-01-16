import java.awt.Container;
import java.awt.Color;
import javax.swing.*;
import java.awt.Font;
import java.io.*;
import static java.lang.System.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout; // Allows for JPanel use 

class GUI extends JFrame implements ActionListener{
    // DECLARE LABELS, TEXT FIELDS, BUTTONS, TEXT AREA, SCROLL PANE, PANEL
    JLabel newPastryLabel, nameLabel, quantityLabel, priceLabel, numProductsLabel, caloriesLabel, productsLabel, sortByLabel;
    JTextField nameInput, quantityInput, priceInput, caloriesInput, searchInput;
    JButton saveButton, resetButton, searchButton, nameButton, quantityButton, priceButton, caloriesButton;
    JTextArea productArea, matchesArea;
    JScrollPane scroll;
    JPanel panel;

    // CREATE FONTS (easier access)
    Font headings = new Font("Calibri", Font.BOLD, 19);
    Font text = new Font("Calibri", Font.PLAIN, 15);
    Font input = new Font("Calibiri", Font.PLAIN, 12);
    Font output = new Font("Calibri", Font.PLAIN, 14);

    public GUI(){
        // CREATING GUI
        setBounds(0, 0, 900, 500);  // Setting bounds
        setLayout(null); // No specified layout
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Closes when 'X' is clicked
        setTitle("Welcome to Eshal's Bakery!"); // Set top title
        Container c = getContentPane(); // Retrieves content layer, allowing objects to be added to it
        // Source for setting colour: https://docs.oracle.com/javase/8/docs/api/java/awt/Color.html
        c.setBackground(new Color(254, 248, 221)); // Set background

        // ADDING LABELS
        // newPastryLabel
        newPastryLabel = new JLabel("Add a New Pastry:");
        newPastryLabel.setFont(headings);
        newPastryLabel.setBounds(60, -60, 200, 200);
        c.add(newPastryLabel);
        //productsLabel
        productsLabel = new JLabel("Products In-Store:");
        productsLabel.setFont(headings);
        productsLabel.setBounds(415, -60, 200, 200);
        c.add(productsLabel);
        //sortByLabel
        sortByLabel = new JLabel("Sort By:");
        sortByLabel.setFont(headings);
        sortByLabel.setBounds(760, -60, 200, 200);
        c.add(sortByLabel);
        // nameLabel
        nameLabel = new JLabel("Pastry Name");
        nameLabel.setFont(text);
        nameLabel.setBounds(10, 40, 100, 100);
        c.add(nameLabel);
        // quantityLabel
        quantityLabel = new JLabel("Quantity");
        quantityLabel.setFont(text);
        quantityLabel.setBounds(10, 100, 100, 100);
        c.add(quantityLabel);
        //priceLabel
        priceLabel = new JLabel("Price");
        priceLabel.setFont(text);
        priceLabel.setBounds(10, 160, 100, 100);
        c.add(priceLabel);
        //caloriesLabel
        caloriesLabel = new JLabel("Calories");
        caloriesLabel.setFont(text);
        caloriesLabel.setBounds(10, 220, 100, 100);
        c.add(caloriesLabel);

        // ADDING TEXT FIELDS
        //nameInput
        nameInput = new JTextField();
        nameInput.setFont(input);
        nameInput.setBounds(120, 75, 200, 30);
        c.add(nameInput);
        //quantityInput
        quantityInput = new JTextField();
        quantityInput.setFont(input);
        quantityInput.setBounds(120, 135, 200, 30);
        c.add(quantityInput);
        //priceInput
        priceInput = new JTextField();
        priceInput.setFont(input);
        priceInput.setBounds(120, 195, 200, 30);
        c.add(priceInput);
        //caloriesInput
        caloriesInput = new JTextField();
        caloriesInput.setFont(input);
        caloriesInput.setBounds(120, 255, 200, 30);
        c.add(caloriesInput);
        //searchInput
        searchInput = new JTextField();
        searchInput.setFont(input);
        searchInput.setBounds(500, 360, 200, 30);
        c.add(searchInput);

        // ADDING BUTTONS
        //saveButton
        saveButton = new JButton("SAVE");
        saveButton.setBounds(170, 315, 150, 30);
        saveButton.addActionListener(this);
        c.add(saveButton);
        //resetButton
        resetButton = new JButton("RESET");
        resetButton.setBounds(10, 315, 150, 30);
        resetButton.addActionListener(this);
        c.add(resetButton);
        //searchButton
        searchButton = new JButton("SEARCH");
        searchButton.setBounds(340, 360, 150, 30);
        searchButton.addActionListener(this);
        c.add(searchButton);
        //nameButton
        nameButton = new JButton("PASTRY NAME");
        nameButton.setBounds(730, 106, 150, 30);
        nameButton.addActionListener(this);
        c.add(nameButton);
        // quantityButton
        quantityButton = new JButton("QUANTITY");
        quantityButton.setBounds(730, 168, 150, 30);
        quantityButton.addActionListener(this);
        c.add(quantityButton);
        //priceButton
        priceButton = new JButton("PRICE");
        priceButton.setBounds(730, 230, 150, 30);
        priceButton.addActionListener(this);
        c.add(priceButton);
        //caloriesButton
        caloriesButton = new JButton("CALORIES");
        caloriesButton.setBounds(730, 292, 150, 30);
        caloriesButton.addActionListener(this);
        c.add(caloriesButton);

        // CREATE JPANEL, JTEXTAREA, AND JSCROLLPANE
        // Since scroll pane is difficult to work with in a null layout, creating a JPanel with a text area inside it allows for a scroll pane to be added more easily
        // Source for adding panel: https://docs.oracle.com/javase/tutorial/uiswing/layout/visual.html
        panel = new JPanel(new BorderLayout()); // Use border layout
        // Set size and location of JPanel (this is where the JTextArea will be)
        panel.setLocation(340, 90);
        panel.setSize(350, 250);

        // ADDING TEXT AREA
        //productArea
        // Source for JTextArea: https://docs.oracle.com/javase/7/docs/api/javax/swing/JTextArea.html
        productArea = new JTextArea("", 10, 50); // No default text, 10 lines, 50 characters per line
        productArea.setFont(output);
        productArea.setLineWrap(true);  // Prevents text area from scrolling left and right
        productArea.setEditable(false); // User cannot edit text area
        display(fileRead(), "name"); // Read text file, and display with method

        // ADDiNG SCROLL
        // Source for JScrollPane: https://docs.oracle.com/javase/tutorial/uiswing/components/scrollpane.html
        scroll = new JScrollPane(productArea);  // Create scroll
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // Always display
        panel.add(scroll); // Add scroll to JPanel (scroll is with text area)
        this.add(panel);  // Add panel to frame

        // ADDING OTHER TEXT AREA
        //matchesArea
        matchesArea = new JTextArea("Possible Matches: ", 3, 50);
        matchesArea.setFont(text);
        matchesArea.setBounds(340, 400, 350, 60);
        matchesArea.setBackground(new Color(254, 248, 221));
        matchesArea.setLineWrap(true);
        matchesArea.setEditable(false);
        c.add(matchesArea);

        // ADDING TOTAL PRODUCTS LABEL (above text area)
        //numProductsLabel
        numProductsLabel = new JLabel("(" + Bakery.getNumPastries() + ")");
        numProductsLabel.setFont(text);
        numProductsLabel.setBounds(340, 30, 100, 100);
        c.add(numProductsLabel);

        setVisible(true); // Set visible at the end, so all components appear at the same time
    }

    // PROGRAMMING BUTTONS
    @Override
    public void actionPerformed(ActionEvent e){
        // saveButton
        if(e.getSource() == saveButton){
            String name;
            int quantity;
            double price;
            int calories;
            Bakery b = new Bakery();
            // If user fills out all 4 text fields
            if(!nameInput.getText().equals("") && !quantityInput.getText().equals("") && !priceInput.getText().equals("") && !caloriesInput.getText().equals("")){
                name = nameInput.getText();
                quantity = Integer.parseInt(quantityInput.getText());
                price = Double.parseDouble(priceInput.getText());
                calories  = Integer.parseInt(caloriesInput.getText());
                // Extract and create new object containing all 4 inputs
                b = new Bakery(name, quantity, price, calories);
            }
            // If user only enters pasty name and quantity
            else if(!nameInput.getText().equals("") && !quantityInput.getText().equals("")){
                name = nameInput.getText();
                quantity = Integer.parseInt(quantityInput.getText());
                // Extract 2 inputs and create new object
                b = new Bakery(name, quantity);

            }
            // If user enters nothing
            else if(nameInput.getText().equals("") && quantityInput.getText().equals("") && priceInput.getText().equals("") && caloriesInput.getText().equals("")){
                // Create new objects using parameterless constructor
                b = new Bakery();
            }
            fileWrite(b); // Write new object to file
            display(fileRead(), "name"); // Read file, format, and display to text area
            numProductsLabel.setText("(" + countLines() + ")"); // Use the number of lines in the text file to display the total products

        }
        // resetButton
        if(e.getSource() == resetButton){
            // Set all text fields to nothing
            nameInput.setText("");
            quantityInput.setText("");
            priceInput.setText("");
            caloriesInput.setText("");
        }
        // Search and sorting buttons call corresponding methods
        if(e.getSource() == searchButton){
            search();
        }
        if(e.getSource() == nameButton){
            sortByName();
        }
        if(e.getSource() == quantityButton){
            sortByQuantity();
        }
        if(e.getSource() == priceButton){
            sortByPrice();
        }
        if(e.getSource() == caloriesButton){
            sortByCalories();
        }
    }

    // WRITING TO FILE
    // fileWrite method - everytime user enters a pastry, an object is created, and written to the file - method takes in the object
    public void fileWrite(Bakery b){
        try{
            FileWriter fw  = new FileWriter("Pastry.txt", true);
            PrintWriter pw = new PrintWriter(fw);
            // When writing a Bakery object to the file, the file write uses the toString method of the object
            pw.println(b);
            pw.close();
        }
        catch(IOException e){
            out.println("File Writing Error");
        }
    }

    // READING FROM FILE
// countLines method - used in the fileRead method and for the numProductsLabel 
    public static int countLines(){
        int count = 0;
        try{
            FileReader fr = new FileReader("Pastry.txt");
            BufferedReader br = new BufferedReader(fr);
            String str = br.readLine();
            // While something is read, continue reading and increase count
            while(str != null && !str.equals(" ")){
                count++;
                str = br.readLine();
            }
            br.close();
        }
        catch(IOException e){
            out.println("File Reading Error");
        }
        return count;
    }

    // fileRead method - reads from file, turns each line into an object, and adds the object to an array of objects, which is returned
    public static Bakery[] fileRead(){
        int numLines = countLines();  // Find num of lines
        Bakery[] bakeryArray = new Bakery[numLines];  // Create array of objects
        try{
            FileReader fr2 = new FileReader("Pastry.txt");
            BufferedReader br2 = new BufferedReader(fr2);
            String line = br2.readLine();
            int counter = 0;
            // While something is read
            while(line != null && !line.equals(" ")){
                // Create a new object from the read line (uses the constructor with a parameter of type String)
                Bakery b1 = new Bakery(line);
                // Add this object to the array
                bakeryArray[counter] = b1;
                counter++;
                line = br2.readLine();
            }
            br2.close();
        }
        catch(IOException e){
            out.println("File Reading Error");
        }
        return bakeryArray;
    }

    // DISPLAY TO TEXT AREA
    // display method - displays an array to the text area. Takes 2 paramaters: 1) an array, which comes from the readFile method and 2) a string, indicating how the array should be formatted
    public void display(Bakery[] b, String n){
        productArea.setText("");  // Clear the text area
        String format = "";
        // Format every element in object array
        for(int i = 0; i < b.length; i++){
            // Split the array element into parts
            String[] part = b[i].toString().split(",");
            switch(n){
                // If "name" is passed, each element in the array is rearranged so that the name of the pastry appears first
                case "name": format = part[0] + "," + part[1] + "," + part[2] + "," + part[3];
                    break;
                case "quantity": format = part[1] + "," + part[0] + "," + part[2] + "," + part[3];
                    break;
                case "price": format = part[2] + "," + part[0] + "," + part[1] + "," + part[3];
                    break;
                case "calories": format = part[3] + "," + part[0] + "," + part[1] + "," + part[2];
                    break;
            }
            productArea.append(format + "\n");  // Add each newly formatted element to the text area
        }
    }

    // SEARCH
    // search method - uses linear search to go through an array of objects (taken from the fileRead method) and displays up to 2 possible matches with the given input
    public void search(){
        String search = searchInput.getText();  // Get input
        Bakery[] bakeryObjects = fileRead();  // Create array of objects from file read
        matchesArea.setText("Possible Matches: ");
        int count = 0;
        for(int i = 0; i < bakeryObjects.length; i++){
            if(bakeryObjects[i].toString().contains(search)){
                count++;
                matchesArea.append("\n" + bakeryObjects[i]);  // If a match is found, add to matchesArea text area
                if(count == 2){
                    break;  // Stop if 2 matches have been found
                }
            }
        }
        if(count == 0){
            matchesArea.append("No matches"); // Display no matches if nothing is found
        }
    }

    //SORTING METHODS - uses insertion to sort through an array of objects from greatest to least (array of objects comes from fileRead method)
    // SORT BY NAME
    public void sortByName(){
        Bakery[] bakeryObject = fileRead();
        Bakery temp = new Bakery();
        // Compare the name of each bakery object to the one before it, and sort alphabetically
        for(int i = 1; i < bakeryObject.length; i++){
            for(int j = i - 1; j >= 0 ; j--){
                if(bakeryObject[j].getName().compareTo(bakeryObject[j+1].getName()) > 0){
                    temp = bakeryObject[j];
                    bakeryObject[j] = bakeryObject[j+1];
                    bakeryObject[j+1] = temp;
                }
                else{
                    j = 0;
                }
            }
        }
        display(bakeryObject, "name");  // Use display method to format the array with the name appearing first
    }

    // SORT BY QUANTITY
    public void sortByQuantity(){
        Bakery[] bakeryObject = fileRead();
        Bakery temp = new Bakery();
        for(int i = 1; i < bakeryObject.length; i++){
            for(int j = i - 1; j >=0; j--){
                if(bakeryObject[j].getQuantity() > bakeryObject[j+1].getQuantity()){
                    temp = bakeryObject[j];
                    bakeryObject[j] = bakeryObject[j+1];
                    bakeryObject[j+1] = temp;
                }
                else{
                    j = 0;
                }
            }
        }
        display(bakeryObject, "quantity");
    }

    // SORT BY PRICE
    public void sortByPrice(){
        Bakery[] bakeryObject = fileRead();
        Bakery temp = new Bakery();
        for(int i = 1; i < bakeryObject.length; i++){
            for(int j = i - 1; j >= 0; j--){
                if(bakeryObject[j].getPrice() > bakeryObject[j+1].getPrice()){
                    temp = bakeryObject[j];
                    bakeryObject[j] = bakeryObject[j+1];
                    bakeryObject[j+1] = temp;
                }
                else{
                    j = 0;
                }
            }
        }
        display(bakeryObject, "price");
    }

    // SORT BY CALORIES
    public void sortByCalories(){
        Bakery[] bakeryObject = fileRead();
        Bakery temp = new Bakery();
        for(int i = 1; i < bakeryObject.length; i++){
            for(int j = i - 1; j >= 0; j--){
                if(bakeryObject[j].getCalories() > bakeryObject[j+1].getCalories()){
                    temp = bakeryObject[j];
                    bakeryObject[j] = bakeryObject[j+1];
                    bakeryObject[j+1] = temp;
                }
                else{
                    j = 0;
                }
            }
        }
        display(bakeryObject, "calories");
    }
}
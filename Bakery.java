class Bakery{
    private String pastryName;
    private int quantity;
    private double price;
    private int calories;
    private static int numPastries = 0;

    // CONSTRUCTORS
    // Full constructor with 4 parameters
    public Bakery(String n, int q, double p, int c){
        pastryName = n;
        // Error check within constructor
        setQuantity(q);
        setPrice(p);
        setCalories(c);
        numPastries++;
    }

    // Two parameters
    public Bakery(String n, int q){
        this(n, q, 2.00, 100);
    }

    // No parameters
    public Bakery(){
        this("Pastry", 0);
    }

    // One String parameter (for assigning lines in the textfile to an object)
    public Bakery(String l){
        String[] parts = l.split(",");
        pastryName = parts[0];
        quantity = Integer.parseInt(parts[1]);
        price = Double.parseDouble(parts[2]);
        calories = Integer.parseInt(parts[3]);
        numPastries++;
    }

    // ACCESSOR / GETTER METHODS
    public String getName(){
        return pastryName;
    }

    public int getQuantity(){
        return quantity;
    }

    public double getPrice(){
        return price;
    }

    public int getCalories(){
        return calories;
    }

    public static int getNumPastries(){
        return numPastries;
    }

    public void setName(String n){
        pastryName = n;
    }

    public void setQuantity(int q){
        if(q >= 0){
            quantity = q;
        }
        else{
            quantity = 0;
        }
    }

    public void setPrice(double p){
        if(p >= 0){
            price = p;
        }
        else{
            price = 2.00;
        }
    }

    public void setCalories(int c){
        if(c >= 0){
            calories = c;
        }
        else{
            calories = 100;
        }
    }

    @Override
    public String toString(){
        return pastryName + "," + quantity + "," + price +  "," + calories;
    }


}
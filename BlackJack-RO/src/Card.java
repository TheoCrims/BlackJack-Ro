public class Card {
    
    String nume;
    int valoare;
    String shape;
    boolean folosit = false;
    int id;
    String simbol;
    
    public Card(int n, String s, int z) {
        if (n > 1 && n < 11) {
            this.nume = Integer.toString(n); 
            this.valoare = n;
            this.simbol = this.nume;
        } else if (n > 10) {
            this.valoare = 10;
            if (n == 11) {
                this.nume = "Jack";
                this.simbol = "J";
            } else if (n == 12) {
                this.nume = "Queen";
                this.simbol = "Q";
            } else if (n == 13) {
                this.nume = "King";
                this.simbol = "K";
            }
        } else if (n == 1) {
            this.valoare = 1;
            this.nume = "Ace";
            this.simbol = "A";
        }
        this.shape = s;
        this.id = z;
   
    }
    
    public void setFolosit() {
        folosit = true;
    
    }
    
    public void setNefolosit() {
        folosit = false;
    
    }
    
}
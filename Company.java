
/**
 * Class Company represents a linked list of the a company's rents 
 *
 * @author Ido Kander 208602367
 * @version Semester 2023A 29/1/2023
 */
public class Company
{
    // instance variables
    private RentNode _head; //the first rent in the list
    /**
     * Initialize a Company to be empty
     */
    public Company(){ 
        _head = null; 
    }
    
    /**
     * Adds a rent to the company list, sorted by pick up date
     * if pick up date is equals, sort by length of rent
     * @param name the of the new rent
     * @param Car the type of car to rent 
     * @param pick the pick up date
     * @param ret the return date
     * @return true if rent isn't in the list already and add, false if not 
     */
     public boolean addRent(String name, Car car,Date pick, Date ret){
        Rent newRent = new Rent(name, car,pick, ret);
        RentNode ptr = _head;
        // Check if the list is empty
        if (ptr == null)
        {
        _head = new RentNode(newRent);
        return true;
        }
        // Check if the rent already exists
        ptr = _head;
        while (ptr != null)
        {
            if (newRent.equals(ptr.getRent()))
            {
            return false;
            }
            ptr = ptr.getNext();
        }
        // Find the appropriate location to insert the new rent
        ptr = _head;
        RentNode prev = null;
        while (ptr != null){
            if ((pick.equals(ptr.getRent().getPickDate()) && (newRent.howManyDays() > ptr.getRent().howManyDays()))){
                if (prev == null){
                    _head = new RentNode(newRent, _head); // if pick dates are equals, longer rent is before
                }
                else{
                prev.setNext(new RentNode(newRent, ptr));
                }
                return true; // rent added
            }
            else if (pick.before(ptr.getRent().getPickDate())){
                if (prev == null){
                    _head = new RentNode(newRent, _head); // if this pick date before ptr pick date, this pick is before 
                }
                else{
                prev.setNext(new RentNode(newRent, ptr)); 
                }
                return true;
            }
            prev = ptr;
            ptr = ptr.getNext();
        }
        prev.setNext(new RentNode(newRent)); // sets the new rents node at the end 
        return true;
    }
      
    /**
     * Removes a rent from the company list 
     * @param d the date of the rent to be removed
     * @param true if rent is removed, false if not
     */
    public boolean removeRent(Date d){
        RentNode ptr = _head; 
        RentNode behind = ptr;
        if (ptr == null)// method works on empty list
            return false;
        if (d.equals(ptr.getRent().getReturnDate())){
            ptr = ptr.getNext(); // if date needed to remove is first
            return true;
        }
        while (behind.getNext() != null){
            if (d.equals(behind.getNext().getRent().getReturnDate())){
                behind.setNext(behind.getNext().getNext()); // if date needed to remove is in the middle
                return true;
            }
            else
                behind = behind.getNext(); 
        }
        return false;
    }
    
    /**
     * Returns number of rents in the company list
     * @return int represents that number
     */
    public int getNumOfRents(){
        RentNode temp = _head; 
        int count = 0; // a counter for the rents
        while (temp != null){
            count++; // for each rent, counter grows in 1
            temp = temp.getNext(); 
        }
        return count; 
    }
    
    /**
     * Return the sum of the prices of all rents of a company
     * @return int represents that sum
     */
    public int getSumOfPrices(){
        RentNode temp = _head;
        int sum = 0; // an int to sum 
        while (temp != null){
            sum += temp.getRent().getPrice(); // sums grows with each price of a rent, using getPrice
            temp = temp.getNext(); // jumps on to the next
        }
        return sum;
    }
    
    /**
     * Return the numbers of days for all of the rents
     * @return int represents that sum
     */
    public int getSumOfDays(){
        RentNode temp = _head;
        int sum = 0; // an int to sum
        while (temp != null){
            sum += temp.getRent().howManyDays(); //sum grows with each rents, and has its amount of days 
            temp = temp.getNext(); // jumps on to the next
        }
        return sum;
    }
    
    /**
     * Returns the average of days of a company list 
     * @return int represents that average
     */
    public double averageRent(){
        if (empty())
            return 0; // no average in case the company's list is empty
        else{
            return (double)(this.getSumOfDays()) / ((double)(this.getNumOfRents()));
        }
    }
    
    /**
     * Returns the car of the rent with the latest return date
     * if there's more then one rents with latest date, return the first
     * @return Car the type of the car to return
     */
    public Car lastCarRent(){
        RentNode ptr = _head, curr = _head;
        RentNode latest = ptr;
        Date date = ptr.getRent().getReturnDate();
        if (ptr == null)
            return null; //if company's list is empty, return null
        while (ptr.getNext() != null){ //goes through the whole list of rents
            if ((ptr.getRent().getReturnDate().after(ptr.getNext().getRent().getReturnDate())) && (ptr.getRent().getReturnDate().after(date))){
                latest = ptr; // saves the one with latest rent as latest
                date = ptr.getRent().getReturnDate();
            }
            ptr = ptr.getNext();
        }
        return latest.getRent().getCar();
        }

    /**
     * Return the longest rent in the company's list
     * @return the rent
     */
    public Rent longestRent(){
        RentNode ptr = _head;
        RentNode maxPtr = _head;
        int maxDays = 0;
        if (ptr == null)
            return null;
        while (ptr != null){
            if (ptr.getRent().howManyDays() > maxDays){
                maxDays = ptr.getRent().howManyDays(); // finding the longest rent
                maxPtr = ptr;
            }
            ptr = ptr.getNext();
        }
        ptr = _head;
        while (ptr != null){
            if (ptr.getRent().equals(maxPtr.getRent())){
                maxPtr = ptr; // makes sure the first longest rent is one returned
                break;
            }
            ptr = ptr.getNext();
        }
        return maxPtr.getRent();
    }  
    
    
    /**
     * a method that checks inside the company list, which type of car is the most common 
     * counts all times are mentioned and returns the maximum one
     * @return the char which is most common in the company list 
     */
    public char mostCommonRate(){
        if (empty() == true)
            return 'N';
        int counterA = 0, counterB = 0, counterC = 0, counterD = 0;
        RentNode tmp = _head; //
        while (tmp != null){ //if any type is mentioned, raise its counter by one
            if (tmp.getRent().getCar().getType() == 'A')
                counterA++; 
            else if (tmp.getRent().getCar().getType() == 'B')
                counterB++;
            else if (tmp.getRent().getCar().getType() == 'C')
                counterC++;
            else if (tmp.getRent().getCar().getType() == 'D')
                counterD++;
            tmp = tmp.getNext();
        }
        int max = Math.max(Math.max(counterA,counterB),Math.max(counterC,counterD));
        if (max == counterA)
            return 'A';
        else if (max == counterB)
            return 'B';
        else if (max == counterC)
            return 'C';
        else
            return 'D';
    }
   
    /**
     * Checks if one company list of rents is included in another
     * @param the company that being check if included
     * @return true if it is included
     */
     
    public boolean includes(Company other){
        RentNode ptr = this._head;
        RentNode otherPtr = other._head;
        int counter = 0;
        if (ptr == null)
            return false;
        if (otherPtr == null)
            return true;
        for (int i = 0; i < this.getNumOfRents() ; i++){ // for loop for all rents in this company
            otherPtr = other._head;
            for (int j = 0; j < other.getNumOfRents() ; j++){// for loop for all rents in other company
                if (ptr.getRent().equals(otherPtr.getRent())){
                    counter++; //everytime a rent is equal - counter grows
                }
                otherPtr = otherPtr.getNext(); 
            }
            ptr = ptr.getNext();
        }
        return counter == other.getNumOfRents(); //if counter is same as number of rents in the other company, it is fully included
        
            
    }
    
     /**
     * a method that uses a private method called merge and merges two companies altogether to one list 
     * @param other represents the company to be merged with 
     */
    public void merge(Company other){
          this._head = Pmerge(this._head, other._head); // goes inside the private method 'merge' which takes both the rentNode of both companies 
    }
       
    /**
     * a method to present the list of rents in the company as a string 
     * @return a string presents all of the rents 
     */     
    public String toString(){
        if(empty())
            return "The company has 0 rents"; // print this in case the list of rents is empty 
        RentNode ptr = _head;
        String str = "";
        System.out.println("The company has " + this.getNumOfRents() + " rents:");
        while (ptr != null){
            str += ptr.getRent().toString() + "\n";
            ptr = ptr.getNext();
        }
        return str;
    }
    
    /**
     * a private method to easily merge between 2 company lists
     * @param1 the first rent in the company
     * @param2 the first rent in the other company 
     * @return the merged list of the companies 
     */
    private RentNode Pmerge(RentNode ptr, RentNode otherPtr){
    if (ptr == null)
        return otherPtr;
    if (otherPtr == null)
        return ptr;
    if (ptr.getRent().getPickDate().equals(otherPtr.getRent().getPickDate())){
        if (ptr.getRent().howManyDays() > otherPtr.getRent().howManyDays()){
            ptr.setNext(Pmerge(ptr.getNext(), otherPtr));
            return ptr;
        }
        else if (ptr.getRent().howManyDays() < otherPtr.getRent().howManyDays()){
            otherPtr.setNext(Pmerge(ptr, otherPtr.getNext()));
            return otherPtr;
        }
        else{
            //Rent time and pick-up dates are equal, add the rent from the other company to the current company's list
            ptr.setNext(Pmerge(ptr.getNext(), otherPtr.getNext()));
            return ptr;
        }
    }
    else if (ptr.getRent().getPickDate().before(otherPtr.getRent().getPickDate())){
        ptr.setNext(Pmerge(ptr.getNext(), otherPtr));
        return ptr;
    }
    else {
        otherPtr.setNext(Pmerge(ptr, otherPtr.getNext()));
        return otherPtr;
    }
    }
    
    /**
     * Checks if the company's list is empty
     * @return true if the list is empty, false if not 
     */
    private boolean empty(){
        return _head == null;
    }
    
    /**
     * Checks if a specific rent is inside a company list 
     * @param toCheck the node of the rent being checked
     * @return true if the rent is there , false if not
     */
      private boolean haveAlready(RentNode toCheck){
        RentNode ptr = _head;
        while (ptr != null){
            if (ptr.getRent().equals(toCheck.getRent())){
                return true;
            }
            ptr = ptr.getNext();
        }
        return false;
    }
}

    
    
   
    
    
   
  



    

    
        
    


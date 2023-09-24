
/**
 * Class RentNode represents a node of a Rent class.
 *
 * @author Ido Kander ID: 208602367
 * @version Semester 2023 29/1/2023 
 */
public class RentNode
{
    // instance variables 
    private Rent _rent; // the Rent of the node
    private RentNode _next; // the node of the next Rent
    // class constuctors 
    /**
     * Initialize a RentNode only with given rent
     * @param Rent to intialize
     */
    public RentNode(Rent r){
        _rent = r;
        _next = null;
    }
    
    /**
     * Initialize a RentNode with both its Rent and the next Node
     * @param Rent to intialize
     * @param RentNode to be it's next
     */
    public RentNode(Rent r, RentNode next){
        _rent = r;
        _next = next;
    }
    
    /**
     * Intialize a RentNode copying the attributes of another one
     * @param RentNode to be copied from
     */
    public RentNode(RentNode other){
        this._rent = other._rent;
        this._next = other._next;
    }
    
    /**
     * Returns the node's Rent
     * @return rent
     */
    public Rent getRent(){
        Rent copyRent = _rent;
        return copyRent;
    }
    
    /**
     * Sets the Node to be the next node
     * @return the next Node
     */
    public RentNode getNext(){
        return _next;
    }
    
    /**
     * Sets the Rent to be a new one
     * @param Rent the new rent
     */
    public void setRent(Rent r){
        _rent = r;
    }
    /**
     * @Sets new _next to the node
     * @param the new Node
     */
    public void setNext(RentNode next){
        _next = next;
    } 
}

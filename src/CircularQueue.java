import java.util.LinkedList;


public class CircularQueue extends Queue
{
  private LinkedList<Object> slots;
  //private int first, last, num;
  //private final int SIZE;
    
  public CircularQueue()
  {
    slots = new LinkedList<Object>();
    //SIZE = s;
    //first = last = num = 0;
  }

  // Number of elements
  public int Rank()
  {
    return slots.size();
  }
    
  // Number of free slots available
  /*public int Free()
  {
    return SIZE - num;
  }*/
    
  // Is full ?
  public boolean IsFull()
  {
    return false;
  }

  // Put value at queue's tail
  public void Put(Object value)
  {
    slots.add(value);
    //last = (last + 1) % slots.size();
    //num++;
  }

  // Get value from queue's head
  public Object Get()
  {
    Object result = slots.getFirst();   
    slots.removeFirst();
    //first = (first + 1) % slots.size();
    //num--;
    return result;
  }
}


public abstract class Queue
{
  // Number of elements
  public abstract int Rank();
    
  // Number of free slots available
  //public abstract int Free();
  
  // Is empty ?
  public boolean IsEmpty()
  {
    return Rank() == 0;
  }

  // Is full ?
  public abstract boolean IsFull();

  // Get value from queue's head
  public abstract Object Get();

  // Put value at queue's tail
  public abstract void Put(Object value);
}

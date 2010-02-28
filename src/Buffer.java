public class Buffer
{
  protected Queue q;

  public Buffer(Queue q)
  {
    this.q = q;
  }

  public synchronized Object Get()
  {
    while (q.IsEmpty())
      {
        try
          {
            wait();
          }
        catch (InterruptedException e)
          {
          }
      }
    Object result = q.Get();
    
    LanzaCheckRyan.lanzaCheck(result);
    
    notifyAll();
    return result;
  }

  public synchronized void Put(Object value)
  {
    while (q.IsFull())
      {
        try
          {
            wait();
          }
        catch (InterruptedException e)
          {
          }
      }
    q.Put(value);
    notifyAll();
  }
}

/**
* Exception class for access in empty containers
* such as stacks, queues, and priority queues.
* @author Mark Allen Weiss
*/
public class UnderflowException extends RuntimeException
{
   /**
     * 
     */
   private static final long serialVersionUID = 1L;

   /**
* Construct this exception object.
* @param message the error message.
*/
   public UnderflowException( String message )
   {
      super( message );
   }
}

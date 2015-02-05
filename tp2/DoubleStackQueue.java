import java.util.Stack;
import java.util.EmptyStackException;

public class DoubleStackQueue<Elem> implements myQueue<Elem>
{
   //A completer
    
    private Stack<Elem> stack1;
    private Stack<Elem> stack2;
    
    public boolean empty()
    {
        return stack1.size() == 0;
    }
    
    public void push(Elem elem)
    {
        stack1.push(elem);
    }
    
    public Elem pop(){
        if (stack1.size() == 0) {
            return null;
        }
        for (int i = 0; i < stack1.size(); i++) {
            stack2.push(stack1.pop());
        }

        Elem temp = stack2.pop(); 

        for (int i = 0; i < stack2.size(); i++) {
            stack1.push(stack2.pop());
        }
        return temp;
    }
        
}

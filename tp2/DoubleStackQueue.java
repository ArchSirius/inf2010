import java.util.Stack;
import java.util.EmptyStackException;

public class DoubleStackQueue<Elem> implements myQueue<Elem>
{
    private Stack<Elem> stack1 = new Stack<Elem>(); // stack principal
    private Stack<Elem> stack2 = new Stack<Elem>(); // stack de dépilement
    
    public void offer(Elem element){
        if (stack1.empty()){
            stack1.push(element);
        } else {
            stack2.push(stack1.pop());  // changement de stack
            offer(element);             // appel récursif
            stack1.push(stack2.pop());  // retour au stack1
        }
    }

    public Elem peek(){
        Elem element;
        try{
            element = stack1.peek();
        } catch(EmptyStackException e){
            element = null;
        }
        return element;
    }

    public Elem poll(){
        Elem element;
        try{
            element = stack1.pop();
        } catch(EmptyStackException e){
            element = null;
        }
        return element;
    }

    public boolean empty(){
        return stack1.empty();
    }
}

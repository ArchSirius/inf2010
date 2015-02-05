import java.io.*;
import java.util.Stack;

public class PostfixSolver 
{
   public static void main(String[] args) throws IOException 
   {
      Stack<Double> stack = new Stack<Double>();

      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      String s;

      //Les caracteres sont lus de la ligne de commande tant que l'usager n'entre
      // pas "exit"
      System.out.println("Entrez une expression postfixe");
      
      while (!(s = in.readLine()).equals("exit")) 
      {
         //L'expression est separee en tokens selon les espaces
         for(String token : s.split("\\s")) 
         {
             if( token.equals("+")  || token.equals("-") || token.equals("*") || token.equals("/") )
             {
                 switch (token)
                 {
                     case "+":
                         stack.push(stack.pop() + stack.pop());
                         break;
                     case "-":
                         stack.push(stack.pop() - stack.pop());
                         break;
                     case "*":
                         stack.push(stack.pop() * stack.pop());
                         break;
                     case "/":
                         stack.push(stack.pop() / stack.pop());
                 }
                         
             }
             else
             {
                stack.push(Double.parseDouble(token));
             }
            
            
         }
         
         
         System.out.println("Le resultat de l'expression est: "+stack.pop());
         
      }
      // "exit" stoppe le programme
   }
}

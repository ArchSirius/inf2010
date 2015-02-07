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
                switch (token)
                {
                    // Opérateur (valide que sur deux opérandes)
                    case "+":
                        if(stack.size() > 1)
                            stack.push(stack.pop() + stack.pop());
                        break;
                    case "-":
                        if(stack.size() > 1)
                            stack.push(stack.pop() - stack.pop());
                        break;
                    case "*":
                        if(stack.size() > 1)
                            stack.push(stack.pop() * stack.pop());
                        break;
                    case "/":
                        if(stack.size() > 1)
                            stack.push(stack.pop() / stack.pop());
                        break;
                    // Opérande (valide que si valeur entière)
                    default:
                        /* REGEX
                         *
                         * Accepte toute chaîne réprésentant un entier, tel que
                         * 2, 2., 42, 3.14, 42.24, etc.
                         *
                         * ^        : début de ligne
                         * [0-9]    : un chiffre
                         *   ­   +   :  au moins une fois
                         * [.]      : le caractère '.'
                         *    ?     :  zéro ou une fois
                         * [0-9]    : un chiffre
                         *      *   :  au moins zéro fois
                         * $        : fin de ligne
                         */
                        if(token.matches("^[0-9]+[.]?[0-9]*$"))     // regex
                            stack.push(Double.parseDouble(token));
                }
            }
            if(!stack.isEmpty()) {
                System.out.println("Le resultat de l'expression est: " + stack.pop());
                stack.clear();
            } else {
                System.out.println("Expression invalide");
            }
            System.out.println("\nEntrez une expression postfixe (\"exit\" pour quitter)");
        }
    // "exit" stoppe le programme
    }
}

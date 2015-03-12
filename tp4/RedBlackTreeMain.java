public class RedBlackTreeMain
{
   public static void main(String[] args)
   {
      // Valeurs a inserer dans l'arbre
      int val[] = {56, 72, 31, 61, 69, 20, 46};
      
      // Creation de l'arbre
      RedBlackTree<Integer> tree = new RedBlackTree<Integer>();
      
      // Insertion des elements dans l'arbre
      for(int i=0; i<val.length; i++)
      {
         // insérer clé
         tree.insert( new Integer( val[i] ) );

         // afficher
          tree.printFancyTree();
          System.out.println();
      }
      
      // Verifier find()
      Integer n = tree.find(20);
      System.out.print("Recherche valeur 20 : ");
      if ( n != null )  
        System.out.println("Noeud trouvé."); 
      else
        System.out.println("Noeud introuvable.");
      
      n = tree.find(99);
      System.out.print("Recherche valeur 99 : ");
      if(n != null)
        System.out.println("Noeud trouvé.");
      else
        System.out.println("Noeud introuvable.");
            
      System.out.println();
      
      // Affichage pre-ordre et post-ordre
      tree.printTreePreOrder();
      tree.printTreePostOrder();
      
      // Affichage par niveaux
//      tree.printTreeLevelOrder(); 
   }
}

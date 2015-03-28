
public class Main  
{
   /**
     * Fonction principale
     */
   public static void main(String[] args) 
   {
      // creer un monceau avec 22 elements et un tableau equivalent
      int numItems = 22;
      BinaryHeap<Integer> heap = new BinaryHeap<Integer>( );
      Integer [ ] items = new Integer[ numItems ];

      int i;
      int j;

      // en inserant les elements un a un
      for( i = 11, j = 0; j != numItems; i = ( i + 37 ), j++ )
      {
         heap.insert( i );
         items[ j ] = i;
         
         i %=  numItems; 
      }

      // en construisant le monceau depuis le depart
      System.out.println("Monceau contruit élément par élément:");
      System.out.println( heap.printFancyTree() );

      heap = new BinaryHeap<Integer>( items );
      System.out.println("Monceau contruit d'un trait:");
      System.out.println( heap.printFancyTree() );

      heap.buildMaxHeap( );
      System.out.println("Monceau max contruit d'un trait:");
      System.out.println( heap.printFancyTree() );

      System.out.println();
      System.out.println("Affichage récursif:");
      System.out.println( heap.printFancyTree() );

      System.out.println("Affichage non récursif:");
      System.out.println( heap.nonRecursivePrintFancyTree() );

      System.out.println();
      System.out.println("Tableau d'origine:");
      System.out.println( printArray( items ) );

      BinaryHeap.heapSort( items );
      System.out.println("Tableau ordonne:");
      System.out.println( printArray( items ) );

      BinaryHeap.heapSortReverse( items );
      System.out.println("Tableau inversement ordonne:");
      System.out.println( printArray( items ) );


      /*
       * Ajouter appels pour repondre a la question
       **/
   }

   private static <AnyType> 
   String printArray(AnyType[] a)
   {
      String outputString = "";

      for(AnyType item : a)
      {
         outputString += item;
         outputString += ", ";
      }

      return outputString;
   }
}

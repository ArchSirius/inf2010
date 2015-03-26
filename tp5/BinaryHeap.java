public class BinaryHeap<AnyType extends Comparable<? super AnyType>> //implements PriorityQueue<AnyType>
{
   @SuppressWarnings("unchecked")
   public BinaryHeap( )
   {
      currentSize = 0;
      array = (AnyType[]) new Comparable[ DEFAULT_CAPACITY + 1 ];
   }

   @SuppressWarnings("unchecked")
   public BinaryHeap( AnyType [ ] items )
   {
      // COMPLETER
      // invoquer buildMinHeap( );    
   }

   public void insert( AnyType x )
   {
      if( currentSize + 1 == array.length )
      doubleArray( );

      // COMPLETER
   }

   public void buildMinHeap( )
   {
      // COMPLETER
      // invoquer : percolateDownMinHeap(array, indice, currentSize, true );
   }

   public void buildMaxHeap( )
   {
      // COMPLETER
      // invoquer : percolateDownMaxHeap(array, i, currentSize, true );
   }

   public boolean isEmpty( )
   {
      return currentSize == 0;
   }

   public int size( )
   {
      return currentSize;
   }

   public void makeEmpty( )
   {
      currentSize = 0;
   }

   private static final int DEFAULT_CAPACITY = 100;

   private int currentSize;      // Nombre d'elements
   private AnyType [ ] array;    // Tableau contenant les donnees (premier element a l'indice 1)

   private static int leftChild( int i, boolean isHeap )
   {
      return ( isHeap ? 2*i : 2*i+1 );
   }

   /**
     * @param a       Tableau a traiter
     * @param hole    Position a percoler
     * @param size    Indice max du tableau
     * @param isHeap  Indique si a est un heap (case 0 non utilisee)
     */
   private static <AnyType extends Comparable<? super AnyType>>
   void percolateDownMinHeap( AnyType[] a, int hole, int size, boolean isHeap )
   {
      // COMPLETER
   }

   /**
     * @param a       Tableau a traiter
     * @param hole    Position a percoler
     * @param size    Indice max du tableau
     * @param isHeap  Indique si a est un heap (case 0 non utilisee)
     */
   private static <AnyType extends Comparable<? super AnyType>>
   void percolateDownMaxHeap( AnyType[] a, int hole, int size, boolean isHeap )
   {
      // COMPLETER
   }

   public static <AnyType extends Comparable<? super AnyType>>
   void heapSort( AnyType [ ] a )
   {
      // COMPLETER
   }

   public static <AnyType extends Comparable<? super AnyType>>
   void heapSortReverse( AnyType [ ] a )
   {
      // COMPLETER
   }

   private static <AnyType> 
   void swapReferences( AnyType [ ] a, int index1, int index2 )
   {
      AnyType tmp = a[ index1 ];
      a[ index1 ] = a[ index2 ];
      a[ index2 ] = tmp;
   }

   @SuppressWarnings("unchecked")
   private void doubleArray( )
   {
      AnyType [ ] newArray;

      newArray = (AnyType []) new Comparable[ array.length * 2 ];
      for( int i = 0; i < array.length; i++ )
      newArray[ i ] = array[ i ];
      array = newArray;
   }

   public String nonRecursivePrintFancyTree()
   {
      String outputString = "";

      // COMPLETER

      return outputString;
   }

   public String printFancyTree( )
   {
      return printFancyTree( 1, "");
   }

   private String printFancyTree( int index, String prefix)
   {
      String outputString = "";

      outputString = prefix + "|__";

      if( index <= currentSize )
      {
         boolean isLeaf = index > currentSize/2;

         outputString += array[ index ] + "\n";

         String _prefix = prefix;

         if( index%2 == 0 )
         _prefix += "|  "; // un | et trois espace
         else
         _prefix += "   " ; // quatre espaces

         if( !isLeaf )
         {
            outputString += printFancyTree( 2*index, _prefix);
            outputString += printFancyTree( 2*index + 1, _prefix);
         }
      }
      else
      outputString += "null\n";

      return outputString;
   }
}

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
      currentSize = items.length;
      array = (AnyType[]) new Comparable[ ( currentSize + 2 ) * 11 / 10 ];
      
      int i = 1;
      for( AnyType item : items )
         array[ i++ ] = item;
      buildMinHeap( );
    }

   public void insert( AnyType x )
   {
      if( currentSize + 1 == array.length )
         doubleArray( );

      // inspiré des notes de cours
      int hole = ++currentSize;
      for(; hole > 1 && x.compareTo(array[hole / 2]) < 0; hole /= 2)
         array[hole] = array[hole / 2];   // swap parent & hole
      array[hole] = x;  // insert x in hole
   }

   public void buildMinHeap( )
   {
      for(int indice = currentSize / 2; indice > 0; indice--)
         percolateDownMinHeap(array, indice, currentSize, true );
   }

   public void buildMaxHeap( )
   {
      for(int indice = currentSize / 2; indice > 0; indice--)
         percolateDownMaxHeap(array, indice, currentSize, true );
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
      int child;
      AnyType tmp = a[ hole ];
      
      for( ; leftChild(hole, isHeap) < size; hole = child )
      {
         child = leftChild(hole, isHeap);
         if( child != size - (isHeap ? 0 : 1) && a[child] != null &&
         a[ child + 1 ].compareTo( a[ child ])<0)
            child++;
         if( a[child] != null && a[ child ].compareTo( tmp ) < 0 )
            a[ hole ] = a[ child ];
         else
            break;
      }
      a[ hole ] = tmp;
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
      int child;
      AnyType tmp = a[ hole ];
      
      for( ; leftChild(hole, isHeap) < size; hole = child )
      {
         child = leftChild(hole, isHeap);
         if( child != size - (isHeap ? 0 : 1) && a[child] != null &&
         a[ child + 1 ].compareTo( a[ child ])>0)
            child++;
         if( a[child] != null && a[ child ].compareTo( tmp ) > 0 )
            a[ hole ] = a[ child ];
         else
            break;
      }
      a[ hole ] = tmp;
   }

   public static <AnyType extends Comparable<? super AnyType>>
   void heapSort( AnyType [ ] a )
   {
      for( int i = a.length / 2 - 1; i >= 0; i-- ) /* buildHeap */
         percolateDownMaxHeap( a, i, a.length, false );
      for( int i = a.length - 1; i > 0; i-- )
      {
         swapReferences( a, 0, i ); /* deleteMax */
         percolateDownMaxHeap( a, 0, i, false );
      }
   }

   public static <AnyType extends Comparable<? super AnyType>>
   void heapSortReverse( AnyType [ ] a )
   {
      for( int i = a.length / 2 - 1; i >= 0; i-- ) /* buildHeap */
         percolateDownMinHeap( a, i, a.length, false );
      for( int i = a.length - 1; i > 0; i-- )
      {
         swapReferences( a, 0, i ); /* deleteMax */
         percolateDownMinHeap( a, 0, i, false );
      }
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

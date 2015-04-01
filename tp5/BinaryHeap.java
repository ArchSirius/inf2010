import java.util.Stack;
import java.util.Vector;

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
         percolateDownMinHeap(array, indice, currentSize + 1, true );
   }

   public void buildMaxHeap( )
   {
      for(int indice = currentSize / 2; indice > 0; indice--)
         percolateDownMaxHeap(array, indice, currentSize + 1, true );
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
      AnyType tmp = a[hole];
      
      // Le trou descend
      for(; leftChild(hole, isHeap) < size; hole = child)
      {
         child = leftChild(hole, isHeap);
         // Selection de l'enfant cible
         if(child != size - 1 &&
         a[child + 1].compareTo(a[child]) < 0)
            child++;
         // Percolation
         if(a[child].compareTo(tmp) < 0)
            a[hole] = a[child];
         else
            break;
      }
      a[hole] = tmp;
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
      AnyType tmp = a[hole];
      
      // Le trou descend
      for(; leftChild(hole, isHeap) < size; hole = child)
      {
         child = leftChild(hole, isHeap);
         // Selection de l'enfant cible
         if(child != size - 1 &&
         a[child + 1].compareTo(a[child]) > 0)
            child++;
         // Percolation
         if(a[child].compareTo(tmp) > 0)
            a[hole] = a[child];
         else
            break;
      }
      a[hole] = tmp;
   }

   public static <AnyType extends Comparable<? super AnyType>>
   void heapSort( AnyType [ ] a )
   {
      // Monceau de tri
      for(int i = a.length / 2 - 1; i >= 0; i--)
         percolateDownMaxHeap(a, i, a.length, false);
      // Tableau trie
      for(int i = a.length - 1; i > 0; i--)
      {
         swapReferences(a, 0, i);
         percolateDownMaxHeap(a, 0, i, false);
      }
   }

   public static <AnyType extends Comparable<? super AnyType>>
   void heapSortReverse( AnyType [ ] a )
   {
      // Monceau de tri
      for(int i = a.length / 2 - 1; i >= 0; i--)
         percolateDownMinHeap(a, i, a.length, false);
      // Tableau trie
      for(int i = a.length - 1; i > 0; i--)
      {
         swapReferences(a, 0, i);
         percolateDownMinHeap(a, 0, i, false);
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
      // 0. Integer est incompatible avec AnyType pour certaines opérations
      // 1. Conditions initiales
      // 2. Pile tampon // boucle sur la pile
      // 3. Définir un vecteur de position left/right pour l'affichage
      // 4. Ajouter l'index de la racine
      // 5. Ajouter les index de RightChild puis LeftChild (dans cet ordre)
      // 6. Pour l'affichage, verifier si left ou right
      // 7. Afficher!
      // 8. Aller prendre un bon thé...

      String outputString = "";  // Fancy
      int index = 1; // Initialement la racine
      Stack<Integer> pile = new Stack<Integer>();  // Tampon

      pile.push(index);

      while(!pile.empty())
      {
         // Cible suivante
         Vector<Boolean> gauche = new Vector<Boolean>();   // Fancy // isLeftChild
         index = pile.pop();
         
         // Si elle possede au moins un LeftChild
         if(2 * index <= currentSize)
         {
            pile.push(leftChild(index, true) + 1);   // pas de verification si rightChild existe car on affiche "null"
            pile.push(leftChild(index, true));
         }
         
         // Fancy
         // On remonte par ses parents jusqu'a la racine
         for(int i = index; i > 0; i /= 2)
         {
            if(i % 2 == 0)
               gauche.add(true);
               // Si l'indice est pair, il est leftChild
            else
               gauche.add(false);
               // Si l'indice est impair, il est rightChild (ou racine)
         }
         
         // Fancy
         // Haut en bas = gauche vers droite
         for(int i = gauche.size() - 1; i > 0; i--)
         {
             if(gauche.get(i))
                  // Branche leftChild
                  outputString += "|  ";
             else
                  // Branche rightChild
                  outputString += "   ";
         }
         
         // Fancy
         // Ajout de la valeur (Integer)
         if(index <= currentSize)
            outputString += "|__" + array[index] + "\n";
         else
            // rightChild inexistant
            outputString += "|__" + "null" + "\n";
      }

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

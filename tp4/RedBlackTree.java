import java.util.LinkedList;
import java.util.Queue;

public class RedBlackTree<T extends Comparable<? super T> > 
{
   private RBNode<T> root;  // Racine de l'arbre
   
   enum ChildType{ left, right }
   
   public RedBlackTree()
   { 
      root = null;
   }
   
   public void printFancyTree()
   {
      printFancyTree( root, "", ChildType.right);
   }
   
   private void printFancyTree( RBNode<T> t, String prefix, ChildType myChildType)
   {
      System.out.print( prefix + "|__"); // un | et trois _
      
      if( t != null )
      {
         boolean isLeaf = (t.isNil()) || ( t.leftChild.isNil() && t.rightChild.isNil() );
         
         System.out.println( t );
         String _prefix = prefix;
         
         if( myChildType == ChildType.left )
            _prefix += "|   "; // un | et trois espaces
         else
            _prefix += "   " ; // trois espaces
         
         if( !isLeaf )
         {
            printFancyTree( t.leftChild, _prefix, ChildType.left );
            printFancyTree( t.rightChild, _prefix, ChildType.right );
         }
      }
      else
         System.out.print("null\n");
   }
   
   public T find(int key)
   {
      return find(root, key);
   }
   
   private T find(RBNode<T> current, int key)
   {
      // À COMPLÉTER
      return null;
   }
   
   public void insert(T val)
   {
      insertNode( new RBNode<T>( val ) );
   }
   
   private void insertNode( RBNode<T> newNode )
   { 
      if (root == null)  // Si arbre vide
         root = newNode;
      else
      {
         RBNode<T> position = root; // On se place a la racine
         
         while( true ) // on insere le noeud (ABR standard)
         {
            int newKey = newNode.value.hashCode();
            int posKey = position.value.hashCode();
            
            if ( newKey < posKey ) 
            {
               if ( position.leftChild.isNil() ) 
               {
                  position.leftChild = newNode;
                  newNode.parent = position;
                  break;
               } 
                  else 
                  position = position.leftChild;
            } 
            else if ( newKey > posKey ) 
            {
               if ( position.rightChild.isNil() )
               {
                  position.rightChild = newNode;
                  newNode.parent = position;
                  break;
               }
               else 
                  position = position.rightChild;
            }
            else // pas de doublons
               return;
         }
      }
      
      insertionCases( newNode );
   }

   private void insertionCases( RBNode<T> X )
   {
      // A MODIFIER/COMPLÉTER
      insertionCase1( X );
   }
   
   private void insertionCase1 ( RBNode<T> X )
   {
 
       if(X == root)
           X.setToBlack();
           
      insertionCase2( X );
   }

   private void insertionCase2( RBNode<T> X )
   {
      if(!X.parent.isBlack())
        insertionCase3( X );
      else 
          return;
   }

   private void insertionCase3( RBNode<T> X )
   {
      if(X.parent.isRed() && X.uncle().isRed())
      {
          X.parent.setToBlack();
          X.uncle().setToBlack();
          X.grandParent().setToRed();
          
          insertionCases(X.grandParent());
      }
      insertionCase4( X );
   }

   private void insertionCase4( RBNode<T> X )
   {
       if(
      //if(X.parent.isRed()
      //&& X.uncle().isBlack()
      //&&(X.parent.leftChild == X
      //&& X.parent == X.grandParent().rightChild)
              )
          
      insertionCase5( X );
   }

   private void insertionCase5( RBNode<T> X )
   {
      // A MODIFIER/COMPLÉTER
      return; 
   }
   
   private void rotateLeft( RBNode<T> G )
   {
      // A MODIFIER/COMPLÉTER
      return; 
   }
   
   private void rotateRight( RBNode<T> G )
   {
      // A MODIFIER/COMPLÉTER
      return; 
   }

   public void printTreePreOrder()
   {
      if(root == null)
         System.out.println( "Empty tree" );
      else
      {
         System.out.print( "PreOrdre ( ");
         printTreePreOrder( root );
         System.out.println( " )");
      }
      return;
   }
   
   private void printTreePreOrder( RBNode<T> P )
   {
      // A MODIFIER/COMPLÉTER
      return; 
   }
   
   public void printTreePostOrder()
   {
      if(root == null)
         System.out.println( "Empty tree" );
      else
      {
         System.out.print( "PostOrdre ( ");
         printTreePostOrder( root );
         System.out.println( ")");
      }
      return;
   }
  
   private void printTreePostOrder( RBNode<T> P )
   {
      // A MODIFIER/COMPLÉTER
      return; 
   }
   
   public void printTreeLevelOrder()
   {
      if(root == null)
         System.out.println( "Empty tree" );
      else
      {
         System.out.print( "LevelOrdre ( ");
         
         Queue<RBNode<T>> q = new LinkedList<RBNode<T>>();
         
         q.add(root);
         
         //  À COMPLÉTER
         
         System.out.println( " )");
      }
      return;
   }
   
   private static class RBNode<T extends Comparable<? super T> > 
   {
      enum RB_COLOR { BLACK, RED }  // Couleur possible
      
      RBNode<T>  parent;      // Noeud parent
      RBNode<T>  leftChild;   // Feuille gauche
      RBNode<T>  rightChild;  // Feuille droite
      RB_COLOR   color;       // Couleur du noeud
      T          value;       // Valeur du noeud
      
      // Constructeur (NIL)   
      RBNode() { setToBlack(); }
      
      // Constructeur (feuille)   
      RBNode(T val)
      {
         setToRed();
         value = val;
         leftChild = new RBNode<T>();
         leftChild.parent = this;
         rightChild = new RBNode<T>();
         rightChild.parent = this;
      }
      
      RBNode<T> grandParent()
      {
         if(parent == null || parent.parent == null)
            return null;
         return parent.parent;
      }
      
      RBNode<T> uncle()
      {
         if (parent == null)
             return null;
         return parent.sibling();
      }
      
      RBNode<T> sibling()
      {
         if(parent == null)
             return null;
         if(parent.rightChild == this)
         {
             if(parent.leftChild == null)
                 return null;
             return parent.leftChild;
         }
         else
         {
             if(parent.leftChild == null)
                 return null;
             return parent.rightChild;
         }
      }
      
      public String toString()
      {
         return value + " (" + (color == RB_COLOR.BLACK ? "black" : "red") + ")"; 
      }
      
      boolean isBlack(){ return (color == RB_COLOR.BLACK); }
      boolean isRed(){ return (color == RB_COLOR.RED); }
      boolean isNil(){ return (leftChild == null) && (rightChild == null); }
      
      void setToBlack(){ color = RB_COLOR.BLACK; }
      void setToRed(){ color = RB_COLOR.RED; }
   }
}

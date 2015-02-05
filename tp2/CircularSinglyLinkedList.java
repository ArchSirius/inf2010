import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularSinglyLinkedList<Elem> implements Iterable<Elem>
{
   private int size = 0;  // La taille de la liste
   private Node last;  // Dernier element de la liste

   // Un noeud de la liste
   private class Node 
   {
      private Elem elem;
      private Node next;

      public Node(Elem elem, Node next) 
      {
         this.elem = elem;
         this.next = next;
      }

      public void setNext(Node next) 
      {
         this.next = next;
      }

      public Node getNext() 
      {
         return next;
      }

      public Elem getElem() 
      {
         return elem;
      }
   }

   // Liste vide?
   public boolean isEmpty() 
   { 
      return size == 0; 
   }

   // Taille de la liste
   public int size() 
   { 
      return size; 
   }

   //Retourne l'element a l'index "index" s'il existe
   public Elem get(int index) 
   {
      if (index >= size || index < 0) 
         throw new IndexOutOfBoundsException();
         
      //A completer
      Node node = last;      // node[0]
      for(int i = 0; i < index; i++){
         node = node.getNext();        // node[i]
      }
      return node.getElem();
   }

   //Creation du premier element de la liste
   private void init(Elem item) 
   {
      //A completer
      last = new Node(item, last);
      size = 1;
   }

   //Ajout d'un element a la fin de la liste
   public void append(Elem item) 
   {
      //La liste est vide
      if (size == 0) 
         init(item);
      else 
      { 
         //A completer
         Node newNode = new Node(item, last.getNext());
         last.setNext(newNode);
         last = newNode;
         size++;
      }
   }

   //Ajoute un element a la position "index" dans la liste
   public void insert(Elem item, int index) 
   {
      if (index > size || index < 0) 
         throw new IndexOutOfBoundsException();
      

      //La liste est vide
      if (size == 0) 
         init(item);
      //On insere a la fin
      else if(index == size)
         append(item);
      //La liste a au moins un element et l'insertion n'est pas a la fin
      else 
      {
         //A completer
         Node node = last;      // node[0]
         for(int i = 0; i < index; i++) {
            node = node.getNext();        // node[i - 1]
         }
         Node newNode = new Node(item, node.getNext());
         node.setNext(newNode);
         size++;
      }
   }

   //Elimine un element a une position donnee dans la liste
   public void remove(int index) 
   {
      if (index >= size || index < 0) 
         throw new IndexOutOfBoundsException();
      
      //A completer
      Node node = last;      // node[0]
      for(int i = 0; i < index - 1; i++) {
         node = node.getNext();        // node[i - 1]
      }
      node.setNext(node.getNext().getNext());
   }

   // Methode requise par l'interface Iterable
   public Iterator<Elem> iterator() 
   { 
      return new CircularSinglyLinkedListIterator(); 
   }

   // L'iterateur retourne par la methode iterator()
   private class CircularSinglyLinkedListIterator implements Iterator<Elem> 
   {
      private Node currentNode = last;
      private int i = 0;

      public boolean hasNext() 
      { 
         return i < size;
      }

      public void remove() 
      { 
         throw new UnsupportedOperationException();  
      }

      public Elem next() 
      {
         if (!hasNext()) 
            throw new NoSuchElementException();
         
         currentNode = currentNode.getNext();
         i++;
         return currentNode.getElem();
      }
   }



   /* ******************************
      Expected output:

      Forward list: 
      0 1 2 3 4 5 6 7 8 9 
      
      Reverse list: 
      9 8 7 6 5 4 3 2 1 0 
      
      Mod3 list: 
      9 6 7 8 3 4 5 0 1 2 
      
      Forward list after removal: 
      9 
      
   ********************************* */

   public static void main(String[] args) 
   {
      CircularSinglyLinkedList<Integer> circularListForward = new CircularSinglyLinkedList<Integer>();
      CircularSinglyLinkedList<Integer> circularListReverse = new CircularSinglyLinkedList<Integer>();
      CircularSinglyLinkedList<Integer> circularListMod3 = new CircularSinglyLinkedList<Integer>();

      for (int i=0; i<10; i++) 
      {
         circularListForward.append(i);
         circularListReverse.insert(i,0);
         circularListMod3.insert(i,i%3);
      }
      
      System.out.println("Forward list: ");
      
      for (Integer i : circularListForward)
         System.out.print(i+" ");
      
      System.out.println("\n");

      System.out.println("Reverse list: ");
      
      for (Integer i : circularListReverse)
         System.out.print(i+" ");
      
      System.out.println("\n");

      System.out.println("Mod3 list: ");
      
      for (Integer i : circularListMod3)
         System.out.print(i+" ");
      
      System.out.println("\n");

      for (int i=0; i<9; i++)
         circularListForward.remove(0);
      
      System.out.println("Forward list after removal: ");
      
      for (Integer i : circularListForward)
         System.out.print(i+" ");      
      
      System.out.println("\n");
   }
}
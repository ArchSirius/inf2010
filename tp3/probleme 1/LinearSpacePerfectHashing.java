import java.util.Random;
import java.util.ArrayList;

public class LinearSpacePerfectHashing<AnyType>
{
   static int p = 46337;
   
   QuadraticSpacePerfectHashing<AnyType>[] data;
   int a, b;
   
   LinearSpacePerfectHashing()
   {
      a=b=0; data = null;
   }
   
   LinearSpacePerfectHashing(ArrayList<AnyType> array)
   {
      AllocateMemory(array);
   }
   
   public void SetArray(ArrayList<AnyType> array)
   {
      AllocateMemory(array);
   }
      
   @SuppressWarnings("unchecked")
   private void AllocateMemory(ArrayList<AnyType> array)
   {
      Random generator = new Random( System.nanoTime() );
      
      if(array == null || array.size() == 0)
      {
         // A completer
         data = null;
         return;
      }
      if(array.size() == 1)
      {
         a = b = 0;
         
         // A completer
         data = (QuadraticSpacePerfectHashing<AnyType>[]) new QuadraticSpacePerfectHashing<?>[1];
         ArrayList<AnyType> liste = new ArrayList<AnyType>();
         liste.add(array.get(0));
         data[0].SetArray(liste);
         return;
      }
      
      // A completer
      data = (QuadraticSpacePerfectHashing<AnyType>[]) new QuadraticSpacePerfectHashing<?>[array.size()];
      a = generator.nextInt() % p;
      b = generator.nextInt() % p;
      // Insère individuellement chaque élément
      for(AnyType item : array){
         // Hashage linéaire
         int index = ((a * item.hashCode() + b ) % p ) % array.size();
         while(index < 0){
            index += array.size();
         }

         // Hashage quadratique
         // liste temporaire
         ArrayList<AnyType> liste = new ArrayList<AnyType>();
         if(data[index] != null){
         // On copie tous les éléments non-nuls déjà présents
            for(AnyType quaditem : data[index].items){
               if(quaditem != null){
                  liste.add(quaditem);
               }
            }
            // ajout de l'élément en cours
            liste.add(item);
            // re-hashage quadratique
            data[index].SetArray(liste);
         } else{
            // liste de 1 éléments à ajouter au hash quadratique
            liste.add(item);
            data[index] = new QuadraticSpacePerfectHashing<AnyType>(liste);
         }
      }
   }
   
   public int Size()
   {
      if( data == null ) return 0;
      
      int size = 0;
      for(int i=0; i<data.length; ++i)
      {
         size += (data[i] == null ? 1 : data[i].Size());
      }
      return size;
   }
   
   public boolean contains(AnyType x )
   {
      if( Size() == 0 ) return false;
      
      int m = data.length;
      
      int index = ( ( a*x.hashCode() + b ) % p ) % m;
      
      index = ( index < 0 ? index + m : index );
      
      return ((data[index] != null) && (data[index].contains(x)));
   }
}

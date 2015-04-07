import java.util.*;

// Classe a remplir pour realiser le TP en utilisant la classe DisjointSet fournie

public class Maze{

	public Maze(int w, int h, int seed){
		width = w;
		height = h;

		LASTROOM = width*height-1;

		// Initialisation du labyrinthe avec tous les murs
		maze = new Vector<Wall>();
		for(int i = 0; i < height; ++i){
			for(int j = 0; j < width; ++j){
				if(i > 0){
					maze.add(new Wall(j+i*height, j+(i-1)*height));
				}
				if(j > 0){
					maze.add(new Wall(j+i*height, j-1+i*height));
				}
			}
		}

		// Creation du graphe de la topologie du labyrinthe
		graph = new Vector<Room>();
		for(int i = 0; i < height*width; ++i)
			graph.add(new Room(i));
		
		// On trie les murs de maniere aleatoire
		generator = new Random(seed);
		for(int i = 0; i < maze.size(); ++i){
			// A completer
		}

		// Initialisation des structures annexes
		ds = new DisjointSet(width*height);
		path = new Vector<Integer>();
	}

	public void generate(){
		// A completer
	}

	public void solve(){
		// A completer
	}

	public class Wall{
		
		public Wall(int r1, int r2){
			room1 = r1;
			room2 = r2;
		}

		public int room1;
		public int room2;
	}

	public class Room{
		
		public Room(int i){
			id = i;
			distance = -1;
			paths = new Vector<Integer>();
		}

		public int id;
		Vector<Integer> paths;
		int distance;		

	}

	public Vector<Wall> maze;
	public Vector<Room> graph;
	public Vector<Integer> path;

	public int LASTROOM;
	public int height;
	public int width;
	public Random generator;
	public DisjointSet ds;
}


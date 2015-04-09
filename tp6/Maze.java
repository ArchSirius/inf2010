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
			int rnd = generator.nextInt() % maze.size();
			while(rnd <0)
				rnd += maze.size();
			swapWall(maze, i, rnd);
		}

		// Initialisation des structures annexes
		ds = new DisjointSet(width*height);
		path = new Vector<Integer>();
	}

	private void swapWall(Vector<Wall> maze, int i, int j){
		Wall tmp = maze.get(i);
		maze.set(i, maze.get(j));
		maze.set(j, tmp);
	}

	public void generate(){
		// Pour chaque mur permuté
		for(int i = 0; i < maze.size() - 1; i++){
			Wall wall = maze.get(i);
			// Si ses deux pièces ne sont pas reliées
			if(ds.find(wall.room1) != ds.find(wall.room2)){
				// Retirer le mur
				maze.remove(wall);
				Room room1 = graph.get(wall.room1);
				Room room2 = graph.get(wall.room2);
				// Relier
				ds.union(room1.id, room2.id);
				room1.paths.add(room2.id);
				room2.paths.add(room1.id);
				// Rester au même index suite à un retrait
				i--;
			}
		}
	}

	public void solve(){
		// Départ
		Room root = graph.get(0);
		solve(root, root);
	}

	private Boolean solve(Room caller, Room room){
		// Est-ce l'arrivée?
		if(room.id == LASTROOM){
			path.add(room.id);
			return true;
		}
		// Vérifier les pièces adjacentes...
		for(int adj : room.paths){
			// ... mais pas celle qui vient appèle celle-ci
			if(adj != caller.id){
				// Est que l'arrivée est trouvée?
				if(solve(room, graph.get(adj))){
					path.add(room.id);
					return true;
				}
				// Sinon, vérifier un autre chemin
			}
		}
		// Si aucune n'est reliée à l'arrivée, remonter
		return false;
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


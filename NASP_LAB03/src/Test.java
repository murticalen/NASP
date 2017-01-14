import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

public class Test {
	
	public static void main(String[] args) throws Exception {
		Graph g = new Graph("cities");
		ShortestPath d = new Dijkstra(g);
		ShortestPath wfi = new WarFloydIng(g);
		System.out.println("Dijkstra equivalent WFI : "+test(g, new ShortestPath[]{d, wfi}));
		demonstration(g, wfi);
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while(true){
			System.out.println("If you want to stop fining paths, input: #");
			System.out.println("Else enter city 1: ");
			String c1 = reader.readLine();
			if(c1.equals("#")){
				break;
			}
			System.out.println("Enter city 2: ");
			String c2 = reader.readLine();
			if(!g.reverseNodes.containsKey(c1) || !g.reverseNodes.containsKey(c2)){
				System.out.println("Graph doesn't contain these cities.");
				continue;
			}
			System.out.println(g.shortestPathQuery(c1, c2, wfi));
		}
		reader.close();
	}
	
	public static Boolean test(final Graph g, final ShortestPath ... algs){
		Boolean equals = true;
		ArrayList<LinkedList<Path>> paths = new ArrayList<>();
		for(ShortestPath alg : algs){
			LinkedList<Path> ps = new LinkedList<>();
			for(String c1 : g.reverseNodes.keySet()){
				for(String c2 : g.reverseNodes.keySet()){
					ps.add(g.shortestPathQuery(c1, c2, alg));
				}
			}
/*			ps.add(g.shortestPathQuery("Cape_Town", "Moscow", alg));
			ps.add(g.shortestPathQuery("Moscow", "Mexico_City", alg));
			ps.add(g.shortestPathQuery("Doha", "Toronto", alg));
			ps.add(g.shortestPathQuery("London", "Zagreb", alg));
			ps.add(g.shortestPathQuery("Toronto", "Cape_Town", alg));
			ps.add(g.shortestPathQuery("Lisbon", "Toronto", alg));
			ps.add(g.shortestPathQuery("Boston", "Tunis", alg));
			ps.add(g.shortestPathQuery("Zagreb", "Rio", alg));
			ps.add(g.shortestPathQuery("Buenos_Aries", "Moscow", alg));
			ps.add(g.shortestPathQuery("Cape_Town", "Boston", alg));
			ps.add(g.shortestPathQuery("Santiago", "Oslo", alg));
			ps.add(g.shortestPathQuery("Havanna", "Oslo", alg));
			ps.add(g.shortestPathQuery("Zagreb", "Mexico_City", alg));
			ps.add(g.shortestPathQuery("Houston", "Miami", alg));*/
			paths.add(ps);
		}
		for(int i = 0; i < paths.size(); i++){
			for(int j = 0; j < paths.size(); j++){
				if(i != j){
					LinkedList<Path> ps1 = paths.get(i);
					LinkedList<Path> ps2 = paths.get(j);
					equals = equals && ps1.equals(ps2);
				}
			}
		}
		return equals;
	}
	
	public static void demonstration(Graph g, ShortestPath alg){
		System.out.println("Demonstration cities: ");
		System.out.println(g.shortestPathQuery("Cape_Town", "Moscow", alg));
		System.out.println(g.shortestPathQuery("Moscow", "Mexico_City", alg));
		System.out.println(g.shortestPathQuery("Doha", "Toronto", alg));
		System.out.println(g.shortestPathQuery("London", "Zagreb", alg));
		System.out.println(g.shortestPathQuery("Toronto", "Cape_Town", alg));
		System.out.println(g.shortestPathQuery("Lisbon", "Toronto", alg));
		System.out.println(g.shortestPathQuery("Boston", "Tunis", alg));
		System.out.println(g.shortestPathQuery("Zagreb", "Rio", alg));
		System.out.println(g.shortestPathQuery("Buenos_Aries", "Moscow", alg));
		System.out.println(g.shortestPathQuery("Cape_Town", "Boston", alg));
		System.out.println(g.shortestPathQuery("Santiago", "Oslo", alg));
		System.out.println(g.shortestPathQuery("Havanna", "Oslo", alg));
		System.out.println(g.shortestPathQuery("Zagreb", "Mexico_City", alg));
		System.out.println(g.shortestPathQuery("Houston", "Miami", alg));
	}
	
}

public class Test {
	
	public static void main(String[] args) throws Exception {
		Graph g = new Graph("cities");
		ShortestPath d;
		//d = new Dijkstra(g);
		d = new WarFloydIng(g);

		System.out.println(g.shortestPathQuery("Cape_Town", "Moscow", d));
		System.out.println(g.shortestPathQuery("Moscow", "Mexico_City", d));
		System.out.println(g.shortestPathQuery("Doha", "Toronto", d));
		System.out.println(g.shortestPathQuery("London", "Zagreb", d));
		System.out.println(g.shortestPathQuery("Toronto", "Cape_Town", d));
		System.out.println(g.shortestPathQuery("Lisbon", "Toronto", d));
		System.out.println(g.shortestPathQuery("Boston", "Tunis", d));
		System.out.println(g.shortestPathQuery("Zagreb", "Rio", d));
		System.out.println(g.shortestPathQuery("Buenos_Aries", "Moscow", d));
		System.out.println(g.shortestPathQuery("Cape_Town", "Boston", d));
		System.out.println(g.shortestPathQuery("Santiago", "Oslo", d));
		System.out.println(g.shortestPathQuery("Havanna", "Oslo", d));
		System.out.println(g.shortestPathQuery("Zagreb", "Mexico_City", d));
		System.out.println(g.shortestPathQuery("Houston", "Miami", d));
	}
	
}

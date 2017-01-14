import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Set;

public class Dijkstra implements ShortestPath{
	
	public Graph g;
	PriorityQueue<DijkstraNode> q;
	Set<Integer> done;
	
	public Dijkstra(Graph g){
		this.g = g;
	}
	
	public static class DijkstraNode implements Comparable<DijkstraNode>{
		
		public Integer node;
		public DijkstraNode pred;
		public Integer len;
		
		public DijkstraNode(Integer node, DijkstraNode pred, Integer len) {
			this.node = node;
			this.pred = pred;
			this.len = len;
		}

		@Override
		public int compareTo(DijkstraNode o) {
			return len.compareTo(o.len);
		}
	}

	@Override
	public Path findShortestPath(Integer begin, Integer end) {
		if(begin.equals(end)){
			List<String> list = new LinkedList<>();
			list.add(g.nodes.get(begin));
			return new Path(list, 0);
		}
		q = new PriorityQueue<>();
		done = new HashSet<>();
		addSuccs(new DijkstraNode(begin, null, 0));
		done.add(begin);
		while(!q.isEmpty()){
			DijkstraNode node = q.poll();
			if(node.node == end){
				return backtrack(node);
			}
			else{
				done.add(node.node);
				addSuccs(node);
			}
		}
		return null;
	}
	
	private Path backtrack(DijkstraNode node) {
		List<String> list = new LinkedList<>();
		DijkstraNode n = node;
		while(n != null){
			list.add(0, g.nodes.get(n.node));
			n = n.pred;
		}
		return new Path(list, node.len);
	}

	private void addSuccs(DijkstraNode d){
		for(Entry<Integer, Integer> n : g.getSucc(d.node)){
			Integer node = n.getKey();
			if(!done.contains(node)){
				q.add(new DijkstraNode(node, d, d.len + n.getValue()));
			}
		}
	}	
}

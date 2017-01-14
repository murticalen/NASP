import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

public class WarFloydIng implements ShortestPath {
	
	//public Integer[][] adj;
	public Graph g;
	public int n;
	public Integer[][] D;
	public Integer[][] Path;
	public Boolean done;
	
	public WarFloydIng(Graph g) {
		this.g = g;
		this.n = g.nodes.size();
		//this.adj = new Integer[n][n];
		this.D = new Integer[n][n];
		this.Path = new Integer[n][n];
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				//adj[i][j] = null;
				D[i][j] = Integer.MAX_VALUE/2-1;
				//D[i][j] = null;
				Path[i][j] = null;
			}
		}
		for(Integer node : g.nodes.keySet()){
			for(Entry<Integer, Integer> next : g.getSucc(node)){
				//adj[node][next.getKey()] = next.getValue();
				D[node][next.getKey()] = next.getValue();
				Path[node][next.getKey()] = node;
			}
		}
		this.done = false;
	}
	
	@Override
	public Path findShortestPath(Integer begin, Integer end) {
		if(done == false){
			for(int k = 0; k < n; k++){
				D[k][k] = 0;
				Path[k][k] = k;
				for(int i = 0; i < n; i++){
					for(int j = 0; j < n; j++){
						if(D[i][k] + D[k][j] < D[i][j]){
							D[i][j] = D[i][k] + D[k][j];
							Path[i][j] = Path[k][j];
						}
					}
				}
			}
			done = true;
		}
		return backtrack(begin, end);
	}
	
	private Path backtrack(Integer begin, Integer end){
		List<String> list = new LinkedList<>();
		if(Path[begin][end] == null){
			return null;
		}
		int node = end;
		while(true){
			list.add(0, g.nodes.get(node));
			node = Path[begin][node];
			if(node == begin){
				list.add(0, g.nodes.get(node));
				break;
			}
		}
		return new Path(list, D[begin][end]);
	}
}

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
/**
 * 
 * @author Murta
 *
 */
public class Graph {
	
	public Map<Integer, String> nodes = new HashMap<Integer, String>();
	public Map<String, Integer> reverseNodes = new HashMap<String, Integer>();
	public Map<Integer, List<Entry<Integer, Integer>>> succ = new HashMap<>();

	public Graph(String string) throws Exception {
		File file = new File(System.getProperty("user.dir")+"\\"+string+".txt");
		BufferedReader reader = new BufferedReader
				(new InputStreamReader(new FileInputStream(file)));
		String line = reader.readLine();
		int k = 0;
		for(String s : line.split(" ")){
			nodes.put(k, s);
			reverseNodes.put(s, k);
			k++;
		}
		while((line = reader.readLine())!=null){
			String[] node = line.split(" ");
			List<Entry<Integer, Integer>> list = new LinkedList<>();
			for(int i = 1; i < node.length; i++){
				String[] n = node[i].split(",");
				list.add(new AbstractMap.SimpleEntry<Integer, Integer>(this.reverseNodes.get(n[0]), Integer.parseInt(n[1])));
			}
			succ.put(this.reverseNodes.get(node[0]), list);
		}
		reader.close();
	}

	public String nameYourNode(int node){
		return nodes.get(node);
	}
	
	public List<Entry<Integer, Integer>> getSucc(Integer node){
		return succ.get(node);
	}
	
	public Path shortestPathQuery(String begin, String end, ShortestPath pathFinder){
		return pathFinder.findShortestPath(reverseNodes.get(begin), reverseNodes.get(end));
	}
	
}

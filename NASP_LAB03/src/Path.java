import java.util.LinkedList;
import java.util.List;

public class Path {
	
	public List<String> path = new LinkedList<>();
	public int len = -1;
	
	public Path(List<String> path, int len) {
		this.path = path;
		this.len = len;
	}
	
	@Override
	public String toString() {
		return new String(path.toString() + " Length: "+len);
	}
}

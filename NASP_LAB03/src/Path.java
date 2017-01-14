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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + len;
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Path other = (Path) obj;
		if (len != other.len)
			return false;
		/*if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;*/
		return true;
	}
	
	
}

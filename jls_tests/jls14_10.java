/*class Graph {
	int edges[][];
	public Graph(int[][] edges) { this.edges = edges; }
	public Graph loseEdges(int i, int j) {
		int n = edges.length;
		int[][] newedges = new int[n][];
		for (int k = 0; k < n; ++k) {
			edgelist: {
				int z;
				search: {
					if (k == i) {
						for (z = 0; z < edges[k].length; ++z)
							if (edges[k][z] == j)
								break search;
					} else if (k == j) {
						for (z = 0; z < edges[k].length; ++z)
							if (edges[k][z] == i)
								break search;
					}
					// No edge to be deleted; share this list.
					newedges[k] = edges[k];
					break edgelist;
				} //search
				// Copy the list, omitting the edge at position z.
				int m = edges[k].length - 1;
				int ne[] = new int[m];
				System.arraycopy(edges[k], 0, ne, 0, z);
				System.arraycopy(edges[k], z+1, ne, z, m-z);
				newedges[k] = ne;
			} //edgelist
		}
		return new Graph(newedges);
	}
}
*/

class G { void e() { int[][] newedges = new int[n][]; } }

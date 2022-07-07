package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.IntStream;

public class Algorithms {
	static int heu_n = 2;
	private static Map<String, Integer> cityMap = new HashMap<String, Integer>();;
	private static ArrayList<Node>[] g = new ArrayList[20];
	private static int[][] heuristicC = new int[20][20];
	static Map<Integer, String> cityMap2 = new HashMap<Integer, String>();
	static int[][] graph = new int[20][20];
	
	
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("aa");
		for (int a = 0; a < 20; a++) {
			for (int b = 0; b < 20; b++) {
				graph[a][b] = 0;
			}
		}
		getCity();
		readNodes();
		DFS("Akko", "Tulkarm");
		DFS("Gaza", "Nahariyya");
		//DFS("Jenin", "Tulkarm");
		//BFS("Akko", "Tulkarm");
		String s = "{";

		for (int a = 0; a < 20; a++) {
			for (int b = 0; b < 20; b++) {
				s += "," + graph[a][b];
			}
			s += "\n";
		}
		// System.out.println(s);
		ArrayList<Object> out3 = aStarAlg("Nablus", "Yafo");
		//System.out.println("from "+source+" to "+destination+" using "+Algorithm+" we got the results:\n");            output.appendText("\n");
		            System.out.println("\n");
		            System.out.println("path :"+out3.get(1));
		            //output.appendText("\n");
		            //output.appendText("\n");
		            //System.out.println("xoxoxoxoxooxoxoxoxoxoxoxoxooxoxoxooxoxoxoxooxoxoxooxoxoxoxoxooxoxoxoxooxoxoxooxoxox");
		            System.out.println("expanded nodes:"+out3.get(2));
		            //output.appendText("\n");
		            //output.appendText("\n");
		            System.out.println("cost:"+out3.get(0)); 

	}*/
	
	
	public static int maxDA(int array[][], int rowN) {
		int array2[] = new int[20];
		for (int i = 0; i < 20; i++) {
			array2[i] = array[rowN][i];
		}
		return Arrays.stream(array2).max().getAsInt();
	}



	public static void getCity() {
		String[] Arr = new String[21];
		try {
			File myObj = new File("City.txt");
			Scanner myReader = new Scanner(myObj);
			int counter = 0;
			
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				//System.out.println(data+" ");
				Arr = data.split(" ", 21);
				String city = Arr[0];
				
				for(int i=0;i<20;i++) {
					//String temp = Arr[i];
					heuristicC[counter][i]=Integer.decode(Arr[i+1]);
					//System.out.print(heuristicC[counter][i-1] + " ");
				}
				
			//	System.out.println();
				cityMap.put(city, counter);
				cityMap2.put(counter++, city);
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void readNodes() {
		//g = new ArrayList[20]; //defiene an array list of 20 elements 
		for (int i = 0; i < 20; i++) {
			g[i] = new ArrayList<Node>(); //each element is an arraylist
		}
		
		String[] Arr = new String[2];
		try {
		File myObj = new File("Nodes.txt");
		Scanner myReader = new Scanner(myObj);
		
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				Arr = data.split(" ", 4);
				int walkCost = (Integer.parseInt(Arr[2]) + heuristicC[cityMap.get(Arr[1])][cityMap.get(Arr[0])])/2;
				//System.out.println(walkCost);
				Node a = new Node(cityMap.get(Arr[1]), Integer.parseInt(Arr[2]),walkCost, Arr[0], Arr[1]);
				Node b = new Node(cityMap.get(Arr[0]), Integer.parseInt(Arr[2]),walkCost, Arr[1], Arr[0]);
				int max1 = maxDA(graph, cityMap.get(Arr[0]));
				int max2 = maxDA(graph, cityMap.get(Arr[1]));
				graph[cityMap.get(Arr[0])][cityMap.get(Arr[1])] = max1 + 1;
				graph[cityMap.get(Arr[1])][cityMap.get(Arr[0])] = max2 + 1;

				g[cityMap.get(Arr[0])].add(a); //add every node connected to each other to an array list, the index is the city name
				g[cityMap.get(Arr[1])].add(b);
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
//DFS	
	public static ArrayList<String> DFS(String source, String destination) {
		int nodes[] = new int[20];
		int n = cityMap.get(source);
		int n2 = cityMap.get(destination);
		// System.out.println(cityMap2.get(1));
		Stack<Integer> stack = new Stack<>();

		stack.push(n); // push root node to the stack
		int a = 0;
		int visN = 0; 
		int flag =0;
		int paren[] = new int[20];
		String Fringe="";
		while (!stack.empty()) {
			n = stack.peek(); // extract the top element of the stack
			stack.pop(); // remove the top element from the stack
			if (nodes[n] == 0) {
				//System.out.print("[City: " + cityMap2.get(n) + " " + n + "]>>");
				Fringe += "[City: " + cityMap2.get(n)+"]>>";
				nodes[n] = visN;
				paren[visN] = n;
				// System.out.println("VisN: " + nodes[n] + " pareN: " + paren[visN]);
				
				visN++;
				if (flag == 0) {
                    nodes[n]=-1;
                    flag++;
                }
				if (n == n2) {
					break;
				}
			}
			// int itemp = 0, str1 = 0;
			int max = maxDA(graph, n);
			// String str = "";
			int array[] = new int[20];
			int parents[] = new int[20];
			for (int i = 0; i < 20; i++) // iterate through the linked list and then propagate to the next few nodes
			{
				if (graph[n][i] != 0) {
					a = graph[n][i];
					array[a] = i;
					// System.out.print("[i: " + i + " a: " + a + " max" + max + " ]");
					// str += Integer.toString(i) + " ";
					// System.out.println("str before " + str);
				}
			}
			for (int i = 1; i <= max; i++)
				if (nodes[array[i]] == 0) // only push those nodes to the stack which aren't in it already
				{
					// System.out.print("{i: " + i + " array: "+array[i]+" max"+ max+" }");
					stack.push(array[i]); // push the top element to the stack
				}
		}
		int[] paren2 = IntStream.range(0, 20).map(i -> paren[20 - i - 1]).toArray();
		int temp = cityMap.get(destination);
		String path = destination;
		for (int i : paren2) {
			if (i == temp && temp != cityMap.get(source)) {
				temp = path(graph, i, nodes);
				path = cityMap2.get(temp) + " >" + path;
				//System.out.println("TEMP: " + temp);
			}
		}
		//System.out.println("\n"+path);
		
		StringBuilder pathS = new StringBuilder();
        StringBuilder FringeS = new StringBuilder();
        pathS.append(path);
        FringeS.append(Fringe);
       // System.out.println("\n" + Fringe);
       // System.out.println("\n" + pathS);
        ArrayList<String> output = new ArrayList<>();
        output.add(pathS.toString());
        output.add(Fringe.toString());
        return output;
	}

//BFS
	public static ArrayList<String> BFS(String source, String destination) {
		int nodes[] = new int[20];
		int n = cityMap.get(source);
		int n2 = cityMap.get(destination);
		// System.out.println(cityMap2.get(1));
		Queue<Integer> queue = new LinkedList<>();

		queue.add(n); // push root node to the stack
		int a = 0;
		int visN = 0; 
		int flag =0;
		int paren[] = new int[20];
		String Fringe="";
		while (!queue.isEmpty()) {
			n = queue.peek(); // extract the top element of the stack
			queue.poll(); // remove the top element from the stack
			if (nodes[n] == 0) {
				//System.out.print("[City: " + cityMap2.get(n) + " " + n + "]>>");
				 Fringe += "[City: " + cityMap2.get(n)+"]>>";
				nodes[n] = visN;
				paren[visN] = n;
				// System.out.println("VisN: " + nodes[n] + " pareN: " + paren[visN]);
				visN++;
				if (flag == 0) {
                    nodes[n]=-1;
                    flag++;
                }
				if (n == n2) {
					break;
				}
			}
			// int itemp = 0, str1 = 0;
			int max = maxDA(graph, n);
			// String str = "";
			int array[] = new int[20];
			int parents[] = new int[20];
			for (int i = 0; i < 20; i++) // iterate through the linked list and then propagate to the next few nodes
			{
				if (graph[n][i] != 0) {
					a = graph[n][i];
					array[a] = i;
					// System.out.print("[i: " + i + " a: " + a + " max" + max + " ]");
					// str += Integer.toString(i) + " ";
					// System.out.println("str before " + str);
				}
			}
			for (int i = 1; i <= max; i++)
				if (nodes[array[i]] == 0) // only push those nodes to the stack which aren't in it already
				{
					// System.out.print("{i: " + i + " array: "+array[i]+" max"+ max+" }");
					queue.add(array[i]); // push the top element to the stack
				}
		}
		int[] paren2 = IntStream.range(0, 20).map(i -> paren[20 - i - 1]).toArray();
		int temp = cityMap.get(destination);
		String path = destination;
		for (int i : paren2) {
			if (i == temp && temp != cityMap.get(source)) {
				temp = path(graph, i, nodes);
				path = cityMap2.get(temp) + " >" + path;
				//System.out.println("TEMP: " + temp);
			}
		}
		
		StringBuilder pathS = new StringBuilder();
        StringBuilder FringeS = new StringBuilder();
        pathS.append(path);
        FringeS.append(Fringe);
       // System.out.println("\n" + Fringe);
       // System.out.println("\n" + pathS);
        
        ArrayList<String> output = new ArrayList<>();
        output.add(pathS.toString());
        output.add(Fringe.toString());
        return output;
        
		//System.out.println("\n"+path);
	}
	
	
	static int path(int graph[][], final int n, final int visit[]) {
		int temp = 0, temp1 = 0;
		for (int i = 0; i < 20; i++) {
			if (graph[n][i] != 0) {
				// System.out.println("{visit[i]: "+visit[i]+"| graph[ni]: "+graph[n][i]+"| I:
				// "+i+"}");
				if (visit[i] != 0) {
					//System.out.println("{visit[i]: " + visit[i] + "| graph[ni]: " + graph[n][i] + "| I: " + i + "}");
					if (visit[i] < temp1 || temp1 == 0) {
						temp = i;
						temp1 = visit[i];
						// System.out.println(temp);
					}
				}
			}
		}
		return temp;
	}

	// return string of path.
	public static String retPath(Node des) {
		Stack<Node> p= new Stack<>();
		while (des != null) {
			p.push(des);
			des = des.getParent();
		}
		StringBuilder s = new StringBuilder();
		while (!p.isEmpty()) {
			s.append(p.pop().getSourceN());
			if (!p.isEmpty()) {
				s.append(" ");
			}
		}
		return s.toString();
	}

	// A* algorithm with heuristic
	public static ArrayList<Object> aStarAlg(String source, String des) {
		
		Map<String, Integer> cost = new HashMap<>();
		for (String x : cityMap.keySet()) {
			cost.put(x, Integer.MAX_VALUE);
		}

		int h = 0;
		h = heuristicC[cityMap.get(des)][cityMap.get(source)];
		
		PriorityQueue<Node> notVis = new PriorityQueue<Node>((Node x1, Node x2) -> (x1.getF() - x2.getF()));
		PriorityQueue<Node> temp1 = new PriorityQueue<Node>((Node x1, Node x2) -> (x1.getF() - x2.getF()));
		PriorityQueue<Node> vis = new PriorityQueue<Node>((Node x1, Node x2) -> (x1.getF() - x2.getF()));
		PriorityQueue<Node> temp2 = new PriorityQueue<Node>((Node x1, Node x2) -> (x1.getF() - x2.getF()));

		cost.put(source, 0);
		Node first = new Node(source, null, 0);
		first.setF(0 + h);
		notVis.add(first);
		Node obj = null;

		ArrayList<Object> output = new ArrayList<>();
		ArrayList<String> fringe = new ArrayList<>();

		while (!notVis.isEmpty()) {
			Node up = notVis.peek();
			if ((up.getSourceN()).equals(des)) {
				output.add(up.getCost());
				obj = up;
				break;
			}

			int x = cityMap.get(up.getSourceN());
			fringe.add(up.getSourceN());
			
			int Hv;
			for (int j = 0; j < g[x].size(); j++) {
				Hv = heuristicC[cityMap.get(des)][cityMap.get(g[x].get(j).getDesN())];
				String to = g[x].get(j).getDesN();
				Node Node1 = new Node(to, up); // name, parent

				int curCost = g[x].get(j).getCost() + up.getCost();

				temp2.clear();
				temp1.clear();
				boolean t1 = false;
				boolean t2 = false;
				while (!notVis.isEmpty()) {
					Node temp = notVis.poll();
					if (temp.getSourceN().equals(Node1.getSourceN())) {
						t1 = true;
					}
					temp1.add(temp);
				}
				while (!vis.isEmpty()) {
					Node temp = vis.poll();
					if (temp.getSourceN().equals(Node1.getSourceN())) {
						t2 = true;
					}
					temp2.add(temp);
				}
				while (!temp1.isEmpty()) {
					notVis.add(temp1.poll());
				}
				while (!temp2.isEmpty()) {
					vis.add(temp2.poll());
				}

				if (t1 == false && t2 == false) {
					Node1.setCost(curCost);
					Node1.setF(Node1.getCost() + Hv );
					notVis.add(Node1);
				} else if (curCost < cost.get(to)) {
					Node1.setCost(curCost);
					Node1.setF(Node1.getCost() + Hv );
					cost.put(to, curCost);
					if (t1 == true) {
						vis.remove(Node1);
						notVis.add(Node1);
					}
				}
			}
			notVis.remove(up);
			vis.add(up);
		}
		
		output.add(retPath(obj));
		output.add(fringe);
		return output;
	}
	
	// A* algorithm with heuristic H
	public static ArrayList<Object> aStarAlgH(String source, String des) {
		
		Map<String, Integer> cost = new HashMap<>();
		for (String x : cityMap.keySet()) {
			cost.put(x, Integer.MAX_VALUE);
		}

		int h = 0;
		h = heuristicC[cityMap.get(des)][cityMap.get(source)];
		
		PriorityQueue<Node> notVis = new PriorityQueue<Node>((Node x1, Node x2) -> (x1.getF() - x2.getF()));
		PriorityQueue<Node> temp1 = new PriorityQueue<Node>((Node x1, Node x2) -> (x1.getF() - x2.getF()));
		PriorityQueue<Node> vis = new PriorityQueue<Node>((Node x1, Node x2) -> (x1.getF() - x2.getF()));
		PriorityQueue<Node> temp2 = new PriorityQueue<Node>((Node x1, Node x2) -> (x1.getF() - x2.getF()));

		cost.put(source, 0);
		Node first = new Node(source, null, 0);
		first.setF(0 + h);
		notVis.add(first);
		Node obj = null;

		ArrayList<Object> output = new ArrayList<>();
		ArrayList<String> fringe = new ArrayList<>();

		while (!notVis.isEmpty()) {
			Node up = notVis.peek();
			if ((up.getSourceN()).equals(des)) {
				output.add(up.getwalkCost());
				obj = up;
				break;
			}

			int x = cityMap.get(up.getSourceN());
			fringe.add(up.getSourceN());
			
			int Hv;
			for (int j = 0; j < g[x].size(); j++) {
				Hv = heuristicC[cityMap.get(des)][cityMap.get(g[x].get(j).getDesN())];
				String to = g[x].get(j).getDesN();
				Node Node1 = new Node(to, up); // name, parent

				int curCost = g[x].get(j).getwalkCost() + up.getwalkCost();

				temp2.clear();
				temp1.clear();
				boolean t1 = false;
				boolean t2 = false;
				while (!notVis.isEmpty()) {
					Node temp = notVis.poll();
					if (temp.getSourceN().equals(Node1.getSourceN())) {
						t1 = true;
					}
					temp1.add(temp);
				}
				while (!vis.isEmpty()) {
					Node temp = vis.poll();
					if (temp.getSourceN().equals(Node1.getSourceN())) {
						t2 = true;
					}
					temp2.add(temp);
				}
				while (!temp1.isEmpty()) {
					notVis.add(temp1.poll());
				}
				while (!temp2.isEmpty()) {
					vis.add(temp2.poll());
				}

				if (t1 == false && t2 == false) {
					Node1.setwalkCost(curCost);
					Node1.setF(Node1.getwalkCost() + Hv );
					notVis.add(Node1);
				} else if (curCost < cost.get(to)) {
					Node1.setwalkCost(curCost);
					Node1.setF(Node1.getwalkCost() + Hv );
					cost.put(to, curCost);
					if (t1 == true) {
						vis.remove(Node1);
						notVis.add(Node1);
					}
				}
			}
			notVis.remove(up);
			vis.add(up);
		}
		
		output.add(retPath(obj));
		output.add(fringe);
		return output;
	}
	
	// uniCOST algorithm with heuristic
	public static ArrayList<Object> uniCOST(String source, String des) {
		
		Map<String, Integer> cost = new HashMap<>();
		for (String x : cityMap.keySet()) {
			cost.put(x, Integer.MAX_VALUE);
		}

		int h = 0;
		//h = heuristicC[cityMap.get(des)][cityMap.get(source)];
		
		PriorityQueue<Node> notVis = new PriorityQueue<Node>((Node x1, Node x2) -> (x1.getF() - x2.getF()));
		PriorityQueue<Node> temp1 = new PriorityQueue<Node>((Node x1, Node x2) -> (x1.getF() - x2.getF()));
		PriorityQueue<Node> vis = new PriorityQueue<Node>((Node x1, Node x2) -> (x1.getF() - x2.getF()));
		PriorityQueue<Node> temp2 = new PriorityQueue<Node>((Node x1, Node x2) -> (x1.getF() - x2.getF()));

		cost.put(source, 0);
		Node first = new Node(source, null, 0);
		first.setF(0 + h);
		notVis.add(first);
		Node obj = null;

		ArrayList<Object> output = new ArrayList<>();
		ArrayList<String> fringe = new ArrayList<>();

		while (!notVis.isEmpty()) {
			Node up = notVis.peek();
			if ((up.getSourceN()).equals(des)) {
				output.add(up.getCost());
				obj = up;
				break;
			}

			int x = cityMap.get(up.getSourceN());
			fringe.add(up.getSourceN());
			
			int Hv;
			for (int j = 0; j < g[x].size(); j++) {
				//Hv = heuristicC[cityMap.get(des)][cityMap.get(g[x].get(j).getDesN())];
				Hv=0;
				String to = g[x].get(j).getDesN();
				Node Node1 = new Node(to, up); // name, parent

				int curCost = g[x].get(j).getCost() + up.getCost();

				temp2.clear();
				temp1.clear();
				boolean t1 = false;
				boolean t2 = false;
				while (!notVis.isEmpty()) {
					Node temp = notVis.poll();
					if (temp.getSourceN().equals(Node1.getSourceN())) {
						t1 = true;
					}
					temp1.add(temp);
				}
				while (!vis.isEmpty()) {
					Node temp = vis.poll();
					if (temp.getSourceN().equals(Node1.getSourceN())) {
						t2 = true;
					}
					temp2.add(temp);
				}
				while (!temp1.isEmpty()) {
					notVis.add(temp1.poll());
				}
				while (!temp2.isEmpty()) {
					vis.add(temp2.poll());
				}

				if (t1 == false && t2 == false) {
					Node1.setCost(curCost);
					Node1.setF(Node1.getCost() + Hv );
					notVis.add(Node1);
				} else if (curCost < cost.get(to)) {
					Node1.setCost(curCost);
					Node1.setF(Node1.getCost() + Hv );
					cost.put(to, curCost);
					if (t1 == true) {
						vis.remove(Node1);
						notVis.add(Node1);
					}
				}
			}
			notVis.remove(up);
			vis.add(up);
		}
		
		output.add(retPath(obj));
		output.add(fringe);
		return output;
	}
}

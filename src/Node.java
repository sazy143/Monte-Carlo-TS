import java.util.ArrayList;

//Because this is a tree search we need nodes. This is the class for the node.
public class Node {
	double wins = 0;
	double plays = 0;
	Node parent;
	Boolean isLeaf = true;
	String move;
	String color;
	ArrayList<Node> children = new ArrayList<Node>();
	
	public Node(String c, String m, Node p) {
		this.color = c;
		this.move = m;
		this.parent = p;
	}
	
	
	public ArrayList<Node> getChildren(){
		return this.children;
	}
	
	public Node selectRandom(ArrayList<String> moves) {
		if(moves.size()==0)
			return null;
		String move = moves.get((int)(Math.random()*moves.size()));
		for(Node child : this.children) {
			if(child.move.equals(move))
				return child;
		}
		String color = Character.toString(move.charAt(0));
		if(color.equals("W"))
			color = "B";
		else
			color = "W";
		Node child = new Node(color,move,this);
		return child;
	}
	
	public double getScore() {
		if(wins==0&&plays==0)
			return 0;
		else if(wins==1&&plays==1)
			return 0.001;
		else {

//			double add = ((double)wins/plays)*Math.log(wins);
//			if(wins==0) {
//				add = 0;
//			}
//			double sub = 0.5*Math.log((double)plays-wins);
//			
//			if(plays-wins==0){
//				sub = 0;
//			}
//			if(plays-wins==plays) {
//				sub = 500;
//			}
//			double score = add-sub;
//			
//			return score;
			return wins/plays * Math.log(plays);
		}
	}
}

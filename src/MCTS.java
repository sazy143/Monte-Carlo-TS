import java.util.ArrayList;

//The general form for MCTS is it goes through 4 phases mine only goes through 3
//1. Selection - from the current node find the best child or a random child depending
//2. Simulation - Randomly play out from that child node
//3. Backpropagation - Update score on previous nodes
//I used my own search method as I want it to still be random, and at the start everynode would have the same score,
//and for a tree with large branching this would be ineffective both in memory and searching.
public class MCTS {
	AmazonsBoard b;
	public MCTS(AmazonsBoard b) {
		this.b = b;
	}
	//takes in the node of our current game state, and outputs the selected node.
	public Node select(Node current) {
		Node selected = null;
		ArrayList<Node> children = current.getChildren();
//		for(Node child : children) {
//			if(child.getScore()>8) {
//				selected = child;
//			}
//		}
		if(selected == null) {
			ArrayList<String> moves = b.getValidMoves(current.color);
			selected = current.selectRandom(moves);
		}
		if(selected == null) {
			return current;
		}
		
		boolean flag = false;
		
		for(Node child : children) {
			if(child.move.equals(selected.move)) {
				selected = child;
				flag = true;
				break;
			}
		}
		
		if(!flag)
			current.children.add(selected);
		b.move(selected.move);
		return selected;
	}
	
	public Node simulation(Node expanded) {
		ArrayList<String> moves = b.getValidMoves(expanded.color);
		if(moves == null || moves.size()==0) {
			return expanded;
		} else {
			Node random = expanded.selectRandom(moves);
			ArrayList<Node> children = random.getChildren();
			boolean flag = false;
			
			for(Node child : children) {
				if(child.move.equals(random.move)) {
					random = child;
					flag = true;
					break;
				}
			}
			
			if(!flag)
				expanded.children.add(random);
			b.move(random.move);
			return simulation(random);
		}
	}
	
	public void Backprop(Node simulated) {
		boolean flip = true;
		int count =0;
		while(simulated!=null) {
			if(flip==true) {
				simulated.wins += 1;
				flip = false;
			}
			else {
				flip = true;
			}
			simulated.plays += 1;
			simulated = simulated.parent;
			count++;
		}
		//System.out.println(count);
		
	}
	
}

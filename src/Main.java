import java.awt.BorderLayout;
import java.awt.Container;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JFrame;

public class Main {

	AmazonsBoard board = new AmazonsBoard();
	JFrame guiFrame;
	MCTS monte = new MCTS(board);
	Node current = new Node("B",null,null);
	boolean gameOver = false;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Main run = new Main();

		
	}
	
	public Main() {
		setupGUI();
		while(gameOver == false) {
			search(2000);
		}
		
	}
	
	public void search(int time) {
		long timeout = System.currentTimeMillis();
		while(System.currentTimeMillis()-timeout<time) {
			int val = searchFromCurrent();
			if(val==0)
				break;
			if(current.plays>150000) {
				break;
			}
		}
		String move = chooseMove();
		if(move==null||move.length()==0) {
			System.out.println("Game Over! No more moves :(");
			gameOver = true;
			return;
		}
		board.move(move);
		board.repaint();

	}
	
	public int searchFromCurrent() {
		monte.b = board.shallowCopy();
		Node selected = monte.select(current);
		if(selected.equals(current))
			return 0 ;
		Node simulated = monte.simulation(selected);
		monte.Backprop(simulated);
		return 1;
	}
	
	public String chooseMove() {
		ArrayList<String> moves = board.getValidMoves(current.color);
		ArrayList<Node> children = current.getChildren();
		ArrayList<Node> goodchildren = new ArrayList<Node>();
		for(Node child : children) {
			for(String move : moves) {
				if(child.move.equals(move)) {
					goodchildren.add(child);
					break;
				}
			}
			
		}
		children = goodchildren;
		current.children = goodchildren;
		Node max = null;
		double maxs = Integer.MIN_VALUE;
		for(Node child : goodchildren) {
			if(child.getScore()>maxs)
				maxs = child.getScore();
				max = child;
		}
		if(max == null) {
			return null;
		}
		System.out.println("/ncurrent plays: "+current.plays+" wins: "+current.wins+" childern: "+goodchildren.size()+" explored of: "+moves.size()+ " current color: "+current.color);
		System.out.println("selected move has plays: "+max.plays+" wins: "+max.wins+" win% "+(double)max.wins/max.plays+" Heuristic score: "+max.getScore());
		current = max;
		
		return max.move;
	}
	
	
	public  void setupGUI() {
		guiFrame = new JFrame();
		   
		guiFrame.setSize(800, 600);
		guiFrame.setTitle("Game of the Amazons");	
		
		guiFrame.setLocation(200, 200);
		guiFrame.setVisible(true);
	    guiFrame.repaint();		
		guiFrame.setLayout(null);
		
		Container contentPane = guiFrame.getContentPane();
		contentPane.setLayout(new  BorderLayout());
		 
		contentPane.add(Box.createVerticalGlue()); 
		
		contentPane.add(board,BorderLayout.CENTER);
		
	}
}

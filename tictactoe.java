package mytictactoe;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;

public class tictactoe implements  ActionListener{
public static int [] gb ={0,0,0,0,0,0,0,0,0};
static int turn = 1;
static int x = 1;
static int o = 2;
JFrame f1,f2;
JButton b1,b2,b3,b4,b5,b6,b7,b8,b9,xb,reset,ob;
JLabel msg;
static boolean game = true;
public static ArrayList<Integer> avamove (int [] board) {
	ArrayList<Integer> moves = new ArrayList<Integer>(); 
	for(int i=0;i<board.length;i++){
		if(board[i] == 0){
			moves.add(i);
		}
		
	}
	//System.out.println(moves);
	return moves;
}
public static boolean winx(int [] board, int pos){
	boolean a = false;
	switch (pos){
	case 0: if((board[1] ==1 && board[2]==1) || (board[4] ==1 && board[8]==1) || (board[3] ==1 && board[6]==1)){ a = true;} break;
	case 1: if((board[0] ==1 && board[2]==1) || (board[4] ==1 && board[7]==1) ){ a = true;} break;
	case 2: if((board[1] ==1 && board[0]==1) || (board[4] ==1 && board[6]==1) || (board[5] ==1 && board[8]==1)){ a = true;} break;
	case 3: if((board[0] ==1 && board[6]==1) || (board[4] ==1 && board[5]==1) ){ a = true;} break;
	case 4: if((board[1] ==1 && board[7]==1) || (board[3] ==1 && board[5]==1) || (board[0] ==1 && board[8]==1) || (board[2] ==1 && board[6]==1)){ a = true;} break;
	case 5: if((board[2] ==1 && board[8]==1) || (board[4] ==1 && board[3]==1) ){ a = true;} break;
	case 6: if((board[3] ==1 && board[0]==1) || (board[4] ==1 && board[2]==1) || (board[7] ==1 && board[8]==1)){ a = true;} break;
	case 7: if((board[6] ==1 && board[8]==1) || (board[4] ==1 && board[1]==1) ){ a = true;} break;
	case 8: if((board[6] ==1 && board[7]==1) || (board[2] ==1 && board[5]==1) || (board[0] ==1 && board[4]==1)){ a = true;} break;
		}
return a;
}
public static boolean wino(int [] board, int pos){
	boolean a = false;
	switch (pos){
	case 0: if((board[1] ==2 && board[2]==2) || (board[4] ==2 && board[8]==2) || (board[3] ==2 && board[6]==2)){ a = true;} break;
	case 1: if((board[0] ==2 && board[2]==2) || (board[4] ==2 && board[7]==2) ){ a = true;} break;
	case 2: if((board[1] ==2 && board[0]==2) || (board[4] ==2 && board[6]==2) || (board[5] ==2 && board[8]==2)){ a = true;} break;
	case 3: if((board[0] ==2 && board[6]==2) || (board[4] ==2 && board[5]==2) ){ a = true;} break;
	case 4: if((board[1] ==2 && board[7]==2) || (board[3] ==2 && board[5]==2) || (board[0] ==2 && board[8]==2) || (board[2] ==2 && board[6]==2)){ a = true;} break;
	case 5: if((board[2] ==2 && board[8]==2) || (board[4] ==2 && board[3]==2) ){ a = true;} break;
	case 6: if((board[3] ==2 && board[0]==2) || (board[4] ==2 && board[2]==2) || (board[7] ==2 && board[8]==2)){ a = true;} break;
	case 7: if((board[6] ==2 && board[8]==2) || (board[4] ==2 && board[1]==2) ){ a = true;} break;
	case 8: if((board[6] ==2 && board[7]==2) || (board[2] ==2 && board[5]==2) || (board[0] ==2 && board[4]==2)){ a = true;} break;
		}
return a;
}
public static boolean checkx(int [] board){
    boolean a = false;
	for(int i=0;i<board.length;i++){
		if(board[i] == 1 && winx(board,i) == true){
			a = true;
		}
	}
	return a;
}
public static boolean checko(int [] board){
    boolean a = false;
	for(int i=0;i<board.length;i++){
		if(board[i] == 2 && wino(board,i) == true){
			a = true;
		}
	}
	return a;
}
public static int alphabetadepth(int [] board , int pos, int turn){
	int val = 0;
	if(turn ==1){
	boolean xwin = winx(board,pos);
	if(xwin == true) val = 10;
	if(xwin == false) {
		val = 0;
	}
	}
	if(turn ==2){
		boolean owin = wino(board,pos);
		if(owin == true) val = -10;
		if(owin == false) {
			val = 0;
		}
		}
	return val;
}
public static int [] minmax(int [] board,int turn,int depth){
	ArrayList<Integer> move = avamove(board);
	int [] score = new int[move.size()];
	if(move.isEmpty()){
		int [] a= {0};
		return a;
	}else{
	for(int i=0;i<move.size();i++){
		int pos = move.get(i);
		int val = alphabetadepth(board, pos, turn);
		if(val==10) score[i] = val-depth;
		if(val==-10) score[i] = val+depth;
		if(val ==0){
			if(turn == 1){
			board[pos] = 1;
			 int next[] = minmax(board,2,depth+1);
			 int k = 10000;
			 for(int j=0;j<next.length;j++){
				 if(next[j] < k){
					 k = next[j];
				 }
			 }
			 score[i]=k;
			 board[pos]=0;
			}
			if(turn == 2){
				board[pos] = 2;
				 int next[] = minmax(board,1,depth+1);
				 int k = -10000;
				 for(int j=0;j<next.length;j++){
					 if(next[j] > k){
						 k = next[j];
					 }
				 }
				 score[i]=k;
				 board[pos]=0;
				}
		}
	}
	}
	return score;
}

tictactoe(){
	f1 = new JFrame("game");
	f1.setSize(616, 638);
	b1 = new JButton();
	b2 = new JButton();
	b3 = new JButton();
	b4 = new JButton();
	b5 = new JButton();
	b6 = new JButton();
	b7 = new JButton();
	b8 = new JButton();
	b9 = new JButton();
	reset =new JButton("reset");
	f2 = new JFrame("options");
	f2.setLayout(new FlowLayout());
	f2.add(reset);
	f2.setSize(200,200);
	msg = new JLabel("game started");
	f2.add(msg);
	f1.setLayout(null);
	f1.add(b1);
	f1.add(b2);
	f1.add(b3);
	f1.add(b4);
	f1.add(b5);
	f1.add(b6);
	f1.add(b7);
	f1.add(b8);
	f1.add(b9);
	b1.setBounds(0, 0, 200, 200);
	b2.setBounds(200, 0, 200, 200);
	b3.setBounds(400, 0, 200, 200);
	b4.setBounds(0, 200, 200, 200);
	b5.setBounds(200, 200, 200, 200);
	b6.setBounds(400, 200, 200, 200);
	b7.setBounds(0, 400, 200, 200);
	b8.setBounds(200, 400, 200, 200);
	b9.setBounds(400, 400, 200, 200);
	b1.setFont(new Font("Aerial", Font.BOLD, 200));
	b2.setFont(new Font("Aerial", Font.BOLD, 200));
	b3.setFont(new Font("Aerial", Font.BOLD, 200));
	b4.setFont(new Font("Aerial", Font.BOLD, 200));
	b5.setFont(new Font("Aerial", Font.BOLD, 200));
	b6.setFont(new Font("Aerial", Font.BOLD, 200));
	b7.setFont(new Font("Aerial", Font.BOLD, 200));
	b8.setFont(new Font("Aerial", Font.BOLD, 200));
	b9.setFont(new Font("Aerial", Font.BOLD, 200));
	b1.addActionListener(this);
	b2.addActionListener(this);
	b3.addActionListener(this);
	b4.addActionListener(this);
	b5.addActionListener(this);
	b6.addActionListener(this);
	b7.addActionListener(this);
	b8.addActionListener(this);
	b9.addActionListener(this);
	reset.addActionListener(this);
	f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	f1.setVisible(true);
	f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	f2.setVisible(true);
	 while(true){
		 if(checkx(gb) == true){
			// System.out.println("x one the game");
			 msg.setText("x won the game");
			 game = false;
			// break;
			 
		 }else  if(checko(gb) == true){
			// System.out.println("o one the game");
			 msg.setText("o won the game");
			game = false;
			 //break;
		 }else{
			 ArrayList<Integer> moves = avamove(gb);
			 if(moves.isEmpty()){
			//	 System.out.println("the game is a draw");
				 msg.setText("the game is a draw");
				 game = false;
				 //break;
			 }else{
				 if(turn == 1){
					 int [] score = minmax(gb,1,0);
					 int nextmove = 0;
					 int maxval = score[0];
					 for(int i=0;i<score.length;i++){
						 if(score[i] > maxval){
							 maxval = score[i];
						 }
					 }
					 for(int i=0;i<score.length;i++){
						 if(score[i] == maxval){
							 nextmove =  moves.get(i);
						 }
					 }
					 gb[nextmove] = 1;
					 switch(nextmove){
					 case 0:b1.setText("X");
					 turn = 2;
					 break;
					 case 1:b2.setText("X");
					 turn = 2;
					 break;
					 case 2:b3.setText("X");
					 turn = 2;
					 break;
					 case 3:b4.setText("X");
					 turn = 2;
					 break;
					 case 4:b5.setText("X");
					 turn = 2;
					 break;
					 case 5:b6.setText("X");
					 turn = 2;
					 break;
					 case 6:b7.setText("X");
					 turn = 2;
					 break;
					 case 7:b8.setText("X");
					 turn = 2;
					 break;
					 case 8:b9.setText("X");
					 turn = 2;
					 break;
					 }
					 
				 }
				 if(turn == 2){
					 //System.out.println("waiting for u to play");
					    msg.setText("its your turn");
						
				 }
			 }
		 }
		 
	 }
	 
}
public void actionPerformed(ActionEvent e){
	try{
		if(game == true && turn == 2){
		if(e.getSource() == b1){ 
			if(gb[0] == 0){
			gb[0] = 2;
			b1.setText("O");
			turn = 1;
			}
		}
        if(e.getSource() == b2){
        	if(gb[1] == 0){
        	gb[1] = 2;
			b2.setText("O");
			turn = 1;
        	}
		}
        if(e.getSource() == b3){
        	if(gb[2] == 0){
        	gb[2] = 2;
			b3.setText("O");
			turn = 1;
        	}
        }
        if(e.getSource() == b4){
        	if(gb[3] == 0){
        	gb[3] = 2;
			b4.setText("O");
			turn = 1;
        	}
		}
        if(e.getSource() == b5){
        	if(gb[4] == 0){
        	gb[4] = 2;
			b5.setText("O");
			turn = 1;
        	}
        }
        if(e.getSource() == b6){
        	if(gb[5] == 0){
        	gb[5] = 2;
			b6.setText("O");
			turn = 1;
        	}
        }
        if(e.getSource() == b7){
        	if(gb[6] == 0){
        	gb[6] = 2;
			b7.setText("O");
			turn = 1;
        	}
        }
        if(e.getSource() == b8){
        	if(gb[7] == 0){
        	gb[7] = 2;
			b8.setText("O");
			turn = 1;
        	}
        }
        if(e.getSource() == b9){
        	if(gb[8] == 0){
        	gb[8] = 2;
			b9.setText("O");
			turn = 1;
        	}
        }
		}if(game == false){
			
		}
		if(e.getSource() == reset){
			for(int k=0;k<gb.length;k++){
				gb[k] = 0;
			}
			
			b1.setText(" ");
			b2.setText(" ");
			b3.setText(" ");
			b4.setText(" ");
			b5.setText(" ");
			b6.setText(" ");
			b7.setText(" ");
			b8.setText(" ");
			b9.setText(" ");
			game = true;
		}
        }catch(Exception e1){
		e1.printStackTrace();
	}
}
	
public static void main(String args[]){
	
   new tictactoe();
	/*int [] a = minmax(gb,1,0);
	for(int i=0;i<a.length;i++){
		System.out.println(a[i]);
	}*/

}
}
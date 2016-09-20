import javax.swing.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
public class wavemap extends JApplet implements Runnable{
	static int[][] a = {
		               {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                       {1,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                       {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                       {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                       {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                       {1,0,1,0,1,1,1,1,1,1,1,0,1,1,1,1,1,0,0,1},
                       {1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1},
                       {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                       {1,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,1},
                       {1,0,0,1,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,1},
                       {1,0,0,0,0,1,0,0,1,1,1,1,1,1,1,0,0,0,0,1},
                       {1,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1},
                       {1,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,1,3,1},
                       {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
        };		
	Graphics g;
	Thread t1=new Thread(this);
	JButton b1;
	/*ServerSocket server;
	Socket s;
	DataInputStream in;
	DataOutputStream out;
	String send;*/
	String rec;
	char dir = 'n';
	public void init(){
		g=getGraphics();
	  //Container con=getContentPane();
	//	JPanel p=new JPanel();
		//p.setSize(700, 700);
		//b1 = new JButton("clicl me");
		//p.add(b1);
	//	con.add(p);
		try{ 
		//server = new ServerSocket(6545);
		//s = server.accept();
		//in = new DataInputStream(s.getInputStream());
		//out = new DataOutputStream(s.getOutputStream());
		t1.start();
		System.out.println("thread started");
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	public void run(){
		int rx =0;
		int ry =0;
		int gx =0;
		int gy =0;
		int z = 3;
		int rt = 0;
		for(int i=0;i<14;i++){
			for(int j=0;j<20;j++){
				try{
				int pos = a[i][j];
				t1.sleep(50);
				g.setColor(new Color(0,0,0));
				 g.drawRect(j+j*25,i+ 25*i, 25, 25 );
				if(pos == 2){
					rx =j;
					ry=i;
					g.setColor(new Color(0,230,115));
					g.fillRect(j+j*25,i+ 25*i, 25, 25 );
				}
				if(pos == 3){
					gx =j;
					gy=i;
					g.setColor(new Color(255,255,0));
					g.fillRect(j+j*25,i+ 25*i, 25, 25 );
				}
				if(pos == 1){
					g.setColor(new Color(255,0,255));
					g.fillRect(j+j*25,i+ 25*i, 25, 25 );
				}
				}catch(Exception e){
					e.printStackTrace();
				}
			} 
		}
		System.out.println("robot position is"+" "+rx+" "+ry+" goal position is "+gx+" "+gy);
	while(z < 260){
		for(int y=1;y<14;y++){
			for(int x=1;x<20;x++){
				
				if(a[y][x] == z){
					if(a[y+1][x] == 0){
						a[y+1][x] = z+1;
					}
					if(a[y][x+1] == 0){
						a[y][x+1] = z+1;
					}
					if(a[y-1][x] == 0){
						a[y-1][x] = z+1;
					}
					if(a[y][x-1] == 0){
						a[y][x-1] = z+1;
					}
				}
			}
		}
		z++;
	}
	
	while(rt == 0){
		try{
		//rec = in.readUTF();
		
		int y = ry +1;
		int p = a[y][rx]; 
		int q = a[ry-1][rx];
		int r = a[ry][rx+1];
		int s = a[ry][rx-1];
		if(p ==1 || p ==0 || p==2) p=5000;
		if(q ==1 || q ==0 || q==2) q=4000;
		if(r ==1 || r ==0 || r==2) r=3000;
		if(s ==1 || s ==0 || s==2) s=1000;
		if(p<q && p<r && p<s){
			ry = ry+1;
			a[ry][rx] = 2;
			mapdraw(rx,ry); 
			if(dir == 'n'){
			//out.writeUTF("forward"); System.out.println("forward");
			dir = 'n';
			}
			else if(dir == 's'){
			//	out.writeUTF("reverse"); System.out.println("reverse");
				dir = 'n';
				}
			else if(dir == 'w'){
			//	out.writeUTF("right"); System.out.println("right");
				dir = 'n';
				}
			else if(dir == 'e'){
			//	out.writeUTF("left"); System.out.println("left");
				dir = 'n';
				}
		}
        if(q<p && q<r && q<s){
        	ry = ry-1;
        	a[ry][rx] = 2;
        	mapdraw(rx,ry); 
        	if(dir == 's'){
    		//	out.writeUTF("forward"); System.out.println("forward");
    			dir = 's';
    			}
        	else if(dir == 'n'){
    			//	out.writeUTF("reverse"); System.out.println("reverse");
    				dir = 's';
    				}
        	else if(dir == 'e'){
    			//	out.writeUTF("right"); System.out.println("right");
    				dir = 's';
    				}
        	else if(dir == 'w'){
    			//	out.writeUTF("left"); System.out.println("left");
    				dir = 's';
    				}
		}
        if(r<p && r<q && r<s){
			rx = rx+1; 
			a[ry][rx] = 2;
			mapdraw(rx,ry); 
			if(dir == 'n'){
			//	out.writeUTF("left"); System.out.println("left");
				dir = 'w';
				}
			else if(dir == 's'){
				//	out.writeUTF("right"); System.out.println("right");
					dir = 'w';
					}
			else if(dir == 'w'){
				//	out.writeUTF("forward"); System.out.println("forward");
					dir = 'w';
					}
			else if(dir == 'e'){
					//out.writeUTF("reverse"); System.out.println("reverse");
					dir = 'w';
					}
		}
        if(s<p && s<q && s<r){
			rx = rx-1;
			a[ry][rx] = 2;
			mapdraw(rx,ry); 
			if(dir == 'n'){
			//	out.writeUTF("right"); System.out.println("right");
				dir = 'e';
				}
			else if(dir == 's'){
				//	out.writeUTF("left"); System.out.println("left");
					dir = 'e';
					}
			else if(dir == 'w'){
				//	out.writeUTF("reverse"); System.out.println("reverse");
					dir = 'e';
					}
			else if(dir == 'e'){
				//	out.writeUTF("forward"); System.out.println("forward");
					dir = 'e';
					}
		}
        if(p == q){
        	if(p<r && p<s){
        		ry = ry+1;
        		a[ry][rx]=2;
        		mapdraw(rx,ry); 
        		if(dir == 'n'){
        		//	out.writeUTF("forward"); System.out.println("forward");
        			dir = 'n';
        			}
        		else if(dir == 's'){
        			//	out.writeUTF("reverse"); System.out.println("reverse");
        				dir = 'n';
        				}
        		else if(dir == 'w'){
        			//	out.writeUTF("right"); System.out.println("right");
        				dir = 'n';
        				}
        		else if(dir == 'e'){
        			//	out.writeUTF("left"); System.out.println("left");
        				dir = 'n';
        				}
        	}
        	if(p>r && r<s){
        		rx = rx+1;
        		a[ry][rx]=2;
        		mapdraw(rx,ry); 
        		if(dir == 'n'){
    			//	out.writeUTF("left"); System.out.println("left");
    				dir = 'w';
    				}
        		else if(dir == 's'){
    				//	out.writeUTF("right"); System.out.println("right");
    					dir = 'w';
    					}
        		else if(dir == 'w'){
    				//	out.writeUTF("forward"); System.out.println("forward");
    					dir = 'w';
    					}
        		else if(dir == 'e'){
    				//	out.writeUTF("reverse"); System.out.println("reverse");
    					dir = 'w';
    					}
        	}
        	if(s<r && p>s){
        		rx = rx-1;
        		a[ry][rx]=2;
        		mapdraw(rx,ry); 
        		if(dir == 'n'){
    			//	out.writeUTF("right"); System.out.println("right");
    				dir = 'e';
    				}
        		else if(dir == 's'){
    				//	out.writeUTF("left"); System.out.println("left");
    					dir = 'e';
    					}
        		else if(dir == 'w'){
    					//out.writeUTF("reverse"); System.out.println("reverse");
    					dir = 'e';
    					}
        		else if(dir == 'e'){
    				//	out.writeUTF("forward"); System.out.println("forward");
    					dir = 'e';
    					}
        	}
        }
        if(p == r){
        	if(p<q && p<s){
        		ry = ry+1;
        		a[ry][rx]=2;
        		mapdraw(rx,ry); 
        		if(dir == 'n'){
        		//	out.writeUTF("forward"); System.out.println("forward");
        			dir = 'n';
        			}
        		else if(dir == 's'){
        			//	out.writeUTF("reverse"); System.out.println("reverse");
        				dir = 'n';
        				}
        		else if(dir == 'w'){
        				//out.writeUTF("right"); System.out.println("right");
        				dir = 'n';
        				}
        		else if(dir == 'e'){
        			//	out.writeUTF("left"); System.out.println("left");
        				dir = 'n';
        				}
        	}
        	if(p>q && q<s){
        		ry = ry-1;
        		a[ry][rx]=2;
        		mapdraw(rx,ry); 
        		if(dir == 's'){
        		//	out.writeUTF("forward"); System.out.println("forward");
        			dir = 's';
        			}
        		else if(dir == 'n'){
        			//	out.writeUTF("reverse"); System.out.println("reverse");
        				dir = 's';
        				}
        		else if(dir == 'e'){
        			//	out.writeUTF("right"); System.out.println("right");
        				dir = 's';
        				}
        		else if(dir == 'w'){
        			//	out.writeUTF("left"); System.out.println("left");
        				dir = 's';
        				}
        	}
        	if(s<q && p>s){
        		rx = rx-1;
        		a[ry][rx]=2;
        		mapdraw(rx,ry); 
        		if(dir == 'n'){
    			//	out.writeUTF("right"); System.out.println("right");
    				dir = 'e';
    				}
        		else if(dir == 's'){
    				//	out.writeUTF("left"); System.out.println("left");
    					dir = 'e';
    					}
        		else if(dir == 'w'){
    			//		out.writeUTF("reverse"); System.out.println("reverse");
    					dir = 'e';
    					}
        		else if(dir == 'e'){
    				
    				//	out.writeUTF("forward"); System.out.println("forward");
    					dir = 'e';
    					}
        	}
        }
        if(p == s){
        	if(p<r && p<q){
        		ry = ry+1;
        		a[ry][rx]=2;
        		mapdraw(rx,ry); 
        		if(dir == 'n'){
        		//	out.writeUTF("forward"); System.out.println("forward");
        			dir = 'n';
        			}
        		else if(dir == 's'){
        			//	out.writeUTF("reverse"); System.out.println("reverse");
        				dir = 'n';
        				}
        		else if(dir == 'w'){
        			//	out.writeUTF("right"); System.out.println("right");
        				dir = 'n';
        				}
        		else if(dir == 'e'){
        			//	out.writeUTF("left"); System.out.println("left");
        				dir = 'n';
        				}
        	}
        	if(p>r && r<q){
        		rx = rx+1;
        		a[ry][rx]=2;
        		mapdraw(rx,ry); 
        		if(dir == 'n'){
    				//out.writeUTF("left"); System.out.println("left");
    				dir = 'w';
    				}
        		else if(dir == 's'){
    				//	out.writeUTF("right"); System.out.println("right");
    					dir = 'w';
    					}
        		else if(dir == 'w'){
    				//	out.writeUTF("forward"); System.out.println("forward");
    					dir = 'w';
    					}
        		else if(dir == 'e'){
    					//out.writeUTF("reverse"); System.out.println("reverse");
    					dir = 'w';
    					}
        	}
        	if(q<r && p>q){
        		ry = ry-1;
        		a[ry][rx]=2;
        		mapdraw(rx,ry); 
        		if(dir == 's'){
        		//	out.writeUTF("forward"); System.out.println("forward");
        			dir = 's';
        			}
        		else if(dir == 'n'){
        			//	out.writeUTF("reverse"); System.out.println("reverse");
        				dir = 's';
        				}
        		else if(dir == 'e'){
        			//	out.writeUTF("right"); System.out.println("right");
        				dir = 's';
        				}
        		else if(dir == 'w'){
        				//out.writeUTF("left"); System.out.println("left");
        				dir = 's';
        				}
        	}
        }
        if(q == s){
        	if(p<r && p<q){
        		ry = ry+1;
        		a[ry][rx]=2;
        		mapdraw(rx,ry); 
        		if(dir == 'n'){
        		//	out.writeUTF("forward"); System.out.println("forward");
        			dir = 'n';
        			}
        		else if(dir == 's'){
        			//	out.writeUTF("reverse"); System.out.println("reverse");
        				dir = 'n';
        				}
        		else if(dir == 'w'){
        			//	out.writeUTF("right"); System.out.println("right");
        				dir = 'n';
        				}
        		else if(dir == 'e'){
        			//	out.writeUTF("left"); System.out.println("left");
        				dir = 'n';
        				}
        	}
        	if(p>r && r<q){
        		rx = rx+1;
        		a[ry][rx]=2;
        		mapdraw(rx,ry); 
        		if(dir == 'n'){
    				//out.writeUTF("left"); System.out.println("left");
    				dir = 'w';
    				}
        		else if(dir == 's'){
    				//	out.writeUTF("right"); System.out.println("right");
    					dir = 'w';
    					}
        		else if(dir == 'w'){
    				//	out.writeUTF("forward"); System.out.println("forward");
    					dir = 'w';
    					}
        		else if(dir == 'e'){
    				//	out.writeUTF("reverse"); System.out.println("reverse");
    					dir = 'w';
    					}
        	}
        	if(q<r && p>q){
        		ry = ry-1;
        		a[ry][rx]=2;
        		mapdraw(rx,ry); 
        		if(dir == 's'){
        		//	out.writeUTF("forward"); System.out.println("forward");
        			dir = 's';
        			}
        		else if(dir == 'n'){
        			//	out.writeUTF("reverse"); System.out.println("reverse");
        				dir = 's';
        				}
        		else if(dir == 'e'){
        			//	out.writeUTF("right"); System.out.println("right");
        				dir = 's';
        				}
        		else if(dir == 'w'){
        			//	out.writeUTF("left"); System.out.println("left");
        				dir = 's';
        				}
        	}
        }
        if(q == r){
        	if(p<q && p<s){
        		ry = ry+1;
        		a[ry][rx]=2;
        		mapdraw(rx,ry); 
        		if(dir == 'n'){
        		//	out.writeUTF("forward"); System.out.println("forward");
        			dir = 'n';
        			}
        		else if(dir == 's'){
        			//	out.writeUTF("reverse"); System.out.println("reverse");
        				dir = 'n';
        				}
        		else if(dir == 'w'){
        			//	out.writeUTF("right"); System.out.println("right");
        				dir = 'n';
        				}
        		else if(dir == 'e'){
        				//out.writeUTF("left"); System.out.println("left");
        				dir = 'n';
        				}
        	}
        	if(p>q && q<s){
        		ry = ry-1;
        		a[ry][rx]=2;
        		mapdraw(rx,ry); 
        		if(dir == 's'){
        			//out.writeUTF("forward"); System.out.println("forward");
        			dir = 's';
        			}
        		else if(dir == 'n'){
        			//	out.writeUTF("reverse"); System.out.println("reverse");
        				dir = 's';
        				}
        		else if(dir == 'e'){
        			//	out.writeUTF("right"); System.out.println("right");
        				dir = 's';
        				}
        		else if(dir == 'w'){
        			//	out.writeUTF("left"); System.out.println("left");
        				dir = 's';
        				}
        	}
        	if(s<q && p>s){
        		rx = rx-1;
        		a[ry][rx]=2;
        		mapdraw(rx,ry); 
        		if(dir == 'n'){
    			//	out.writeUTF("right"); System.out.println("right");
    				dir = 'e';
    				}
        		else if(dir == 's'){
    			//		out.writeUTF("left"); System.out.println("left");
    					dir = 'e';
    					}
        		else if(dir == 'w'){
    				//	out.writeUTF("reverse"); System.out.println("reverse");
    					dir = 'e';
    					}
        		else if(dir == 'e'){
    			//		out.writeUTF("forward"); System.out.println("forward");
    					dir = 'e';
    					}
        	}
        }
        if(r == s){
        	if(p<r && p<q){
        		ry = ry+1;
        		a[ry][rx]=2;
        		mapdraw(rx,ry); 
        		if(dir == 'n'){
        		//	out.writeUTF("forward"); System.out.println("forward");
        			dir = 'n';
        			}
        		else if(dir == 's'){
        			//	out.writeUTF("reverse"); System.out.println("reverse");
        				dir = 'n';
        				}
        		else if(dir == 'w'){
        				//out.writeUTF("right"); System.out.println("rigth");
        				dir = 'n';
        				}
        		else if(dir == 'e'){
        			//	out.writeUTF("left"); System.out.println("left");
        				dir = 'n';
        				}
        	}
        	if(p>r && r<q){
        		rx = rx+1;
        		a[ry][rx]=2;
        		mapdraw(rx,ry); 
        		if(dir == 'n'){
    			//	out.writeUTF("left"); System.out.println("left");
    				dir = 'w';
    				}
        		else if(dir == 's'){
    					//out.writeUTF("right"); System.out.println("right");
    					dir = 'w';
    					}
        		else if(dir == 'w'){
    					//out.writeUTF("forward"); System.out.println("forward");
    					dir = 'w';
    					}
        		else if(dir == 'e'){
    				//	out.writeUTF("reverse"); System.out.println("reverse");
    					dir = 'w';
    					}
        	}
        	if(q<r && p>q){
        		ry = ry-1;
        		a[ry][rx]=2;
        		mapdraw(rx,ry);
        		if(dir == 's'){
        			//out.writeUTF("forward"); System.out.println("forward");
        			dir = 's';
        			}
        		else if(dir == 'n'){
        			//	out.writeUTF("reverse"); System.out.println("reverse");
        				dir = 's';
        				}
        		else if(dir == 'e'){
        				//out.writeUTF("rigth"); System.out.println("right");
        				dir = 's';
        				}
        		else if(dir == 'w'){
        			//	out.writeUTF("left"); System.out.println("left");
        				dir = 's';
        				}
        	}
        }
		if(rx==gx && ry==gy){
			rt = 1;
			//out.writeUTF("finish");
		}
	
		
	}catch(Exception e1){
		e1.printStackTrace();
	}
	}
	for(int i=0;i<14;i++){
		for(int j=0;j<20;j++){
		
			System.out.print(a[i][j]+"\t");
		} 
		System.out.println("");
	}
}
	public void mapdraw(int j, int i){
		
		try{
		g.setColor(new Color(0,0,255));
		int x1 = j+j*25;
		int y1 = i+ 25*i;
		t1.sleep(100);
//		g.drawRect(x1,y1, 50, 50 );
		g.fillRect(x1,y1, 25, 25 );
		
		System.out.println(x1+" "+y1);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}

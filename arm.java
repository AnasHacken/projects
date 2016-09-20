package armcon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import gnu.io.CommPortIdentifier; 
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent; 
import gnu.io.SerialPortEventListener; 
import java.util.Enumeration;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class arm implements SerialPortEventListener, ActionListener {
    static int xa = 0;
    int [] ckb = {0,0,0,0,0,0};
	SerialPort serialPort;
	private static final String PORT_NAMES[] = { 
			"COM3", // Windows
	};
	private BufferedReader input;
	private  OutputStream output;
	private static final int TIME_OUT = 2000;
	private static final int DATA_RATE = 9600;
	JButton b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13;
	JFrame f1;
	JLabel l1,l2,l3,l4,l5,l6;
	public  void app(){
		l1 = new JLabel("s1 angle" + ckb[0]);
		l2 = new JLabel("s2 angle" + ckb[1]);
		l3 = new JLabel("s3 angle" + ckb[2]);
		l4 = new JLabel("s4 angle" + ckb[3]);
		l5 = new JLabel("s5 angle" + ckb[4]);
		l6 = new JLabel("s6 angle" + ckb[5]);
		b1 = new JButton("s1up");
		b2 = new JButton("s2up");
		b3 = new JButton("s3up");
		b4 = new JButton("s4up");
		b5 = new JButton("s5up");
		b6 = new JButton("s6up");
		b7 = new JButton("s1down");
		b8 = new JButton("s2down");
		b9 = new JButton("s3down");
		b10 = new JButton("s4down");
		b11 = new JButton("s5down");
		b12 = new JButton("s6down");
		b13 = new JButton("Send");
		f1 = new JFrame();
		f1.setTitle("serialcommun");
		f1.setLayout(new FlowLayout());
		f1.add(l1);
		f1.add(l2);
		f1.add(l3);
		f1.add(l4);
		f1.add(l5);
		f1.add(l6);
		f1.add(b1);
		f1.add(b2);
		f1.add(b3);
		f1.add(b4);
		f1.add(b5);
		f1.add(b6);
		f1.add(b7);
		f1.add(b8);
		f1.add(b9);
		f1.add(b10);
		f1.add(b11);
		f1.add(b12);
		f1.add(b13);	
		f1.setSize(400,500);
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		b7.addActionListener(this);
		b8.addActionListener(this);
		b9.addActionListener(this);
		b10.addActionListener(this);
		b11.addActionListener(this);
		b12.addActionListener(this);
		b13.addActionListener(this);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		f1.setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
	if(e.getSource() == b1)
	{
	ckb[0]++;
	l1.setText("s1angle"+ckb[0]);
	if(ckb[0] > 179){
		ckb[0] = 179;
	}
	}
	if(e.getSource() == b2)
	{
	ckb[1]++;
	l2.setText("s2angle"+ckb[1]);
	if(ckb[1] > 179){
		ckb[1] = 179;
	}
	}
	if(e.getSource() == b3)
	{
	ckb[2]++;
	l3.setText("s1angle"+ckb[2]);
	if(ckb[2] > 179){
		ckb[2] = 179;
	}
	}
	if(e.getSource() == b4)
	{
	ckb[3]++;
	l4.setText("s2angle"+ckb[3]);
	if(ckb[3] > 179){
		ckb[3] = 179;
	}
	}
	if(e.getSource() == b5)
	{
	ckb[4]++;
	l5.setText("s1angle"+ckb[4]);
	if(ckb[4] > 179){
		ckb[4] = 179;
	}
	}
	if(e.getSource() == b6)
	{
	ckb[5]++;
	l6.setText("s2angle"+ckb[5]);
	if(ckb[5] > 179){
		ckb[5] = 179;
	}
	}
	if(e.getSource() == b7)
	{
	ckb[0]--;
	l1.setText("s1angle"+ckb[0]);
	if(ckb[0] < 1){
		ckb[0] = 1;
	}
	}
	if(e.getSource() == b8)
	{
	ckb[1]--;
	l2.setText("s2angle"+ckb[1]);
	if(ckb[1] < 1){
		ckb[1] = 1;
	}
	}
	if(e.getSource() == b9)
	{
	ckb[2]--;
	l3.setText("s1angle"+ckb[2]);
	if(ckb[2] < 1){
		ckb[2] = 1;
	}
	}
	if(e.getSource() == b10)
	{
	ckb[3]--;
	l4.setText("s2angle"+ckb[3]);
	if(ckb[3] < 1){
		ckb[3] = 1;
	}
	}
	if(e.getSource() == b11)
	{
	ckb[4]--;
	l5.setText("s1angle"+ckb[4]);
	if(ckb[4] < 1){
		ckb[4] = 1;
	}
	}
	if(e.getSource() == b12)
	{
	ckb[5]--;
	l6.setText("s2angle"+ckb[5]);
	if(ckb[5] < 1){
		ckb[5] = 1;
	}
	}
	if(e.getSource() == b13)
	{
		
		try{
			output = serialPort.getOutputStream();
			for(int i1 =0;i1 < 6;i1++){
			output.write(ckb[i1]);
			System.out.print(ckb[i1]);
			}
			}catch(Exception e5){}
		System.out.println("data send to the arm");
	}
	}
	public void initialize() {

		CommPortIdentifier portId = null;
		Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();
		while (portEnum.hasMoreElements()) {
			CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
			for (String portName : PORT_NAMES) {
				if (currPortId.getName().equals(portName)) {
					portId = currPortId;
					break;
				}
			}
		}
		if (portId == null) {
			System.out.println("Could not find COM port.");
			return;
		}

		try {
			serialPort = (SerialPort) portId.open(this.getClass().getName(),	TIME_OUT);
			serialPort.setSerialPortParams(DATA_RATE,
					SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);

			input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	public synchronized void close() {
		if (serialPort != null) {
			serialPort.removeEventListener();
			serialPort.close();
		}
	}
	public  void datawr1() {
		try{
		output = serialPort.getOutputStream();
		output.write(xa);
		}catch(Exception es){}
	}

	public synchronized void serialEvent(SerialPortEvent oEvent) {
		if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			try {
				String inputLine=input.readLine();
				System.out.println(inputLine);
			} catch (Exception e) {
				System.err.println(e.toString());
			}
		}
	}

	public static void main(String[] args) throws Exception {
		arm anas = new arm();
		anas.app();
		anas.initialize();
		System.out.println(xa);
	}
}
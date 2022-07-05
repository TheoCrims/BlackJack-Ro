import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.util.*;

public class GUI extends JFrame {
	
       
    
	//randomizator pentru carti
	Random rand = new Random();
	
	//numar intreg folosit pentru statusul de folosit
	int tempC;
	
	//boolean care indica daca dealer-ul gandeste sau nu
	boolean dHitter = false;
	
	//lista cartilor
	ArrayList<Card> Cards = new ArrayList<Card>();
	
	//lista de mesaje
	ArrayList<Message> Log = new ArrayList<Message>();
	
	//fonturi folosite
	Font fontCarte = new Font("Arial", Font.PLAIN, 40);
	Font fontIntrebare = new Font("Arial", Font.BOLD, 40);
	Font fontButon = new Font("Arial", Font.PLAIN, 25);
	Font fontLog = new Font("Arial", Font.ITALIC, 30);
	
	//Log culori mesaje
	Color cDealer = Color.red;
	Color cPlayer = new Color(25,255,225);
	
	//strings folosite
	String questionTragiStai = new String("Tragi sau stai?");
	String questionMaiJoci = new String("Inca o mana?");
	
	//culori folosite
	Color culoareFundal = new Color(39,119,20);
	Color culoareButon = new Color(204,204,0);
	
	//butoane folosite
	JButton bTrage = new JButton();
	JButton bStau = new JButton();
	JButton bDa = new JButton();
	JButton bNu = new JButton();
	
	//rezolutia ecranului
	int sW = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	int sH = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	
	//rezolutia ferestrei
	int aW = 1300;
	int aH = 800;
	
	//grila cartilor si dimensiunea lor
	int gridX = 50;
	int gridY = 50;
	int gridW = 900;
	int gridH = 400;
	
	//spatierea si rotunjirea cartilor
	int spatiere = 10;
	int rotunjire = 10;
	int tCardW = (int) gridW/6;
	int tCardH = (int) gridH/2;
	int cardW = tCardW - spatiere*2;
	int cardH = tCardH - spatiere*2;
	
	//boolean pentru fazele jocului
	boolean tragi_stai_q = true;
	boolean rand_dealer = false;
	boolean mai_joci_q = false;
	
	//player si dealer card array
	ArrayList<Card> pCards = new ArrayList<Card>();
	ArrayList<Card> dCards = new ArrayList<Card>();
	
	//total player si dealer
	int pMinTotal = 0;
	int pMaxTotal = 0;
	int dMinTotal = 0;
	int dMaxTotal = 0;
	
	//pentru romb
	int[] rombX = new int[4];
	int[] rombY = new int[4];
	
	public GUI() {
		this.setTitle("Blackjack");
		this.setBounds((sW-aW-6)/2, (sH-aH-29)/2, aW+6, aH+29);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		Board board = new Board();
		this.setContentPane(board);
		board.setLayout(null);
		
		Move move = new Move();
		this.addMouseMotionListener(move);
		
		Click click = new Click();
		this.addMouseListener(click);
		
		//butoanele
		
		actHit actHit = new actHit();
		bTrage.addActionListener(actHit);
		bTrage.setBounds(1000, 200, 125, 50);
		bTrage.setBackground(culoareButon);
		bTrage.setFont(fontButon);
		bTrage.setText("TRAG");
		board.add(bTrage);
		
		ActStay actStay = new ActStay();
		bStau.addActionListener(actStay);
		bStau.setBounds(1150, 200, 100, 50);
		bStau.setBackground(culoareButon);
		bStau.setFont(fontButon);
		bStau.setText("STAU");
		board.add(bStau);
		
		ActYes actYes = new ActYes();
		bDa.addActionListener(actYes);
		bDa.setBounds(1000, 600, 100, 50);
		bDa.setBackground(culoareButon);
		bDa.setFont(fontButon);
		bDa.setText("DA");
		board.add(bDa);
		
		ActNo actNo = new ActNo();
		bNu.addActionListener(actNo);
		bNu.setBounds(1150, 600, 100, 50);
		bNu.setBackground(culoareButon);
		bNu.setFont(fontButon);
		bNu.setText("NU");
		board.add(bNu);
		
		//creare cartilor (a pachetului)
		
		String temp_str = "starting_temp_str_nume";
		for (int i = 0; i < 52; i++) {
			if (i % 4 == 0) {
				temp_str = "Neagra";
			} else if (i % 4 == 1) {
				temp_str = "Rosie";
			} else if (i % 4 == 2) {
				temp_str = "Romb";
			} else if (i % 4 == 3) {
				temp_str = "Trefla";
			}
			Cards.add(new Card((i/4) + 1, temp_str, i));
		}
		
		//alegere aleatorie a cartilor pentru player si dealer
		
		tempC = rand.nextInt(52);
		pCards.add(Cards.get(tempC));
		Cards.get(tempC).setFolosit();
		
		tempC = rand.nextInt(52);
		while (Cards.get(tempC).folosit == true) {
			tempC = rand.nextInt(52);
		}
		dCards.add(Cards.get(tempC));
		Cards.get(tempC).setFolosit();
		
		tempC = rand.nextInt(52);
		while (Cards.get(tempC).folosit == true) {
			tempC = rand.nextInt(52);
		}
		pCards.add(Cards.get(tempC));
		Cards.get(tempC).setFolosit();
		
		tempC = rand.nextInt(52);
		while (Cards.get(tempC).folosit == true) {
			tempC = rand.nextInt(52);
		}
		dCards.add(Cards.get(tempC));
		Cards.get(tempC).setFolosit();
	}
	
	public void totalsChecker() {
		
		int contorAsi;
		
		//calculul pt player total
		pMinTotal = 0;
		pMaxTotal = 0;
		contorAsi = 0;
		
		for (Card c : pCards) {
			pMinTotal += c.valoare;
			pMaxTotal += c.valoare;
			if (c.nume == "As")
				contorAsi++;
			
		}
		
		if (contorAsi > 0)
			pMaxTotal += 10;
		
		dMinTotal = 0;
		dMaxTotal = 0;
		contorAsi = 0;
		
		for (Card c : dCards) {
			dMinTotal += c.valoare;
			dMaxTotal += c.valoare;
			if (c.nume == "As")
				contorAsi++;
			
		}
		
		if (contorAsi > 0)
			dMaxTotal += 10;
	}
	
	public void setCastigator() {
		int pPoints = 0;
		int dPoints = 0;
		
		if (pMaxTotal > 21) {
			pPoints = pMinTotal;
		} else {
			pPoints = pMaxTotal;
		}
		
		if (dMaxTotal > 21) {
			dPoints = dMinTotal;
		} else {
			dPoints = dMaxTotal;
		}
		
		if (pPoints > 21 && dPoints > 21) {
			Log.add(new Message("Nimeni nu a castigat!", "Dealer"));
		} else if (dPoints > 21) {
			Log.add(new Message("Ai castigat!", "Player"));
			Main.pWins++;
		} else if (pPoints > 21) {
			Log.add(new Message("Dealerul a castigat!", "Dealer"));
			Main.dWins++;
		} else if (pPoints > dPoints) {
			Log.add(new Message("Ai castigat!", "Player"));
			Main.pWins++;
		} else {
			Log.add(new Message("Dealerul a castigat!", "Dealer"));
			Main.dWins++;
		}
		
	}
	
	public void dealerTrageStai() {
		dHitter = true;
		
		int dDisponibil = 0;
		if (dMaxTotal > 21) {
			dDisponibil = dMinTotal;
		} else {
			dDisponibil = dMaxTotal;
		}
		
		int pDisponibil = 0;
		if (pMaxTotal > 21) {
			pDisponibil = pMinTotal;
		} else {
			pDisponibil = pMaxTotal;
		}
		
		repaint();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if ((dDisponibil < pDisponibil && pDisponibil <= 21) || dDisponibil < 16) {
			int tempMax = 0;
			if (dMaxTotal <= 21) {
				tempMax = dMaxTotal;
			} else {
				tempMax = dMinTotal;
			}
			String mess = ("Dealerul a tras o carte! (total: " + Integer.toString(tempMax) + ")");
			Log.add(new Message(mess, "Dealer"));
			tempC = rand.nextInt(52);
			while (Cards.get(tempC).folosit == true) {
				tempC = rand.nextInt(52);
			}
			dCards.add(Cards.get(tempC));
			Cards.get(tempC).setFolosit();
		} else {
			int tempMax = 0;
			if (dMaxTotal <= 21) {
				tempMax = dMaxTotal;
			} else {
				tempMax = dMinTotal;
			}
			String mess = ("Dealerul a decis sa stea! (total: " + Integer.toString(tempMax) + ")");
			Log.add(new Message(mess, "Dealer"));
			setCastigator();
			rand_dealer = false;
			mai_joci_q = true;
		}
		dHitter = false;
	}
	
	public void refresher() {
		
		if (tragi_stai_q == true) {
			bTrage.setVisible(true);
			bStau.setVisible(true);
		} else {
			bTrage.setVisible(false);
			bStau.setVisible(false);
		}
		
		if (rand_dealer == true) {
			if (dHitter == false)
				dealerTrageStai();
		}
		
		if (mai_joci_q == true) {
			bDa.setVisible(true);
			bNu.setVisible(true);
		} else {
			bDa.setVisible(false);
			bNu.setVisible(false);
		}
		
		totalsChecker();
		
		if ((pMaxTotal == 21 || pMinTotal >= 21) && tragi_stai_q == true) {
			int tempMax = 0;
			if (pMaxTotal <= 21) {
				tempMax = pMaxTotal;
			} else {
				tempMax = pMinTotal;
			}
			String mess = ("Stop Automat! (total: " + Integer.toString(tempMax) + ")");
			Log.add(new Message(mess, "Player"));
			tragi_stai_q = false;
			rand_dealer = true;
		}
		
		if ((dMaxTotal == 21 || dMinTotal >= 21) && rand_dealer == true) {
			int tempMax = 0;
			if (dMaxTotal <= 21) {
				tempMax = dMaxTotal;
			} else {
				tempMax = dMinTotal;
			}
			String mess = ("Dealer Stop Automat! (total: " + Integer.toString(tempMax) + ")");
			Log.add(new Message(mess, "Dealer"));
			setCastigator();
			rand_dealer = false;
			mai_joci_q = true;
		}
		
		repaint();
	}
	
	public class Board extends JPanel {
		
		public void paintComponent(Graphics g) {
			//background
			g.setColor(culoareFundal);
			g.fillRect(0, 0, aW, aH);
			
			//intrebari pentru jucator
			if (tragi_stai_q == true) {
				g.setColor(Color.yellow);
				g.setFont(fontIntrebare);
				g.drawString(questionTragiStai, gridX+gridW+50, gridY+90);
				g.drawString("Total:", gridX+gridW+60, gridY+290);
				if (pMinTotal == pMaxTotal) {
					g.drawString(Integer.toString(pMaxTotal), gridX+gridW+60, gridY+350);
				} else if (pMaxTotal <= 21) {
					g.drawString(Integer.toString(pMinTotal) + " or " + Integer.toString(pMaxTotal), gridX+gridW+60, gridY+350);
				} else {
					g.drawString(Integer.toString(pMinTotal), gridX+gridW+60, gridY+350);
				}
			} else if (mai_joci_q == true) {
				g.setColor(Color.yellow);
				g.setFont(fontIntrebare);
				g.drawString(questionMaiJoci, gridX+gridW+70, gridY+490);
			}
			g.setColor(Color.black);
			g.fillRect(gridX, gridY+gridH+50, gridW, 500);
			
			//Log
			g.setFont(fontLog);
			int logIndex = 0;
			for (Message L : Log) {
				if (L.getWho().equalsIgnoreCase("Dealer")) {
					g.setColor(cDealer);
				} else {
					g.setColor(cPlayer);
				}
				g.drawString(L.getMessage(), gridX+20, gridY+480+logIndex*35);
				logIndex++;
			}
			
			//Scor
			g.setColor(Color.BLACK);
			g.setFont(fontIntrebare);
			String Scor = ("Scor: " + Integer.toString(Main.pWins) + " - " + Integer.toString(Main.dWins));
			g.drawString(Scor, gridX+gridW+70, gridY+gridH+300);
			
			//carti jucator
			int index = 0;
			for (Card c : pCards) {
				g.setColor(Color.white);
				g.fillRect(gridX+spatiere+tCardW*index+rotunjire, gridY+spatiere, cardW-rotunjire*2, cardH);
				g.fillRect(gridX+spatiere+tCardW*index, gridY+spatiere+rotunjire, cardW, cardH-rotunjire*2);
				g.fillOval(gridX+spatiere+tCardW*index, gridY+spatiere, rotunjire*2, rotunjire*2);
				g.fillOval(gridX+spatiere+tCardW*index, gridY+spatiere+cardH-rotunjire*2, rotunjire*2, rotunjire*2);
				g.fillOval(gridX+spatiere+tCardW*index+cardW-rotunjire*2, gridY+spatiere, rotunjire*2, rotunjire*2);
				g.fillOval(gridX+spatiere+tCardW*index+cardW-rotunjire*2, gridY+spatiere+cardH-rotunjire*2, rotunjire*2, rotunjire*2);
				
				g.setFont(fontCarte);
				if (c.shape.equalsIgnoreCase("Rosie") || c.shape.equalsIgnoreCase("Romb")) {
					g.setColor(Color.red);
				} else {
					g.setColor(Color.black);
				}
				
				g.drawString(c.simbol, gridX+spatiere+tCardW*index+rotunjire, gridY+spatiere+cardH-rotunjire);
				
				if (c.shape.equalsIgnoreCase("Rosie")) {
					g.fillOval(gridX+tCardW*index+42, gridY+70, 35, 35);
					g.fillOval(gridX+tCardW*index+73, gridY+70, 35, 35);
					g.fillArc(gridX+tCardW*index+30, gridY+90, 90, 90, 51, 78);
				} else if (c.shape.equalsIgnoreCase("Romb")) {
					rombX[0] = gridX+tCardW*index+75;
					rombX[1] = gridX+tCardW*index+50;
					rombX[2] = gridX+tCardW*index+75;
					rombX[3] = gridX+tCardW*index+100;
					rombY[0] = gridY+60;
					rombY[1] = gridY+100;
					rombY[2] = gridY+140;
					rombY[3] = gridY+100;
					g.fillPolygon(rombX, rombY, 4);
				} else if (c.shape.equalsIgnoreCase("Neagra")) {
					g.fillOval(gridX+tCardW*index+42, gridY+90, 35, 35);
					g.fillOval(gridX+tCardW*index+73, gridY+90, 35, 35);
					g.fillArc(gridX+tCardW*index+30, gridY+15, 90, 90, 51+180, 78);
					g.fillRect(gridX+tCardW*index+70, gridY+100, 10, 40);
				} else {
					g.fillOval(gridX+tCardW*index+40, gridY+90, 35, 35);
					g.fillOval(gridX+tCardW*index+75, gridY+90, 35, 35);
					g.fillOval(gridX+tCardW*index+58, gridY+62, 35, 35);
					g.fillRect(gridX+tCardW*index+70, gridY+75, 10, 70);
				}
				
				//-------------------------
				index++;
			}
			
			if (rand_dealer == true || mai_joci_q == true) {
				//carti dealer
				index = 0;
				for (Card c : dCards) {
					g.setColor(Color.white);
					g.fillRect(gridX+spatiere+tCardW*index+rotunjire, gridY+spatiere+200, cardW-rotunjire*2, cardH);
					g.fillRect(gridX+spatiere+tCardW*index, gridY+spatiere+rotunjire+200, cardW, cardH-rotunjire*2);
					g.fillOval(gridX+spatiere+tCardW*index, gridY+spatiere+200, rotunjire*2, rotunjire*2);
					g.fillOval(gridX+spatiere+tCardW*index, gridY+spatiere+cardH-rotunjire*2+200, rotunjire*2, rotunjire*2);
					g.fillOval(gridX+spatiere+tCardW*index+cardW-rotunjire*2, gridY+spatiere+200, rotunjire*2, rotunjire*2);
					g.fillOval(gridX+spatiere+tCardW*index+cardW-rotunjire*2, gridY+spatiere+cardH-rotunjire*2+200, rotunjire*2, rotunjire*2);
					
					g.setFont(fontCarte);
					if (c.shape.equalsIgnoreCase("Rosie") || c.shape.equalsIgnoreCase("Romb")) {
						g.setColor(Color.red);
					} else {
						g.setColor(Color.black);
					}
					
					g.drawString(c.simbol, gridX+spatiere+tCardW*index+rotunjire, gridY+spatiere+cardH-rotunjire+200);
					
					if (c.shape.equalsIgnoreCase("Rosie")) {
						g.fillOval(gridX+tCardW*index+42, gridY+70+200, 35, 35);
						g.fillOval(gridX+tCardW*index+73, gridY+70+200, 35, 35);
						g.fillArc(gridX+tCardW*index+30, gridY+90+200, 90, 90, 51, 78);
					} else if (c.shape.equalsIgnoreCase("Romb")) {
						rombX[0] = gridX+tCardW*index+75;
						rombX[1] = gridX+tCardW*index+50;
						rombX[2] = gridX+tCardW*index+75;
						rombX[3] = gridX+tCardW*index+100;
						rombY[0] = gridY+60+200;
						rombY[1] = gridY+100+200;
						rombY[2] = gridY+140+200;
						rombY[3] = gridY+100+200;
						g.fillPolygon(rombX, rombY, 4);
					} else if (c.shape.equalsIgnoreCase("Neagra")) {
						g.fillOval(gridX+tCardW*index+42, gridY+90+200, 35, 35);
						g.fillOval(gridX+tCardW*index+73, gridY+90+200, 35, 35);
						g.fillArc(gridX+tCardW*index+30, gridY+15+200, 90, 90, 51+180, 78);
						g.fillRect(gridX+tCardW*index+70, gridY+100+200, 10, 40);
					} else {
						g.fillOval(gridX+tCardW*index+40, gridY+90+200, 35, 35);
						g.fillOval(gridX+tCardW*index+75, gridY+90+200, 35, 35);
						g.fillOval(gridX+tCardW*index+58, gridY+62+200, 35, 35);
						g.fillRect(gridX+tCardW*index+70, gridY+75+200, 10, 70);
					}
					
					//-------------------------
					index++;
				}
				
				g.setColor(Color.yellow);
				g.setFont(fontIntrebare);
				g.drawString("Totalul tau: ", gridX+gridW+0, gridY+40);
				if (pMaxTotal <= 21) {
					g.drawString(Integer.toString(pMaxTotal), gridX+gridW+60, gridY+120);
				} else {
					g.drawString(Integer.toString(pMinTotal), gridX+gridW+60, gridY+120);
				}
				g.drawString("Totalul dealerului: ", gridX+gridW+0, gridY+240);
				if (dMaxTotal <= 21) {
					g.drawString(Integer.toString(dMaxTotal), gridX+gridW+60, gridY+320);
				} else {
					g.drawString(Integer.toString(dMinTotal), gridX+gridW+60, gridY+320);
				}
			}
			
		}
		
	}
	
	public class Move implements MouseMotionListener {

		@Override
		public void mouseDragged(MouseEvent arg0) {
			
		}

		@Override
		public void mouseMoved(MouseEvent arg0) {
			
		}
		
	}
	
	public class Click implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			
		}
		
	}
	
	public class actHit implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (tragi_stai_q == true) {
				
				int tempMax = 0;
				if (pMaxTotal <= 21) {
					tempMax = pMaxTotal;
				} else {
					tempMax = pMinTotal;
				}
				String mess = ("Ai tras o carte! (total: " + Integer.toString(tempMax) + ")");
				Log.add(new Message(mess, "Player"));
				
				tempC = rand.nextInt(52);
				while (Cards.get(tempC).folosit == true) {
					tempC = rand.nextInt(52);
				}
				pCards.add(Cards.get(tempC));
				Cards.get(tempC).setFolosit();
			}
		}
		
	}
	
	public class ActStay implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (tragi_stai_q == true) {
				
				int tempMax = 0;
				if (pMaxTotal <= 21) {
					tempMax = pMaxTotal;
				} else {
					tempMax = pMinTotal;
				}
				String mess = ("Ai ales sa stai! (total: " + Integer.toString(tempMax) + ")");
				Log.add(new Message(mess, "Player"));
				
				tragi_stai_q = false;
				rand_dealer = true;
			}
		}
		
	}
	
	public class ActYes implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			for (Card c : Cards) {
				c.setNefolosit();
			}
			
			pCards.clear();
			dCards.clear();
			Log.clear();
			
			mai_joci_q = false;
			tragi_stai_q = true;
			
			tempC = rand.nextInt(52);
			pCards.add(Cards.get(tempC));
			Cards.get(tempC).setFolosit();
			
			tempC = rand.nextInt(52);
			while (Cards.get(tempC).folosit == true) {
				tempC = rand.nextInt(52);
			}
			dCards.add(Cards.get(tempC));
			Cards.get(tempC).setFolosit();
			
			tempC = rand.nextInt(52);
			while (Cards.get(tempC).folosit == true) {
				tempC = rand.nextInt(52);
			}
			pCards.add(Cards.get(tempC));
			Cards.get(tempC).setFolosit();
			
			tempC = rand.nextInt(52);
			while (Cards.get(tempC).folosit == true) {
				tempC = rand.nextInt(52);
			}
			dCards.add(Cards.get(tempC));
			Cards.get(tempC).setFolosit();
			
		}
		
	}
	
	public class ActNo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Main.terminator = true;
			dispose();
		}
		
	}
	
}

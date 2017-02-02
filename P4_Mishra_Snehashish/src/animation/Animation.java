package animation;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * Represents the class where Animation occurs
 * @author Snehashish Mishra
 */
public class Animation
{
	
    /*****************************************************************
     * This methods draws the animation. It is called repeatedly 
     * as the animation proceeds. It draws to g how the animation 
     * should look like after t milliseconds have passed.
     * 
     * @param g
     *            Graphics object on which to draw
     * @param t
     *            Number of milliseconds that have passed since 
     *            the animation has started
     ******************************************************************/
    public static void paintFrame (Graphics g, int t)
    {
    	
    	background(g);
    	cloud1(g, t);
    	cloud2(g, t);
    	powerUp(g, t);
    	
    	brickBox(g, 90, 210);
    	coinBox(g, 120, 210);
    	brickBox(g, 150, 210);
    	coinBox(g, 180, 210);
    	brickBox(g, 210, 210);
    	
    	mario(g, t);
    	luigi(g, t);

    }
    
    /**********************************************
     * This methods draws and animates the powerUp
     * mushroom
     **********************************************/
	public static void powerUp(Graphics g, int t) 
	{
		if(t < 9000)
		{
			int x = 125, y = 226; 
			
			//Mushroom top color change
			if (t / 100 % 2 == 0)
	        {
				for(int i = 1; i < 50; i++)
				{
					g.setColor(new Color(249-i, 0, 0));
				}
	        }
			else
			{
				g.setColor(new Color(249, 220, 52));
			}
			
			mushroomTopAnimate(g, x, y, t);  	//Animate mushroom top
			
			//Mushroom stump color change
			g.setColor(Color.WHITE);
			if (t / 100 % 2 == 0)
	        {
				for(int i = 1; i < 50; i++)
				{
					g.setColor(new Color(255-i, 255-i, 255-i));
				}
	        }
			else
			{
				g.setColor(Color.WHITE);
			}
			
			mushroomStumpAnimate(g, x, y, t);	//Animate mushroom stump
		}	
	}
	
	/***********************************************************************
	 * This method animates the mushroom stump
	 ***********************************************************************/
	public static void mushroomStumpAnimate(Graphics g, int x, int y, int t) 
	{
		//Each if() handles a certain segment of 
		//animation for the mushroom stump
		if(t < 300)
		{
			g.fillRoundRect(x, y-t/10, 20, 10, 2, 10);	
			g.drawRoundRect(x, y-t/10, 20, 10, 2, 10);
		}
		if(t >= 300 && t < 1150)
		{
			g.fillRoundRect(125 + (t-300)/8, 200, 20, 10, 2, 10);	
			g.drawRoundRect(125 + (t-300)/8, 200, 20, 10, 2, 10);
		}
		if(t >= 1150 && t < 1750)
		{
			g.fillRoundRect(250, 200 + (t-1150)/5, 20, 10, 2, 10);	
			g.drawRoundRect(250, 200 + (t-1150)/5, 20, 10, 2, 10);
		}
	}

	/**********************************************************************
	 * This method animates the mushroom top
	 **********************************************************************/
	public static void mushroomTopAnimate(Graphics g, int x, int y, int t) 
	{
		//Each if() handles a certain segment of 
		//animation for the mushroom top
		if(t < 300)
		{
			g.fillOval(x-2, (y-17)-t/10, 23, 20);  
			g.drawOval(x-2, (y-17)-t/10, 23, 20);	
		}
		
		if(t >= 300 && t < 1150)
		{
			g.fillOval((125-2) + (t-300)/8, (200-17), 23, 20); 
			g.drawOval((125-2) + (t-300)/8, (200-17), 23, 20);
		}
		
		if(t >= 1150 && t < 1750)
		{
			g.fillOval((250-2), (200-17) + (t-1150)/5, 23, 20); 
			g.drawOval((250-2), (200-17) + (t-1150)/5, 23, 20);
		}	
	}

	/*********************************************
	 * This method draws and animates the 
	 * first cloud
	 *********************************************/
	public static void cloud1(Graphics g, int t) 
	{
		int x = 300;
		int y = 50;
		
		g.setColor(new Color(255, 255, 255));
		
		//Animates the cloud
		if(t < 15000)
		{
			x = 300 - t / 10;
			if(t > 2000 && t < 15000)
			{
				x = 50 + t / 30; 
			}
			
		}
		
		//Draws the Cloud
		g.fillRoundRect(x, y, 90, 30, 20, 30);
		g.drawRoundRect(x, y, 90, 30, 20, 30);
		g.fillOval(x+30, y-15, 40, 30);
		g.drawOval(x+30, y-15, 40, 30);
		
		//Draws the Left Eye
		g.setColor(Color.BLACK);
		g.fillRoundRect(x + 40, y-5, 2, 8, 1, 8);
		g.drawRoundRect(x + 40, y-5, 2, 8, 1, 8);
		
		//Draws the Right Eye
		g.fillRoundRect(x + 50, y-5, 2, 8, 1, 8);
		g.drawRoundRect(x + 50, y-5, 2, 8, 1, 8);	
	}

	/********************************************
	 * This Method draws and animates the
	 * second cloud
	 ********************************************/
	public static void cloud2(Graphics g, int t) 
	{
		int x = -80;
		int y = 150;
		
		//Color Changing code
		if (t / 100 % 2 == 0)
        {
			for(int i = 1; i < 50; i++)
			{
				g.setColor(new Color(255, 255, 255));
			}
        }
        else if(t / 200 % 2 == 0)
        {
        	for(int i = 1; i < 50; i++)
			{
				g.setColor(new Color(200-i, 200-i, 200-i));
			}
        }
        else
        {
        	g.setColor(new Color(249, 220, 52));
        }
		
		//Animates the cloud
		if(t < 16000)
		{
			x = -80 + t/6;
			if(t > 2000 && t < 10000)
			{
				x = 260 - (t-2000) / 20; 
			}
			
		}
		
		//Draws the Cloud
		g.fillRoundRect(x, y, 90, 30, 20, 30);
		g.drawRoundRect(x, y, 90, 30, 20, 30);
		g.fillOval(x+30, y-15, 40, 30);
		g.drawOval(x+30, y-15, 40, 30);
		g.fillOval(x+3, y+7, 47, 30);
		g.drawOval(x+3, y+7, 47, 30);
		g.fillOval(x+47, y+7, 40, 30);
		g.drawOval(x+47, y+7, 40, 30);
		
		//Draws the Left Eye
		g.setColor(Color.BLACK);
		g.fillRoundRect(x+40, y+5, 2, 12, 1, 12);
		g.drawRoundRect(x+40, y+5, 2, 12, 1, 12);
		
		//Draws the Right Eye
		g.fillRoundRect(x+50, y+5, 2, 12, 1, 12);
		g.drawRoundRect(x+50, y+5, 2, 12, 1, 12);
	}

	/***************************************************
	 * This methods draws a single yellow coin box
	 ***************************************************/
	public static void coinBox(Graphics g, int x, int y) 
	{
		g.setColor(new Color(228, 165, 47));
    	g.fillRect(x, y, 30, 30);
    	g.drawRect(x, y, 30, 30);
	}
	
	/*******************************************
	 * This method draws the green exit pipe
	 *******************************************/
	public static void drawPipe(Graphics g) 
	{
		//Pipe body
		g.setColor(new Color(13, 154, 89));
		g.fillRect(350, 300, 50, 50);	
		g.drawRect(350, 300, 50, 50);
		
		//Pipe shadow
		g.setColor(new Color(14, 134, 77));
		g.fillRect(344, 300, 6, 50);
		g.drawRect(344, 300, 6, 50);
		
		//Pipe top
		g.setColor(new Color(13, 154, 89));
		g.fillRect(323, 290, 20, 60);
		g.drawRect(323, 290, 20, 60);
		
	}

	/****************************************************
	 * This method draws a single brick box
	 ****************************************************/
	public static void brickBox(Graphics g, int x, int y) 
	{
		g.setColor(new Color(172, 93, 0));
    	g.fillRect(x, y, 30, 30);
    	g.drawRect(x, y, 30, 30);
		
	}
	
	/************************************************
	 * This method draws ALL the static background
	 * elements
	 ************************************************/
	public static void background(Graphics g) 
	{	
		//Green Top Ground Layer
		g.setColor(new Color(73, 106, 6));
		g.fillRect(0, 350, 400, 10);
		g.drawRect(0, 350, 400, 10);
		
		//Dark Ground Layers
		for(int h = 20; h <= 60; h += 20)
		{
			g.setColor(new Color(187, 105, 29));
			g.fillRect(0, 340 + h, 400, 10);
			g.drawRect(0, 340 + h, 400, 10);
		}
		
		//Light Ground Layers
		for(int h = 20; h <= 40; h += 20)
		{
			g.setColor(new Color(221, 134, 45));
			g.fillRect(0, 350 + h, 400, 10);
			g.drawRect(0, 350 + h, 400, 10);
		}
		
		//Sky
		g.setColor(new Color(164, 164, 255));
		g.fillRect(0, 0, 400, 350);
		g.drawRect(0, 0, 400, 350);
		
		//Hill 1
		g.setColor(new Color(204, 162, 215));  
		g.fillRect(50, 255, 200, 95);
		g.drawRect(50, 255, 200, 95);
		g.fillOval(50, 170, 200, 160);
		g.drawOval(50, 170, 200, 160);
		
		//Hill 2
		g.setColor(new Color(173, 150, 150));  
		g.fillRect(0, 150, 100, 200);
		g.drawRect(0, 150, 100, 200);
		g.fillOval(0, 110, 100, 90);
		g.drawOval(0, 110, 100, 90);
		
	}
	
	/***********************************************
	 * This method draws and animates the 
	 * minimalistic Mario
	 ***********************************************/
	public static void mario(Graphics g, int t) 
	{
		if(t < 15000)
		{
			int x = 5;
			int y = 320;
			
			//Animation segment 1
			if(t < 1000)
			{
				g.setColor(new Color(216, 41, 0));
				g.fillRect((x+5) + t/4, y, 25, 30);
				g.drawRect((x+5) + t/4, y, 25, 30);
			}
			
			//Animation segment 2
			if(t >= 1000 && t <= 1800)
			{
				g.setColor(new Color(216, 41, 0));
				g.fillRect((250 + 5), 320, 25, 30);
				g.drawRect((250 + 5), 320, 25, 30);
			}
			
			//Animation segment 3 (Mario Expanding)
			if(t >= 1800 && t < 2000)
			{
				g.setColor(new Color(216, 41, 0));
				g.fillRect((250+5) - t/100, 320 - (t/100), 25+t/200, 30+t/100);
				g.drawRect((250+5) - t/100, 320 - (t/100), 25+t/200, 30+t/100);
			}
			
			//Animation segment 4
			if(t >= 2000 && t < 2500)
			{
				g.setColor(new Color(216, 41, 0));
				g.fillRect((230 + 5), 300, 35, 50); 
				g.drawRect((230 + 5), 300, 35, 50);
			}
			
			//Animation segment 5
			if(t >= 2500 && t <= 2550)
			{
				g.setColor(new Color(216, 41, 0));
				g.fillRect((230+5) + (t-2500), 300, 35, 50);  
				g.drawRect((230+5) + (t-2500), 300, 35, 50);	
			}
			
			drawPipe(g);
		}		
	}
	
	/********************************************
	 * This method draws and animates the 
	 * minimalistic Luigi
	 ********************************************/
	public static void luigi(Graphics g, int t) 
	{
		if(t > 4500)	//Entry condition
		{
			
			int x = 0;
			int y = 320;
			
			//Animation segment 1
			if(t >= 4500 && t < 6000)
			{
				//Draws and animates Luigi
				g.setColor(new Color(64, 166, 64));
				g.fillRect((x+5) + (t-4500)/10, y-5, 25, 35);
				g.drawRect((x+5) + (t-4500)/10, y-5, 25, 35);
				
				//Draws and animates his mustache
				g.setColor(Color.BLACK);
				g.fillRect((x+18) + (t-4500)/10, y+6, 12, 4);
				g.drawRect((x+18) + (t-4500)/10, y+6, 12, 4);
			}
			
			//Animation segment 2
			if(t >= 6000 && t < 11000)
			{
				//Draws and animates Luigi
				g.setColor(new Color(64, 166, 64));
				g.fillRect((150 + 5), y-5, 25, 35);
				g.drawRect((150 + 5), y-5, 25, 35);
				
				//Draws and animates his mustache
				g.setColor(Color.BLACK);
				g.fillRect((150 + 18), y+6, 12, 4);
				g.drawRect((150 + 18), y+6, 12, 4);
				
				//Dialogs
				Font newFont1 = new Font ("Helvetica", Font.BOLD, 16); 
				g.setFont(newFont1);
				
				if(t >=6000 && t<7500)			//Dialog 1
				{
					g.drawString("Mario ??", 140, 300);
				}
				if(t >= 7500 && t < 8900)		//Dialog 2
				{
					g.drawString("!!!", 160, 300);
				}
				if(t >= 8900 && t < 11000)		//Dialog 3
				{
					g.drawString("Wait! You forgot your mustache!!", 60, 300);
				}
			}
			
			//Animation segment 3
			if(t >= 11000 && t < 12000)
			{
				//Draws and animates Luigi
				g.setColor(new Color(64, 166, 64));
				g.fillRect((150+5) + (t-11000)/5, y-5, 25, 35);
				g.drawRect((150+5) + (t-11000)/5, y-5, 25, 35);
				
				//Draws and animates his mustache
				g.setColor(Color.BLACK);
				g.fillRect((150+18) + (t-11000)/5, y+6, 12, 4);
				g.drawRect((150+18) + (t-11000)/5, y+6, 12, 4);
			}
		}
		drawPipe(g);
	}
}							

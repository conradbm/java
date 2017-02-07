import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

/*
 MY CONCLUSION ON GRAPHICS:
 
 I believe that graphics2D.drawLine throws the line we want to draw ..
 .. into the repaint() queue as a Graphics g object
 
 I believe graphics2D.drawLine places a Graphics g object in the queue ..
 .. that we draw on to our image
 
 */

class PadDraw extends JComponent{
		
	Image image;
	Graphics2D graphics2D;
	int currentX, currentY, oldX, oldY;
	private BufferedImage paintImage = new BufferedImage(500, 400, BufferedImage.TYPE_3BYTE_BGR);

	public PadDraw(){

		// Mouse clicked
		this.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				oldX = e.getX();
				oldY = e.getY();
				
				if(graphics2D == null)
					initImage();
				graphics2D.drawOval(oldX, oldY,1, 1);
				repaint();
			}
		});
		
		// Mouse dragged
		addMouseMotionListener(new MouseMotionAdapter(){
			public void mouseDragged(MouseEvent e){
			
				// New positions
				currentX = e.getX();
				currentY = e.getY();

				// Instantiate our graphics2D object's relationship with our image object
				if(graphics2D == null)
					initImage();
					
				// Place the correct line into the repaint() queue as a Graphics g object
				graphics2D.drawLine(oldX, oldY, currentX, currentY);
				repaint();
				
				// Old positions
				oldX = currentX;
				oldY = currentY;
			}

		});
	}

	//Accessed by repaint() method
	public void paintComponent(Graphics g){
		
		// Pull the last item out of our repaint() queue, and draw it on our image
		g.drawImage(image, 0, 0, null);
	}

	private void initImage(){
		
		// create our graphics2D's relationship to our image
		image = createImage(this.getSize().width, this.getSize().height);
		graphics2D = (Graphics2D)image.getGraphics();
	}
	
	public void save() throws IOException{
		paintImage = (BufferedImage) image;
        ImageIO.write(paintImage, "PNG", new File("filename.png"));
    }

    public void load() throws IOException {
    	paintImage = (BufferedImage) image;
        paintImage = ImageIO.read(new File("filename.png"));
        // update panel with new paint image
        repaint();
    }

	public void clear(){
		graphics2D.setPaint(Color.white);
		graphics2D.fillRect(0, 0, getSize().width, getSize().height);
		graphics2D.setPaint(Color.black);
		repaint();
	}

	public void red(){
		graphics2D.setPaint(Color.red);
		repaint();
	}
	public void green(){
		graphics2D.setPaint(Color.green);
		repaint();
	}
	public void blue(){
		graphics2D.setPaint(Color.blue);
		repaint();
	}
}
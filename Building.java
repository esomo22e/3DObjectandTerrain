import javax.media.opengl.GL;

import com.sun.opengl.util.GLUT;
import com.sun.opengl.util.texture.Texture;

public class Building {

	private float height, width, depth;
	private float xOffset, yOffset;
	
	public Building(float height, float width, float depth, float xOffset, float yOffset){
		this.height = height;
		this.width = width;
		this.depth = depth;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	
	public void render(Texture t, GL gl, GLUT glut){
		gl.glPushMatrix();
		gl.glTranslatef(xOffset,0f,yOffset);
		//gl.glBegin(gl.GL_QUADS);   
		//gl.glTranslatef(1,1,0);// Begin drawing the color cube with 6 quads
			glut.glutSolidCube(1.0f);
	      // Top face (y = 1.0f)
	      // Define vertices in counter-clockwise (CCW) order with normal pointing out
	      //gl.glColor3f(0.0f, 1.0f, 0.0f);     // Green
	      //gl.glVertex3f( height, width, -depth);//gl.glVertex3f( 1.0f, 1.0f, -1.0f);
	      //gl.glVertex3f(-height, width, -depth);//gl.glVertex3f(-1.0f, 1.0f, -1.0f);
	      //gl.glVertex3f(-height, width,  depth);//gl.glVertex3f(-1.0f, 1.0f,  1.0f);//***changed the last -1.0f to 1.0f
	      //gl.glVertex3f( height, width,  depth);//gl.glVertex3f( 1.0f, 1.0f,  1.0f);
	 
	      // Bottom face (y = -1.0f)
	      //gl.glColor3f(1f, 0.5f, 0.0f);     // Orange
	      //gl.glVertex3f( height, -width,  depth);//gl.glVertex3f( 1.0f, -1.0f,  1.0f);
	      //gl.glVertex3f(-height, -width,  depth);//gl.glVertex3f(-1.0f, -1.0f,  1.0f);
	      //gl.glVertex3f(-height, -width, -depth);//gl.glVertex3f(-1.0f, -1.0f, -1.0f);
	      //gl.glVertex3f( height, -width, -depth);//gl.glVertex3f( 1.0f, -1.0f, -1.0f);
	 
	      // Front face  (z = 1.0f)
	      //gl.glColor3f(1.0f, 0.0f, 0.0f);     // Red
	      //gl.glVertex3f( height,  width, depth);//gl.glVertex3f( 1.0f,  1.0f, 1.0f);
	      //gl.glVertex3f(-height,  width, depth);//gl.glVertex3f(-1.0f,  1.0f, 1.0f);
	      //gl.glVertex3f(-height, -width, depth);//gl.glVertex3f(-1.0f, -1.0f, 1.0f);
	     // gl.glVertex3f( height, -width, depth);//gl.glVertex3f( 1.0f, -1.0f, 1.0f);
	 
	      // Back face (z = -1.0f)
	      //gl.glColor3f(1.0f, 1.0f, 0.0f);     // Yellow
	      //gl.glVertex3f( height, -width, -depth);//gl.glVertex3f( 1.0f, -1.0f, -1.0f);
	      //gl.glVertex3f(-height, -width, -depth);//gl.glVertex3f(-1.0f, -1.0f, -1.0f);
	      //gl.glVertex3f(-height,  width, -depth);//gl.glVertex3f(-1.0f,  1.0f, -1.0f);
	      //gl.glVertex3f( height,  width, -depth);//gl.glVertex3f( 1.0f,  1.0f, -1.0f);
	 
	      // Left face (x = -1.0f)
	     // gl.glColor3f(0.0f, 0.0f, 1.0f);     // Blue
	     // gl.glVertex3f(-height,  width,  depth);//gl.glVertex3f(-1.0f,  1.0f,  1.0f);
	      //gl.glVertex3f(-height,  width, -depth);//gl.glVertex3f(-1.0f,  1.0f, -1.0f);
	      //gl.glVertex3f(-height, -width, -depth);//gl.glVertex3f(-1.0f, -1.0f, -1.0f);
	      //gl.glVertex3f(-height, -width,  depth);//gl.glVertex3f(-1.0f, -1.0f,  1.0f);
	 
	      // Right face (x = 1.0f)
	      //gl.glColor3f(1.0f, 0.0f, 1.0f);     // Magenta
	      //gl.glVertex3f(height,  width, -depth);//gl.glVertex3f(1.0f,  1.0f, -1.0f);
	      //gl.glVertex3f(height,  width,  depth);//gl.glVertex3f(1.0f,  1.0f,  1.0f);
	      //gl.glVertex3f(height, -width,  depth);//gl.glVertex3f(1.0f, -1.0f,  1.0f);
	      //gl.glVertex3f(height, -width, -depth);//gl.glVertex3f(1.0f, -1.0f, -1.0f);
	   //gl.glEnd();  // End of drawing color-cube
	   gl.glPopMatrix();
	}
	
	
}

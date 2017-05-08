

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.media.opengl.GL;
import javax.media.opengl.GLException;
import javax.media.opengl.glu.GLU;

import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureIO;

public class Skybox {
	
	public Skybox(){
		
	}
	
	
	
	public void render(GL gl, GLU glu, float centerx, float centery, float centerz, Texture[] textures, int height, int width){
		 
		 Texture texture = textures[0];
		
		 gl.glPushMatrix();
		 
		    gl.glDisable(gl.GL_LIGHTING);
		    
		 // Render the top quad
		    texture = textures[5];
		    gl.glTranslatef(0, 100 ,0);
		    gl.glTranslatef(-width/2, 0f, -height/2);
		    texture.bind();
		    texture.enable();
		    gl.glBegin(gl.GL_QUADS);
		        gl.glTexCoord2f(0, 1); gl.glVertex3f(  0,  0, width );
		        gl.glTexCoord2f(0, 0); gl.glVertex3f(  0,  0, 0);
		        gl.glTexCoord2f(1, 0); gl.glVertex3f(  height, 0, 0 );
		        gl.glTexCoord2f(1, 1); gl.glVertex3f(  height,  0, width);
		    gl.glEnd();
		    texture.disable();
		    
		    
		    // Render the left quad
		    texture = textures[1];
		    gl.glTranslatef(0, -100 ,0);
		    texture.bind();
		    texture.enable();
		    gl.glBegin(gl.GL_QUADS);
		        gl.glTexCoord2f(0, 1); gl.glVertex3f(  0,  -5, 0 );
		        gl.glTexCoord2f(0, 0); gl.glVertex3f(  0,  100, 0 );
		        gl.glTexCoord2f(1, 0); gl.glVertex3f(  0, 100, width );
		        gl.glTexCoord2f(1, 1); gl.glVertex3f(  0,  -5, width);
		    gl.glEnd();
		    texture.disable();
		 
		    
		    // Render the back quad
		    texture = textures[4];
		    texture.bind();
		    texture.enable();
		    gl.glBegin(gl.GL_QUADS);
		        gl.glTexCoord2f(0, 1); gl.glVertex3f( height,  -5, 0 );
		        gl.glTexCoord2f(0, 0); gl.glVertex3f( height,  100, 0 );
		        gl.glTexCoord2f(1, 0); gl.glVertex3f(  0, 100, 0 );
		        gl.glTexCoord2f(1, 1); gl.glVertex3f(  0,  -5, 0);
		    gl.glEnd();
		    texture.disable();
		    
		    
		 // Render the front quad
		    gl.glTranslatef(width/2, 0f, height/2);
		    gl.glTranslatef(width/2, 0f, height/2);
		    texture = textures[2];
		    texture.bind();
		    texture.enable();
		    gl.glBegin(gl.GL_QUADS);
		        gl.glTexCoord2f(0, 1); gl.glVertex3f( -height,  -5, 0 );
		        gl.glTexCoord2f(0, 0); gl.glVertex3f( -height,  100, 0 );
		        gl.glTexCoord2f(1, 0); gl.glVertex3f(  0, 100, 0 );
		        gl.glTexCoord2f(1, 1); gl.glVertex3f(  0,  -5, 0);
		    gl.glEnd();
		    texture.disable();
		    
		    // Render the right quad
		    gl.glTranslatef(-5, 0f, 0);
		    texture = textures[3];
		    texture.bind();
		    texture.enable();
		    gl.glBegin(gl.GL_QUADS);
		        gl.glTexCoord2f(0, 1); gl.glVertex3f( 0,  -5, 0 );
		        gl.glTexCoord2f(0, 0); gl.glVertex3f( 0,  100, 0 );
		        gl.glTexCoord2f(1, 0); gl.glVertex3f(  0, 100, -width );
		        gl.glTexCoord2f(1, 1); gl.glVertex3f(  0,  -5, -width);
		    gl.glEnd();
		    texture.disable();
		    
		    
		    gl.glEnable(gl.GL_LIGHTING);
		    
		    gl.glPopMatrix();
	}
}

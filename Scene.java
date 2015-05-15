import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.glu.GLU;
import javax.vecmath.Vector3f;

import com.sun.opengl.util.GLUT;
import com.sun.opengl.util.texture.Texture;


public class Scene {

	private static Vector3f bunnyPos;
	
	public static Vector3f getBunnyPos() {
		return bunnyPos;
	}


	


	public Scene(){
		
		
	}
	
	
	public static void render(GL gl, objModel tree1, objModel tree2, objModel bunny, Texture[] textures){
		
		bunnyPos = new Vector3f(21f, -0.5f,0);
		Texture t;
		
		gl.glPushMatrix();
		
		  gl.glScalef(5, 5, 5);
		  
		  t = textures[7];
		  t.bind();
		  t.enable();
		  float[] bunnyDiffuse = {0.7f,0.7f, 0f};
			 
			 gl.glMaterialfv( GL.GL_FRONT, GL.GL_DIFFUSE, bunnyDiffuse, 0);
		 gl.glTranslatef(bunnyPos.x, bunnyPos.y, bunnyPos.z);
		 // gl.glTranslatef(21f,-0.5f,0);
		  bunny.Draw(gl);
		  gl.glTranslatef(-21f,0,0);
		  
		  t.disable();
		  
		  t = textures[8];
		  t.bind();
		  t.enable();
		  
		  
		  gl.glTranslatef(20f,0,1);
		  gl.glRotatef(180,0,1,0);
		  bunny.Draw(gl);
		  gl.glRotatef(-180,0,1,0);
		  gl.glTranslatef(-20f,0.5f,-1);
		  
		  t.disable();
		  
		  gl.glScalef(.2f, .2f, .2f);
		  gl.glScalef(50, 50, 50);
		gl.glPushMatrix();
		  // white light at the eye
		    float light0_position[] = { 0,1, 1, 1 };
		    float light0_diffuse[] = { 1, 1, 1, 1 };
		    float light0_specular[] = { 1, 1, 1, 1 };
		    gl.glLightfv( GL.GL_LIGHT0, GL.GL_POSITION, light0_position, 0);
		    gl.glLightfv( GL.GL_LIGHT0, GL.GL_DIFFUSE, light0_diffuse, 0);
		    gl.glLightfv( GL.GL_LIGHT0, GL.GL_SPECULAR, light0_specular, 0);

		    //red light
		    float light1_position[] = { -.1f, .5f, 0, 0 };
		    float light1_diffuse[] = { .6f, .05f, .05f, 1 };
		    float light1_specular[] = { .6f, .05f, .05f, 1 };
		    gl.glLightfv( GL.GL_LIGHT1, GL.GL_POSITION, light1_position, 0);
		    gl.glLightfv( GL.GL_LIGHT1, GL.GL_DIFFUSE, light1_diffuse, 0);
		    gl.glLightfv( GL.GL_LIGHT1, GL.GL_SPECULAR, light1_specular, 0);

		    //blue light
		    float light2_position[] = { .1f, .1f, 0, 0 };
		    float light2_diffuse[] = { .05f, .05f, .6f, 1 };
		    float light2_specular[] = { .05f, .05f, .6f, 1 };
		    gl.glLightfv( GL.GL_LIGHT2, GL.GL_POSITION, light2_position, 0);
		    gl.glLightfv( GL.GL_LIGHT2, GL.GL_DIFFUSE, light2_diffuse, 0);
		    gl.glLightfv( GL.GL_LIGHT2, GL.GL_SPECULAR, light2_specular, 0);

		  float[] treeDiffuse = {0.7f,0.7f, 0f};
			 
			 gl.glMaterialfv( GL.GL_FRONT, GL.GL_DIFFUSE, treeDiffuse, 0);
		  gl.glTranslatef(-1,0.5f,0);
		  tree1.Draw(gl);
		  gl.glTranslatef(1,0,0);
		  
		  gl.glTranslatef(2,0,-1);
		  tree1.Draw(gl);
		  gl.glTranslatef(-2,0,1);
		  
		  gl.glTranslatef(3,0,0);
		  tree1.Draw(gl);
		  gl.glTranslatef(-3,0,0);

		  
		  gl.glTranslatef(-3,0,2);
		  tree1.Draw(gl);
		  gl.glTranslatef(3,0,-2);
		  
		  
		  gl.glTranslatef(1,0,2.5f);
		  tree1.Draw(gl);
		  gl.glTranslatef(-1,0,-2.5f);
		  
		  gl.glTranslatef(0,-0.2f,3);
		  tree2.Draw(gl);
		  gl.glTranslatef(0,0,-3);
		  
		  gl.glTranslatef(-2,0,-2);
		  tree2.Draw(gl);
		  gl.glTranslatef(2,0,2);
		  
		  gl.glTranslatef(2,0,-2);
		  tree2.Draw(gl);
		  gl.glTranslatef(-2,0,2);
		  
		  gl.glTranslatef(-2,0,2);
		  tree2.Draw(gl);
		  gl.glTranslatef(2,0,-2);
		  
		  gl.glTranslatef(-1,0.3f,-1);
		  tree2.Draw(gl);
		  gl.glTranslatef(1,-0.3f,1);
		  
		  gl.glTranslatef(1,0,3);
		  tree2.Draw(gl);
		  gl.glTranslatef(-1,-0.3f,-3);
		  
		  
		  
		  
		  
		  //gl.glScalef(0.1f, 0.1f, 0.1f);
		  gl.glPopMatrix();
		
		
	}

	
	
}

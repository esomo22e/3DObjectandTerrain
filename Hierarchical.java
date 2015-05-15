import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.*;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import javax.media.opengl.*;
import javax.media.opengl.glu.*;
import javax.swing.JFrame;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;



import com.sun.opengl.util.BufferUtil;
import com.sun.opengl.util.FPSAnimator;
import com.sun.opengl.util.GLUT;
import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureIO;

import java.util.ArrayList;

class Hierarchical extends JFrame implements GLEventListener, KeyListener, MouseListener, MouseMotionListener, ActionListener {

	/* This defines the objModel class, which takes care
	 * of loading a triangular mesh from an obj file,
	 * estimating per vertex average normal,
	 * and displaying the mesh.
	 */

	//Eunice
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_ESCAPE:
		case KeyEvent.VK_Q:
			System.exit(0);
			break;		
		case 37://left arrow
			rotateSpeed *= xMove;
			rotateT1 += -100f * animation_speed;
			sphere.getAccel().x += -1 ;
			sphere.getAccel().y += 0;//heightmap[(int) sphere.getPosition().x][(int) sphere.getPosition().z];
			sphere.getAccel().z += 0;
			//dir-= 10;
			//xMove -= 20* motionSpeed;
			//xMove -= dir * motionSpeed;
			//rotateSpeed *= xMove * animation_speed;
			//rotateT1 -= xMove;
		//canvas.display();
			break;
		case 39://right arrow
			rotateSpeed *= xMove;
			rotateT1 += 100f * animation_speed;
			sphere.getAccel().x += 1 ;
			sphere.getAccel().y += 0;//heightmap[(int) sphere.getPosition().x][(int) sphere.getPosition().z];
			sphere.getAccel().z += 0;
			//xMove += 20* motionSpeed;
			//dir +=10;
			//rotateSpeed *= xMove;
			//rotateT1 -= 3.1f * animation_speed;
			//canvas.display();
			break;	
		case 38://up arrow
			//zMove -= 50 * motionSpeed;
			rotateSpeed *= sphere.getAccel().z;
			rotateT5 += -100f * animation_speed;
			sphere.getAccel().x = 0 ;
			sphere.getAccel().y += 0;//terrain.getYpositionAt((int)(sphere.getPosition().x+200), (int)(sphere.getPosition().z+200));
			sphere.getAccel().z += -1;
			canvas.display();
		
			break;
		case 40://down arrow
			//zMove += 50 * motionSpeed;
			
			rotateSpeed *= sphere.getAccel().z;
			rotateT5 += 100f * animation_speed;
			sphere.getAccel().x += 0 ;
			sphere.getAccel().y += 0;//heightmap[(int) sphere.getPosition().x][(int) sphere.getPosition().z];;
			sphere.getAccel().z += 1;
			
			break;
		case 'r':
		case 'R':
		
			initViewParameters();
			break;
		case 'c':
		case 'C':
			sphereRot -=1;
			
			break;
		case 'v':
		case 'V':
		
			sphereRot+=1;
			break;
		case 'e':
		case 'E':
		
			zpos++;
			canvas.display();
			break;
		case 't':
		case 'T':
		
			zpos--;
			canvas.display();
			break;
		case 'w':
		case 'W':
			wireframe = ! wireframe;
			break;
		case 'b':
		case 'B':
			cullface = !cullface;
			break;
		case 'f':
		case 'F':
			flatshade = !flatshade;
		
			break;
		
		case 'a':
		case 'A':
			if (animator.isAnimating())
				animator.stop();
			else 
				animator.start();
			break;
		case '+':
		case '=':
			animation_speed *= 1.2f;
			break;
		case '-':
		case '_':
			animation_speed /= 1.2;
			break;
		default:
			
			
			break;
		}
		canvas.display();
	}
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
		case 37:
			
			sphere.getAccel().x = 0;
			sphere.getAccel().y = 0;
			sphere.getAccel().z = 0;
			
			sphere.getVel().x = 0;
			sphere.getVel().y = 0;
			sphere.getVel().z = 0;
			break;
		case 39:
			sphere.getAccel().x = 0;
			sphere.getAccel().y = 0;
			sphere.getAccel().z = 0;
			
			sphere.getVel().x = 0;
			sphere.getVel().y = 0;
			sphere.getVel().z = 0;
			break;	
		case 38:
			//zMove -= 50 * motionSpeed;
			//rotateSpeed *= zMove;
			//rotateT5 -= 40f * animation_speed;
			sphere.getAccel().x = 0;
			sphere.getAccel().y = 0;
			sphere.getAccel().z = 0;
			
			sphere.getVel().x = 0;
			sphere.getVel().y = 0;
			sphere.getVel().z = 0;
			break;
		case 40:
			sphere.getAccel().x = 0;
			sphere.getAccel().y = 0;
			sphere.getAccel().z = 0;
			
			sphere.getVel().x = 0;
			sphere.getVel().y = 0;
			sphere.getVel().z = 0;
			
			break;
		}
		
	}
	/* GL, display, model transformation, and mouse control variables */
	private final GLCanvas canvas;
	private GL gl;
	private final GLU glu = new GLU();	
	private final GLUT glut = new GLUT();
	private FPSAnimator animator;

	private int winW = 1000, winH = 1000;
	private boolean wireframe = false;
	private boolean cullface = true;
	private boolean flatshade = false;
	
	private float xpos = 0, ypos = 0, zpos = 0;
	private float centerx, centery, centerz;
	private float roth = 0, rotv = 0;
	private float znear, zfar;
	private int mouseX, mouseY, mouseButton;
	private float motionSpeed = 0, rotateSpeed;
	private float animation_speed = 1.0f;
	
	
	
	/* === YOUR WORK HERE === */
	
	private float rotateT1 = 0.0f;

	private float rotateT5 = 0.0f;
	

	private float xmin = -1f, ymin = -1f, zmin = -1f;
	private float xmax = 1f, ymax = 1f, zmax = 1f;	
	private float xMove = 0f, yMove = 0f, zMove = 0f;
	
	private float t = System.currentTimeMillis();
	private float lastTime;
	
   Sphere sphere = new Sphere(new Vector3f(0,0,0));
   
   //Maxine:
     private ImageReader im = new ImageReader("/Users/eesom_000/workspace/3dGame(ObjectAndTerrain)/Terrain/Heightmap_Example.jpg");
	 private double[][] heightmap = im.getHeightMap();
     private Terrain terrain = new Terrain(heightmap);
	 private Skybox skybox = new Skybox();
	 public Texture[] textures = new Texture[9];
	 private objModel tree1 = new objModel("/Users/eesom_000/workspace/3dGame(ObjectAndTerrain)/Obj Files/tree_aspen.obj");
	 private objModel tree2 = new objModel("/Users/eesom_000/workspace/3dGame(ObjectAndTerrain)/Obj Files/tree_conical.obj");
	 private objModel plant = new objModel("/Users/eesom_000/workspace/3dGame(ObjectAndTerrain)/Obj Files/plant.obj");
	 private objModel bunny = new objModel("/Users/eesom_000/workspace/3dGame(ObjectAndTerrain)/Obj Files/bunny2.obj");
	private objModel cactus = new objModel("/Users/eesom_000/workspace/3dGame(ObjectAndTerrain)/Obj Files/cactus.obj");
	//private objModel bird = new objModel("/Users/eesom_000/workspace/3dGame(ObjectAndTerrain)/Obj Files/objects/bird.obj");
    //
	float sphereRot = sphere.getRotY();
   float dir = 0;
   float speed = 0;
	public void display(GLAutoDrawable drawable) {
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		
		gl.glPolygonMode(GL.GL_FRONT_AND_BACK, wireframe ? GL.GL_LINE : GL.GL_FILL);	
		gl.glShadeModel(flatshade ? GL.GL_FLAT : GL.GL_SMOOTH);		
		if (cullface)
			gl.glEnable(GL.GL_CULL_FACE);
		else
			gl.glDisable(GL.GL_CULL_FACE);		
		
		gl.glLoadIdentity();
	
		//Eunice
		/* this is the transformation of the entire scene */
		//gl.glTranslatef(-sphere.getPosition().x,0,-sphere.getPosition().z);
		//Eunice
		gl.glRotated(-sphereRot, 0, 1.0f,0);
		gl.glTranslatef(-sphere.getPosition().x,0,0); //terrain movement to simulate camera
		gl.glTranslatef(-xpos, -sphere.getPosition().y, -zpos);
		
		gl.glTranslatef(-xpos, -ypos, -sphere.getPosition().z);
		//gl.glTranslatef(-xpos,-ypos, -zpos);
		
		
		//gl.glTranslatef(centerx, centery, centerz);
		//gl.glRotatef(0, 360.f - roth, 1.0f, 0);
		//gl.glRotatef(1.0f, rotv, 0, 0);
		//gl.glTranslatef(-centerx, -centery, -centerz);	
		
		  terrain.renderHeightMap(gl, textures[6], tree1, tree2);
		  skybox.render(gl, glu, centerx, centery, centerz, textures, terrain.getHeight(), terrain.getWidth());
		 Scene.render(gl, tree1, tree2, bunny, textures);
		 //
		
		//Eunice
		 
		gl.glPushMatrix();

	
		gl.glTranslatef(sphere.getPosition().x,   sphere.getPosition().y,sphere.getPosition().z);
		
		gl.glRotatef(sphereRot, 0,1, 0);
			gl.glRotatef(rotateT5, 1, 0, 0);//ball movements
			
			gl.glRotatef(rotateT1, 0, 1, 0);
			
			
			float[] sunDiffuse = {0.5f, 0.0f, .50f};
			gl.glLightf((int)sphere.getPosition().x, (int)sphere.getPosition().z, 15.f);
			 gl.glMaterialfv( GL.GL_FRONT, GL.GL_DIFFUSE, sunDiffuse, 0);
			
			 sphere.draw();
				t = 0.1f;
				lastTime = (float) 0.0;
				float dt = t - lastTime;
			 sphere.move(t, dt, terrain.getHeight(), terrain.getWidth(), heightmap, terrain);
		gl.glPopMatrix();
		/* increment example_rotateT */
		//if (animator.isAnimating())
		
		//rotateT1 += 3.1f * animation_speed;
	}	
	
	public Hierarchical() {
		super("Assignment 3 -- Hierarchical Modeling");
		canvas = new GLCanvas();
		canvas.addGLEventListener(this);
		canvas.addKeyListener(this);
		canvas.addMouseListener(this);
		canvas.addMouseMotionListener(this);
		animator = new FPSAnimator(canvas, 30);	// create a 30 fps animator
		getContentPane().add(canvas);
		setSize(winW, winH);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		animator.start();
		canvas.requestFocus();
	}
	
	public static void main(String[] args) {

		new Hierarchical();
	}
	
	public void init(GLAutoDrawable drawable) {
		
		
		try{
			for(int i=0; i<9; i++){ //load textures
				switch(i){
					case 0: textures[i] = TextureIO.newTexture(new File("/Users/eesom_000/workspace/3dGame(ObjectAndTerrain)/Textures/city Texture3.png"), true);
							textures[i].disable();
							break;
					case 1: textures[i] = TextureIO.newTexture(new File("/Users/eesom_000/workspace/3dGame(ObjectAndTerrain)/Textures/citybox1.png"), true);
							textures[i].disable();
							break;
					case 2: textures[i] = TextureIO.newTexture(new File("/Users/eesom_000/workspace/3dGame(ObjectAndTerrain)/Textures/citybox2.png"), true);
							textures[i].disable();
							break;
					case 3: textures[i] = TextureIO.newTexture(new File("/Users/eesom_000/workspace/3dGame(ObjectAndTerrain)/Textures/citybox3.png"), true);
							textures[i].disable();
							break;
					case 4: textures[i] = TextureIO.newTexture(new File("/Users/eesom_000/workspace/3dGame(ObjectAndTerrain)/Textures/citybox4.png"), true);
							textures[i].disable();
							break;
					case 5: textures[i] = TextureIO.newTexture(new File("/Users/eesom_000/workspace/3dGame(ObjectAndTerrain)/Textures/cityboxTop.png"), true);
							textures[i].disable();
							break;
					case 6: textures[i] = TextureIO.newTexture(new File("/Users/eesom_000/workspace/3dGame(ObjectAndTerrain)/Textures/cityboxBottom.png"), true);
							textures[i].disable();
							break;
					case 7: textures[i] = TextureIO.newTexture(new File("/Users/eesom_000/workspace/3dGame(ObjectAndTerrain)/Textures/bunny_fur.png"), true);
							textures[i].disable();
							break;
					case 8: textures[i] = TextureIO.newTexture(new File("/Users/eesom_000/workspace/3dGame(ObjectAndTerrain)/Textures/bunny_fur.png"), true);
							textures[i].disable();
							break;
					default: break;
				}
			}
			
			} catch (GLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
	
	gl = drawable.getGL();

		initViewParameters();
		gl.glClearColor(.1f, .1f, .1f, 1f);
		gl.glClearDepth(1.0f);

	    // white light at the eye
	    float light0_position[] = { 0, 0, 1, 0 };
	    float light0_diffuse[] = { 1, 1, 1, 1 };
	    float light0_specular[] = { 1, 1, 1, 1 };
	    gl.glLightfv( GL.GL_LIGHT0, GL.GL_POSITION, light0_position, 0);
	    gl.glLightfv( GL.GL_LIGHT0, GL.GL_DIFFUSE, light0_diffuse, 0);
	    gl.glLightfv( GL.GL_LIGHT0, GL.GL_SPECULAR, light0_specular, 0);

	    //red light
	    float light1_position[] = { -.1f, .1f, 0, 0 };
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

	    //material
	    float mat_ambient[] = { 0, 0, 0, 1 };
	    float mat_specular[] = { .8f, .8f, .8f, 1 };
	    float mat_diffuse[] = { .4f, .4f, .4f, 1 };
	    float mat_shininess[] = { 128 };
	    gl.glMaterialfv( GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient, 0);
	    gl.glMaterialfv( GL.GL_FRONT, GL.GL_SPECULAR, mat_specular, 0);
	    gl.glMaterialfv( GL.GL_FRONT, GL.GL_DIFFUSE, mat_diffuse, 0);
	    gl.glMaterialfv( GL.GL_FRONT, GL.GL_SHININESS, mat_shininess, 0);
	
	    float bmat_ambient[] = { 0, 0, 0, 1 };
	    float bmat_specular[] = { 0, .8f, .8f, 1 };
	    float bmat_diffuse[] = { 0, .4f, .4f, 1 };
	    float bmat_shininess[] = { 128 };
	    gl.glMaterialfv( GL.GL_BACK, GL.GL_AMBIENT, bmat_ambient, 0);
	    gl.glMaterialfv( GL.GL_BACK, GL.GL_SPECULAR, bmat_specular, 0);
	    gl.glMaterialfv( GL.GL_BACK, GL.GL_DIFFUSE, bmat_diffuse, 0);
	    gl.glMaterialfv( GL.GL_BACK, GL.GL_SHININESS, bmat_shininess, 0);

	    float lmodel_ambient[] = { 0, 0, 0, 1 };
	    gl.glLightModelfv( GL.GL_LIGHT_MODEL_AMBIENT, lmodel_ambient, 0);
	    gl.glLightModeli( GL.GL_LIGHT_MODEL_TWO_SIDE, 1 );

	    gl.glEnable( GL.GL_NORMALIZE );
	    gl.glEnable( GL.GL_LIGHTING );
	    gl.glEnable( GL.GL_LIGHT0 );
	    gl.glEnable( GL.GL_LIGHT1 );
	    gl.glEnable( GL.GL_LIGHT2 );

	    gl.glEnable(GL.GL_DEPTH_TEST);
		gl.glDepthFunc(GL.GL_LESS);
		gl.glHint(GL.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);
		gl.glCullFace(GL.GL_BACK);
		gl.glEnable(GL.GL_CULL_FACE);
		gl.glShadeModel(GL.GL_SMOOTH);		
	}
	
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		winW = width;
		winH = height;
		
		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GL.GL_PROJECTION);
		  gl.glShadeModel(GL.GL_SMOOTH);
			gl.glLoadIdentity();
			glu.gluPerspective(45.f, (float)width/(float)height, znear, zfar);
		gl.glMatrixMode(GL.GL_MODELVIEW);
	}
	
	public void mousePressed(MouseEvent e) {	
		mouseX = e.getX();
		mouseY = e.getY();
		mouseButton = e.getButton();
		canvas.display();
	}
	
	public void mouseReleased(MouseEvent e) {
		mouseButton = MouseEvent.NOBUTTON;
		canvas.display();
	}	
	
	public void mouseDragged(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if (mouseButton == MouseEvent.BUTTON3) {
			zpos -= (y - mouseY) * motionSpeed;
			mouseX = x;
			mouseY = y;
			canvas.display();
		} else if (mouseButton == MouseEvent.BUTTON2) {
			xpos -= (x - mouseX) * motionSpeed;
			ypos += (y - mouseY) * motionSpeed;
			mouseX = x;
			mouseY = y;
			canvas.display();
		} else if (mouseButton == MouseEvent.BUTTON1) {
			roth -= (y - mouseX) * rotateSpeed;
			rotv += (y - mouseY) * rotateSpeed;
			mouseX = x;
			mouseY = y;
			canvas.display();
		}
	}

	void collision(Sphere sph){
		
		if((sph.getPosition().x == Scene.getBunnyPos().x) && (sph.getPosition().z == Scene.getBunnyPos().z)){
			System.out.println("detection");
		}
		
	}
	
	/* computes optimal transformation parameters for OpenGL rendering.
	 * this is based on an estimate of the scene's bounding box
	 */	
	void initViewParameters()
	{
		roth = rotv = 0;

		float ball_r = (float) Math.sqrt((xmax-xmin)*(xmax-xmin)
							+ (ymax-ymin)*(ymax-ymin)
							+ (zmax-zmin)*(zmax-zmin)) * 0.707f;

		centerx = (xmax+xmin)/2.f;
		centery = (ymax+ymin)/2.f;
		centerz = (zmax+zmin)/2.f;
		xpos = centerx;
		ypos = centery;
		zpos = ball_r/(float) Math.sin(45.f*Math.PI/180.f)+centerz;

		znear = 0.01f;
		zfar  = 1000.f;

		motionSpeed = 0.002f * ball_r;
		rotateSpeed = 0.1f;

	}	
	
	// these event functions are not used for this assignment
	public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) { }
	public void keyTyped(KeyEvent e) { }
	
	public void mouseMoved(MouseEvent e) { }
	public void actionPerformed(ActionEvent e) { }
	public void mouseClicked(MouseEvent e) { }
	public void mouseEntered(MouseEvent e) { }
	public void mouseExited(MouseEvent e) {	}	
}

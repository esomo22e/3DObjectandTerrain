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

import java.util.ArrayList;
public class Sphere {

	private  GLCanvas canv;
	private GL gl;
	private GLU glu;
	private GLUT glut;
	private Vector3f vel;


	private Vector3f accel;
	
	private Vector3f position;
	private float rest;
	private float angVel;
	private float angAcc;
	private float rotY;
	private float orAng;
	
	

	public Sphere(Vector3f pos){
		glu = new GLU();
		glut = new GLUT();
		vel = new Vector3f(0,0,0);
		orAng = 20;
		angAcc = 50;
		angVel = 50;
	//	angVel = -2;
		rotY = 0;
		//rest = 1;
		
		setPosition(pos);
		accel = new Vector3f(0,0,0);
		
	}
	
	public void draw(){
	
			glut.glutSolidSphere(0.3, 30,30);
		
		
	}
	void move(float t, float dt, int height, int width, double[][] heightmap, Terrain terrain){
		
		vel.x += accel.x * dt;
		vel.y += accel.y * dt;
		vel.z += accel.z * dt;
		
		
		if(!((position.x+(vel.x*dt))<=-width/2) && !((position.x+(vel.x*dt))>=(width/2)-5)){//edge detections
			position.x += vel.x *dt;
		}
		
		position.y += vel.y *dt;
		
		//position.y = (float) terrain.getYpositionAt((int)position.x+200,(int)position.z+200);
		//System.out.println(""+position.x+"      "+position.y+"      "+position.z);
		
		if(!((position.z+(vel.z*dt))<=-height/2) && !((position.z+(vel.z*dt))>=height/2)){//edge detections
			position.z += vel.z *dt;
		}


		angVel += angAcc * dt;
		orAng += angVel * dt;
		
	
		//angAcc+=20;
		
	//	angVel *= Math.pow(0.8, dt);
	//	orAng += angVel * dt;
		//if (position.y < 0){
	//		vel.y *= -rest;
	//	}
		
	}
	public float getAngAcc() {
		return angAcc;
	}

	public void setAngAcc(float angAcc) {
		this.angAcc = angAcc;
	}

	public float getOrAng() {
		return orAng;
	}

	public void setOrAng(float orAng) {
		this.orAng = orAng;
	}
	
	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}
	
	public Vector3f getAccel() {
		return accel;
	}

	public void setAccel(Vector3f accel) {
		this.accel = accel;
	}

	public Vector3f getVel() {
		return vel;
	}

	public void setVel(Vector3f vel) {
		this.vel = vel;
	}
	public float getRotY() {
		return rotY;
	}

	public void setRotY(float rotY) {
		this.rotY = rotY;
	}
}

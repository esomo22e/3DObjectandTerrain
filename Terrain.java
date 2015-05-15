import javax.media.opengl.GL;

import com.sun.opengl.util.texture.Texture;

public class Terrain {

	private double[][] heightMap;
	private int height;
	private int width;
	private double[][] yValues;
	
	public Terrain(double[][] heightMap){
		this.heightMap = heightMap;
		this.width = heightMap.length; //number of rows
		this.height = heightMap[0].length; //number of columns
		this.yValues = new double[this.width][this.height];
		System.out.println("Height: "+height+" Width: "+width);
	}
	
	public int getHeight(){
		return height;
	}
	
	public int getWidth(){
		return width;
	}
	
	//modeled after: https://azerdark.wordpress.com/2010/01/09/landscape-terrain-using-jogl/
	public void renderHeightMap(GL gl, Texture t, objModel tree1, objModel tree2){
		int STEP = 5; //level of smoothness to the heights in the heightmap
		
		t.enable();
		t.bind();
		gl.glDisable(gl.GL_LIGHTING);
		gl.glPushMatrix();
		gl.glTranslatef(-200,-5,-200);
	        gl.glBegin(gl.GL_QUADS);
	        for (int X = 0; X < (width - STEP); X += STEP)
	            for (int Y = 0; Y < (height - STEP); Y += STEP)
	            {
	            	//System.out.print("Quad: ");
	                // downleft vertex
	                int x = X;
	                int y = (int)heightMap[X][Y];
	                int z = Y;
	                
	                yValues[x][z]=y;
	                
	                //System.out.print("("+x+", "+y+", "+z+")");
	                
	                gl.glTexCoord2f((float)x / (float)width, (float)z / (float)height);
 
	                gl.glVertex3f(x, y, z);
	 
	                // upleft vertex
	                x = X;
	                y = (int)heightMap[X][Y+STEP];
	                z = Y + STEP;
	                
	                yValues[x][z]=y;
	                
	                
	                //System.out.print("("+x+", "+y+", "+z+")");
	                
	            
	                gl.glTexCoord2f((float)x / (float)width, (float)(z + STEP) / (float)height);
	                //System.out.print("  Text Coor.("+((float)x / (float)width)+", "+((float)(z + STEP) / (float)height)+")  ");
	                //    setVertexColor(gl, pHeightMap, x, z);
	                gl.glVertex3f(x, y, z);

	 
	                // upright vertex
	                x = X + STEP;
	                y = (int)heightMap[X+STEP][Y+STEP];
	                z = Y + STEP;
	                
	                yValues[x][z]=y;
	                
	                //System.out.print("("+x+", "+y+", "+z+")");
	                
	                gl.glTexCoord2f((float)(x + STEP) / (float)width, (float)(z + STEP) / (float)height);
	               // System.out.print("  Text Coor.("+((float)(x + STEP) / (float)width)+", "+((float)(z + STEP) / (float)height)+")  ");
	                
	               //     setVertexColor(gl, pHeightMap, x, z);
	                gl.glVertex3f(x, y, z);
	 
	                // downright vertex
	                x = X + STEP;
	                y = (int)heightMap[X+STEP][Y];
	                z = Y;
	                
	                yValues[x][z]=y;
	                
	                
	                //System.out.print("("+x+", "+y+", "+z+")");
	                
	               gl.glTexCoord2f((float)(x + STEP) / (float)width, (float)z / (float)height);
	              // System.out.println("  Text Coor.("+((float)(x + STEP) / (float)width)+", "+((float)z  / (float)height)+")  ");
	                
	                    //setVertexColor(gl, pHeightMap, x, z);
	                gl.glVertex3f(x, y, z);
	                
	                
	            }
	 
	        t.disable();
	        gl.glEnd();
	        gl.glColor4f(1.0f, 1.0f, 1.0f, 1.0f); // reset
	        gl.glEnable(gl.GL_LIGHTING);
	        gl.glPopMatrix();
	        
	}
	
	public double getYpositionAt(int x, int z){
		return yValues[x][z];
	}
}

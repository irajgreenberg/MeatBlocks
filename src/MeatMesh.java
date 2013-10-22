
import processing.core.*;

import java.util.ArrayList;

public class MeatMesh {
	
	public PApplet p;
	public PVector pos;
	public int blockCount;
	public ArrayList<VerletStick> sticks;
	public ArrayList<VerletBall> balls;
	public Block[] blocks;
	public String[] meatURLs = {"chopmeat_texture.jpg", "chuckmeat_texture.jpg", "cubemeat_texture.jpg", "meat_texture.jpg",
			"porkmeat_texture.jpg", "rawmeat_texture.jpg", "steakmeat_texture.jpg"};
	
	// cage
	public Dimension3 meshBounds;
	private VerletBall[] anchors = new VerletBall[8];
	private VerletStick[] tethers = new VerletStick[8];
	private VerletStick[] frame = new VerletStick[12];
	

	// cstr's
	public MeatMesh(){
	}

	public MeatMesh(PApplet p, PVector pos, int blockCount, Dimension3 meshBounds){
		this.p = p;
		this.pos = pos;
		this.blockCount = blockCount;
		this.meshBounds = meshBounds;
		blocks = new Block[blockCount];
		init();
	}

	public void init(){
		balls = new ArrayList<VerletBall>();
		sticks = new ArrayList<VerletStick>();
		for(int i=0; i<blocks.length; ++i) {
			balls.add(new VerletBall(p, new PVector(p.random(-500, 500), p.random(-500, 500), p.random(-500, 500)), .2f));
			int index = (int)(p.random(3));
			String  dynamicTexture = "data/"+meatURLs[(int)p.random(meatURLs.length)];
			switch(index){
			case 0:
				blocks[i] = new Block(p, balls.get(i).pos, new PVector(p.random(-p.PI/45.0f, p.PI/45.0f), 
						p.random(-p.PI/45.0f, p.PI/45.0f), p.random(-p.PI/45.0f, p.PI/45.0f)), 
						new Dimension3(p.random(35, 65), p.random(35, 65), p.random(35, 65)), dynamicTexture);
				blocks[i].setDynamics(new PVector(), new PVector(p.random(-p.PI/80.0f, p.PI/80.0f), p.random(-p.PI/80.0f, p.PI/80.0f), p.random(-p.PI/80.0f, p.PI/80.0f)));
				break;
			case 1:
				blocks[i] = new LBlock(p, balls.get(i).pos, new PVector(p.random(-p.PI/45.0f, p.PI/45.0f), 
						p.random(-p.PI/45.0f, p.PI/45.0f), p.random(-p.PI/45.0f, p.PI/45.0f)), 
						new Dimension3(p.random(35, 65), p.random(35, 65), p.random(35, 65)), dynamicTexture, p.random(.6f, 1.0f), p.random(.6f, 1.0f));
				blocks[i].setDynamics(new PVector(), new PVector(p.random(-p.PI/80.0f, p.PI/80.0f), p.random(-p.PI/80.0f, p.PI/80.0f), p.random(-p.PI/80.0f, p.PI/80.0f)));
				break;
			case 2:
				blocks[i] = new CrossBlock(p, balls.get(i).pos, new PVector(p.random(-p.PI/45.0f, p.PI/45.0f), 
						p.random(-p.PI/45.0f, p.PI/45.0f), p.random(-p.PI/45.0f, p.PI/45.0f)), 
						new Dimension3(p.random(35, 65), p.random(35, 65), p.random(35, 65)), dynamicTexture);
				blocks[i].setDynamics(new PVector(), new PVector(p.random(-p.PI/80.0f, p.PI/80.0f), p.random(-p.PI/80.0f, p.PI/80.0f), p.random(-p.PI/80.0f, p.PI/80.0f)));
				break;
			}
			
			if(i>3){
				blocks[i].setIsFadeable(true);
			}
		}
		
		// Create Cage geometry
		// VERLET BALL ANCHORS
		anchors[0] = new VerletBall(p, new PVector(-meshBounds.w/2.0f, -meshBounds.h/2.0f, meshBounds.d/2.0f), .02f); // tlf
		anchors[1] = new VerletBall(p, new PVector(-meshBounds.w/2.0f, meshBounds.h/2.0f, meshBounds.d/2.0f), .02f); // blf
		anchors[2] = new VerletBall(p, new PVector(meshBounds.w/2.0f, meshBounds.h/2.0f, meshBounds.d/2.0f), .02f); // brf
		anchors[3] = new VerletBall(p, new PVector(meshBounds.w/2.0f, -meshBounds.h/2.0f, meshBounds.d/2.0f), .02f); // trf
		anchors[4] = new VerletBall(p, new PVector(-meshBounds.w/2.0f, -meshBounds.h/2.0f, -meshBounds.d/2.0f), .02f); // tlr
		anchors[5] = new VerletBall(p, new PVector(-meshBounds.w/2.0f, meshBounds.h/2.0f, -meshBounds.d/2.0f), .02f); // blr
		anchors[6] = new VerletBall(p, new PVector(meshBounds.w/2.0f, meshBounds.h/2.0f, -meshBounds.d/2.0f), .02f); // brr
		anchors[7] = new VerletBall(p, new PVector(meshBounds.w/2.0f, -meshBounds.h/2.0f, -meshBounds.d/2.0f), .02f); // trr
		
		// VERLET STICK TETHERS
		tethers[0] = new VerletStick(p, anchors[0], balls.get(0), .5f, new Tuple2<Float, Float>(0.0f, 1.0f));
		tethers[1] = new VerletStick(p, anchors[1], balls.get(1), .5f, new Tuple2<Float, Float>(0.0f, 1.0f));
		tethers[2] = new VerletStick(p, anchors[2], balls.get(2), .5f, new Tuple2<Float, Float>(0.0f, 1.0f));
		tethers[3] = new VerletStick(p, anchors[3], balls.get(3), .5f, new Tuple2<Float, Float>(0.0f, 1.0f));
		tethers[4] = new VerletStick(p, anchors[4], balls.get(4), .5f, new Tuple2<Float, Float>(0.0f, 1.0f));
		tethers[5] = new VerletStick(p, anchors[5], balls.get(5), .5f, new Tuple2<Float, Float>(0.0f, 1.0f));
		tethers[6] = new VerletStick(p, anchors[6], balls.get(6), .5f, new Tuple2<Float, Float>(0.0f, 1.0f));
		tethers[7] = new VerletStick(p, anchors[7], balls.get(7), .5f, new Tuple2<Float, Float>(0.0f, 1.0f));
		
		// VERLET STICK FRAME
		frame[0] = new VerletStick(p, anchors[0], anchors[1], .5f, new Tuple2<Float, Float>(0.0f, 0.0f));
		frame[1] = new VerletStick(p, anchors[1], anchors[2], .5f, new Tuple2<Float, Float>(0.0f, 0.0f));
		frame[2] = new VerletStick(p, anchors[2], anchors[3], .5f, new Tuple2<Float, Float>(0.0f, 0.0f));
		frame[3] = new VerletStick(p, anchors[3], anchors[0], .5f, new Tuple2<Float, Float>(0.0f, 0.0f));
		
		frame[4] = new VerletStick(p, anchors[4], anchors[5], .5f, new Tuple2<Float, Float>(0.0f, 0.0f));
		frame[5] = new VerletStick(p, anchors[5], anchors[6], .5f, new Tuple2<Float, Float>(0.0f, 0.0f));
		frame[6] = new VerletStick(p, anchors[6], anchors[7], .5f, new Tuple2<Float, Float>(0.0f, 0.0f));
		frame[7] = new VerletStick(p, anchors[7], anchors[4], .5f, new Tuple2<Float, Float>(0.0f, 0.0f));
		
		frame[8] = new VerletStick(p, anchors[0], anchors[4], .5f, new Tuple2<Float, Float>(0.0f, 0.0f));
		frame[9] = new VerletStick(p, anchors[1], anchors[5], .5f, new Tuple2<Float, Float>(0.0f, 0.0f));
		frame[10] = new VerletStick(p, anchors[2], anchors[6], .5f, new Tuple2<Float, Float>(0.0f, 0.0f));
		frame[11] = new VerletStick(p, anchors[3], anchors[7], .5f, new Tuple2<Float, Float>(0.0f, 0.0f));
		
//		for(int i=0; i<balls.size(); ++i) {
//			for(int j=0; j<balls.size()-i; ++j) {
//				if(balls.get(i) != balls.get(j)){
//					sticks.add(new VerletStick(p, balls.get(i), balls.get(j), .5f));
//				}
//			}
//		}
		
		for(int i=0; i<balls.size()*2; ++i) {
			//for(int j=0; j<balls.size()-i; ++j) {
				int index1 = (int)p.random(balls.size());
				int index2 = (int)p.random(balls.size());
				if(index1 != index2){
					sticks.add(new VerletStick(p, balls.get(index1), balls.get(index2), .005f));
				}
			//}
		}
		
		jitter();
	}

	// eventually parameterize
	public void jitter(){ 
		balls.get((int)p.random(balls.size())).pos.add(new PVector(p.random(-56, 56), p.random(-56, 56), p.random(-56, 56)));
//		for(int i=0; i<balls.size(); ++i) {
//			balls.get(i).pos.add(new PVector(p.random(-16, 16), p.random(-16, 16), p.random(-16, 16)));
//		}
	}
	
	public void jitter(int id, float val){ 
		balls.get(id).pos.add(new PVector(val, val, val));
	}
	
	public void glow(int id, float val){
		
	}

	public void start(){
		for (int i=0; i<anchors.length; i++) {
			anchors[i].verlet();
		}

		for (int i=0; i<tethers.length; i++) {
			tethers[i].constrainLen();
		}

		for (int i=0; i<balls.size(); i++) {
			balls.get(i).verlet();
		}

		for(int i=0; i<sticks.size(); ++i) {
			sticks.get(i).constrainLen();
		}	

		// boundary and simple object-object collision response
		collide();
	}

	private void collide(){
		// boundaries
		for (int i=0; i<balls.size(); i++) {
			// x-axis
			if (balls.get(i).pos.x > meshBounds.w/2.0f){
				balls.get(i).pos.x = meshBounds.w/2.0f;
				balls.get(i).pos.x -=10;
			} else if (balls.get(i).pos.x < -meshBounds.w/2.0f){
				balls.get(i).pos.x = -meshBounds.w/2.0f;
				balls.get(i).pos.x += 10;
			}
			
			// y-axis
			if (balls.get(i).pos.y > meshBounds.h/2.0f){
				balls.get(i).pos.y = meshBounds.h/2.0f;
				balls.get(i).pos.y -=10;
			} else if (balls.get(i).pos.y < -meshBounds.h/2.0f){
				balls.get(i).pos.y = -meshBounds.h/2.0f;
				balls.get(i).pos.y +=10;
			}
			
			// z-axis
			if (balls.get(i).pos.z > meshBounds.d/2.0f){
				balls.get(i).pos.z = meshBounds.d/2.0f;
				balls.get(i).pos.z -=10;
			} else if (balls.get(i).pos.z < -meshBounds.d/2.0f){
				balls.get(i).pos.z = -meshBounds.d/2.0f;
				balls.get(i).pos.z +=10;
			}
		}

	}

	public void render(){

		p.pushMatrix();
		p.translate(pos.x, pos.y, pos.z);
		p.rotateY(p.frameCount*p.PI/180.0f);
		p.rotateZ(p.frameCount*p.PI/720.0f);
		p.rotateX(p.frameCount*p.PI/540.0f);
		// sticks
		p.stroke(.9f, .55f, .55f, .25f);
		for(int i=0; i<sticks.size(); ++i) {
			sticks.get(i).render();
		}	

		// blocks
		p.noStroke();
		
		for(int i=0; i<blocks.length; ++i) {
			
			if(blocks[i].dynamicAlpha > blocks[i].baseAlpha && blocks[i].isFadeable){
				blocks[i].dynamicAlpha-=.01f;
			}
			
			p.tint(1.0f, blocks[i].dynamicAlpha);
			blocks[i].render();
		}
		p.popMatrix();
		
//		// frame
//		p.pushMatrix();
//		p.translate(p.width/2.0f, p.height/2.0f, 0.0f);
//		p.stroke(.55f, .4f);
//		for(int i=0; i<tethers.length; ++i) {
//			tethers[i].render();
//		}	
//
//		for(int i=0; i<frame.length; ++i) {
//			frame[i].render();
//		}	
//		p.popMatrix();
	}
}

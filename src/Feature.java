import java.util.Random;


public class Feature {
	private int[] row = new int[4];
	private int[] col = new int[4];
	private boolean[] marked = new boolean[4];
	private boolean[][] image;

	public Feature(boolean[][] image){
		this.image = image;
		//construct random features
		constructFeatures();
	}

	public void constructFeatures(){
		Random random = new Random(0);
		for (int i =0; i<4; i++){
			//Choose 4 random points on image
			row[i] = random.nextInt(image.length);
			//Choose 4 random connections
			col[i] =  random.nextInt(image.length);
			//Choose 4 random 
			marked[i] = random.nextBoolean();
		}
	}

	public int[] getRows(){
		return row;
	}

	public int[] getCols(){
		return col;
	}

	public boolean[] getMarked(){
		return marked;
	}

	/**
	 * Returns 1 if there are 3 or more connections
	 * otherwise returns 0
	 * @return
	 */
	public int computeValue(){
		int sum=0;
		for(int i=0; i < 4; i++){
			if (image[row[i]][col[i]]== marked[i]){
				sum++;
			}
		}
		return ((sum>=3)?1:0);	//Return 1 if 3 or more match, otherwise return 0
	}
	
	
	public int dummyFeature(){
		return 1;
	}
}

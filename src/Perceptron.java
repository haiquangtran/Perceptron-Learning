import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Perceptron {
	private ArrayList<Image> images = new ArrayList<Image>();
	private ArrayList<Feature> features = new ArrayList<Feature>();
	private ArrayList<Double> weights = new ArrayList<Double>(); 

	public Perceptron(String fileName){
		readData(fileName);
		perceptronLearning(features);
	}


	public void perceptronLearning(ArrayList<Feature> features){
		//Note extra weight for the threshold
		//weights.add(-threshold);
		//initialize weights to random values from 0 to 1
		for (int i = 0; i < features.size(); i++){ 
			weights.add(new Random().nextDouble());
		}
		//classifier
		double classifier = 0; 
		String output = null;
		//Until the perceptron is always right (or some limit):
		for (int times = 0; times < 3; times++){
			//Go through images
			for (Image image: images){
				//Present an example (+ve or -ve)
				String positive = "Yes";
				String negative = "other";
				//Compute perceptron formula 
				for (int i = 0; i<features.size(); i++){					
					if (i == 0){
						//Dummy value
						classifier += (weights.get(i) * features.get(i).dummyFeature());
					} else {
						classifier += (weights.get(i) * features.get(i).computeValue());
					}
				}
				
				//Classify - formula
				if (classifier >= 0){
					output = positive;
				} else {
					output = negative;
				}
				//If classified correctly, do nothing
				if (image.getClassOfImage().equalsIgnoreCase(output)){
					image.setMatched(true);
				}
				//If -ve example and wrong
				else if (output.equalsIgnoreCase(negative) && !(image.getClassOfImage().equalsIgnoreCase(output))){
					//(ie, weights on active features are too high)
					//Subtract feature vector from weight vector
					for (int i=0; i < weights.size(); i++){
						weights.set(i, weights.get(i) + features.get(i).computeValue());
					}
				}
				//If +ve example and wrong
				else if (output.equalsIgnoreCase(positive) && !(image.getClassOfImage().equalsIgnoreCase(output))){
					//(ie, weights on active features are too low)
					//Add feature vector to weight vector
					for (int i =0; i < weights.size(); i++){
						weights.set(i, weights.get(i) - features.get(i).computeValue());
					}
				} 
			}
		}

		int correct = 0;
		int incorrect = 0;
		for (Image image: images){
			if (image.getMatched()){
				correct++;
			} else {
				incorrect++;
			}
		}
		System.out.println();
		System.out.println(" correct "  +correct + "      incorrect:"+  incorrect + "   " + (double)correct/(double)(incorrect+correct)*100 + " %");

	}


	public void readData(String filename){
		try {
			Scanner scan = new Scanner(new FileReader(filename));
			java.util.regex.Pattern bit = java.util.regex.Pattern.compile("[01]");
			while (scan.hasNext()){
				if (!scan.hasNext("P1"))System.out.println("Not a P1 PBM file");
				String type = scan.next();
				String classOfImage = scan.next().substring(1);
				int  width = scan.nextInt();
				int  height = scan.nextInt();
				boolean[][] newImage = new boolean[width][height];
				//int index = 0;
				for (int r=0; r< width; r++){
					for (int c=0; c< height; c++){
						newImage[r][c] = (scan.findWithinHorizon(bit,0).equals("1"));
					}
				}
				//Add features
				features.add(new Feature(newImage));
				//Store images
				images.add(new Image(type, classOfImage, width, height, newImage));
			
			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}




}

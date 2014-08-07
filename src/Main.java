
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		if (args.length == 1){
			Perceptron reader = new Perceptron(args[0]);
			
		} else {
			System.out.println("Wrong number of input files.");
		}
	}

}

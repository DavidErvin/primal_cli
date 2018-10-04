package us.ervin.runner;

import java.util.List;

import us.ervin.PrimeNumberGenerator;
import us.ervin.impl.PrimeNumberGeneratorImpl;

/**
 * Main class that accepts CLI input and writes prime numbers to stdout
 * 
 * @author dervin
 */
public class PrimalRunner {

	public static void main(String[] args) {
		new PrimalRunner().printRange(args);
	}

	public PrimalRunner() {
	}
	

	public void printRange(String[] args) {
		// parse out command line args
		if (args.length == 2) {
			Integer rangeStart = parseInt(args[0]);
			Integer rangeEnd = parseInt(args[1]);
			if (rangeStart != null && rangeEnd != null) {
				PrimeNumberGenerator generator = new PrimeNumberGeneratorImpl();
				List<Integer> primes = generator.generate(rangeStart, rangeEnd);
				System.out.println(primes);
			} else {
				printHelp();
			}
		} else {
			printHelp();
		}
	}
	

	private Integer parseInt(String val) {
		Integer i = null;
		try {
			i = Integer.valueOf(val);
		} catch (Exception ex) {
			System.err.println("Error parsing value '" + val + "' as an integer: " + ex.getMessage());
		}
		return i;
	}

	private static void printHelp() {
		System.out.println("Usage: primal_cli-1.0.0.jar [rangeStart] [rangeEnd]\n");
		System.out.println("rangeStart and rangeEnd must be positive integers.\n");
		System.out.println("The CLI will output all prime numbers between rangeStart and rangeEnd, inclusive.\n");
		System.out.println("Values for rangeStart and rangeEnd which are not positive integers will produce an error.");
	}
}

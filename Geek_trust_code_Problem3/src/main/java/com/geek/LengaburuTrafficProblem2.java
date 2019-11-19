package com.geek;

import java.util.List;
import java.util.Scanner;

import com.geek.domain.Result;
import com.geek.domain.Route;
import com.geek.util.RoutesUtilility;


/**Geek_trust_code
 * @author Praveen
 * @version 1.0 TODO
 * @since Aug 26, 2019
 

 */
public class LengaburuTrafficProblem2 {
	public static void main(String[] args) {
		System.out.println("weather is:");
		Scanner scanner = new Scanner(System.in);
		String weather = scanner.nextLine();
		System.out.println("Orbit 1 traffic speed is");
		String orbit1Input = scanner.nextLine();
		System.out.println("Orbit 2 traffic speed is");
		String orbit2Input = scanner.nextLine();
		System.out.println("Orbit 3 traffic speed is");
		String orbit3Input = scanner.nextLine();
		System.out.println("Orbit 4 traffic speed is");
		String orbit4Input = scanner.nextLine();

		String source = "SilkDorb";
		String destination1 = "Hallitharam";
		String destination2 = "RKPuram";

		Route orbit1 = new Route("Orbit1", 18L, 20L, Integer.parseInt(orbit1Input), source, destination1);
		Route orbit2 = new Route("Orbit2", 20L, 10L, Integer.parseInt(orbit2Input), source, destination1);
		Route orbit3 = new Route("Orbit3", 30L, 15L, Integer.parseInt(orbit3Input), source, destination2);
		Route orbit4 = new Route("Orbit4", 15L, 18L, Integer.parseInt(orbit4Input), destination2, destination1);

		List<Result> results = RoutesUtilility.calculateBestRouteForMultipleDestination(RoutesUtilility.vehicles,
				new Route[] { orbit1, orbit2, orbit3, orbit4 }, weather);
		Result finalResult[] = results.stream().toArray(Result[]::new);
		System.out.println(Result.getFormattedOutput(finalResult));
	}

}

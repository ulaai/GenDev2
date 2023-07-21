package de.buw.se.gendev.lab2;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import tau.smlab.syntech.controller.executor.ControllerExecutor;
import tau.smlab.syntech.games.controller.jits.BasicJitController;
import java.util.Scanner;


public class SpecSimulatorCmd {

	public static void main(String[] args) throws IOException {

		Map<String, String> inputs = new HashMap<>();
		Scanner in = new Scanner(System.in);
		// Instantiate a new controller executor
		ControllerExecutor executor = new ControllerExecutor(new BasicJitController(), "out/jit", "Spec");

		boolean iniState = true;
		while (true) {
			inputs.clear();

			// TODO read inputs here
			System.out.println("Is the document already sent to the queue? (Y/n) [Y]");
			String line = in.nextLine(); 
			// TODO send inputs to controller
			boolean queue = !"n".equals(line);
			
			System.out.println("Make sure there is a paper (Y/n) [Y]");
			line = in.nextLine(); 
			boolean paper = !"n".equals(line);

			System.out.println("How many paper? Press 1 or 2 [1]");
			line = in.nextLine(); 
			Integer noPages = Integer.parseInt(line);

			// send inputs to controller
			inputs.put("queue", Boolean.toString(queue));
			inputs.put("paper", Boolean.toString(paper));
			inputs.put("noPages", Integer.toString(noPages));
//			System.out.println(inputs);

			// execute controller
			if (iniState) {
				executor.initState(inputs);
				iniState = false;
			} else {
				executor.updateState(inputs);
			}

			// TODO read outputs and decide how to proceed
			String status = executor.getCurrValue("status");
			System.out.println("Status: "+ status);
			if (status.equals("PRINTING")) {
				System.out.println("Your document is being printed.");
			}
			else if (status.equals("COMPLETE")) {
				System.out.println("Your document has finished printing. You can start another print job.");
			}
			else {
				System.out.println("Please check and make sure you have sent a printing job and loaded paper into the printer.");
			}
		}
	}
}

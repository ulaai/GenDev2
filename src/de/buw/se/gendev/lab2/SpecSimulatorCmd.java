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
			System.out.println("Is the document already sent to the queue? (Y/n)");
			String line = in.nextLine(); 
			// TODO send inputs to controller
			boolean queue = !"n".equals(line);
			
			System.out.println("Is the safety cover closed? (Y/n)");
			line = in.nextLine(); 
			boolean safetyCover = !"n".equals(line);

			System.out.println("Make sure there is a paper (Y/n)");
			line = in.nextLine(); 
			boolean paper = !"n".equals(line);

			// send inputs to controller
			inputs.put("queue", Boolean.toString(queue));
			inputs.put("safetyCover", Boolean.toString(safetyCover));
			inputs.put("paper", Boolean.toString(paper));
			// execute controller
			if (iniState) {
				executor.initState(inputs);
				iniState = false;
			} else {
				executor.updateState(inputs);
			}

			// TODO read outputs and decide how to proceed
			boolean hasPaper = Boolean.parseBoolean(executor.getCurrValue("hasPaper"));
			if (hasPaper) {
				System.out.println("It has paper");
			}
			else {
				System.out.println("No paper");
			}
		}
	}
}

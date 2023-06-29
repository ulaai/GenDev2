package de.buw.se.gendev.lab2;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

import tau.smlab.syntech.gameinput.model.GameInput;
import tau.smlab.syntech.spectragameinput.ErrorsInSpectraException;
import tau.smlab.syntech.spectragameinput.SpectraInputProviderNoIDE;
import tau.smlab.syntech.spectragameinput.SpectraTranslationException;

class SpecTest {
	static final String specFile = "Spec.spectra";
	static final String specFileUnreal = "Spec_Unreal.spectra";

	@Test
	void testModelExists() {
		File f = new File(specFile);
		assertTrue("Make sure that the original file " + specFile + " has not been deleted or renamed.", f.exists());
	}

	@Test
	void testUnsatVersionExists() {
		File f = new File(specFileUnreal);
		assertTrue("Make sure that the file " + specFileUnreal + " has been created.", f.exists());
	}
	
	@Test
	void testVarsEnv() throws ErrorsInSpectraException, SpectraTranslationException {
		// get the Xtext-based input parser
		SpectraInputProviderNoIDE sip = new SpectraInputProviderNoIDE();
		// parse (via Xtext) and translate to abstract syntax (Xtext independent)
		GameInput gi = sip.getGameInput(specFile);
		assertTrue("The specification must contain at least 3 environment variables.",
				gi.getEnv().getVars().size() >= 3);
	}

	@Test
	void testVarsSys() throws ErrorsInSpectraException, SpectraTranslationException {
		// get the Xtext-based input parser
		SpectraInputProviderNoIDE sip = new SpectraInputProviderNoIDE();
		// parse (via Xtext) and translate to abstract syntax (Xtext independent)
		GameInput gi = sip.getGameInput(specFile);
		assertTrue("The specification must contain at least 3 system variables.", gi.getSys().getVars().size() >= 3);
	}

	@Test
	void testGars() throws ErrorsInSpectraException, SpectraTranslationException {
		// get the Xtext-based input parser
		SpectraInputProviderNoIDE sip = new SpectraInputProviderNoIDE();
		// parse (via Xtext) and translate to abstract syntax (Xtext independent)
		GameInput gi = sip.getGameInput(specFile);
		assertTrue("The specification must contain at least 2 guarantees.", gi.getSys().getConstraints().size() >= 2);
	}

	@Test
	void testAsms() throws ErrorsInSpectraException, SpectraTranslationException {
		// get the Xtext-based input parser
		SpectraInputProviderNoIDE sip = new SpectraInputProviderNoIDE();
		// parse (via Xtext) and translate to abstract syntax (Xtext independent)
		GameInput gi = sip.getGameInput(specFile);
		assertTrue("The specification must contain at least 2 assumptions.", gi.getEnv().getConstraints().size() >= 2);
	}

}

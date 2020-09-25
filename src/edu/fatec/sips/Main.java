package edu.fatec.sips;

import java.awt.HeadlessException;
import java.io.IOException;

import edu.fatec.sips.view.MenuPrincipal;

public class Main {

	public static void main(String[] args) throws Exception, HeadlessException, IOException {
		MenuPrincipal menuPrincipal = new MenuPrincipal();
		menuPrincipal.menuPrincipal(0);
	}
}

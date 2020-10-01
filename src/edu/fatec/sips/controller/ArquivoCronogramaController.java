package edu.fatec.sips.controller;

import java.io.FileWriter;
import java.io.IOException;

import edu.fatec.sips.model.CronogramaDeAtividades;

public class ArquivoCronogramaController {

  private final String arquivoCronograma = "ArquivoCronograma.txt";
  private final String separator = " - ";

  
  public void gravarCronograma(CronogramaDeAtividades cronograma) throws IOException {
	  FileWriter fw = new FileWriter(arquivoCronograma, true);
	  //fw.write();
  }

}

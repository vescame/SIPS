package edu.fatec.sips.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import edu.fatec.sips.model.Candidato;

public class ArquivoController {
	public void LerCandidato ( Candidato[ ] candidato ) throws IOException	 {	
		  int i=0;	
		  String fileName = "ArquivoCandidato.txt";	
		  BufferedReader ler = new BufferedReader(new FileReader( fileName ));	
		  //for (i = 0 ; i < 3 ; i++)	qtd de objetos necessários criar
		   candidato[i] = new Candidato();
		  //for (i = 0 ; i < 3 ; i++)   {	qtd p ler
		    candidato[i].nome = ler.readLine();  	
		    //demais atributos	
		  // }	
		  	
		  //for (i = 0 ; i < 3; i++) {	qtd p printar
		   System.out.println(candidato[i].nome); //concatenar demais atributos	
		  //}
		  ler.close();	
		  }	
	
	public Candidato[ ] GravarCandidato (Candidato[ ] candidato  ) throws IOException {	
	     int i=0;	
	     String fileName = "ArquivoCandidato.txt";	
	     BufferedWriter writer = new BufferedWriter(new FileWriter( fileName ));	
	     //for (i = 0 ; i < 3 ; i++)	
	          candidato[i] = new Candidato();
		
	    // for (i = 0 ; i < 3 ; i++)  {	
	           candidato[i].nome = JOptionPane.showInputDialog("Digite o nome do candidato:");	
	           writer.write( candidato[i].nome );  	
	           writer.newLine();	
	           
	//} 
	      System.out.println("CANDIDATO GRAVADO COM SUCESSO ");	
	    writer.close();
	  return candidato;
	  }
}

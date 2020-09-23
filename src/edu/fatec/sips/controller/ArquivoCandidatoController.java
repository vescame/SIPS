package edu.fatec.sips.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;

import javax.swing.JOptionPane;

import edu.fatec.sips.model.Candidato;

public class ArquivoCandidatoController {
	
	public int contar() throws FileNotFoundException, IOException {
		int qtdLinhas;		
		  try
		  (
		     FileReader       leitorArquivo = new FileReader("ArquivoCandidato.txt");
		     LineNumberReader contador = new LineNumberReader(leitorArquivo);
		  )
		  {
		     while (contador.skip(Long.MAX_VALUE) > 0)
		     {
		        // Loop just in case the file is > Long.MAX_VALUE or skip() decides to not read the entire file
		     }

		     qtdLinhas = contador.getLineNumber()+1 ;
		  }
		
		return qtdLinhas;
		
	}
	public void LerCandidato ( Candidato[ ] candidato ) throws IOException	 {	
		  int i, qtdLinhas;
		  qtdLinhas = contar();
		  String nomeArquivo = "ArquivoCandidato.txt";	
		  BufferedReader ler = new BufferedReader(new FileReader( nomeArquivo ));	
		  for (i = 0 ; i < qtdLinhas ; i++)	
		   candidato[i] = new Candidato();
		  for (i = 0 ; i < qtdLinhas ; i++)   {	
		    candidato[i].nome = ler.readLine();  	
		   }			  	
		  for (i = 0 ; i < qtdLinhas; i++) {	
		   System.out.println(candidato[i].nome);	
		  }
		  ler.close();	
		  }	
	
	public Candidato[ ] GravarCandidato (Candidato[ ] candidato  ) throws IOException {	
		 int i=0;
		 candidato[i] = new Candidato();
	     FileWriter fw = new FileWriter("ArquivoCandidato.txt",true);
	           candidato[i].nome = JOptionPane.showInputDialog("Digite o nome do candidato:");
	           fw.write( "\n" + candidato[i].nome );
	           System.out.println("CANDIDATO GRAVADO COM SUCESSO ");	
	    fw.close();
	  return candidato;
	  }
}

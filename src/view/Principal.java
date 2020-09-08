/*
Fazer, em java, uma aplica��o que liste os processos ativos, permita ao usu�rio entrar com o nome ou o PID do processo e o mate. A aplica��o dever� funcionar, minimamente em Windows e Linux (Alunos
com Mac podem fazer para os 3 SO). � not�rio que cada SO tem comandos diferentes para as a��es supracitadas. Pesquisar os comandos para cada SO. A aplica��o dever� ter, no package view, uma classe
que tenha um m�todo main que d� ao usu�rio a possibilidade de ver os processos ativos ou matar os processos (Por Nome ou PID).
No package controller, dever� ter:
1) Uma classe que tenha um m�todo que identifique o SO;
2) Um m�todo que, recebendo o SO, no qual est� rodando, como par�metro de entrada, selecione o comando para listar os processos ativos;
3) Um m�todo que, recebendo o SO, no qual est� rodando, e o PID do processo, como par�metros de entrada, selecione o comando para matar o processo e o finalize;
4) Um m�todo que, recebendo o SO, no qual est� rodando, e o Nome do processo, como par�metros de entrada, selecione o comando para matar o processo e o finalize;
 */

package view;

import javax.swing.JOptionPane;
import controller.ProcessosController;

public class Principal {
	
	public static void main(String[] args) {
		
		ProcessosController m = new ProcessosController();
		String os = m.os();
		
		int opc = 0;
		while (opc != 4) {
			opc = Integer.parseInt(JOptionPane.showInputDialog("Escolha a op��o desejada: " + "\n" + "\n1. [Processos ativos]" + "\n2. [Finalizar processo pelo PID]" + "\n3. [Finalizar processo pelo NOME]" + "\n" + "\n4. [Finalizar]"));
			switch (opc) {
				case 1: m.commandProcess(os);
				break;
				
				case 2: 
					int pid = Integer.parseInt(JOptionPane.showInputDialog("Insira o PID do processo: "));
					m.commandPid(pid, os);
				break;
				
				case 3: 
					String name = JOptionPane.showInputDialog("Insira o NOME do processo: ");
					m.commandNome(name, os);
				break;
				
				case 4: JOptionPane.showMessageDialog(null, "Finalizado");
				break;
				
				default: JOptionPane.showMessageDialog(null, "Op��o inv�lida");
			}
		}
	}
}
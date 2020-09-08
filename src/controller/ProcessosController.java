package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

public class ProcessosController {
	
	public ProcessosController(){
		super();
	}
	
	public String os() {
		String os = System.getProperty("os.name");
		if (os.contains("Windows") || os.contains("Linux")) {
			JOptionPane.showMessageDialog(null, "Sistema Operacional: " + os);
		}
		else {
			JOptionPane.showMessageDialog(null, "Sistema Operacional incompativel");
		}
		return os;
	}
	
	public void commandProcess(String os) {
		if (os.contains("Windows")) {
			try {
				Process p = Runtime.getRuntime().exec("tasklist");
				InputStream fluxo = p.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);
				String linha = buffer.readLine();
				while (linha != null) {
					System.out.println(linha);
					linha = buffer.readLine();
				}
				fluxo.close();
				leitor.close();
				buffer.close();
				JOptionPane.showMessageDialog(null, "Processos ativos em seu sistema");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			if(os.contains("Linux")) {
				Process p;
				try {
					p = Runtime.getRuntime().exec("ps aux");
					InputStream fluxo = p.getInputStream();
					InputStreamReader leitor = new InputStreamReader(fluxo);
					BufferedReader buffer = new BufferedReader(leitor);
					String linha = buffer.readLine();
					while (linha != null) {
						System.out.println(linha);
						linha = buffer.readLine();
					}
					buffer.close();
					fluxo.close();
					leitor.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "Processos ativos em seu sistema");
			}
			else {
				JOptionPane.showMessageDialog(null, "Programa incompativel com Sistema Operacional");
			}
		}
	}
	
	public void commandPid(int pid, String os) {
		StringBuffer buffer = new StringBuffer();
		if (os.contains("Windows")) {
			String cmdPid = "taskkill /pid ";
			buffer.append(cmdPid);
			buffer.append(pid);
		}
		else {
			String cmdPid = "kill -9 ";
			buffer.append(cmdPid);
			buffer.append(pid);
		}
		callProcess(buffer.toString(), os);
	}
	
	public void commandNome(String name, String os) {
		if (os.contains("Windows")) {
			String cmdName = "taskkill /im ";
			StringBuffer buffer = new StringBuffer();
			buffer.append(cmdName);
			buffer.append(name);
			callProcess(buffer.toString(),os);
		}
		else {
			if (os.contains("Linux")) {
				String cmdName = ("pkill -f ");
				StringBuffer buffer = new StringBuffer();
				buffer.append(cmdName);
				buffer.append(name);
				callProcess(buffer.toString(),os);
			}
			else {
				JOptionPane.showMessageDialog(null, "Sistema Operacional incompativel");
			}
		}
	}
	
	void callProcess(String process, String os) {
		if (os.contains("Windows")) {
			try {
				Runtime.getRuntime().exec(process);
			} catch (IOException e) {
				String msgError = e.getMessage();
				if (msgError.contains("740")) {
					StringBuffer buffer = new StringBuffer();
					buffer.append("cmd /c ");
					buffer.append(process);
					try {
						Runtime.getRuntime().exec(process);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				else {
					e.printStackTrace();
				}
			}
		}
		else {
			try {
				Runtime.getRuntime().exec(process);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		JOptionPane.showMessageDialog(null, "Processo finalizado");
	}
}
package generareInput;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.Random;

public class Generare_input_txt {

		private String discipline[];
		private int nrOre[];
		private boolean areTeza[];
		private int nrDiscipline=0;
		private String elevi[];
		private int nrElevi=0;

	
	
	//metoda genereaza fisierul input.txt fara note si absente
	//astfel incat sa poata fi completat manual cu date reale
	public  void scrie_input1(File f)
	{
		
		try
		{
			f.createNewFile();
			PrintWriter fout=new PrintWriter(f);
			for(int i=0;i<nrElevi;i++)
			{
				//scriu numele elevului
				fout.println(elevi[i]);
				

				//pentru fiecare elev, scriu lista disciplinelor 
				//urmate de 4 seturi de paranteze
				for(int j=0;j<nrDiscipline;j++)
				{
					fout.println(discipline[j]+"()"+"()"+"()"+"()");
				}
				
				//afisez trei randuri goale dupa fiecare elev
				fout.println();
				fout.println();
				fout.println();
			}
			
			
			fout.close();
		}
		catch(IOException e)
		{
			System.out.print("nu s-a creat fisierul input.txt");
		}
	}
	
	
	
	//metoda genereaza fisierul input.txt completat cu note si absente aleator
	//fiecare disciplina primeste un nr aleator de note cuprins intre
	//nr de ore alocat disciplinei+1 si nr de ore alocat disciplinei+3
	public  void scrie_inputRand(File f)
	{
		
		try
		{
			f.createNewFile();
			PrintWriter fout=new PrintWriter(f);
			for(int i=0;i<nrElevi;i++)
			{
				//scriu numele elevului
				fout.println(elevi[i]);
			

				//pentru fiecare elev, scriu lista disciplinelor si note aleatoare 
				for(int j=0;j<nrDiscipline;j++)
				{
					//scriu numele disciplinei
					fout.print(discipline[j]+"(");
					
					//generez aleatoriu note 
					Random r=new Random();
					int nrEfectivDeNote=r.nextInt(2)+nrOre[j]+1;
					for(int k=1;k<nrEfectivDeNote;k++)
					{
						fout.print(r.nextInt(9)+1+",");
						
					}
					fout.print(r.nextInt(9)+1);
					fout.print(")(");
					
					//generez nota la teza, daca exista
					if(areTeza[j]==true)
					{
						fout.print(r.nextInt(9)+1);
					}
					fout.print(")(");
					
					//generez un nr aleator de absente motivate
					//fiecare absenta va fi X3, deoarece nu ma intereseaza propriu-zis
					//in aplicatie in ce zi a absentat, si doar numarul lor
					//nrMaxAbs=18*nrOrePeSaptamanaPtDisciplina
					//nr de absente va varia in intervalul [0;18*nrMaxAbs]
					//deoarece sunt 18 saptamani in semestrul scolar, relativ
					int nrAbs=r.nextInt(18*nrOre[j]);
					for(int k=1;k<nrAbs;k++)
						fout.print("X3,");
					fout.print("X3");
					fout.print(")(");
					
					//generez nr aleator de absente nemotivate
					//nr de absente va varia in intervalul [0,nrMaxAbs-nrAbs]
					int nrAbs2=r.nextInt(18*nrOre[j]-nrAbs);
					for(int k=1;k<nrAbs2;k++)
						fout.print("X3,");
					fout.print("X3");
					fout.print(")");
					
					//trec la urmatoarea disciplina pe un rand nou
					fout.println();
				}
				
				//afisez un rand gol dupa fiecare elev
				fout.println();
				
			}
			
			
			fout.close();
		}
		catch(IOException e)
		{
			System.out.print("nu s-a creat fisierul input.txt");
		}
	}
	
	
	
	
	//metoda preia numele disciplinelor din fisierul discipline.txt
	private void citesteDiscipline()
	{
		String sir=new String();
		String aux[]=new String[3];
		
		File f=new File("FisiereIntrare/discipline.txt");
		try(Scanner fin=new Scanner(f))
		{
			//citeste nr de discipline
			nrDiscipline=fin.nextInt();fin.nextLine();
			discipline=new String[nrDiscipline];
			nrOre=new int[nrDiscipline];
			areTeza=new boolean[nrDiscipline];
			
			//citeste numele fiecarei discipline si nr de ore alocat acesteia
			//si daca are sau nu teza disciplina respectiva
			for(int i=1;i<=nrDiscipline;i++)
			{
				sir=fin.nextLine();
				aux=sir.split(Pattern.quote("("));
				discipline[i-1]=aux[0];
				nrOre[i-1]=Integer.parseInt((aux[1].substring(0, aux[1].length()-1)).trim());
				if(aux[2].trim().charAt(0)=='D')
					areTeza[i-1]=true;
				else
					areTeza[i-1]=false;
				//System.out.println(discipline[i-1]+" "+nrOre[i-1]);
			}
		}
		catch(IOException e)
		{
			System.out.println("Nu s-a gasit fisierul discipline.txt");
			nrDiscipline=0;
			discipline=new String[nrDiscipline];
			nrOre=new int[nrDiscipline];
		}
		
	}
	

	
	
	//metoda preia numele elevilor din fisierul elevi.txt
	private void citesteElevi()
	{
		File f=new File("FisiereIntrare/elevi.txt");
		try(Scanner fin=new Scanner(f))
		{
			//citeste nr de elevi 
			nrElevi=fin.nextInt();fin.nextLine();
			elevi=new String[nrElevi];
			
			//citeste numele fiecarui elev in parte
			for(int i=1;i<=nrElevi;i++)
			{
				elevi[i-1]=fin.nextLine();
				
			}
			//System.out.print(Arrays.toString(elevi));
		}
		catch(IOException e)
		{
			System.out.print("Nu s-a gasit fisierul elevi.txt");
			nrElevi=0;
			elevi=new String[nrElevi];
			
		}
	}
	
	
	
	
	public static void main(String args[])
	{
		Generare_input_txt obtest=new Generare_input_txt();
		
		obtest.citesteDiscipline();
		obtest.citesteElevi();
		
		/*obtest.scrie_input1(new File("FisiereIntrare/InputSem1.txt"));
		obtest.scrie_input1(new File("FisiereIntrare/InputSem2.txt"));*/
		
		obtest.scrie_inputRand(new File("FisiereIntrare/InputSem1.txt"));
		obtest.scrie_inputRand(new File("FisiereIntrare/InputSem2.txt"));
	}
}

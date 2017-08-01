package SituatieScolara.SituatieScolara;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.PrintWriter;

public class GenereazaSituatie {
	private static List<Elev> elevi;
	
	public static void main(String[] args) {
		
		citesteInput(new File("FisiereIntrare/InputSem1.txt"),
				new File("FisiereIntrare/InputSem2.txt"));
		
		scrieHTMLSemestrial(new File("FisiereIesire/SituatieSem1.html"));
		scrieHTMLSemestrial(new File("FisiereIesire/SituatieSem2.html"));
		/*scrieHTMLGeneral(new File("FisiereIesire/SituatieGenerala.html"));*/
		
	}

	//aceasta metoda citeste fisierele input.txt corespunzatoare fiecarui semestru
	//si completeaza datele in tabloul elevi
	private static void citesteInput(File f1, File f2)
	{
		//aflu cate discipline sunt din fisierul discipline.txt
		//si cati elevi sunt din fisierul elevi.txt
		int nrDiscipline=0;
		int nrElevi=0;
		try(Scanner fin1=new Scanner(new File("FisiereIntrare/discipline.txt"));
				Scanner fin2=new Scanner(new File("FisiereIntrare/elevi.txt")))
		{
			nrDiscipline=fin1.nextInt();
			nrElevi=fin2.nextInt();
			//System.out.println(nrDiscipline+ " "+nrElevi);
		}
		catch(IOException e)
		{
			System.out.print("Nu s-au gasit fisierele discipline.txt si elevi.txt");
		}
		
		//variabilele list1 si list2 retin situatia scolara inregistrata la fiecare
		//disciplina pentru un elev (list1 pt sem1, list2 pt sem2
		List<Disciplina> list1=new ArrayList<>(nrDiscipline);
		List<Disciplina> list2=new ArrayList<>(nrDiscipline);
		
		elevi=new ArrayList<>(nrElevi);
		
		//variabile pentru a citi din cele doua fisiere de intrare 
		//informatiile si a fi transmise mai apoi constructorului unei discipline
		//si constructorului unui elev
		String numeElev=new String();
		String sir1=new String();//variabila de lucru
		String sir2=new String();//variabila de lucru
		String []sirSplit1=null;//variabila de lucru
		String []sirSplit2=null;//variabila de lucru
		String numeDisciplina=null;
		StringBuilder sirNote=null;//variabila de lucru
		String []sirNoteSplit=null;//variabila de lucru
		int []note1;
		int []note2;
		int teza1=0;
		int teza2=0;
		StringBuilder sirAbsMot=null;//variabila de lucru
		String []absMot1=null;//trebuie alocat dupa ce stim cate absente sunt
		String []absMot2=null;//trebuie alocat dupa ce stim cate absente sunt
		StringBuilder sirAbsNemot=null;//variabila de lucru
		String []absNemot1=null;//trebuie alocat dupa ce stim cate absente sunt
		String []absNemot2=null;//trebuie alocat dupa ce stim cate absente sunt
		Disciplina disciplina=null;
		Elev elev=null;
		
		//parcurg cele doua fisiere inputSem1.txt si inputSem2.txt
		//pentru a prelua datele din ele si ale stoca in variabilele de mai sus
		try(Scanner fin1=new Scanner(f1);Scanner fin2=new Scanner(f2))
		{
			for(int i=1;i<=nrElevi;i++)
			{
				list1.clear();
				list2.clear();
				numeElev=fin1.nextLine();
				fin2.nextLine();//sar peste numele elevului in fisierul pt sem2
				
				//pentru fiecare elev, prelucrez fiecare disciplina in parte
				for(int j=1;j<=nrDiscipline;j++)
				{
					sir1=fin1.nextLine();// System.out.println(sir1);
					sir2=fin2.nextLine();//System.out.println(sir2);
					
					//obtin acces la componentele unei linii din fisier
					//pt aprelucra numele disciplinei, notele, teza, absentele 
					sirSplit1=sir1.split(Pattern.quote("("));//System.out.println(Arrays.toString(sirSplit1));
					sirSplit2=sir2.split(Pattern.quote("("));//System.out.println(Arrays.toString(sirSplit2));
					
					numeDisciplina=new String(sirSplit1[0]);//System.out.println(numeDisciplina);
					
			//note
					//notele din sem 1 pentru disciplina curenta
					sirNote=new StringBuilder(sirSplit1[1]);//System.out.println(sirSplit1[1]);System.out.println(sirNote);
					//elimin ")"
					sirNote.setLength(sirNote.length()-1);
					//impart dupa , pt a obtine fiecare nota
					if(sirNote.length()>0)
					{
						sirNoteSplit=sirNote.toString().split(",");//System.out.println(Arrays.toString(sirNoteSplit));
						note1=new int[sirNoteSplit.length];
					}
					else 
						note1=new int[0];
					
					for(int k=0;k<note1.length;k++)
						note1[k]=Integer.parseInt(sirNoteSplit[k].trim());
					//System.out.println(Arrays.toString(note1));
					
					
					
					//notele din sem 2 pentru disciplina curenta
					sirNote=new StringBuilder(sirSplit2[1]);
					//elimin ')'
					sirNote.setLength(sirNote.length()-1);
					if(sirNote.length()>0)
					{
						sirNoteSplit=sirNote.toString().split(",");//System.out.println(Arrays.toString(sirNoteSplit));
						note2=new int[sirNoteSplit.length];
					}
					else 
						note2=new int[0];
					
					//System.out.println(sirNoteSplit.length);
					for(int k=0;k<note2.length;k++)
						note2[k]=Integer.parseInt(sirNoteSplit[k].trim());
					//System.out.println(Arrays.toString(note2));
					
					
			//teze		
					//obtin nota de la teza sem 1
					sirNote=new StringBuilder(sirSplit1[2]);
					//elimin ')'
					sirNote.setLength(sirNote.length()-1);
					if(sirNote.length()>0)teza1=Integer.parseInt(sirNote.toString());
					//System.out.println(teza1);
					
					//obtin nota de la teza sem 2
					sirNote=new StringBuilder(sirSplit2[2]);
					//elimin ')'
					sirNote.setLength(sirNote.length()-1);
					if(sirNote.length()>0)teza1=Integer.parseInt(sirNote.toString());
					//System.out.println(teza2);
					
					
			//absente motivate
					//obtin absentele motivate sem1
					sirAbsMot=new StringBuilder(sirSplit1[3]);
					//sterg ')'
					sirAbsMot.setLength(sirAbsMot.length()-1);
					if(sirAbsMot.length()>0) 
						absMot1=sirAbsMot.toString().split(",");
					else 
						absMot1=new String[0];
					//System.out.println(absMot1.length);
					
					//obtin absentele motivate sem2
					sirAbsMot=new StringBuilder(sirSplit2[3]);
					//sterg ')'
					sirAbsMot.setLength(sirAbsMot.length()-1);
					if(sirAbsMot.length()>0) 
						absMot2=sirAbsMot.toString().split(",");
					else 
						absMot2=new String[0];
					//System.out.println(absMot2.length);
					
					
			//absente nemotivate
					//obtin absentele nemotivate sem1
					sirAbsNemot=new StringBuilder(sirSplit1[4]);
					//sterg ')'
					sirAbsNemot.setLength(sirAbsNemot.length()-1);
					if(sirAbsNemot.length()>0) 
						absNemot1=sirAbsNemot.toString().split(",");
					else 
						absNemot1=new String[0];
					//System.out.println(absNemot1.length);
					
					//obtin absentele Nemotivate sem2
					sirAbsNemot=new StringBuilder(sirSplit2[4]);
					//sterg ')'
					sirAbsNemot.setLength(sirAbsNemot.length()-1);
					if(sirAbsNemot.length()>0) 
						absNemot2=sirAbsNemot.toString().split(",");
					else 
						absNemot2=new String[0];
					//System.out.println(absNemot2.length);	
					
					
					
					//creez un obiect disciplina care va retine caracteristicile 
					//disciplinei curente 
					//numarul de ore nu mai conteaza in calcularea situatiei scolare
					//asadar il voi considera 1
					//apoi il adaug in lista semestrului corespunzator
					
					//sem1
					disciplina=new Disciplina(numeDisciplina,1,note1,teza1,absMot1,absNemot1);
					//adaug disciplina creata in lista semestrului 1
					list1.add(disciplina); 
					
					//sem2
					disciplina=new Disciplina(numeDisciplina,1,note2,teza2,absMot2,absNemot2);
					//adaug disciplina creata in lista semestrului 1
					list2.add(disciplina);
				}
				
				//construiesc un elev
				elev=new Elev(numeElev,list1,list2);
				//il adaug in lista de elevi
				elevi.add(elev);
				fin1.nextLine();fin2.nextLine();
			}
			
			
		}
		catch(IOException e)
		{
			
		}
	}
	
	//aceasta metoda scrie fisierul de iesire situatieSemestrulX.HTML,
	//numele fisierului fiind precizat de obiectul File primit ca parametru
	private static void scrieHTMLSemestrial(File f)
	{
		String s=f.toString();
		
		try{
			f.createNewFile();
			PrintWriter fout=new PrintWriter(f);
			fout.println("<html>");
			fout.println("	<head><title>Situatie Semestriala</title></head>");
			fout.println("	<body bgcolor=grey>");
			fout.println("		<center><h1>SITUATIE SEMESTRUL "+s.charAt(25)+"</h1></center>");
			fout.println("		<br><br><br>");
			
			
			//parcurgen lista de elevi si pentru fiecare in parte vom afisa informatiile
			//din situatia semestriala
			for(Elev elev:elevi)
			{
				//determin pentru ce semestru este apelata metoda si 
				//aleg situatia corecta
				SituatieSemestriala ss;
				if(f.toString().charAt(25)=='1') 
					ss=elev.getSem1();
				else
					ss=elev.getSem2();
				
				//scriu numele elevului
				
				fout.println("<b>"+elev.getNume().toUpperCase()+
						":	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;MEDIA SEMESTRIALA: "+
						ss.getMediaSemestriala()+"</b>");
				fout.println("		<table border=2 bgcolor=white>");
				//antetul tabelului
				fout.println("			<tr>");
					
					fout.print("				<td>");
					fout.print("Nr.Crt.");	
					fout.println("</td>");
					
					fout.print("				<td>");
					fout.print("Disciplina");	
					fout.println("</td>");
					
					fout.print("				<td>");
					fout.print("Note");	
					fout.println("</td>");
					
					fout.print("				<td>");
					fout.print("Teza");	
					fout.println("</td>");
					
					fout.print("				<td>");
					fout.print("Absente Motivate");	
					fout.println("</td>");
					
					fout.print("				<td>");
					fout.print("Absente Nemotivate");	
					fout.println("</td>");
					
					fout.print("				<td>");
					fout.print("Media");	
					fout.println("</td>");
				
				fout.println("			</tr>");
				
				
				
				
				//pentru fiecare disciplina afisam cate o linie de tabel
				int k=1;
				for(Disciplina disciplina:ss.getDiscipline())
				{
				fout.println("			<tr>");
					
					fout.print("				<td width=5%>");
					fout.print(k++);	
					fout.println("</td>");
					
					fout.print("				<td width=20%>");
					fout.print(disciplina.getNume().toUpperCase());	
					fout.println("</td>");
					
					fout.print("				<td  width=15%>");
					fout.print(Arrays.toString(disciplina.getNote()));	
					fout.println("</td>");
					
					fout.print("				<td width=5%>");
					fout.print(disciplina.getTeza());	
					fout.println("</td>");
					
					fout.print("				<td width=25%>");
					fout.print(Arrays.toString(disciplina.getAbsMot()));	
					fout.println("</td>");
					
					fout.print("				<td width=25%>");
					fout.print(Arrays.toString(disciplina.getAbsNemot()));	
					fout.println("</td>");
					
					fout.print("				<td width=5%>");
					fout.print(disciplina.getMedia());	
					fout.println("</td>");
				
				fout.println("			</tr>");
				}
				fout.println("			<tr>");
				fout.print("				<td>"+k+"</td>");
				fout.print("				<td colspan=5>PURTARE</td>");
				fout.print("				<td>"+ss.getMediaPurtare()+"</td>");
				fout.println("			</tr>");
				fout.println("		</table>");
				fout.println("<br>");
			}
			

			fout.println("	</body>");
			fout.println("</html>");
			fout.close();
		}
		catch(IOException e)
		{
			System.out.print("Nu s-a creat fisierul");
		}
		
	}
	
	private static void scrieHTMLGeneral(File f)
	{
		
		try{
			f.createNewFile();
		}
		catch(IOException e)
		{
			
		}
	}
}

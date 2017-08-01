package SituatieScolara.SituatieScolara;

import java.util.Arrays;

public class Disciplina {
	
	private String nume;
	private int nrOre;
	private int note[];
	private int teza=0;
	private String absNemot[];
	private String absMot[];
	private int media=0;
		
	
	public Disciplina(String nume,int nrOre,int []note,
			int teza,String []absNemot, String []absMot)
	{
		this.nume=nume;
		this.nrOre=nrOre;
		
		this.note=new int[note.length];
		this.note=Arrays.copyOf(note, note.length);
		
		this.teza=teza;
		
		this.absMot=new String[absMot.length];
		this.absMot=Arrays.copyOf(absMot,absMot.length);
		
		this.absNemot=new String[absNemot.length];
		this.absNemot=Arrays.copyOf(absNemot,absNemot.length);
		
		int suma=0;
		for(int i:note)
			suma+=i;
		if(teza==0)
		{
			media=Math.round(((float)suma)/note.length);
		}
		else 
		{
			media=Math.round(((((float)suma)/note.length)*3+teza)/4);
		}
	}

	
	public String getNume() {
		return nume;
	}

	

	public int getNrOre() {
		return nrOre;
	}

	

	public int[] getNote() {
		return note;
	}

	

	public int getTeza() {
		return teza;
	}

	

	public String[] getAbsNemot() {
		return absNemot;
	}

	

	public String[] getAbsMot() {
		return absMot;
	}

	

	public int getMedia() {
		return media;
	}

	


}

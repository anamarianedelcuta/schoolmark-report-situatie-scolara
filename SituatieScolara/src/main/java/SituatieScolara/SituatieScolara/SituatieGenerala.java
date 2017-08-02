package SituatieScolara.SituatieScolara;

import java.util.Arrays;
import java.util.List;


//aceasta clasa calculeaza si furnizeaza media generala
//pe baza a doua situatii semestriale furnizate
public class SituatieGenerala {
	
	private float mediaGenerala;
	private float mediiAnuale[];
	public SituatieGenerala(SituatieSemestriala sem1,SituatieSemestriala sem2)
	{
		//calculam mediile anuale
		List<Disciplina> disciplineSem1=sem1.getDiscipline();
		List<Disciplina> disciplineSem2=sem2.getDiscipline();
		
		int k=0;
		mediiAnuale=new float[disciplineSem1.size()];
		Arrays.fill(mediiAnuale, 0);
		
		
		for(Disciplina i:disciplineSem1)
		{
			mediiAnuale[k++]=i.getMedia(); 
		}
		//System.out.println("medii sme1 "+Arrays.toString(mediiAnuale));
		
		k=0;
		for(Disciplina i:disciplineSem2)
		{
			mediiAnuale[k]=((float)(mediiAnuale[k++]+i.getMedia()))/2;
		}
		//System.out.println("medii sme1+2 "+Arrays.toString(mediiAnuale));
		
		//calculam media generala
		float suma=0;
		for(int i=0;i<mediiAnuale.length;i++)
		{
			suma+=mediiAnuale[i];
		}
		suma+=((float)(sem1.getMediaPurtare()+sem2.getMediaPurtare()))/2;
		mediaGenerala=(float)(Math.floor(suma/(mediiAnuale.length+1)*100)/100);
	}

	public float getMediaGenerala() {
		return mediaGenerala;
	}

	public float[] getMediiAnuale() {
		return mediiAnuale;
	}

	
}

package SituatieScolara.SituatieScolara;

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
		
		for(Disciplina i:disciplineSem1)
		{
			mediiAnuale[k++]=i.getMedia();
		}
		
		k=0;
		for(Disciplina i:disciplineSem2)
		{
			mediiAnuale[k]=(mediiAnuale[k]+i.getMedia())/2;
		}
		
		//calculam media generala
		float suma=0;
		for(int i=0;i<mediiAnuale.length;i++)
		{
			suma+=mediiAnuale[i];
		}
		mediaGenerala=suma/mediiAnuale.length;
	}

	public float getMediaGenerala() {
		return mediaGenerala;
	}

	public float[] getMediiAnuale() {
		return mediiAnuale;
	}

	
}

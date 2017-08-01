package SituatieScolara.SituatieScolara;

import java.util.List;
import java.util.ArrayList;


//aceasta clasa calculeaza media la purtare pentru o situatie pe discipline data si 
//apoi calculeaza media semestriala pentru un anumit elev
public class SituatieSemestriala {
	
	private int mediaPurtare;
	private float mediaSemestriala=0;
	private List<Disciplina> discipline;
	
	public SituatieSemestriala(List<Disciplina> discipline)
	{
		this.discipline=new ArrayList<>(discipline);
		
		//numaram toate absentele nemotivate
		int nrAbsNemot=0;
			
		for(Disciplina i:discipline)
		{
			nrAbsNemot+=i.getAbsNemot().length;
		}

		//calculam media la purtare tinand cont de numarul de absente nemotivate
		if(nrAbsNemot>=60)
			mediaPurtare=4;
		else 
			mediaPurtare=10-nrAbsNemot/10;
		
		//calculam media semestriala, tinand cont si de media la purtare, calculata anterior
		float suma=0;
		for(Disciplina i:discipline)
		{
			suma+=i.getMedia();
		}
		mediaSemestriala=(float)(Math.floor(((float)(suma+mediaPurtare))/(discipline.size()+1)*100)/100);
	}

	public int getMediaPurtare() {
		return mediaPurtare;
	}

	public float getMediaSemestriala() {
		return mediaSemestriala;
	}

	public List<Disciplina> getDiscipline() {
		return discipline;
	}

	
		
}

package SituatieScolara.SituatieScolara;

import java.util.List;

//aceasta clasa asociaza numele elevului cu situatiile smesetriale
//si determina situatia generala pe baza celor semestriale
public class Elev {
	private String nume;
	private SituatieSemestriala sem1;
	private SituatieSemestriala sem2;
	private SituatieGenerala general;
	
	public Elev(String nume, List<Disciplina> lista1, List<Disciplina> lista2)
	{
		this.nume=nume;
		sem1=new SituatieSemestriala(lista1);
		sem2=new SituatieSemestriala(lista2);
		general=new SituatieGenerala(sem1,sem2);
		
	}

	public String getNume() {
		return nume;
	}

	public SituatieSemestriala getSem1() {
		return sem1;
	}

	public SituatieSemestriala getSem2() {
		return sem2;
	}

	public SituatieGenerala getGeneral() {
		return general;
	}


	
}

package Serie53;

import java.util.*;

import MesInterfaces.InterfaceStructure;
import Utils.*;

public class Commande53<TypeDeNumero> implements InterfaceStructure<LigneDeCommande53, TypeDeNumero>{
	private static final long serialVersionUID = 1L;
	private Vector<LigneDeCommande53> cde = new Vector<LigneDeCommande53>();
	private TypeDeNumero numCde;
	private DateUser dat_Commande = new DateUser();
	private String numClient; 
	private float valeurTotal = 0f; 
	private DateUser dateFacture; 
	private Boolean etatFacture = false; 
	
	/**
	 * @param cde
	 * @param numCde formatÃ© selon : 201709121 pour la commande 1 passeÌ�e le 12 Septembre 2017
	 */
	public Commande53(Vector<LigneDeCommande53> cde, TypeDeNumero numCde) {
		this.cde = cde;
		this.numCde = numCde; 
	}

	public Commande53() {
	}

	public void ajouter(LigneDeCommande53 ldc) {
		cde.addElement(ldc);
	}

	/**
	 * supprimer une ligne de commande en fonction d'un code produit
	 * @param code
	 */
	public void supprimer(int code2) {
		int code = (int)code2;
		Iterator<LigneDeCommande53> it = cde.iterator();
		while (it.hasNext()) { 
			LigneDeCommande53 v = it.next();
			if (v.getCode() == code)
				it.remove();
		}
	}
	
	public void supprimer(TypeDeNumero code2) {} //Laisser vide pour le contrat d'intereface
	
	 public LigneDeCommande53 retournerIndice(int indice) {
		if (taille() == 0) return null;
		return cde.get(indice);
	 }
	
	 /**
	  * retourner le code 
	  * @param code
	  * @return
	  */
	 //TOFIX retourner qlqch
	public LigneDeCommande53 retourner(int cle) {
		int code = (int)cle;
		if (taille() == 0) return null;
		for (LigneDeCommande53 ldc : cde) {
			if (ldc.getCode() == code)
				return ldc;
		}
		return null;
	}
	
	public LigneDeCommande53 retourner(TypeDeNumero cle) {return null; }//Pour l'interface laisser tel que
	
	public int taille() {
		return cde.size();
	}
	
	public String facturer(TableDesArticles53 tabArt) {
		if (taille() == 0) return "\n*** PAS DE FACTURE A EDITER ***\n";
		String entete = "_______________________________________________________________________________________________________________________________\n"
						+ "Code \tDESIGNATION\t\tQUANTITE\tPU(HT)\tTOTAL(HT)\tTOTAL APRES REDUCTION(HT)\n" 
						+ "_______________________________________________________________________________________________________________________________\n";
		String details = "";
		float prixTotalHT =0F;
		for(LigneDeCommande53 ldc : cde) {
			details = details + ldc.facturer(tabArt) + "\n";
			prixTotalHT += ldc.prixTotal(tabArt);
		}
		float prixTotalTTC = (float) ((int)((prixTotalHT*18.6)/100 + prixTotalHT)*100)/100f;
		String pied = "_______________________________________________________________________________________________________________________________\n"
					+ "_______________________________________________________________________________________________________________________________\n"
					+ "\n\t\t\t\t\t\t**PRIX TOTAL(HT)** " + prixTotalHT + "\n"
					+ "\n\t\t\t\t\t\t**TVA (18.6%) ** " + (float) (((int)(((prixTotalHT*18.6)/100))*100)/100f) + "\n" 
					+ "\n\t\t\t\t\t\t**PRIX TOTAL(TTC)** " + prixTotalTTC + "\n";
		return entete+details+pied;
	}

	public Vector<LigneDeCommande53> getCde() {
		return cde;
	}

	public TypeDeNumero getNumcde() {
		return numCde;
	}

	public DateUser getDatCde() {
		return dat_Commande;
	}

	public void setCde(Vector<LigneDeCommande53> cde) {
		this.cde = cde;
	}

	public void setNumcde(TypeDeNumero numCde) {
		this.numCde = numCde;
	}

	public void setDatCde(DateUser datCde) {
		this.dat_Commande = datCde;
	}
	
	public TypeDeNumero getNumCde() {
		return numCde;
	}

	public String getNumClient() {
		return numClient;
	}

	public float getValeurTotal() {
		return valeurTotal;
	}

	public DateUser getDateFacture() {
		return dateFacture;
	}

	public Boolean getEtatFacture() {
		return etatFacture;
	}

	public void setNumCde(TypeDeNumero numCde) {
		this.numCde = numCde;
	}

	public void setNumClient(String numClient) {
		this.numClient = numClient;
	}

	public void setValeurTotal(float valeurTotal) {
		this.valeurTotal = valeurTotal;
	}

	public void setDateFacture(DateUser dateFacture) {
		this.dateFacture = dateFacture;
	}

	public void setEtatFacture(Boolean etatFacture) {
		this.etatFacture = etatFacture;
	}

	@Override
	public String toString() {
		String st = "\n\t Numero du Client : " + numClient + "\tCommande NÂ°: " + numCde + "\t\tDate Cde: " + dat_Commande + "\n";
			  st += "_______________________________________________________________________________________\n";
		if (etatFacture) {
			st += "  FACTURÃ‰ LE " + dateFacture + " pour une valeur totale H.T. de " + valeurTotal + "â‚¬\n";
		}else {
			st += "  EN ATTENTE DE FACTURATION  pour une valeur totale H.T. de " + valeurTotal +"\n";
		}
		st += "_______________________________________________________________________________________\n";
		st += "Code\tQuantitÃ©\n";
		st += "_______________________________________________________________________________________\n";
		for (LigneDeCommande53 ldc : cde) {
			st += ldc.toString() + "\n";
		}
		return st;
	}

	public float prixTotalHT(TableDesArticles53 tabArt) {
		float prixTotalHT = 0f;
		for (LigneDeCommande53 ldc : cde) {
			prixTotalHT += ldc.prixTotal(tabArt);
		}
		return prixTotalHT;
	}

	@Override
	public Set<TypeDeNumero> cle() {
		// Laisser null pour le contrat de l'interface.
		return null;
	}

	
	
}

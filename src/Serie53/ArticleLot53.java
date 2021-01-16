package Serie53;

import Utils.DateUser;
/**
 * DESTOCKAGE
 * Articles vendu par lot sur une période de « réclame » de 7jours 
 *
 */
public class ArticleLot53 extends ArticleAbstrait53{
	private static final long serialVersionUID = 1L;
	private int qtiteLot; 
	private float reduction;
	private int periode = 7;
	private DateUser dateDebut;
	private DateUser dateFin;
	
	
	public ArticleLot53(int code, String designation, float pu, int qtiteLot, float reduction) {
		super(code, designation, pu);
		this.qtiteLot = qtiteLot;
		this.reduction = reduction / 100;
		this.dateDebut = new DateUser();
		setPeriode(periode);		
	}

	@Override
	public String toString() {
		return "Code: " + getCode() + "\t\tDésignation: " + getDesignation() + "\t\tP.U.: " + getPu() + "\t* PROMO LOT * Reduction: " + reduction + " * Lot de:" + qtiteLot + "\n\t\t\t\t\t\t Du " + dateDebut + " au " + dateFin;
	}

	/**
	 * Si la facture est comprise dans la période (7 jours par défaut) de la réduction de vente par lot
	 * la quantité d'articles est divisé par le nombre de lot pour appliquer la réduction
	 * le reste est facturé au prix unitaire normal : sans réduction.
	 */
	@Override
	public float prixFacture(int quantite) {
		DateUser today = new DateUser();
		float prix;
		if ((dateDebut.compareTo(today) <= 0) && (today.compareTo(dateFin) <= 0)){
			int nbLot = quantite / qtiteLot;
			int reste = quantite % qtiteLot;
			prix = (nbLot * qtiteLot) * getPu() * (1 - reduction) + reste * getPu();
		}else
			prix = quantite * getPu();
		return prix;
	}

	@Override
	public String facturer(int quantite) {
		return " " + getCode() 
		+ "\t"+ getDesignation() 
		+ "\t\t" + quantite 
		+"\t" + getPu() 
		+"\t" + (int) (getPu() * quantite) 
		+"\t" + (int)(prixFacture(quantite) * 100) / 100f 
		+ "\n\t\t\t\t\t\tArticle vendu par lot (remise de : " + (reduction *100) + "% pour un lot de " + qtiteLot +")"
		+ "\n\t\t\t\t\t\tSur la période du " + dateDebut + " au " + dateFin;
	}

	public int getQtiteLot() {
		return qtiteLot;
	}

	public float getReduction() {
		return reduction;
	}

	public int getPeriode() {
		return periode;
	}

	public DateUser getDateDebut() {
		return dateDebut;
	}

	public void setQtiteLot(int qtiteLot) {
		this.qtiteLot = qtiteLot;
	}

	public void setReduction(float reduction) {
		this.reduction = reduction;
	}

	public void setPeriode(int periode) {
		this.periode = periode;
		this.dateFin = new DateUser().ajouter(periode);
	}

	public void setDateDep(DateUser dateDep) {
		this.dateDebut = dateDep;
	}

	public DateUser getDateFin() {
		return dateFin;
	}
	
}

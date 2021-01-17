package Serie53;

import java.io.Serializable;

public class LigneDeCommande53 implements Serializable{
	private static final long serialVersionUID = 1L;
	private int code_Commande;
	private int quantite;

	public LigneDeCommande53(int code, int quantite) {
		this.code_Commande = code;
		this.quantite = quantite;
	}

	public LigneDeCommande53() {}
	
	public String facturer(TableDesArticles53 tabArt) {
		ArticleAbstrait53 art = tabArt.retourner(code_Commande);
		return art.facturer(quantite);
	}
	
	public float prixTotal(TableDesArticles53 tabArt) {
		ArticleAbstrait53 art = tabArt.retourner(code_Commande);
		if (art != null) return art.prixFacture(quantite); // exemple de liaison Dynamique
		return 0;
	}
	
	public String toString() {
		return code_Commande +"\t" + quantite;
	}

	public int getCode() {
		return code_Commande;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setCode(int code) {
		this.code_Commande = code;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}



}

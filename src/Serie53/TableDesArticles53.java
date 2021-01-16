package Serie53;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

import MesInterfaces.InterfaceStructure;
import Utils.DateUser;

public class TableDesArticles53 implements InterfaceStructure <ArticleAbstrait53, Integer>{
	private static final long serialVersionUID = 1L;
	private TreeMap<Integer, ArticleAbstrait53> tabArt = new TreeMap<Integer, ArticleAbstrait53>();
	
	public TableDesArticles53() {
		ArticleAbstrait53 article1 = new Article53(1, "disque dur", 50.5f);
		ArticleAbstrait53 article2 = new Article53(4, "carte mère", 1000f);
		ArticleAbstrait53 article3 = new Article53(5, "carte réseau", 24.7f);
		ArticleAbstrait53 article4 = new Article53(12, "boite 100 cd", 75.5f);
		ArticleAbstrait53 article5 = new Article53(18, "mémoire flash", 17f);
		
		ArticleAbstrait53 artpro = new ArticlePromo53(2, "Clé usb", 4f, 2, 50f) ;
		ArticleAbstrait53 artLot = new ArticleLot53(6, "raspberry", 30, 2, 50);
		
		tabArt.put(article1.getCode(), article1);
		tabArt.put(article2.getCode(), article2);
		tabArt.put(article3.getCode(), article3);
		tabArt.put(article4.getCode(), article4);
		tabArt.put(article5.getCode(), article5);
		tabArt.put(artpro.getCode(), artpro);
		tabArt.put(artLot.getCode(), artLot);
	}
	
	public String toString() {
		String st = "";
		if (taille() == 0) st = "\n*** STOCK VIDE ***\n";
		else {
			st = "\n*** LISTE DES ARTICLES EN STOCK \n";
			for(ArticleAbstrait53 art : tabArt.values()) {
				st += art.toString() + "\n";
			}
		}
		return st;
	}
	
	public String toStringPromo() {
		String st = "";
		if (taille() == 0) st = "\n*** STOCK VIDE ***\n";
		else {
			st = "\n*** LISTE DES ARTICLES EN PROMO EN STOCK \n";
			for(ArticleAbstrait53 art : tabArt.values()) {
				if (art instanceof ArticlePromo53)
					st += art.toString() + "\n"; 
			}
		}
		return st;
	}
	
	public String toStringLot() {
		String st = "";
		if (taille() == 0) st = "\n*** STOCK VIDE ****\n";
		else {
			st = "\n*** LISTE DES ARTICLES VENDU EN LOT EN STOCK *** \n";
			for(ArticleAbstrait53 art : tabArt.values()) {
				if (art instanceof ArticleLot53)
					st += art.toString() + "\n"; 
			}
		}
		return st;
	}
	
	public int taille() {
		return tabArt.size();
	}
	
	public void ajouter(ArticleAbstrait53 art) {
		if(!tabArt.containsKey(art.getCode())) 
			tabArt.put(art.getCode(), art);
	}
	
	public void modifier(ArticleAbstrait53 art) {
		tabArt.put(art.getCode(), art);
	}
	
	public ArticleAbstrait53 retourner(Integer cle) {
		int code = (int) cle;
		return tabArt.get(code);
	}

	public TreeMap<Integer, ArticleAbstrait53> getTabArt() {
		return tabArt;
	}

	public void setTabArt(TreeMap<Integer, ArticleAbstrait53> tabArt) {
		this.tabArt = tabArt;
	}
	
	public Set<Integer> cle(){
		return tabArt.keySet();
	}

	public void supprimer(Integer cle) {
		int code = (int) cle;
		tabArt.remove(code);
	}

	/**
	 * Si la période de braderie pour l'article lot est dépassé. 
	 * les articles lots concernés sont transformé en article sans réduction.
	 */
	public void purge() {
		Collection<ArticleAbstrait53> tabart = tabArt.values();
		for (Iterator<ArticleAbstrait53> i = tabart.iterator() ; i.hasNext();){
			ArticleAbstrait53 element = i.next();
			if (element instanceof ArticleLot53) {
				DateUser dateSys = new DateUser();
				DateUser datLimite = new DateUser(((ArticleLot53) element).getDateFin());
    				if (dateSys.compareTo(datLimite) > 0) {
    					ArticleAbstrait53 transfo = new Article53(element.getCode(), element.getDesignation(), element.getPu());
    					tabArt.put(element.getCode(), transfo);
    				}
 			}
		}
	}

}

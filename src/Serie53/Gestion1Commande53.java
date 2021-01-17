	package Serie53;



import MesInterfaces.InterfaceGestion;
import iPane.ES;
import mesExceptions.AbandonException;

public class Gestion1Commande53 implements InterfaceGestion <Commande53<String>, LigneDeCommande53 >{
	static ES ES = new ES();
	
	public Gestion1Commande53() {
	}

	/**
	 * MENU GESTION D'UNE COMMANDE
	 * 
	 * @return
	 * @throws AbandonException 
	 */
	public void menuGeneral(Commande53<String> cde,  Object ...objects) throws AbandonException {
		TableDesArticles53 tabArt = (TableDesArticles53) objects[0];
		String numCde = (String) objects[1];
		TableDesCommandes53 tabCde = (TableDesCommandes53) objects[2];
		int choix;
		tabArt.purge();
		do {
			choix = menuChoix(numCde);
			switch (choix) {
			case 1:
				creer(cde, tabArt);
				break;
			case 2:
				modifier(cde, tabArt, tabCde);
				break;
			case 3:
				supprimer(cde, tabCde);
				break;
			case 4:
				afficher(cde, tabCde);
				break;
			case 0:
				break;
			}
		} while (choix != 0);
		tabCde.purgeVide();
	}

	/**
	 * IHM MENU CREATION D'UNE COMMANDE
	 * @return
	 * @throws AbandonException 
	 */
	public  int menuChoix(Object ...objects) throws AbandonException {
		String numCde = (String) objects[0];
		String menu = "\n\n ***   GESTION DE LA COMMANDE N° " + numCde + "    ***\n\n"
						+ " SAISIR une ligne de commande ........................1 \n"
						+ " MODIFIER une ligne de commande ......................2 \n" 
						+ " SUPPRIMER une ligne de commande .................... 3 \n" 
						+ " AFFICHER la commande ............................... 4 \n"
						+ " FIN .................................................0 \n"
						+ " VOTRE CHOIX :\n";
		return ES.saisie(menu, 0, 4);
	}
	
	/**
	 * Ajoute une ligne de commande si la saisie du code produit existe.
	 * 	Si la ligne de commande existe on additionne les quantités.
	 * 	Sinon on crée une nouvelle ligne de commande
	 * @param cde
	 * @param tabArt
	 * @throws AbandonException 
	 */
	public void creer(Commande53<String> cde,Object ...objects) throws AbandonException {
		TableDesArticles53 tabArt = (TableDesArticles53) objects[0];
		ES.affiche(tabArt.toString());
		LigneDeCommande53 ldc = saisie(tabArt);
		if (ldc != null) {
			LigneDeCommande53 nldc = cde.retourner(ldc.getCode());
			if (nldc == null)
				cde.ajouter(ldc);
			else
				nldc.setQuantite(nldc.getQuantite() + ldc.getQuantite());
		} else
			ES.affiche("Code article n'existe pas\n");
	}

	/**
	 * IHM Saisie une ligne de commande. 
	 * Si le code existe on saisie la quantité et on retourne une nouvelle ligne de commande
	 * Sinon on renvoi null
	 * @param tabArt
	 * @return une nouvelle ligne de commande
	 * @throws AbandonException 
	 */
	public LigneDeCommande53 saisie(TableDesArticles53 tabArt, Object... objects) throws AbandonException {
		int code = ES.saisie("\n***   SAISIE D'UNE LIGNE   ***\n Code : ", 1);
		ArticleAbstrait53 art=tabArt.retourner(code) ;
		if (art!= null) {
			if (art instanceof ArticlePromo53) 
				ES.affiche("***   Cet article est en PROMOTION   ***\nPour une quantité minimum de : " +((ArticlePromo53)art).getQuantiteMini()+"\n");
			if (art instanceof ArticleLot53) 
				ES.affiche("***   Cet article est VENDU EN LOT   ***\nUn lot a une quantité minimum de : " +((ArticleLot53)art).getQtiteLot()+"\n");
			int quantite = ES.saisie("Quantité : ", 1);
			return (new LigneDeCommande53(code, quantite));
		}
		return null;
	}

	public void afficher(Commande53<String> cde, TableDesCommandes53 tabCde) {
		if(cde.taille() != 0) {
			String st = "\n\n\t\t***   CONTENU DE LA COMMANDE   ***\n";
			ES.affiche(st + cde.toString() + "\n");
		}else
			ES.affiche("***   CETTE COMMANDE EST VIDE   ***");
		tabCde.purgeVide();
	}
	

	public void supprimer(Commande53<String> cde, Object... objects) throws AbandonException {
		TableDesCommandes53 tabCde =  (TableDesCommandes53) objects[0];
		afficher(cde, tabCde);
		int code = ES.saisie("***   SUPPRIMER UNE LIGNE   ***\nsaisir un code produit à supprimer : ", 1);
		if (cde.retourner(code) != null && !cde.getEtatFacture())
			cde.supprimer(code);
		else if (cde.getEtatFacture())
			ES.affiche("La facture a été créée, vous ne pouvez pas modifier ou supprimer la commande.");
		else
			ES.affiche("Ce Produit n'existe pas.\n");
		
	}
	
	public void modifier(Commande53<String> cde, Object ...objects) throws AbandonException{
		TableDesArticles53 tabArt = (TableDesArticles53) objects[0];
		TableDesCommandes53 tabCde =  (TableDesCommandes53) objects[1];
		afficher(cde, tabCde);
		int code = ES.saisie("***   MODIFIER UNE LIGNE   ***\nsaisir le code produit de la ligne à modifier : ", 1);
		if (cde.retourner(code) != null) {
			LigneDeCommande53 ldc = cde.retourner(code);
			if (ldc != null) {
				if (ldc == null)
					ES.affiche("le code produit de cette ligne n'existe pas. vous devez la créer");
				else {
					int qtite = ES.saisieModification("Quantité \n(laissez vide pour l'ancienne valeur : " + ldc.getQuantite() + " ) ", 1);
					if(qtite != 0)
						ldc.setQuantite(qtite);
				}
			} else
				ES.affiche("Code article n'existe pas\n");
		}else
			ES.affiche("Ce Produit n'existe pas.\n");
	}

	@Override
	public LigneDeCommande53 saisie(Commande53<String> tab, Object... objects) throws Exception {
		// Laisser vide pour remplir contrat interface
		return null;
	}

	@Override
	public void sauvegarder(Commande53<String> tab) {
		// Laisser vide pour remplir contrat interface
		
	}

	@Override
	public Commande53<String> recuperer() {
		// Laisser vide pour remplir contrat interface
		return null;
	}

	@Override
	public void afficherTous(Commande53<String> tabCde) {
		// Laisser vide pour remplir contrat interface
	}

	@Override
	public void afficher(Commande53<String> tab) throws Exception {
		// A garder pour remplir le contrat de l'interface
	}



}

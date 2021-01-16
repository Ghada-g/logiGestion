package Serie53;


import MesInterfaces.InterfaceGestion;
import iPane.ES;
import mesExceptions.AbandonException;
import connexions.ConnexionFichiers;

public class GestionTableDesArticles53 implements InterfaceGestion <TableDesArticles53, ArticleAbstrait53 >{
	private ES ES = new ES();
	ConnexionFichiers<TableDesArticles53> fichArt; //nomLogique
	
	public GestionTableDesArticles53(String nomPhysique) {
		fichArt = new ConnexionFichiers<TableDesArticles53>(nomPhysique);
	}
	
	
	/**
	 * MENU GESTION DES ARTICLES
	 * 
	 * @param tabArt
	 */
	public void menuGeneral(TableDesArticles53 tabArt, Object ...objects) throws AbandonException {
		TableDesCommandes53 tabCde = (TableDesCommandes53) objects[0];
		tabArt.purge();
		int choix;
		do {
			int num = premierNumero(tabArt);
			choix = menuChoix();
			try {
				switch (choix) {
				case 1:
					creer(tabArt, num, tabCde);
					break;
				case 2:
					modifier(tabArt, tabCde);
					break;
				case 3:
					supprimer(tabArt, tabCde);
					break;
				case 4:
					afficherTous(tabArt);
					break;
				case 5:
					affichePromo(tabArt); 
					break;
				case 6:
					afficheLot(tabArt);
					break;
				case 7:
					sauvegarder(tabArt);
					break;
				case 0:
					break;
				}
			} catch (AbandonException abe) {
			}
		} while (choix != 0);
		sauvegarder(tabArt);
	}


	public int menuChoix(Object ...objects) throws AbandonException {
		String menu = "\n ***   GESTION des ARTICLES   ***\n\n" 
						+ " CRÉER un article ................................ 1 \n"
						+ " MODIFIER un article ............................. 2 \n"
						+ " SUPPRIMER un article ............................ 3 \n"
						+ " AFFICHER le stock ............................... 4 \n"
						+ " AFFICHER les articles en Promotion .............. 5 \n"
						+ " AFFICHER les articles bradés par lot ............ 6 \n"
						+ " SAUVEGARDER les articles ........................ 7 \n"
						+ " FIN ............................................. 0 \n"
						+ " VOTRE CHOIX : \n";
		return ES.saisie(menu, 0, 7);
	}

	public void creer(TableDesArticles53 tabArt, Object ...objects) throws AbandonException {
		int num = (int)objects[0];
		TableDesCommandes53 tabCde = (TableDesCommandes53)objects[1];
		try {
			ArticleAbstrait53 art = saisie(tabArt, premierNumero(tabArt));
			tabArt.ajouter(art);
		}catch (AbandonException e) {
			menuGeneral(tabArt, tabCde);
		}
	}

	/**
	 * Saisie pour la création
	 * @param tabArt
	 * @param code
	 * @return
	 * @throws AbandonException
	 */
	public ArticleAbstrait53 saisie(TableDesArticles53 tabArt, Object ...objects) throws AbandonException {
		int code = (int)objects[0];
		String designation;
		do {
			designation = ES.saisie("\n***   SAISIE D'UN ARTICLE   ***\nDésignation : ");
		}while(designation.isEmpty());
		float pu = ES.saisie("Prix Unitaire : ", 0.1f);
		
		int choix;
		do {
			choix = menuTypeProduit();
			switch (choix) {
			case 1:
				return new Article53(code, designation, pu);
			case 2:
				float reduction = ES.saisie(" Réduction à appliquer :",	1, Integer.MAX_VALUE);
				int quantiteMini = ES.saisie(" Pour quelle quantite mininum ?", 1, Integer.MAX_VALUE);
				return new ArticlePromo53(code, designation, pu, quantiteMini, reduction);
			case 3 : 
				float reduct = ES.saisie(" Réduction à appliquer :",	1, Integer.MAX_VALUE);
				int quantiteMin = ES.saisie(" Quelle est la quantité d'un lot ?", 1, Integer.MAX_VALUE);
				boolean periodeOui = ES.saisieOuiNon(" Votre article sera bradé pendant 7 jours. \n Cela vous convient-il ?");
				if (periodeOui)
					return new ArticleLot53(code, designation, pu, quantiteMin, reduct);
				int periode = ES.saisie(" Pour combien de jours voulez-vous brader cet article par lot ? \n", 1, 365);
				ArticleLot53 art = new ArticleLot53(code, designation, pu, quantiteMin, reduct);
				art.setPeriode(periode);
				return art;
			case 0:
				throw new AbandonException();
			}
		} while (choix != 0);
		return null;
	}
	

	private int menuTypeProduit(Object... obj) throws AbandonException {
		String menuProduit = "\n ***   CHOIX DU TYPE D'ARTICLE   ***";
		if (obj.length == 1) {
			String msgSup = (String) obj[0];
			menuProduit += msgSup;
		}
		menuProduit += "\n Choisissez le type d'article que vous voulez : \n\n" +
						" ARTICLE sans réduction ............................................... 1 \n" +
						" ARTICLE en promotion \n (toute l'année et selon une quantité donnée) ............... 2 \n" +
						" ARTICLE à réduction par lot \n (sur une période donnée) ............................................. 3 \n" +
						" FIN .............................................................................. 0 \n" +
						" VOTRE CHOIX : \n";
		return  ES.saisie(menuProduit, 0, 3);
	
	}
	

	public ArticleAbstrait53 saisieModification(TableDesArticles53 tabArt) throws AbandonException {
		int code = ES.saisie("\n***   MODIFIER UN ARTICLE   ***\nSaisissez le code : ", 1);
		ArticleAbstrait53 ancienArt = tabArt.retourner(code);
		if ( ancienArt != null) {
			String designation = ES.saisie("Designation \n(laissez vide pour garder la valeur actuelle : " +  ancienArt.getDesignation() + " ): \n");
			float pu = ES.saisieModification("Prix Unitaire \n(laissez vide pour garder la valeur actuelle : " +  ancienArt.getPu() + " ): \n", 0.1f);
			if(designation.isEmpty())
				designation = ancienArt.getDesignation();
			if(pu == 0)
				pu = ancienArt.getPu();
			
			String type = "";
			if (ancienArt instanceof ArticlePromo53)
					type = "en promotion";
			else if(ancienArt instanceof ArticleLot53)
					type = "à réduction par lot";
			else if(ancienArt instanceof Article53)
					type = "sans réduction";
			String msg = "\n Cet article est actuellement un article  *** " + type + " ***\n";
			int choix = menuTypeProduit(msg);
			switch (choix) {
				case 1:
					return new Article53(code, designation, pu);
				case 2:
					int quantiteMini;
					float reduction;
					if (ancienArt instanceof ArticlePromo53) {
						reduction = ES.saisieModification("Réduction \n(laissez vide pour garder la valeur actuelle: " 
									+ ((ArticlePromo53)ancienArt).getReduction() + " ) :\n", 0.1f);
						quantiteMini = ES.saisieModification("Quantité Minimum \n(laissez vide pour garder la valeur actuelle: " 
								+ ((ArticlePromo53)ancienArt).getQuantiteMini() + " ) :\n", 1);						if(quantiteMini == 0)
							quantiteMini = ((ArticlePromo53) ancienArt).getQuantiteMini();
						if(reduction == 0)
							reduction = ((ArticlePromo53) ancienArt).getReduction();
					}else {
						reduction = ES.saisie("Réduction :\n", 0.1f);
						quantiteMini = ES.saisie("Quantité Minimum :\n", 1);
					}
					return new ArticlePromo53(code, designation, pu, quantiteMini, reduction);
				case 3:
					float reduc;
					int quantiteMin;
					int periode ;
					if (ancienArt instanceof ArticleLot53) {
						reduc = ES.saisieModification("Réduction \n(laissez vide pour garder la valeur actuelle: " 
								+ ((ArticleLot53)ancienArt).getReduction() + " ) :\n", 0.1f);
						quantiteMin = ES.saisieModification(" Quantité d'un lot \n(laissez vide pour garder la valeur actuelle: " 
								+ ((ArticleLot53)ancienArt).getQtiteLot() + " ) :\n", 1);
						periode = ES.saisieModification(" Durée en jour de la vente en lot \n(laissez vide pour garder la valeur actuelle: " + ((ArticleLot53)ancienArt).getPeriode() + " ) :\n", 1, 365);
						if(quantiteMin == 0)
							quantiteMin = ((ArticleLot53) ancienArt).getQtiteLot();
						if(reduc == 0)
							reduc = ((ArticleLot53) ancienArt).getReduction();
						if(periode == 0)
							periode = ((ArticleLot53) ancienArt).getPeriode();
					}else {
						reduc = ES.saisie("Réduction :\n", 0.1f);
						quantiteMin = ES.saisie(" Quantité d'un lot :\n", 1);
						periode = ES.saisie(" Durée en jour de la vente en lot :\n", 1, 365);
						}
					ArticleLot53 art = new ArticleLot53(code, designation, pu, quantiteMin, reduc);
					art.setPeriode(periode);
					return art;
				case 0:
					throw new AbandonException();
			}
		}
		return ancienArt;
	}


	/**
	 * retourne le premier numéro disponible d'un Article
	 * 
	 * @param tabCde
	 */
	private int premierNumero(TableDesArticles53 tabArt) {
		int num = 1;
		do {
			if (tabArt.retourner(num) == null)
				return num;
			num++;
		} while (true);
	}
	

	/**
	 * Supprime un article et purge les commandes de cet article. Si la commande
	 * devient vide, suprression de la commande.
	 * 
	 * @param tabArt
	 * @param tabCde
	 */
	public void supprimer(TableDesArticles53 tabArt, Object... objects) throws AbandonException {

		TableDesCommandes53 tabCde = (TableDesCommandes53) objects[0];
		ES.affiche(tabArt.toString());
		int code = ES.saisie("\nQuel code d'article voulez-vous supprimer ? ", 1);
		ArticleAbstrait53 art = tabArt.retourner(code);
		if (art != null) {
			Boolean sur = ES.saisieOuiNon("Êtes-vous certain de vouloir supprimer? \nCette action est irréversible (O/N): ");
			if (!sur) throw new AbandonException();
			tabArt.supprimer(code);
			tabCde.purge(code);
		} else
			ES.affiche("\nCet article n'existe pas!\n");
	
	}
	

	/**
	 * Modifie (écrase en l'occurence) un article s'il existe
	 * Un article peut devenir un article normal, en promotion ou brader en lot
	 * 
	 * @param tabArt
	 */
	public void modifier(TableDesArticles53 tabArt, Object ...objects) throws AbandonException {
		TableDesCommandes53 tabCde = (TableDesCommandes53)objects[0];
		ES.affiche(tabArt.toString());
		try {
		ArticleAbstrait53 a1 = saisieModification(tabArt); 
		if (a1 != null)
			tabArt.modifier(a1);
		else
			ES.affiche("\nCet article n'existe pas!\n");
		}catch (AbandonException e) {
			menuGeneral(tabArt, tabCde);
		}
		
	} 

	public void afficher(TableDesArticles53 tabArt) throws AbandonException {
		// Laisser vide pour remplir contrat interface
	}
	public void afficherTous(TableDesArticles53 tabArt) {
		ES.affiche(tabArt.toString() + "\n\n");
	}
	
	public void affichePromo(TableDesArticles53 tabArt) {
		ES.affiche(tabArt.toStringPromo() + "\n\n");
		
	}
	
	public void afficheLot(TableDesArticles53 tabArt) {
		ES.affiche(tabArt.toStringLot() + "\n\n");
	}

	/**
	 * sauvegarde dans un fichier
	 * @param tabArt
	 */
	public void sauvegarder(TableDesArticles53 tabArt) {
		fichArt.ecrire(tabArt);
	}
	
	
	/**
	 * lis le fichier et retourne la table
	 * @return
	 */
	public TableDesArticles53 recuperer() {
		TableDesArticles53 tabArt = fichArt.lire();
		if(tabArt == null) 
			tabArt = new TableDesArticles53();
		return tabArt;	
	}


	
}
package Serie53;

import MesInterfaces.InterfaceGestion;
import Utils.DateUser;
import connexions.ConnexionFichiers;
import iPane.ES;
import mesExceptions.AbandonException;

public class GestionTableDesFactures53 implements InterfaceGestion<TableDesFactures53, Facture53<String>>{
	private ES ES = new ES();
	ConnexionFichiers<TableDesFactures53> fichFact; //nom logique
	
	public GestionTableDesFactures53(String nomPhysique) {
		fichFact = new ConnexionFichiers<TableDesFactures53>(nomPhysique);
	}


	/**
	 * menu pour la gestion des factures
	 */
	@Override
	public void menuGeneral(TableDesFactures53 tabFact, Object... tab) throws AbandonException  {
		TableDesCommandes53 tabCde = (TableDesCommandes53) tab[0];
		TableDesArticles53 tabArt = (TableDesArticles53)tab[1];
		tabArt.purge();
		int choix;
		do {
			choix = menuChoix();
			try {
				switch (choix) {
				case 1:
					creer(tabFact, tabCde, tabArt);
					break;
				case 2:
					creerToutes(tabFact, tab);
					break;
				case 3:
					supprimer(tabFact);
					break;
				case 4:
					afficher(tabFact);
					break;
				case 5: 
					afficherTous(tabFact);
					break;
				case 6:
					afficherNonFacturer(tabCde);
					break;
				case 7:
					sauvegarder(tabFact);
					break;
				case 0:
					break;
				default:
					break;
				}
			} catch (AbandonException abe) {
			}
		}while(choix !=0);
		sauvegarder(tabFact);
		
	}


	@Override
	public int menuChoix(Object... objects) throws AbandonException {
		String menu = "\n ***   GESTION des FACTURES   ***\n\n" 
				+ " CREATION d'une facture .........................1 \n"
				+ " CREATION de toutes les factures ............... 2 \n"
				+ " SUPPRIMER une facture ..........................3 \n"
				+ " AFFICHER une facture ...........................4 \n"
				+ " AFFICHER toutes les factures ...................5 \n"
				+ " AFFICHER les commandes Ã  facturer ............ 6 \n"
				+ " SAUVEGARDER les factures .......................7 \n"
				+ " FIN ............................................0 \n"
				+ " VOTRE CHOIX : \n";
		return ES.saisie(menu, 0, 7);
	}

	@Override
	public void creer(TableDesFactures53 tabFact, Object... tab) throws AbandonException  {
		TableDesCommandes53 tabCde = (TableDesCommandes53) tab[0];
		TableDesArticles53 tabArt = (TableDesArticles53)tab[1];
		if (tabCde.cle().isEmpty())
			ES.affiche("*** AUCUNE COMMANDE ACTUELLEMENT ***");
		else {
			String numCde = ES.saisie("Quelle commande voulez vous facturer ? \nNumÃ©ro de Commande : " + tabCde.clesNonFacture().toString());
			Commande53<String> cde = tabCde.retourner(numCde); 
			if(cde == null) 
				ES.affiche("\n La commande n'existe pas ! ");
			else if (tabFact.retourner(numCde) != null)
				ES.affiche("Cette commande a dÃ©jÃ  Ã©tÃ© facturÃ©");
			else {
				DateUser datJour = new DateUser();
				Facture53<String> fact = new Facture53<String>(cde.getNumCde(), datJour, cde.getNumClient(),
						cde.facturer(tabArt));
				tabFact.ajouter(fact);
				cde.setDateFacture(datJour);
				cde.setEtatFacture(true);
				ES.affiche("LA FACTURE NÂ° " + fact.getNumFacture() + " DE LA COMMANDE NÂ° " + cde.getNumCde() + " A Ã‰TÃ‰ CRÃ‰Ã‰ ");
			}
		}
	}
	
	public void creerToutes(TableDesFactures53 tabFact, Object... tab) throws AbandonException  {
		TableDesCommandes53 tabCde = (TableDesCommandes53) tab[0];
		TableDesArticles53 tabArt = (TableDesArticles53) tab[1];
		if (tabCde.cle().isEmpty())
			ES.affiche("*** AUCUNE COMMANDE ACTUELLEMENT ***");
		else {
			for (String numCde : tabCde.clesNonFacture()) {
				Commande53<String> cde = tabCde.retourner(numCde); 
				if(cde == null) 
					ES.affiche("\\n La commande n'existe pas ! ");
				else {
					DateUser datJour = new DateUser();
					Facture53<String> fact = new Facture53<String>(cde.getNumCde(), datJour, cde.getNumClient(),
							cde.facturer(tabArt));
					tabFact.ajouter(fact);
					cde.setDateFacture(datJour);
					cde.setEtatFacture(true);
				}
			}
			ES.affiche("***   TOUTES LES FACTURES ONT Ã‰TÃ‰ CRÃ‰Ã‰ES    ***");
		}
		
	}


	@Override
	public void supprimer(TableDesFactures53 tabFact, Object... objects) throws AbandonException {
		if (tabFact.taille() == 0)
			ES.affiche("*** AUCUNE FACTURE ACTUELLEMENT ***");
		else {
			String num = ES.saisie("Quelle Facture voulez-vous supprimer ? \nNumÃ©ro de Facture :" + tabFact.cle().toString());
			Facture53<String> fact = tabFact.retourner(num);
			if (fact != null) {
				Boolean sur = ES.saisieOuiNon("ÃŠtes-vous certain de vouloir supprimer? \nCette action est irreÌ�versible (O/N): ");
				if (!sur) throw new AbandonException();
				DateUser dateSys = new DateUser();
				DateUser datLimite = new DateUser(fact.getDateFacture());
				datLimite.ajouter(7);
				if (dateSys.compareTo(datLimite) < 0) {
					ES.affiche(
							"Vous ne pouvez pas supprimer une facture avant les 7 jours qui ont suivis sa crÃ©ation\n Attendez jusqu'au "
									+ datLimite);
				} else {
					tabFact.supprimer(num);
					ES.affiche("La facture NÂ° " + num + " a Ã©tÃ© supprimÃ©e");
				}
			} else {
				ES.affiche(" La facture n'existe pas ! ");
			}
		}
	}
	
	@Override
	public void afficher(TableDesFactures53 tabFact) throws AbandonException {
		if (tabFact.taille() == 0)
			ES.affiche("*** AUCUNE FACTURE ACTUELLEMENT ***");
		else {
			String num = ES.saisie("Quelle Facture voulez-vous afficher ? \nNumÃ©ro de Facture :" + tabFact.cle().toString());
			Facture53<String> fact = tabFact.retourner(num);
			if (fact != null) 
				ES.affiche(fact.toString());
			else 
				ES.affiche(" La facture n'existe pas ! ");
		}
		
	}
	
	public void afficherTous(TableDesFactures53 tabFact) {
		if (tabFact.taille() == 0)
			ES.affiche("*** AUCUNE FACTURE ACTUELLEMENT ***");
		else 
			ES.affiche(tabFact.toString());
	}

	public void afficherNonFacturer(TableDesCommandes53 tabCde) {
		if (tabCde.clesNonFacture().isEmpty())
			ES.affiche("*** AUCUNE COMMANDE A FACTURER ACTUELLEMENT ***");
		else 
			ES.affiche("Les numÃ©ros de commandes Ã  facturer sont :\n" + tabCde.clesNonFacture().toString());
		
	}
	@Override
	public Facture53<String> saisie(TableDesFactures53 tabFact, Object... objects) throws AbandonException {
		// Laisser vide pour remplir contrat interface
		return null;
	}

	@Override
	public void modifier(TableDesFactures53 tabFact, Object... objects) throws AbandonException {
		// Laisser vide pour remplir contrat interface
		
	}

	@Override
	public void sauvegarder(TableDesFactures53 tab) {
		fichFact.ecrire(tab);
	}

	@Override
	public TableDesFactures53 recuperer() {
		TableDesFactures53 tabFact = fichFact.lire();
		if (tabFact == null)
			tabFact = new TableDesFactures53();
		return tabFact;
		
	}
}

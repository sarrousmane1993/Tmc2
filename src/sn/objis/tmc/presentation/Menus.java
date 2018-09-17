package sn.objis.tmc.presentation;

import java.util.List;
import java.util.Scanner;

import org.bson.Document;
import sn.objis.tmc.domaine.Abonne;
import sn.objis.tmc.domaine.Portefeuille;
import sn.objis.tmc.service.IServiceAbonneImpl;
import sn.objis.tmc.service.IServicePortefeuilleImpl;

/**
 * cette classe represente les menus et sous menu de l'application
 * 
 * @author sarr
 * @version 1.0-snapshot
 * @since 26/07/2018
 *
 */

public class Menus {
	public IServiceAbonneImpl serviceAbonneImpl = new IServiceAbonneImpl();
	public IServicePortefeuilleImpl servicePortefeuilleImpl = new IServicePortefeuilleImpl();

	Scanner sc = new Scanner(System.in);

	public void menuGeneral() {
		String choix;
		System.out.println("");
		System.out.println("=====================MENU GENERAL==================");
		System.out.println("		1-Creer un compte");
		System.out.println("		2-Charger du credit Tmc");
		System.out.println("		3-Recharge telephonique");
		System.out.println("		4-Transfert");
		System.out.println("		5-Afficher infos Compte ");
		System.out.println("		6-Affiche liste Compte");
		System.out.println("		7-mettre à jour les infos d'un abonné");
		System.out.println("===================================================");
		System.out.println("		Faite votre choix");
		choix = sc.nextLine();
		switch (choix) {
		case "1":
			presentationCreationCompte();
			menuGeneral(); // retour au menu
			break;
		case "2":
			chargerCreditTmc();
			menuGeneral(); // retour au menu
			break;
		case "3":
			presentationRechargeTelephonique();
			menuGeneral(); // retour au menu
			break;
		case "4":
			presentationTransfert();
			menuGeneral();
			break;
		case "5":
			System.out.println("entrer le numero");
			Document docAbonne = serviceAbonneImpl.serviceFindByCard(saisieChiffre());
			presentationInfosCompte(docAbonne);
			menuGeneral();
		case "6":
			System.out.println("liste des comptes");
			List<Document> list = serviceAbonneImpl.serviceRead();
			for (Document document : list) {
			//	presentationInfosCompte(document);
				Document documentportefeuille = servicePortefeuilleImpl.serviceFindByCard(document.getObjectId("_id"));
				System.out.print("Numero "+document.get("Numero"));
				System.out.print(", Nom "+document.get("Nom")+", Prénom "+document.get("Prenom"));
				System.out.print(", Adresse "+document.get("Adresse")+", Crédit "+document.get("Credit"));
				if(documentportefeuille != null) {
				System.out.print(", Solde "+documentportefeuille.getLong("Solde")+"\n");
				}else {
					System.out.println("");
				}
			}
			menuGeneral();
			break;
		case "7":
			presentationMAJ();
			menuGeneral();
			break;
		default:
			System.out.println("veuillez faire le bon choix Svp!");
			menuGeneral();
		}
	}

	/**
	 * cette methode represente la presentation de la creation d'un compte elle
	 * comporte un sous menu et ne retourne pas de resultat
	 * 
	 */
	public void presentationCreationCompte() {
		String choix;
		Abonne abonne;
		String reponse = "";
		System.out.println("============================");
		System.out.println("1-Creer un abonné");
		System.out.println("2-Creer le compte Tmc");
		System.out.println("3-Retour");
		System.out.println("Faite votre choix");
		choix = sc.nextLine();
		switch (choix) {
		case "1":
			do {
				abonne = new Abonne();
				// test saisie entier
				System.out.println("entrer le numero");

				abonne.setNumero(saisieChiffre());

				// sc.nextLine(); // liberer le cache
				System.out.println("entrer le nom");
				abonne.setNom(sc.nextLine());
				System.out.println("entrer le prenom");
				abonne.setPrenom(sc.nextLine());
				System.out.println("entrer l'adresse");
				abonne.setAdresse(sc.nextLine());
				abonne.setCredit(0);
				// * ici on utlise la couche service pour enregistrer l'abonne dans la liste
				// c'est la methode dans la couche service qui teste si le numero est déjà
				// present dans la base
				serviceAbonneImpl.serviceCreate(abonne);
				System.out.println("voulez vous en creer un autre(o/n)");
				reponse = sc.nextLine();

			} while (reponse.equals("o"));
			presentationCreationCompte(); // retour au menu
			break;
		case "2":

			// * maintenant on lui demande de saisir le numero et s'il
			// s'avere que le numero n'existe pas on lui dit que l'abonné doit etre
			// enregistré d'abord
			// * dans creer abonné .Pour cela on utilise la couche service pour les
			// * tests
			System.out.println("entrer le numero");
			Document docAbonne = serviceAbonneImpl.serviceFindByCard(saisieChiffre());// ici on verifie si l'abonné
																						// existe
			// mais c'est la methode dans la couche service qui se charge de verifier
			// si le numero à déjà eu un portefeuille
			if (docAbonne != null) {
				servicePortefeuilleImpl.serviceCreate(new Portefeuille(docAbonne.getObjectId("_id"), 0));
			} else {
				System.out.println("ce numero n'existe pas.vueillew créer l'abonné d'abord");
			}
			presentationCreationCompte(); // retour au menu

			break;
		case "3":
			menuGeneral();
			break;
		default:
			System.out.println("veuillez faire le bon choix");
			presentationCreationCompte();

		}
	}

	/**
	 * cette methode permet d'interagir avec le client pour le depot d'argent dans
	 * son compte. elle ne retourne pas de resultat
	 */
	public void chargerCreditTmc() { // elle est geré par le service update
		System.out.println("entrer le numero");

		Document docAbonne = serviceAbonneImpl.serviceFindByCard(saisieChiffre());

		if (docAbonne != null) {
			// maintenant on sait que l'abonné existe il reste à verifier s'il a un
			// portefeuille
			Document docPortefeuille = servicePortefeuilleImpl.serviceFindByCard(docAbonne.getObjectId("_id"));
			if (docPortefeuille != null) {// il a un portefeuille
				System.out.println("entrer le montant ");
				servicePortefeuilleImpl
						.serviceUpdate(new Portefeuille(docPortefeuille.getObjectId("CleAbonneEtrangere"),
								docPortefeuille.getLong("Solde") + saisieChiffre()));
			} else {
				System.out.println("ce numero n'a pas de portefeuille");
			}

		} else {
			System.out.println("ce compte n'existe pas");
		}
	}

	/**
	 * cette methode permet de consulter un portefeuille mais aussi de recharger du
	 * credit telephonique à partir de son compte elle ne retourne pas de resultat
	 * lui aussi
	 */
	public void presentationRechargeTelephonique() {
		String choix;
		Document docAbonne = null;
		Document docPortefeuille = null;

		Portefeuille portefeuille = null;
		System.out.println("===========================");
		System.out.println("1-Verifier solde de compte Tmc");
		System.out.println("2-Recharge  de credit telephonique par votre compte");
		System.out.println("3-Retour");
		System.out.println("Faite votre choix");
		choix = sc.nextLine();
		switch (choix) {
		case "1":
			System.out.println("entrer le numero de telephone");
			docAbonne = serviceAbonneImpl.serviceFindByCard(saisieChiffre());
			if (docAbonne != null) {// le numero existe
				docPortefeuille = servicePortefeuilleImpl.serviceFindByCard(docAbonne.get("_id"));
				if (docPortefeuille != null) {// le numero a un portefeuille
					System.out.println("le solde de ce compte est : " + docPortefeuille.getLong("Solde"));
				} else {
					System.out.println("ce numero n'a pas de portefeuillle.");
				}

			} else {
				System.out.println("ce numero abonné n'existe pas");
			}
			presentationRechargeTelephonique();
			break;
		case "2":

			System.out.println("entrer le numero ");
			docAbonne = serviceAbonneImpl.serviceFindByCard(saisieChiffre());
			if (docAbonne != null) {// le numero existe
				docPortefeuille = servicePortefeuilleImpl.serviceFindByCard(docAbonne.getObjectId("_id"));
				if (docPortefeuille != null) {// le numero a un portefeuille
					System.out.println("entrer le montant");
					long montant = saisieChiffre();
					if (docPortefeuille.getLong("Solde") >= montant) {
						docPortefeuille.replace("Solde", docPortefeuille.getLong("Solde") - montant);

						serviceAbonneImpl.serviceChargeCredit(docAbonne.getObjectId("_id"), montant);// du côté abonné
						servicePortefeuilleImpl.serviceUpdate(new Portefeuille(
								docPortefeuille.getObjectId("CleAbonneEtrangere"), docPortefeuille.getLong("Solde"))); // du
																														// côté
																														// portefeuille
						System.out.println("recharge telephonique effectué");
					} else {
						System.out.println("le montant doit être inférieur au solde alors que votre solde est  "
								+ "egale à " + docPortefeuille.getLong("Solde"));
					}

				} else {
					System.out.println("ce numero n'a pas de portefeuillle.");
				}

			} else {
				System.out.println("ce numero abonné n'existe pas");
			}

			presentationRechargeTelephonique(); // retour au menu
			break;
		case "3":
			menuGeneral(); // retour au menu principale
			break;
		default:
			System.out.println("veuillez faire le bon choix Svp!");
		}

	}

	/**
	 * cette methode permet d'interagir avec le client pour un transfert d'argent
	 * vers un autre portefeuille elle ne reçoit pas de parametre et ne retourne pas
	 * de resultat
	 */
	public void presentationTransfert() {
		Portefeuille expediteur = null, recepteur = null;
		System.out.println("entrer le numero du compte expediteur");
		long numeroExp = saisieChiffre();
		Document docAbonneExp = serviceAbonneImpl.serviceFindByCard(numeroExp);
		if (docAbonneExp != null) {
			Document docPortefeuilleExp = servicePortefeuilleImpl.serviceFindByCard(docAbonneExp.getObjectId("_id"));
			if (docPortefeuilleExp != null) {
				System.out.println("entrer le numero du compte recepteur");
				long numeroRep = saisieChiffre();
				Document docAbonneRep = serviceAbonneImpl.serviceFindByCard(numeroRep);
				if (docAbonneRep != null) {
					Document docPortefeuilleRep = servicePortefeuilleImpl
							.serviceFindByCard(docAbonneRep.getObjectId("_id"));
					if (docPortefeuilleRep != null) {
						System.out.println("entrer le montant ");
						long montant = saisieChiffre();
						if (montant <= docPortefeuilleExp.getLong("Solde")) {
							docPortefeuilleExp.replace("Solde", docPortefeuilleExp.getLong("Solde") - montant);
							docPortefeuilleRep.replace("Solde", docPortefeuilleRep.getLong("Solde") + montant);
							servicePortefeuilleImpl.serviceUpdate(
									new Portefeuille(docPortefeuilleExp.getObjectId("CleAbonneEtrangere"),
											docPortefeuilleExp.getLong("Solde")));
							servicePortefeuilleImpl.serviceUpdate(
									new Portefeuille(docPortefeuilleRep.getObjectId("CleAbonneEtrangere"),
											docPortefeuilleRep.getLong("Solde")));
							System.out.println("transfert ok");
						} else {
							System.out.println("le montant doit etre inferieur au solde solde== "
									+ docPortefeuilleExp.getLong("Solde"));
						}

					}
				}
			}
		}

	}

	/**
	 * cette methode affiche les infos d'un numero saisi mais aussi de son
	 * portefeuille
	 */
	public void presentationInfosCompte(Document docAbonne) {
		Document docPortefeuille = servicePortefeuilleImpl.serviceFindByCard(docAbonne.getObjectId("_id"));
		System.out.println("================");
		System.out.print("Numéro " + docAbonne.get("Numero") + "\nNom " + docAbonne.get("Nom") + "\nPrénom "
				+ docAbonne.get("Prenom") + "\nAdresse " + docAbonne.get("Adresse") + "\nCredit "
				+ docAbonne.get("Credit"));
		if (docPortefeuille != null) {
			System.out.println("\nSolde " + docPortefeuille.get("Solde"));
		} else {
			// System.out.println("ce numéro "+docAbonne.getLong("Numero")+"n'a pas de
			// portefeuille");
			System.out.println("\nPortefeuille == NULL");
		}
		System.out.println("================");
	}

	/**
	 * cette methode permet de tester si l'utilisateur a saisi des chiffres
	 * 
	 * @return saisie: elle retourne ce qu'il a saisi comme chiffre
	 */
	public long saisieChiffre() {
		boolean testNum = false;
		long saisie = 0;
		Scanner sc = new Scanner(System.in);
		do {
			try {
				testNum = false;
				saisie = Long.parseLong(sc.nextLine());
			} catch (Exception e) {
				testNum = true;
				System.out.println("saisir des chiffres svp!");
			}
		} while (testNum == true);
		return saisie;
	}

	/**
	 * cette methode permet de mettre à jour les infos d'un abonné elle ne reçoit
	 * pas de paramètre mais aussi ne retourne pas de resultat
	 */
	public void presentationMAJ() {
		System.out.println("entrer le numéro objet de votre mise à jour");
		long numero = saisieChiffre();
		Document docAbonne = serviceAbonneImpl.serviceFindByCard(numero);
		if (docAbonne != null) {
			Abonne abonne = new Abonne();
			abonne.setNumero(docAbonne.getLong("Numero"));
			System.out.println("entrer le nouveau nom");
			
			abonne.setNom(sc.nextLine());
			System.out.println("entrer le nouveau prénom");
			abonne.setPrenom(sc.nextLine());
			System.out.println("entrer la nouvel Adresse");
			abonne.setAdresse(sc.nextLine());
			abonne.setCredit(docAbonne.getLong("Credit"));
			serviceAbonneImpl.serviceUpdate(abonne);
			System.out.println("abonné mis à jour");

		} else {
			System.out.println("ce numero n'existe pas ");
		}
	}
}

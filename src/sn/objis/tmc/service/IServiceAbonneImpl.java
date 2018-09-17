package sn.objis.tmc.service;

import java.util.List;
import java.util.Scanner;

import org.bson.Document;
import org.bson.types.ObjectId;

import sn.objis.tmc.dao.IDaoAbonneImpl;
import sn.objis.tmc.domaine.Abonne;

/**
 * cette classe va contenir l'implémentation des services elle implémente aussi
 * l'interface serviceAbonne
 * 
 * @author sarr
 * @version 1.0-snapshot
 * @since 13/05/2018
 */
public class IServiceAbonneImpl implements IServiceAbonne {
	IDaoAbonneImpl iDaoAbonneImpl = new IDaoAbonneImpl();

	/**
	 * cette methode fait appel à celle qui insère dans la base
	 * 
	 * @param param:
	 *            param est l'abonné qui a été reçu en paramètre
	 */
	@Override
	public void serviceCreate(Abonne param) {
		// on doit verifier d'abord si le numero existe déjà
		Document doc = serviceFindByCard(param.getNumero());
		if (doc == null) {
			iDaoAbonneImpl.create(param);
		} else {
			System.out.println("ce numero existe déjà dans la base");
		}
	}
	/**
	 * cette methode fait appel à celle qui modifie qui modifie les propriété d'un abonné
	 * 
	 * @param param:
	 *            param est l'abonné qui a été reçu en paramètre et elle ne retourne
	 *            pas de resultat
	 */
	@Override
	public void serviceUpdate(Abonne param) {
		iDaoAbonneImpl.update(param);

	}
	/**
	 * cette methode permet de faire appel à celle qui supprime un abonné depuis la base de données
	 * 
	 * @param param:
	 *            param est l'abonné qui a été reçu en paramètre
	 */
	@Override
	public void serviceDelete(Abonne param) {
		iDaoAbonneImpl.delete(param);

	}
	/**
	 * cette methode permet de faire appel à celle qui renvoie une liste des abonnés à noter ici que chaque
	 * abonné est un document
	 * 
	 * @return list: list représente la liste de documents pour les abonnés
	 */
	@Override
	public List<Document> serviceRead() {

		return iDaoAbonneImpl.read();
	}
	/**
	 * cette methode permet de faire de l'appel à celle qui renvoie un abonné 
	 * 
	 * @param numéro:numero
	 *            est le numéro de l'abonné qui sera recherché
	 */
	@Override
	public Document serviceFindByCard(Object numero) {
		return iDaoAbonneImpl.findByCard(numero);
	}
	/**
	 * cette méthode permet de faire appel à celle qui interagi  avec la base de données pour recharger du
	 * crédit
	 * 
	 * @param objectId:
	 *            objectId est l'id de l'abonné
	 * @param montant:
	 *            montant est le montant à recharger elle ne retourne pas de
	 *            resultat
	 */
	@Override
	public void serviceChargeCredit(ObjectId cleAbonneEtrangere, long montant) {
		iDaoAbonneImpl.chargeCredit(cleAbonneEtrangere, montant);

	}

}

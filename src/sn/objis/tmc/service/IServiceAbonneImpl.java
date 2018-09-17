package sn.objis.tmc.service;

import java.util.List;
import java.util.Scanner;

import org.bson.Document;
import org.bson.types.ObjectId;

import sn.objis.tmc.dao.IDaoAbonneImpl;
import sn.objis.tmc.domaine.Abonne;

/**
 * cette classe va contenir l'impl�mentation des services elle impl�mente aussi
 * l'interface serviceAbonne
 * 
 * @author sarr
 * @version 1.0-snapshot
 * @since 13/05/2018
 */
public class IServiceAbonneImpl implements IServiceAbonne {
	IDaoAbonneImpl iDaoAbonneImpl = new IDaoAbonneImpl();

	/**
	 * cette methode fait appel � celle qui ins�re dans la base
	 * 
	 * @param param:
	 *            param est l'abonn� qui a �t� re�u en param�tre
	 */
	@Override
	public void serviceCreate(Abonne param) {
		// on doit verifier d'abord si le numero existe d�j�
		Document doc = serviceFindByCard(param.getNumero());
		if (doc == null) {
			iDaoAbonneImpl.create(param);
		} else {
			System.out.println("ce numero existe d�j� dans la base");
		}
	}
	/**
	 * cette methode fait appel � celle qui modifie qui modifie les propri�t� d'un abonn�
	 * 
	 * @param param:
	 *            param est l'abonn� qui a �t� re�u en param�tre et elle ne retourne
	 *            pas de resultat
	 */
	@Override
	public void serviceUpdate(Abonne param) {
		iDaoAbonneImpl.update(param);

	}
	/**
	 * cette methode permet de faire appel � celle qui supprime un abonn� depuis la base de donn�es
	 * 
	 * @param param:
	 *            param est l'abonn� qui a �t� re�u en param�tre
	 */
	@Override
	public void serviceDelete(Abonne param) {
		iDaoAbonneImpl.delete(param);

	}
	/**
	 * cette methode permet de faire appel � celle qui renvoie une liste des abonn�s � noter ici que chaque
	 * abonn� est un document
	 * 
	 * @return list: list repr�sente la liste de documents pour les abonn�s
	 */
	@Override
	public List<Document> serviceRead() {

		return iDaoAbonneImpl.read();
	}
	/**
	 * cette methode permet de faire de l'appel � celle qui renvoie un abonn� 
	 * 
	 * @param num�ro:numero
	 *            est le num�ro de l'abonn� qui sera recherch�
	 */
	@Override
	public Document serviceFindByCard(Object numero) {
		return iDaoAbonneImpl.findByCard(numero);
	}
	/**
	 * cette m�thode permet de faire appel � celle qui interagi  avec la base de donn�es pour recharger du
	 * cr�dit
	 * 
	 * @param objectId:
	 *            objectId est l'id de l'abonn�
	 * @param montant:
	 *            montant est le montant � recharger elle ne retourne pas de
	 *            resultat
	 */
	@Override
	public void serviceChargeCredit(ObjectId cleAbonneEtrangere, long montant) {
		iDaoAbonneImpl.chargeCredit(cleAbonneEtrangere, montant);

	}

}

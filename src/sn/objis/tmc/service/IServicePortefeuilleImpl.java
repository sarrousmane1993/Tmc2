package sn.objis.tmc.service;

import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import sn.objis.tmc.dao.IDaoPortefeuilleImpl;
import sn.objis.tmc.domaine.Portefeuille;
/**
 *  cette classe va contenir l'implémentation des services et elle implémente aussi
 * l'interface servicePortefeuille
 * @author sarr
 * @version 1.0-snapshot
 * @since 13/05/2018
 */
public class IServicePortefeuilleImpl implements IServicePortefeuille {
	IDaoPortefeuilleImpl iDaoPortefeuilleImpl = new IDaoPortefeuilleImpl();
	/**
	 * cette methode permet de faire appel à celle qui insere un abonné dans la base
	 * 
	 * @param param:
	 *            param est le portefeuille qui a été reçu en paramètre
	 */
	@Override
	public void serviceCreate(Portefeuille param) {

		Document doc = serviceFindByCard(param.getCleAbonneEtrangere());
		if (doc == null) {
			iDaoPortefeuilleImpl.create(param);
		} else {
			System.out.println("ce numero a déjà un portefeuille");
		}
	}
	/**
	 * cette methode permet de faire appel à celle qui met à jour le portefeuille.
	 * 
	 * @param param:
	 *            param est le portefeuille qui a été reçu en paramètre
	 */
	@Override
	public void serviceUpdate(Portefeuille param) {
		
		iDaoPortefeuilleImpl.update(param);

	}
	/**
	 * cette methode permet de faire appel à celle qui supprime un abonné depuis la base de données
	 * 
	 * @param param:
	 *            param est l'abonné qui sera supprimé
	 */
	@Override
	public void serviceDelete(Portefeuille param) {
		// TODO Auto-generated method stub
		iDaoPortefeuilleImpl.delete(param);
	}
	/**
	 * cette methode permet de verifier si un abonné existe dans la base
	 * 
	 * @param cleAbonneEtrangere
	 *            :cleAbonneEtrangere est la clé etrangère du portefeuille qui sera
	 *            recherché
	 * @return findIterable.first() : c'est le document du portefeuille qui sera
	 *         renvoyé
	 */
	@Override
	public Document serviceFindByCard(Object cleAbonneEtrangere) {
		// TODO Auto-generated method stub
		return iDaoPortefeuilleImpl.findByCard(cleAbonneEtrangere);
	}

}

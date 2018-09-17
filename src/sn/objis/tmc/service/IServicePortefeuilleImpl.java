package sn.objis.tmc.service;

import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import sn.objis.tmc.dao.IDaoPortefeuilleImpl;
import sn.objis.tmc.domaine.Portefeuille;
/**
 *  cette classe va contenir l'impl�mentation des services et elle impl�mente aussi
 * l'interface servicePortefeuille
 * @author sarr
 * @version 1.0-snapshot
 * @since 13/05/2018
 */
public class IServicePortefeuilleImpl implements IServicePortefeuille {
	IDaoPortefeuilleImpl iDaoPortefeuilleImpl = new IDaoPortefeuilleImpl();
	/**
	 * cette methode permet de faire appel � celle qui insere un abonn� dans la base
	 * 
	 * @param param:
	 *            param est le portefeuille qui a �t� re�u en param�tre
	 */
	@Override
	public void serviceCreate(Portefeuille param) {

		Document doc = serviceFindByCard(param.getCleAbonneEtrangere());
		if (doc == null) {
			iDaoPortefeuilleImpl.create(param);
		} else {
			System.out.println("ce numero a d�j� un portefeuille");
		}
	}
	/**
	 * cette methode permet de faire appel � celle qui met � jour le portefeuille.
	 * 
	 * @param param:
	 *            param est le portefeuille qui a �t� re�u en param�tre
	 */
	@Override
	public void serviceUpdate(Portefeuille param) {
		
		iDaoPortefeuilleImpl.update(param);

	}
	/**
	 * cette methode permet de faire appel � celle qui supprime un abonn� depuis la base de donn�es
	 * 
	 * @param param:
	 *            param est l'abonn� qui sera supprim�
	 */
	@Override
	public void serviceDelete(Portefeuille param) {
		// TODO Auto-generated method stub
		iDaoPortefeuilleImpl.delete(param);
	}
	/**
	 * cette methode permet de verifier si un abonn� existe dans la base
	 * 
	 * @param cleAbonneEtrangere
	 *            :cleAbonneEtrangere est la cl� etrang�re du portefeuille qui sera
	 *            recherch�
	 * @return findIterable.first() : c'est le document du portefeuille qui sera
	 *         renvoy�
	 */
	@Override
	public Document serviceFindByCard(Object cleAbonneEtrangere) {
		// TODO Auto-generated method stub
		return iDaoPortefeuilleImpl.findByCard(cleAbonneEtrangere);
	}

}

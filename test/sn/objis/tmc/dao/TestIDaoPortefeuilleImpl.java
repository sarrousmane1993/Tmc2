package sn.objis.tmc.dao;

import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import sn.objis.tmc.domaine.Portefeuille;
/**
 * classe qui contient les tests des méthodes qui interagissent avec la base de données
 * pour la gestion des portefeuilles
 * @author sarr
 * @since 15/03/2018
 * @version 1.0-snapshot
 */
public class TestIDaoPortefeuilleImpl {
	IDaoPortefeuilleImpl daoPortefeuilleImpl;
	Portefeuille portefeuille;

	@Before
	public void init() {
		daoPortefeuilleImpl = new IDaoPortefeuilleImpl();
		portefeuille = new Portefeuille(new ObjectId(), 1000);
	}

	private void testCreate() {
		daoPortefeuilleImpl.create(portefeuille);
	}

	private void testUpdate() {
		daoPortefeuilleImpl.update(portefeuille);
	}

	private void testFindByCard() {
		Assert.assertNotNull("non trouvé", daoPortefeuilleImpl.findByCard(portefeuille.getCleAbonneEtrangere()));
		System.out.println("recherche ok");
	}

	private void testDelete() {
		daoPortefeuilleImpl.delete(portefeuille);
	}

	/**
	 * cette methode me permet d'ordonner l'exection des méthodes de tests on crée,
	 * met à jour, recherche, et enfin supprime
	 */
	@Test
	public void lanceTest() {
		testCreate();
		testUpdate();
		testFindByCard();
		testDelete();
	}

}

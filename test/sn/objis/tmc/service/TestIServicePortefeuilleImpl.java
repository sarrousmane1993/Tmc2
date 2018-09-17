package sn.objis.tmc.service;

import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;

import sn.objis.tmc.domaine.Portefeuille;
/**
 * classe qui contient les tests des services pour
 * la gestion des portefeuilles
 * @author sarr
 * @since 15/03/2018
 * @version 1.0-snapshot
 */
public class TestIServicePortefeuilleImpl {
	IServicePortefeuilleImpl service;
	Portefeuille portefeuille;

	@Before
	public void init() {
		service = new IServicePortefeuilleImpl();
		portefeuille = new Portefeuille(new ObjectId(), 1000);
	}

	private void testServiceCreate() {
		service.serviceCreate(portefeuille);
	}

	private void testServiceUpdate() {
		service.serviceUpdate(portefeuille);
	}

	private void testServiceDelete() {
		service.serviceDelete(portefeuille);
	}

	private void testServiceFindByCard() {
		service.serviceFindByCard(portefeuille.getCleAbonneEtrangere());
		System.out.println("recherche ok");
	}

	/**
	 * cette methode me permet d'ordonner l'exection des méthodes de tests on crée,
	 * met à jour, recherche, et enfin supprime
	 */
	@Test
	public void lanceTest() {
		testServiceCreate();
		testServiceUpdate();
		testServiceFindByCard();
		testServiceDelete();
	}

}

package sn.objis.tmc.dao;

import org.bson.Document;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sn.objis.tmc.domaine.Abonne;
/**
 * classe qui contient les tests des méthodes qui interagissent avec la base de données 
 * pour la gestion des abonnées
 * @author sarr
 * @since 15/03/2018
 * @version 1.0-snapshot
 */
public class TestIDaoAbonneImpl {
	IDaoAbonneImpl daoAbonneImpl;
	Abonne abonne;

	@Before
	public void init() {
		daoAbonneImpl = new IDaoAbonneImpl();
		abonne = new Abonne(777777777, "sarr", "ousmane", "dakar");
	}

	private void testCreate() {
		daoAbonneImpl.create(abonne);
	}

	private void testUpdate() {
		daoAbonneImpl.update(abonne);
	}

	private void testDelete() {
		daoAbonneImpl.delete(abonne);
	}

	private void testFindByCard() {
		Assert.assertNotNull("non trouvé", daoAbonneImpl.findByCard(abonne.getNumero()));
		System.out.println("recherche ok");
	}

	private void testRead() {
		Assert.assertNotNull("liste null", daoAbonneImpl.read());
	}

	private void testChargeCredit() {
		Document doc = daoAbonneImpl.findByCard(abonne.getNumero());
		daoAbonneImpl.chargeCredit(doc.getObjectId("_id"), 1000);
	}

	/**
	 * cette methode me permet d'ordonner l'exection des méthodes de tests on crée,
	 * met à jour, recherche, lit, charge du credit et enfin supprime
	 */
	@Test
	public void lanceTest() {
		testCreate();
		testUpdate();
		testFindByCard();
		testRead();
		testChargeCredit();
		testDelete();
	}

}

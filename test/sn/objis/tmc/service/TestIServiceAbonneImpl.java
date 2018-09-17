package sn.objis.tmc.service;

import org.bson.Document;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import sn.objis.tmc.domaine.Abonne;
/**
 * classe qui contient les tests des services pour
 * la gestion des abonnés
 * @author sarr
 * @since 15/03/2018
 * @version 1.0-snapshot
 */
public class TestIServiceAbonneImpl {
	IServiceAbonneImpl service;
	Abonne abonne;

	@Before
	public void init() {
		service = new IServiceAbonneImpl();
		abonne = new Abonne(777777777, "sarr", "ousmane", "dakar");
	}

	private void testServiceCreate() {
		service.serviceCreate(abonne);
	}

	private void testServiceUpdate() {
		service.serviceUpdate(abonne);
	}

	private void testServiceDelete() {
		service.serviceDelete(abonne);
	}

	private void testServiceRead() {
		Assert.assertNotNull("liste vide", service.serviceRead());
	}

	private void testServiceFindByCard() {
		Assert.assertNotNull("non trouvé", service.serviceFindByCard(abonne.getNumero()));
		System.out.println("recherche ok");
	}

	private void testServiceChargeCredit() {
		Document doc = service.serviceFindByCard(abonne.getNumero());
		service.serviceChargeCredit(doc.getObjectId("_id"), 1000);
	}

	@Test
	public void lanceTest() {
		testServiceCreate();
		testServiceUpdate();
		testServiceFindByCard();
		testServiceRead();
		testServiceChargeCredit();
		testServiceDelete();
	}

}

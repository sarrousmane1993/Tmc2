package sn.objis.tmc.service;

import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import sn.objis.tmc.domaine.Abonne;

/**
 * cette interface service va etendre linterface IServiceGenerique mais aussi
 * elle va contenir la declaration des services spécifique aux abonnés
 * 
 * @author sarr
 * @version 1.0-snapshot
 * @since 13/05/2018
 */
public interface IServiceAbonne extends IServiceGenerique<Abonne, Document> {
	public List<Document> serviceRead();

	public void serviceChargeCredit(ObjectId objectId, long montant);
}

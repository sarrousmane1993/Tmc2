package sn.objis.tmc.dao;

import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import sn.objis.tmc.domaine.Abonne;

/**
 * cette interface contient la déclarration des methodes spécifiques aux
 * abonnées
 * 
 * @author sarr
 * @version 1.0-snapshot
 * @since 22/07/2018
 *
 */
public interface IDaoAbonne extends IDaoGenerique<Abonne, Document> {
	public List<Document> read();

	public void chargeCredit(ObjectId objectId, long montant);
}

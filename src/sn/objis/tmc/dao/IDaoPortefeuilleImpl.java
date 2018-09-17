package sn.objis.tmc.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Ignore;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;

import sn.objis.tmc.domaine.Portefeuille;
import sn.objis.tmc.utils.MongodbConnexion;

/**
 * cette classe contient l'ensemble des methodes qui interagissent avec la base
 * de données. ces methodes sont en rapport avec la gestion des portefeuilles
 * 
 * @author sarr
 * @version 1.0-snapshot
 * @since 13/05/2018
 *
 */
public class IDaoPortefeuilleImpl implements IDaoPortefeuille {
	public MongoDatabase connexion = MongodbConnexion.getInstanceConnexion();
	public MongoCollection<Document> collection = connexion.getCollection("Portefeuille");

	/**
	 * cette methode permet d'inserer un abonné dans la base
	 * 
	 * @param param:
	 *            param est le portefeuille qui a été reçu en paramètre
	 */
	@Override
	public void create(Portefeuille param) {
		try {
			Document document = new Document("CleAbonneEtrangere", param.getCleAbonneEtrangere()).append("Solde",
					param.getSolde());
			// document.append("doc", new Document("_id", new ObjectId()).append("cle2",
			// 13));
			collection.insertOne(document);
			System.out.println("insertion success");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + " : " + e.getMessage());
		}

	}

	/**
	 * cette methode permet de mettre à jour le portefeuille sa mise à jour consiste
	 * à modifier le solde
	 * 
	 * @param param:
	 *            param est le portefeuille qui a été reçu en paramètre
	 */
	@Override
	public void update(Portefeuille param) {
		Document document = new Document("CleAbonneEtrangere", param.getCleAbonneEtrangere()).append("Solde",
				param.getSolde());
		collection.replaceOne(Filters.eq("CleAbonneEtrangere", param.getCleAbonneEtrangere()), document);
		System.out.println("update success");
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
	public Document findByCard(Object cleAbonneEtrangere) {
		FindIterable<Document> findIterable = collection.find(Filters.eq("CleAbonneEtrangere", cleAbonneEtrangere));
		return findIterable.first();
	}

	/**
	 * cette methode permet de supprimer un abonné depuis la base de données
	 * 
	 * @param param:
	 *            param est l'abonné qui sera supprimé
	 */
	@Override
	public void delete(Portefeuille param) {
		// TODO Auto-generated method stub
		collection.deleteOne(Filters.eq("CleAbonneEtrangere", param.getCleAbonneEtrangere()));
		System.out.println("deletion success");
	}

}

package sn.objis.tmc.dao;

import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import sn.objis.tmc.domaine.Abonne;
import sn.objis.tmc.utils.MongodbConnexion;

/**
 * cette classe contient l'ensemble des methodes qui interagissent avec la base
 * de donn�es mais ces methodes sont en rapport avec la gestion des abonn�s
 * 
 * @author sarr
 * @version 1.0-snapshot
 * @since 13/05/2018
 *
 */
public class IDaoAbonneImpl implements IDaoAbonne {
	private MongoDatabase connexion = MongodbConnexion.getInstanceConnexion();
	private MongoCollection<Document> collection = connexion.getCollection("Abonne");
	
	/**
	 * cette methode permet d'inserer un abonn� dans la base
	 * 
	 * @param param:
	 *            param est l'abonn� qui a �t� re�u en param�tre
	 */
	@Override
	public void create(Abonne param) {
		try {
			Document document = new Document("Numero", param.getNumero()).append("Nom", param.getNom())
					.append("Prenom", param.getPrenom()).append("Adresse", param.getAdresse())
					.append("Credit", param.getCredit());

			collection.insertOne(document);
			System.out.println("insertion success");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + " : " + e.getMessage());
		}
	}

	/**
	 * cette methode permet de modifier les propri�t� d'un abonn�
	 * 
	 * @param param:
	 *            param est l'abonn� qui a �t� re�u en param�tre et elle ne retourne
	 *            pas de resultat
	 */
	@Override
	public void update(Abonne param) {
		// on sait que le numero ne peut �tre chang� donc tout se base sur ce dernier
		// on lui demande de saisir les valeurs dans la classe qui interagi et on cr�e
		// un nouvel abonn�
		// ce qui fait ce param�tre sera l'abonn� qui a �t� cr��
		Document document = new Document("Numero", param.getNumero()).append("Nom", param.getNom())
				.append("Prenom", param.getPrenom()).append("Adresse", param.getAdresse())
				.append("Credit", param.getCredit());
		collection.replaceOne(Filters.eq("Numero", param.getNumero()), document);
		System.out.println("update success");

	}

	/**
	 * cette methode permet de supprimer un abonn� depuis la base de donn�es
	 * 
	 * @param param:
	 *            param est l'abonn� qui a �t� re�u en param�tre
	 */
	@Override
	public void delete(Abonne param) {
		collection.deleteOne(Filters.eq("Numero", param.getNumero()));
		System.out.println("deletion success");
	}

	/**
	 * cette methode permet de renvoyer un abonn�
	 * 
	 * @param num�ro:numero
	 *            est le num�ro de l'abonn� qui sera recherch�
	 */
	@Override
	public Document findByCard(Object numero) {
		FindIterable<Document> findIterable = collection.find(Filters.eq("Numero", numero));
		return findIterable.first();

	}

	/**
	 * cette methode permet de renvoyer une liste des abonn�s � noter ici que chaque
	 * abonn� est un document
	 * 
	 * @return list: list repr�sente la liste de documents pour les abonn�s
	 */
	@Override
	public List<Document> read() {

		List<Document> list = new ArrayList<>();
		FindIterable<Document> findIterable = collection.find();
		MongoCursor<Document> mongoCursor = findIterable.iterator();
		while (mongoCursor.hasNext()) {
			list.add(mongoCursor.next());
		}
		return list;
	}

	/**
	 * cette m�thode permet d'interagir avec la base de donn�es pour recharger du
	 * cr�dit
	 * 
	 * @param objectId:
	 *            objectId est l'id de l'abonn�
	 * @param montant:
	 *            montant est le montant � recharger elle ne retourne pas de
	 *            resultat
	 */
	@Override
	public void chargeCredit(ObjectId objectId, long montant) {
		FindIterable<Document> findIterable = collection.find(Filters.eq("_id", objectId));
		Document doc = findIterable.first();
		doc.replace("Credit", doc.getLong("Credit") + montant);
		collection.replaceOne(Filters.eq("_id", objectId), doc);
		System.out.println("rechargement ok.Compteur credit: " + doc.getLong("Credit"));

	}
}

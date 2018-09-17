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
 * de données mais ces methodes sont en rapport avec la gestion des abonnés
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
	 * cette methode permet d'inserer un abonné dans la base
	 * 
	 * @param param:
	 *            param est l'abonné qui a été reçu en paramètre
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
	 * cette methode permet de modifier les propriété d'un abonné
	 * 
	 * @param param:
	 *            param est l'abonné qui a été reçu en paramètre et elle ne retourne
	 *            pas de resultat
	 */
	@Override
	public void update(Abonne param) {
		// on sait que le numero ne peut être changé donc tout se base sur ce dernier
		// on lui demande de saisir les valeurs dans la classe qui interagi et on crée
		// un nouvel abonné
		// ce qui fait ce paramètre sera l'abonné qui a été créé
		Document document = new Document("Numero", param.getNumero()).append("Nom", param.getNom())
				.append("Prenom", param.getPrenom()).append("Adresse", param.getAdresse())
				.append("Credit", param.getCredit());
		collection.replaceOne(Filters.eq("Numero", param.getNumero()), document);
		System.out.println("update success");

	}

	/**
	 * cette methode permet de supprimer un abonné depuis la base de données
	 * 
	 * @param param:
	 *            param est l'abonné qui a été reçu en paramètre
	 */
	@Override
	public void delete(Abonne param) {
		collection.deleteOne(Filters.eq("Numero", param.getNumero()));
		System.out.println("deletion success");
	}

	/**
	 * cette methode permet de renvoyer un abonné
	 * 
	 * @param numéro:numero
	 *            est le numéro de l'abonné qui sera recherché
	 */
	@Override
	public Document findByCard(Object numero) {
		FindIterable<Document> findIterable = collection.find(Filters.eq("Numero", numero));
		return findIterable.first();

	}

	/**
	 * cette methode permet de renvoyer une liste des abonnés à noter ici que chaque
	 * abonné est un document
	 * 
	 * @return list: list représente la liste de documents pour les abonnés
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
	 * cette méthode permet d'interagir avec la base de données pour recharger du
	 * crédit
	 * 
	 * @param objectId:
	 *            objectId est l'id de l'abonné
	 * @param montant:
	 *            montant est le montant à recharger elle ne retourne pas de
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

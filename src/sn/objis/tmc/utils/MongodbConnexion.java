package sn.objis.tmc.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 * cette classe definit la notion de singleton c'est à dire qu'on ne créera qu'une seul instance de connexion
 * @author sarr
 *@since 15/03/2018
 *@version 1.0-snapshot
 */
public class MongodbConnexion {
	private static MongoClient mongoClient = null;
	private static MongoDatabase mongoDatabase = null; ///cette interface a remplacer la classe DB
	private static String dataBase = "TmcBase";
	private static String Address = "localhost";
	/**
	 * ce constructeur nous permet de restreindre l'instanciation de cette classe
	 */
	private MongodbConnexion() {
		super();
	}
	
	public static MongoDatabase getInstanceConnexion() {
		if(mongoDatabase == null) {
		Logger logg = Logger.getLogger(""); //ceci c'est pour supprimer le journal de connection sur la console
		logg.setLevel(Level.OFF);
		//pour plus d'infos sur la journalisation
		//veuillez voir la documentation sur la classe Logger et la classe level
			mongoClient = new MongoClient( Address , 27017 );
			 mongoDatabase = mongoClient.getDatabase(dataBase);
	
		}
			 return mongoDatabase;
	}
	
}

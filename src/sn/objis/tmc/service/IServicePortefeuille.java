package sn.objis.tmc.service;

import org.bson.Document;
import org.bson.types.ObjectId;

import sn.objis.tmc.domaine.Portefeuille;

/**
 * cette interface va etendre l'interface générique mais aussi elle va contenir
 * la déclaration des methodes spécifiques aux portefeuilles
 * 
 * @author sarr
 * @version 1.0-snapshot
 * @since 13/05/2018
 */
public interface IServicePortefeuille extends IServiceGenerique<Portefeuille, Document> {

}

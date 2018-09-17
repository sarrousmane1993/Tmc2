package sn.objis.tmc.service;

import org.bson.Document;
import org.bson.types.ObjectId;

import sn.objis.tmc.domaine.Portefeuille;

/**
 * cette interface va etendre l'interface g�n�rique mais aussi elle va contenir
 * la d�claration des methodes sp�cifiques aux portefeuilles
 * 
 * @author sarr
 * @version 1.0-snapshot
 * @since 13/05/2018
 */
public interface IServicePortefeuille extends IServiceGenerique<Portefeuille, Document> {

}

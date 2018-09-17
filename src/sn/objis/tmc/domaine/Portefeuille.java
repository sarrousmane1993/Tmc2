package sn.objis.tmc.domaine;

import org.bson.types.ObjectId;

/**
 * classe qui represente le portefeuille(compte) du client
 * 
 * @author sarr
 * @since 15/03/2018
 * @version 1.0-snapshot
 */
public class Portefeuille {
	private ObjectId cleAbonneEtrangere;
	private long solde;

	/**
	 * constructeur sans paramètre
	 */
	public Portefeuille() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * constructeur avec paramètre
	 * 
	 * @param idAbonne:represente
	 *            l'id de l'abonné
	 * @param solde:represente
	 *            le solde du compte
	 */
	public Portefeuille(ObjectId cleAbonneEtrangere, long solde) {
		this.cleAbonneEtrangere = cleAbonneEtrangere;
		this.solde = solde;
		// TODO Auto-generated constructor stub
	}

	public ObjectId getCleAbonneEtrangere() {
		return cleAbonneEtrangere;
	}

	public void setCleAbonneEtrangere(ObjectId cleAbonneEtrangere) {
		this.cleAbonneEtrangere = cleAbonneEtrangere;
	}

	public long getSolde() {
		return solde;
	}

	public void setSolde(long solde) {
		this.solde = solde;
	}

	@Override
	public String toString() {
		return "Portefeuille [cleAbonneEtrangere=" + cleAbonneEtrangere + ", solde=" + solde + "]";
	}

}

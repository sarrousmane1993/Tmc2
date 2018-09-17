package sn.objis.tmc.domaine;

/**
 * classe qui represente un numero telephone
 * 
 * @author sarr
 * @since 15/03/2018
 * @version 1.0-snapshot
 */
public class Abonne {

	private long numero;
	private String nom;
	private String prenom;
	private String adresse;
	private long credit = 0;

	/**
	 * constructeur sans parametre
	 */
	public Abonne() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 *
	 * @param numero:
	 *            represente le numero de telephone
	 * @param nom:
	 *            represente le nom du client
	 * @param prenom:
	 *            represente le prenom du client
	 * @param adresse:
	 *            represente l'adresse du client ici le credit sera initialisé a 0
	 *            lors de la creation
	 */
	public Abonne(long numero, String nom, String prenom, String adresse) {
		super();
		this.numero = numero;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.credit = 0;
	}

	public long getNumero() {
		return numero;
	}

	public void setNumero(long numero) {
		this.numero = numero;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public long getCredit() {
		return credit;
	}

	public void setCredit(long credit) {
		this.credit = credit;
	}

	@Override
	public String toString() {
		return "Abonne [numero=" + numero + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", credit="
				+ credit + "]";
	}
}

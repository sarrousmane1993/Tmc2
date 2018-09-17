package sn.objis.tmc.service;

import java.util.List;

import org.bson.Document;

/**
 * cette classe est une classe générique à partir duquel les autres interfaces
 * heritent
 * 
 * @author sarr
 * @version 1.0-snapshot
 * @since 13/05/2018
 */
public interface IServiceGenerique<P, T> {
	public void serviceCreate(P param);

	public void serviceUpdate(P param);

	public void serviceDelete(P param);

	public T serviceFindByCard(Object object);
}

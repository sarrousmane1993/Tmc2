package sn.objis.tmc.dao;

import java.util.List;

/**
 * cette interface est une interface générique qui sera étendu par d'autres
 * interfaces
 * 
 * @author sarr
 * @version 1.0-snapshot
 * @since 13/05/2018
 *
 */
public interface IDaoGenerique<P, T> {
	public void create(P param);

	public void update(P param);

	public void delete(P param);

	public T findByCard(Object object);
}

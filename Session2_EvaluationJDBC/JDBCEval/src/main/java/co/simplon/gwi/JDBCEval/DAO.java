package co.simplon.gwi.JDBCEval;

import java.security.InvalidParameterException;
import java.sql.Connection;
import java.sql.SQLException;

abstract class DAO<T> {

	protected Connection dbCon = null;

	public abstract T create(T o);

	public abstract T read(int id);

	public abstract T update(T o);

	public abstract void delete(T o);

	public DAO(Connection dbCon) throws InvalidParameterException, SQLException {
		if (dbCon == null) {
			throw new InvalidParameterException("A valid database connection is required !");
		} else {
			try {
				if (dbCon.isClosed() || dbCon.isReadOnly()) {
					throw new SQLException("Connection state is invalid (closed or read only) ! ");
				}
			} catch (SQLException s) {
				// This catch will throw exception :
				// * If isClosed or isReadOnly raises exceptions
				// * If connection is closed or read only
				throw new SQLException(s.getMessage());
			}
		}

		this.dbCon = dbCon;
	}

	

}

package co.simplon.gwi.JDBCEval;

import java.lang.reflect.GenericArrayType;
import java.security.InvalidParameterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class VideoGameDAO extends DAO<VideoGame> {

	/*
	 * 
	 * CREATE TABLE APPStore.TB_videogame( pk_id Serial PRIMARY KEY, name
	 * VARCHAR(50) NOT NULL, credits_price DECIMAL(5,2) NOT NULL, PEGI INTEGER,
	 * short_description VARCHAR(256), -- NOT NULL, full_description TEXT, --
	 * fk_publisher_id INTEGER REFERENCES APPStore.TB_Publisher(pk_id), website_url
	 * VARCHAR(512) );
	 */

	private final static String TABLE_NAME = "TB_videogame";

	private final static String COLUMN_NAME_ID = "pk_id";
	private final static String COLUMN_NAME_VIDEOGAME_NAME = "name";
	private final static String COLUMN_NAME_PRICE = "credits_price";
	private final static String COLUMN_NAME_PEGI_CLASSIFICATION = "PEGI";
	private final static String COLUMN_NAME_SHORT_DESC = "short_description";
	private final static String COLUMN_NAME_FULL_DESC = "full_description";
	private final static String COLUMN_NAME_WEBSITE_URL = "website_url";

	public VideoGameDAO(Connection dbCon) throws InvalidParameterException, SQLException {
		super(dbCon);
		// TODO Auto-generated constructor stub
	}

	@Override
	public VideoGame create(VideoGame o) {
		// TODO Auto-generated method stub

		/*
		 * name VARCHAR(50) NOT NULL, credits_price DECIMAL(5,2) NOT NULL, PEGI INTEGER,
		 * short_description VARCHAR(256), -- NOT NULL, full_description TEXT, --
		 * fk_publisher_id INTEGER REFERENCES APPStore.TB_Publisher(pk_id), website_url
		 * VARCHAR(512)
		 */

		String insertQuery = String.format("INSERT INTO %s ( %s, %s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?, ?);",
				TABLE_NAME,
				COLUMN_NAME_VIDEOGAME_NAME,
				COLUMN_NAME_PRICE,
				COLUMN_NAME_PEGI_CLASSIFICATION,
				COLUMN_NAME_SHORT_DESC,
				COLUMN_NAME_FULL_DESC,
				COLUMN_NAME_WEBSITE_URL);

		try (PreparedStatement pS = dbCon.prepareStatement(insertQuery,
				Statement.RETURN_GENERATED_KEYS)) {
			pS.setString(1, o.getName());
			pS.setDouble(2, o.getPrice());
			pS.setInt(3, o.getPegiClassification());
			pS.setString(4, o.getShortDescription());
			pS.setString(5, o.getFullDescription());
			pS.setString(6, o.getWebSiteURL());

			pS.executeUpdate();

			// Only one result is expected to get customer ID
			ResultSet rS = pS.getGeneratedKeys();
			rS.next();

			o.setId(rS.getInt(COLUMN_NAME_ID));

		} catch (SQLException e) {
			System.err.println("Videogame has not been saved in database : " + e.getMessage());
			e.printStackTrace();
			return null;
		}

		return o;
	}

	@Override
	public VideoGame read(int id) {

		VideoGame result = null;

		try (Statement readQuery = dbCon.createStatement()) {
			String selectQuery = String.format("SELECT * FROM %s WHERE %s = %d;",
					TABLE_NAME,
					COLUMN_NAME_ID,
					id);
			ResultSet rS = readQuery.executeQuery(selectQuery);
			rS.next();

			result = new VideoGame(rS.getInt(COLUMN_NAME_ID),
					rS.getString(COLUMN_NAME_VIDEOGAME_NAME),
					rS.getDouble(COLUMN_NAME_PRICE),
					rS.getInt(COLUMN_NAME_PEGI_CLASSIFICATION),
					rS.getString(COLUMN_NAME_SHORT_DESC),
					rS.getString(COLUMN_NAME_FULL_DESC),
					rS.getString(COLUMN_NAME_WEBSITE_URL));

			rS.close();

		} catch (SQLException e) {
			System.err.println("An SQL error occured during reading of VideoGame [" + id + "] : " + e.getMessage());
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public VideoGame[] readAll() {

		VideoGame[] result = null;

		try (Statement readQuery = dbCon.createStatement()) {
			String selectQuery = String.format("SELECT * FROM %s ;",
					TABLE_NAME);
			ResultSet rS = readQuery.executeQuery(selectQuery);
			ArrayList<VideoGame> resultTmp = new ArrayList<VideoGame>();
			while (rS.next()) {
				resultTmp.add(resultTmp.size(),
						new VideoGame(rS.getInt(COLUMN_NAME_ID),
								rS.getString(COLUMN_NAME_VIDEOGAME_NAME),
								rS.getDouble(COLUMN_NAME_PRICE),
								rS.getInt(COLUMN_NAME_PEGI_CLASSIFICATION),
								rS.getString(COLUMN_NAME_SHORT_DESC),
								rS.getString(COLUMN_NAME_FULL_DESC),
								rS.getString(COLUMN_NAME_WEBSITE_URL)));
			}
			rS.close();

			result = (VideoGame[]) resultTmp.toArray();

		} catch (SQLException e) {
			System.err.println(
					"An SQL error occured during retrieving all data from Videogame TABLE : " + e.getMessage());
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public VideoGame update(VideoGame o) {

		if (o.getId() == null) {
			return null;
		}

		String updateQuery = String.format(
				"UPDATE %s SET %s = ?, %s = ? , %s = ?, %s = ?, %s = ?, %s = ? ) WHERE %s = ?;",
				TABLE_NAME,
				
				COLUMN_NAME_VIDEOGAME_NAME,
				COLUMN_NAME_PRICE,
				COLUMN_NAME_PEGI_CLASSIFICATION,
				COLUMN_NAME_SHORT_DESC,
				COLUMN_NAME_FULL_DESC,
				COLUMN_NAME_WEBSITE_URL,

				COLUMN_NAME_ID);

		try (PreparedStatement pS = dbCon.prepareStatement(updateQuery)) {
			pS.setString(1, o.getName());
			pS.setDouble(2, o.getPrice());
			pS.setInt(3, o.getPegiClassification());
			pS.setString(4, o.getShortDescription());
			pS.setString(5,  o.getFullDescription());
			pS.setString(6, o.getWebSiteURL());
			
			pS.setInt(7, o.getId());

			pS.executeUpdate();

			pS.close();

		} catch (SQLException e) {
			System.err.println("Credit sale has not been saved in database : " + e.getMessage());
			e.printStackTrace();
			return null;
		}

		return o;
	}

	@Override
	public void delete(VideoGame o) {
		if (o.getId() == null) {
			return;
		}

		String deleteQuery = String.format("DELETE FROM %s WHERE %s = %d;",
				TABLE_NAME,
				COLUMN_NAME_ID,
				o.getId());

		try (Statement deleteStatement = dbCon.createStatement()) {
			deleteStatement.execute(deleteQuery);
		} catch (SQLException s) {
			System.out.println("Delete of VideoGame " + o.getId() + " falied : " + s.getMessage());
			s.printStackTrace();
		}
	}
}

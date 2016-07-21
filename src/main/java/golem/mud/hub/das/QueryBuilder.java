package golem.mud.hub.das;

import java.util.Map;
import java.util.HashMap;
//import java.util.logging.Logger;

public class QueryBuilder {
	//private final Logger logger = Logger.getLogger(QueryBuilder.class.getName());

	private String table;
	private Map<String, String> conditions = new HashMap<String, String>();

	private QueryBuilder(final String table) {
		this.table = table;
	}

	private void addCondition(final String field, final String value) {
		this.conditions.put(field, value);	
	}

	private String build() {
		return table; 
	}

	private class SelectQuery {
		private QueryBuilder queryBuilder;

		public SelectQuery(String table) {
			this.queryBuilder = new QueryBuilder(table);
		}

		public SelectQuery addCondition(final String field, final String value) {
			queryBuilder.addCondition(field, value);
			return this;
		}

		public String build() {
			return queryBuilder.build();
		}
	}

	//SELECT * FROM {TABLE} {WHERE};
		//WHERE
		//AND
		//{FIELD} IS {VALUE}

	//INSERT INTO {TABLE} ({FIELDS}) VALUES ({VALUES});

	//UPDATE {TABLE} SET {SET} {WHERE};

	//DELETE FROM {TABLE} {WHERE};
	
}

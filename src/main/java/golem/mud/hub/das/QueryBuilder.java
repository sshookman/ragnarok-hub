package golem.mud.hub.das;

import java.util.Map;
import java.util.HashMap;

import static golem.mud.hub.das.QueryConstants.*;

public class QueryBuilder {
	
	private static String buildWhere(final Map<String, String> conditions) {
		StringBuilder whereBuilder = new StringBuilder();

		boolean isFirst = true;
		for (Map.Entry<String, String> entry : conditions.entrySet()) {
			if (isFirst) {
				isFirst = false;
				whereBuilder.append(WHERE);
			} else {
				whereBuilder.append(AND);
			}
			whereBuilder
				.append(entry.getKey())
				.append(" = ")
				.append(entry.getValue());
		}
	
		return whereBuilder.toString();
	}

	public static class SelectQuery {

		private String table;
		private Map<String, String> conditions = new HashMap<String, String>();

		public SelectQuery(String table) {
			this.table = table;
		}

		public SelectQuery addCondition(final String field, final String value) {
			conditions.put(field, value);
			return this;
		}

		public String build() {
			String query = SELECT_TEMPLATE;
			query = query.replace("{TABLE}", table);
			query = query.replace("{WHERE}", buildWhere(conditions));
			return query;
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

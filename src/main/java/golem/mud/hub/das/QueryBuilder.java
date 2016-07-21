package golem.mud.hub.das;

import java.util.Map;
import java.util.HashMap;
//import java.util.logging.Logger;

public class QueryBuilder {
	//private final Logger logger = Logger.getLogger(QueryBuilder.class.getName());
	
	private static final String WHERE = "WHERE ";
	private static final String AND = " AND ";

	public class SelectQuery {
		private static final String TEMPLATE = "SELECT * FROM {TABLE} {WHERE}";

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
			String query = TEMPLATE;
			query = query.replace("{WHERE}", buildWhere());
			return query;
		}

		private String buildWhere() {
			StringBuilder whereBuilder = new StringBuilder(WHERE);

			boolean isFirst = true;
			for (Map.Entry<String, String> entry : conditions.entrySet()) {
				if (!isFirst) {
					whereBuilder.append(AND);
				}
				whereBuilder.append(entry.getKey());
				whereBuilder.append(" = ");
				whereBuilder.append(entry.getValue());
				isFirst = false;
			}
		
			return whereBuilder.toString();
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

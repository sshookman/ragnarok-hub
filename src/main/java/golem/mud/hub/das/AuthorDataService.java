package golem.mud.hub.das;

import java.sql.Connection;

import golem.mud.hub.das.model.AuthorDO;

public class AuthorDataService extends AbstractDataService<AuthorDO> {

	public AuthorDataService(final Connection story) {
		super(new AuthorDO(), story);
	}
}

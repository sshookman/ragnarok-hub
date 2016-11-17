package com.codepoet.enchiridion.das;

import com.codepoet.enchiridion.das.model.AuthorDO;
import java.sql.Connection;

public class AuthorDataService extends AbstractDataService<AuthorDO> {

	public AuthorDataService(final Connection story) {
		super(new AuthorDO(), story);
	}
}

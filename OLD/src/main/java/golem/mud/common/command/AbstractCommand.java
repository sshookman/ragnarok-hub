package golem.mud.common.command;

import java.util.regex.Pattern;

public abstract class AbstractCommand implements CommandInterface {

    public boolean matches(final String text) {
        Pattern pattern = Pattern.compile(getRegex());
        return pattern.matcher(text).matches();
    }
}

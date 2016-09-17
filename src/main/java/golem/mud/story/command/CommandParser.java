package golem.mud.story.command;

import java.util.List;
import java.util.ArrayList;

import golem.mud.common.exception.CommandException;
import golem.mud.common.enums.CommandType;
import static golem.mud.common.enums.CommandType.*;

public class CommandParser {

    private CommandDictionary dictionary;

    public CommandParser(CommandDictionary dictionary) {
        this.dictionary = dictionary;
    }

    public boolean evaluate(String command) throws CommandException {
        return evaluate(command, 1, new ArrayList<CommandType>());
    }

    private boolean evaluate(String command, Integer position, List<CommandType> types) throws CommandException {
        String[] words = command.split(" ");
        if (position <= words.length) {
            String word = words[position-1];
            List<CommandWord> commandWords = dictionary.search(word, position, types);

            List<CommandType> typeList = new ArrayList<>();
            for (CommandWord commandWord : commandWords) {
                if (commandWord.isLastWord()) {
                    CommandExecutor.execute(command, commandWord.getTypes()[0]);
                    return true;
                }
                for (CommandType type : commandWord.getTypes()) {
                    if (!typeList.contains(type)) {
                        typeList.add(type);
                    }
                }
            }

            return evaluate(command, position+1, typeList);
        } else {
            throw new CommandException("I do not understand.", command);
        }
    }
}

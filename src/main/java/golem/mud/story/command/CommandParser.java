package golem.mud.story.command;

import java.util.List;
import java.util.ArrayList;

import golem.mud.common.enums.CommandType;
import static golem.mud.common.enums.CommandType.*;

public class CommandParser {

    private CommandDictionary dictionary;

    public CommandParser(CommandDictionary dictionary) {
        this.dictionary = dictionary;
    }

    public boolean evaluate(String command) {
        return evaluate(command, 1, new ArrayList<CommandType>());
    }

    private boolean evaluate(String command, Integer position, List<CommandType> types) {
        String[] words = command.split(" ");
        if (position <= words.length) {
            String word = words[position-1];
            List<CommandWord> commandWords = dictionary.search(word, position, types);

            List<CommandType> typeList = new ArrayList<>();
            for (CommandWord commandWord : commandWords) {
                if (commandWord.isLastWord()) {
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
            return false;
        }
    }
}

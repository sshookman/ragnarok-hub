package golem.mud.story.command;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import golem.mud.common.enums.CommandType;

public class CommandWord {

    private String word;
    private Integer[] positions;
    private CommandType[] types;
    private Boolean lastWord;

    public CommandWord(String word, Integer[] positions, CommandType[] types, Boolean lastWord) {
        this.word = word;
        this.positions = positions;
        this.types = types;
        this.lastWord = lastWord;
    }

    public boolean matches(String word, Integer position, List<CommandType> types) {

        if (this.positions != null && this.types != null) {
            List<Integer> positionList = new ArrayList<>(Arrays.asList(this.positions));
            List<CommandType> typeList = new ArrayList<>(Arrays.asList(this.types));

            if (this.word != null && this.word.equalsIgnoreCase(word)
                    && positionList.contains(position)) { 

                if (types == null || types.size() == 0) {
                    return true;
                }
                for (CommandType type : types) {
                    if (typeList.contains(type)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public String getWord() {
        return word;
    }

    public Integer[] getPositions() {
        return positions;
    }

    public CommandType[] getTypes() {
        return types;
    }

    public Boolean isLastWord() {
        return lastWord;
    }
}

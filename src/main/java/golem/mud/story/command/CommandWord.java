package golem.mud.story.command;

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

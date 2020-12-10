package com.miro.task.params;



public class BoardParams {

    private String boardTitle;
    private String boardDescription;


    public BoardParams(String boardTitle, String boardDescription) {

        this.boardTitle = boardTitle;
        this.boardDescription = boardDescription;
    }


    public String getBoardTitle() {
        return boardTitle;
    }

    public String getDescription(){ return boardDescription;}
}

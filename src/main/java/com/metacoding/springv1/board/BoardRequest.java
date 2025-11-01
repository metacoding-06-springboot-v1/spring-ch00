package com.metacoding.springv1.board;

import lombok.Data;

public class BoardRequest {

    @Data
    public static class SaveDTO {

        private String title;
        private String content;

    // 빌더 패턴으로 엔티티 생성
	    public Board toEntity() {
	        return Board.builder()
	            .title(title)
	            .content(content)
	            .build();
	    }
    }   
    @Data
    public static class UpdateDTO {
        private String title;
        private String content;
    }
}
package com.metacoding.springv1.board;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor // 기본 생성자
@Data
@Entity  
@Table(name="board_tb") 
public class Board {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)  // 자동 증가
    private Integer id;
    private  String title;
    private  String content;

    @CreationTimestamp // 자동으로 현재 시간 저장
    private  Timestamp createdAt;

    @Builder // 객체 생성 용도
    public Board(String title, String content) {
        this.title = title;
        this.content = content;
    }
}

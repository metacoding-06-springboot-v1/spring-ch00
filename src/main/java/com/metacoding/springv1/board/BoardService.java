package com.metacoding.springv1.board;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public List<BoardResponse.DTO> 게시글목록() {

        List<Board> boards = boardRepository.findAll(); // board 엔티티 조회
        List<BoardResponse.DTO> dtoList = boards.stream()  
	            .map(board -> new BoardResponse.DTO(board))
	            .collect(Collectors.toList());
        return dtoList;
    }    

    public BoardResponse.DetailDTO 게시글상세(Integer id) {
        Board board = boardRepository.findById(id).get(); // pk 로 조회
        BoardResponse.DetailDTO dto = new BoardResponse.DetailDTO(board); // DTO 담기기
        return dto;
    }

    @Transactional
    public void 게시글추가(BoardRequest.SaveDTO requestDTO){
        Board board = requestDTO.toEntity(); //DTO -> 엔티티
        boardRepository.save(board);
    }
    
    @Transactional
    public void 게시글삭제(Integer id){
        Board board = boardRepository.findById(id).get();
        boardRepository.delete(board);
    }

    public BoardResponse.DTO 게시글수정폼(Integer id){
        Board board = boardRepository.findById(id).get();
        BoardResponse.DTO dto = new BoardResponse.DTO(board);
        return dto;
    }

    @Transactional
    public void 게시글수정(Integer id, BoardRequest.UpdateDTO requestDTO){
        Board board = boardRepository.findById(id).get();
        // 더티 체킹
        board.setTitle(requestDTO.getTitle());
        board.setContent(requestDTO.getContent());
    }
}

package com.example.simpleboard.board.model;

import com.example.simpleboard.post.model.PostDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BoardDto {

    private Long id;

    private String boardName;

    private String status;

    private List<PostDto> postList = new ArrayList<>();

}

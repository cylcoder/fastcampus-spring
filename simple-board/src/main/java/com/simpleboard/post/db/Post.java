package com.simpleboard.post.db;

import com.simpleboard.common.Status;
import com.simpleboard.board.db.Board;
import com.simpleboard.reply.db.Reply;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@SQLDelete(sql = "UPDATE post SET status = 'UNREGISTERED' WHERE id = ?")
@SQLRestriction("status = 'REGISTERED'")
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String username;

  private String password;

  private String email;

  @Enumerated(EnumType.STRING)
  private Status status;

  @Column(columnDefinition = "TEXT")
  private String content;

  private LocalDateTime postedAt;

  private String title;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "board_id")
  private Board board;

  @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
  @Builder.Default
  private List<Reply> replies = new ArrayList<>();

  public void validatePassword(String password) {
    if (!this.password.equals(password)) {
      throw new IllegalArgumentException();
    }
  }

}

package Cybersoft.ExJPA_Security.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "posts")
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    @ManyToOne
    @JoinColumn(name = "id_author")
    private User author;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;
    @PrePersist
    public void prePersist(){
        if(createDate == null){
            createDate = LocalDateTime.now();
        }
    }
}

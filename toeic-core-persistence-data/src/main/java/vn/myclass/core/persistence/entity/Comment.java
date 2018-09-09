package vn.myclass.core.persistence.entity;

import javax.persistence.*;
import java.sql.Timestamp;

public class Comment {
    @Id     // mark as primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // if primary key auto_increament
    private Integer commentId;

    @Column(name = "content")
    private String content;

    @Column(name = "createddate")
    private Timestamp createdDate;

    @ManyToOne  // many comment to one User
    @JoinColumn(name = "userid")
    private User user;

    @ManyToOne  // many comment to one ListenGuideLine
    @JoinColumn(name = "listenguidelineid")
    private ListenGuideLine listenGuideLine;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ListenGuideLine getListenGuideLine() {
        return listenGuideLine;
    }

    public void setListenGuideLine(ListenGuideLine listenGuideLine) {
        this.listenGuideLine = listenGuideLine;
    }
}

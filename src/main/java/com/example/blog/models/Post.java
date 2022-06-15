package com.example.blog.models;

import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "posts")

public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private Long id;

    @Column(name="title", nullable = false, length = 300)
    private String title;

    @Column(name ="body", columnDefinition = "text", nullable = false)
    private String body;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="date", nullable = false, updatable = false)
    private Date currentDate;
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }
    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * @return the body
     */
    public String getBody() {
        return body;
    }
    /**
     * @param body the body to set
     */
    public void setBody(String body) {
        this.body = body;
    }
    /**
     * @return the date
     */
    public Date getDate() {
        return currentDate;
    }
    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.currentDate = currentDate;
    }
    /**
     *
     */
    public Post() {
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Post{" + "id=" + id + ", title='"+title+"\'"+", body='" + body + "\'" + ", creationDate=" + currentDate + "}";
    }

}

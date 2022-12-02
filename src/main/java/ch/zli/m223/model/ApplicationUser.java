package ch.zli.m223.model;

import java.util.Set;

import javax.persistence.*;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class ApplicationUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(readOnly = true)
    private Long id;

    @Column(nullable = false)
    private String userName;

    @OneToMany(mappedBy="applicationUser")
    @JsonIgnoreProperties("applicationUser")
    @Fetch(FetchMode.JOIN)
    private Set<Entry> entries;

    @Column(nullable = false)
    @JsonIgnore
    private String pwd;

    public ApplicationUser(String userName, Set<Entry> entries, String pwd) {
        this.userName = userName;
        this.entries = entries;
        this.pwd = pwd;
    }

    public ApplicationUser() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Set<Entry> getEntries() {
        return entries;
    }

    public void setEntries(Set<Entry> entries) {
        this.entries = entries;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}

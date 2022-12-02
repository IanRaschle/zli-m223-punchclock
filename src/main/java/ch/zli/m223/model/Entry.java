package ch.zli.m223.model;

import javax.persistence.*;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Entry {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(readOnly = true)
  private Long id;

  @Column(nullable = false)
  private LocalDateTime checkIn;

  @Column(nullable = false)
  private LocalDateTime checkOut;

  @ManyToMany
  @JoinTable(
  name = "entry_tag", 
  joinColumns = @JoinColumn(name = "entry_id"), 
  inverseJoinColumns = @JoinColumn(name = "tag_id"))
  @JsonIgnoreProperties("entries")
  @Fetch(FetchMode.JOIN)
  private Set<Tag> tags;

  @ManyToOne
  private Category category;

  @ManyToOne
  @JsonIgnoreProperties("entries")
  private ApplicationUser applicationUser;

  public Entry(LocalDateTime checkIn, LocalDateTime checkOut) {
    this.checkIn = checkIn;
    this.checkOut = checkOut;
  }

  public Entry(Long id,LocalDateTime checkIn, LocalDateTime checkOut) {
    this.id = id;
    this.checkIn = checkIn;
    this.checkOut = checkOut;
  }

  public Entry() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDateTime getCheckIn() {
    return checkIn;
  }

  public void setCheckIn(LocalDateTime checkIn) {
    this.checkIn = checkIn;
  }

  public LocalDateTime getCheckOut() {
    return checkOut;
  }

  public void setCheckOut(LocalDateTime checkOut) {
    this.checkOut = checkOut;
  }

  public Set<Tag> getTags() {
      return tags;
  }

  public void setTags(Set<Tag> tags) {
      this.tags = tags;
  }

  public Category getCategory() {
      return category;
  }

  public void setCategory(Category category) {
      this.category = category;
  }

  public ApplicationUser getApplicationUser() {
    return applicationUser;
  }

  public void setApplicationUser(ApplicationUser applicationUser) {
    this.applicationUser = applicationUser;
  }
}
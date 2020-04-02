/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.javamovies;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dishi
 */
@Entity
@Table(name = "MOVIESTVSHOW")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Moviestvshow.findAll", query = "SELECT m FROM Moviestvshow m"),
    @NamedQuery(name = "Moviestvshow.findByContentId", query = "SELECT m FROM Moviestvshow m WHERE m.contentId = :contentId"),
    @NamedQuery(name = "Moviestvshow.findByCategory", query = "SELECT m FROM Moviestvshow m WHERE m.category = :category"),
    @NamedQuery(name = "Moviestvshow.findByTitle", query = "SELECT m FROM Moviestvshow m WHERE m.title = :title"),
    @NamedQuery(name = "Moviestvshow.findByGenre", query = "SELECT m FROM Moviestvshow m WHERE m.genre = :genre"),
    @NamedQuery(name = "Moviestvshow.findByDescription", query = "SELECT m FROM Moviestvshow m WHERE m.description = :description"),
    @NamedQuery(name = "Moviestvshow.findByProducer", query = "SELECT m FROM Moviestvshow m WHERE m.producer = :producer"),
    @NamedQuery(name = "Moviestvshow.findByDirector", query = "SELECT m FROM Moviestvshow m WHERE m.director = :director"),
    @NamedQuery(name = "Moviestvshow.findByDuration", query = "SELECT m FROM Moviestvshow m WHERE m.duration = :duration"),
    @NamedQuery(name = "Moviestvshow.findByDataPublished", query = "SELECT m FROM Moviestvshow m WHERE m.dataPublished = :dataPublished"),
    @NamedQuery(name = "Moviestvshow.findByThumbnail", query = "SELECT m FROM Moviestvshow m WHERE m.thumbnail = :thumbnail"),
    @NamedQuery(name = "Moviestvshow.findBySeasonNo", query = "SELECT m FROM Moviestvshow m WHERE m.seasonNo = :seasonNo"),
    @NamedQuery(name = "Moviestvshow.findByEpisodeNo", query = "SELECT m FROM Moviestvshow m WHERE m.episodeNo = :episodeNo"),
    @NamedQuery(name = "Moviestvshow.findByEpisodeTitle", query = "SELECT m FROM Moviestvshow m WHERE m.episodeTitle = :episodeTitle")})
public class Moviestvshow implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CONTENT_ID")
    private int contentId;
    @Size(max = 38)
    @Column(name = "CATEGORY")
    private String category;
    @Size(max = 38)
    @Column(name = "TITLE")
    private String title;
    @Size(max = 38)
    @Column(name = "GENRE")
    private String genre;
    @Size(max = 38)
    @Column(name = "DESCRIPTION")
    private String description;
    @Size(max = 38)
    @Column(name = "PRODUCER")
    private String producer;
    @Size(max = 38)
    @Column(name = "DIRECTOR")
    private String director;
    @Column(name = "DURATION")
    private int duration;
    @Column(name = "DATA_PUBLISHED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataPublished;
    @Lob
    @Column(name = "THUMBNAIL")
    private byte[] thumbnail;
    @Column(name = "SEASON_NO")
    private int seasonNo;
    @Column(name = "EPISODE_NO")
    private int episodeNo;
    @Size(max = 38)
    @Column(name = "EPISODE_TITLE")
    private String episodeTitle;

    public Moviestvshow() {
    }

    public Moviestvshow(int contentId) {
        this.contentId = contentId;
    }

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Date getDataPublished() {
        return dataPublished;
    }

    public void setDataPublished(Date dataPublished) {
        this.dataPublished = dataPublished;
    }

    public byte[] getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(byte[] thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getSeasonNo() {
        return seasonNo;
    }

    public void setSeasonNo(int seasonNo) {
        this.seasonNo = seasonNo;
    }

    public int getEpisodeNo() {
        return episodeNo;
    }

    public void setEpisodeNo(int episodeNo) {
        this.episodeNo = episodeNo;
    }

    public String getEpisodeTitle() {
        return episodeTitle;
    }

    public void setEpisodeTitle(String episodeTitle) {
        this.episodeTitle = episodeTitle;
    }

    @Override
    public int hashCode() {
        
        return this.contentId;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Moviestvshow)) {
            return false;
        }
        Moviestvshow other = (Moviestvshow) object;
        
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.javamovies.Moviestvshow[ contentId=" + contentId + " ]";
    }
    
}

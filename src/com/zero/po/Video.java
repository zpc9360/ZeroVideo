package com.zero.po;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "videos")
public class Video implements Serializable {

	private static final long serialVersionUID = 8087250024327313154L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "gene")
	@SequenceGenerator(name = "gene", sequenceName = "videos_id")
	private long id;
	private String videoAbstract;
	private String videoActs;
	private String videoMaker;
	private String videoMenu;
	private long videoPlayId;
	private String videoPhotoUrl;
	private String videoFilesrUrl;
	private String videoName;
	private int videoViewSum;
	private int videoPlaySum;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getVideoAbstract() {
		return videoAbstract;
	}

	public void setVideoAbstract(String videoAbstract) {
		this.videoAbstract = videoAbstract;
	}

	public String getVideoActs() {
		return videoActs;
	}

	public void setVideoActs(String videoActs) {
		this.videoActs = videoActs;
	}

	public String getVideoMaker() {
		return videoMaker;
	}

	public void setVideoMaker(String videoMaker) {
		this.videoMaker = videoMaker;
	}

	public String getVideoMenu() {
		return videoMenu;
	}

	public void setVideoMenu(String videoMenu) {
		this.videoMenu = videoMenu;
	}

	public long getVideoPlayId() {
		return videoPlayId;
	}

	public void setVideoPlayId(long videoUrl) {
		this.videoPlayId = videoUrl;
	}

	public String getVideoPhotoUrl() {
		return videoPhotoUrl;
	}

	public void setVideoPhotoUrl(String videoPhotoUrl) {
		this.videoPhotoUrl = videoPhotoUrl;
	}

	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	public int getVideoViewSum() {
		return videoViewSum;
	}

	public void setVideoViewSum(int videoViewSum) {
		this.videoViewSum = videoViewSum;
	}

	public int getVideoPlaySum() {
		return videoPlaySum;
	}

	public void setVideoPlaySum(int videoPlaySum) {
		this.videoPlaySum = videoPlaySum;
	}

	public String getVideoFilesrUrl() {
		return videoFilesrUrl;
	}

	public void setVideoFilesrUrl(String videoFilesrUrl) {
		this.videoFilesrUrl = videoFilesrUrl;
	}

}

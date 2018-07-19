package com.zero.po;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "videoplays")
public class VideoPlay implements Serializable {

	private static final long serialVersionUID = 1470263505566396967L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "gene")
	@SequenceGenerator(name = "gene", sequenceName = "videoplays_id")
	private long id;
	private String videoUrl;
	private String videoNo;
	private String videoName;
	private long videoId;
	private int isCharge;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}


	public long getVideoId() {
		return videoId;
	}

	public void setVideoId(long videoId) {
		this.videoId = videoId;
	}

	public String getVideoNo() {
		return videoNo;
	}

	public void setVideoNo(String videoNo) {
		this.videoNo = videoNo;
	}

	public int getIsCharge() {
		return isCharge;
	}

	public void setIsCharge(int isCharge) {
		this.isCharge = isCharge;
	}

	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

}

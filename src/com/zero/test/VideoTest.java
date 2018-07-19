package com.zero.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zero.po.Video;
import com.zero.po.VideoPlay;
import com.zero.service.VideoPlayService;
import com.zero.service.VideoService;

public class VideoTest {

	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	VideoService videoService = (VideoService) context.getBean("videoServiceImpl");
	VideoPlayService videoPlayService = (VideoPlayService) context.getBean("videoPlayServiceImpl");
	String[] videoName = { "movie", "teleplay", "anime", "original", "crazy", "music" };
	int videoSize = videoName.length;

	@Test
	public void save() {
		//addVideo();
	}

	private void addVideo() {
		Video video = new Video();
		for (int i = 0; i < videoSize; i++) {
			video.setVideoName(videoName[i]);
			video.setVideoAbstract("Abstract " + videoName[i]);
			video.setVideoActs("Acts " + videoName[i]);
			video.setVideoMaker("Maker " + videoName[i]);
			video.setVideoMenu(videoName[i]);
			video.setVideoPhotoUrl("resources/videos/20180603/" + videoName[i] + ".jpg");
			video.setVideoPlayId(0);
			video.setVideoViewSum(0);
			videoService.save(video);
			addVideoPlay((long) i + 1, video.getVideoName());
		}
	}

	private void addVideoPlay(long videoId, String name) {
		VideoPlay vp = new VideoPlay();
		for (int i = 1; i < 25; i++) {
			vp.setVideoId(videoId);
			vp.setVideoName(name);
			vp.setVideoNo("第 " + i + " 集");
			vp.setVideoUrl("resources/videos/20180603/" + i + ".mp4");
			if (i == 12)
				vp.setIsCharge(1);
			videoPlayService.save(vp);
		}
	}

}

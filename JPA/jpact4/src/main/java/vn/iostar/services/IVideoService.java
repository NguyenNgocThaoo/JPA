package vn.iostar.services;

import java.util.List;

import vn.iostar.entity.Video;
public interface IVideoService {
	void insert(Video video);

    void update(Video video);

    void delete(int videoId) throws Exception;

    Video findById(String videoId);

    List<Video> findAll();

    List<Video> findByVideoTitle(String title);

    List<Video> findAll(int page, int pageSize);

    int count();
}
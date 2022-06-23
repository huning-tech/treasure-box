package tech.huning.treasure.box.async.dto;

/**
 * 视频信息
 *
 * <p>更多内容参看<a href="https://huning.tech"><b>胡宁Tech</b></a>
 * @author huning
 * @version 1.0
 */
public class VideoInfo {

    // 视频标题
    private String title;
    // 视频时长(毫秒)
    private long duration;

    public VideoInfo(String title, long duration) {
        this.duration = duration;
        this.title = title;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}

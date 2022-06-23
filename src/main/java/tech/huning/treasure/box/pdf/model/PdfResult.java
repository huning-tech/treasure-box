package tech.huning.treasure.box.pdf.model;

import java.util.List;

/**
 * Pdf处理结果
 *
 * <p>更多内容参看<a href="https://huning.tech"><b>胡宁Tech</b></a>
 * @author huning
 * @version 1.0
 */
public class PdfResult {

    // 处理结果
    private boolean isOk;
    // 图片文件列表
    private List<String> imageFiles;
    // 总页数
    private long totalPage;

    public boolean isOk() {
        return isOk;
    }

    public void setOk(boolean ok) {
        this.isOk = ok;
    }

    public List<String> getImageFiles() {
        return imageFiles;
    }

    public void setImageFiles(List<String> imageFiles) {
        this.imageFiles = imageFiles;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }

}

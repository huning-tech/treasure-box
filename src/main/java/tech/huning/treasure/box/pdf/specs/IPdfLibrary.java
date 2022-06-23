package tech.huning.treasure.box.pdf.specs;

import tech.huning.treasure.box.pdf.exception.PdfException;
import tech.huning.treasure.box.pdf.model.PdfParam;
import tech.huning.treasure.box.pdf.model.PdfResult;

/**
 * Pdf处理类库
 *
 * <p>更多内容参看<a href="https://huning.tech"><b>胡宁Tech</b></a>
 * @author huning
 * @version 1.0
 */
public interface IPdfLibrary {

    /**
     * 转为图片
     * @param param Pdf处理入参
     * @return Pdf处理结果
     * @throws PdfException Pdf处理异常
     */
    PdfResult convert(PdfParam param) throws PdfException;

    /**
     * 目录清理
     * @throws PdfException Pdf处理异常
     */
    void cleanFileDir(PdfParam param) throws PdfException;

    /**
     * 组装图片文件路径
     * @param param Pdf处理入参
     * @param pageNo 页码
     * @return Pdf处理结果
     * @throws PdfException Pdf处理异常
     */
    String pkgImageFilePath(PdfParam param, int pageNo) throws PdfException;

    /**
     * 获取总页数
     * @param filePath 文件路径
     * @return Pdf处理结果
     * @throws PdfException Pdf处理异常
     */
    long getTotalPage(String filePath) throws PdfException;

}

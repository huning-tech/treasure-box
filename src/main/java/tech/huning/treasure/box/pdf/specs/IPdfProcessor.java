package tech.huning.treasure.box.pdf.specs;

import tech.huning.treasure.box.pdf.model.PdfParam;
import tech.huning.treasure.box.pdf.model.PdfResult;

/**
 * Pdf处理器
 *
 * <p>更多内容参看<a href="https://huning.tech"><b>胡宁Tech</b></a>
 * @author huning
 * @version 1.0
 */
public interface IPdfProcessor {

    /**
     * 加载库
     * @param clazz 处理类库
     * @return Pdf处理器
     */
    IPdfProcessor load(Class<? extends IPdfLibrary> clazz);

    /**
     * 获取总页数
     * @param param Pdf处理入参
     * @return Pdf处理结果
     */
    PdfResult getTotalPage(PdfParam param);

    /**
     * 转为图片
     * @param param Pdf处理入参
     * @return Pdf处理结果
     */
    PdfResult convertToImage(PdfParam param);

}

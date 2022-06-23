package tech.huning.treasure.box.pdf;

import com.alibaba.fastjson.JSON;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.huning.treasure.box.pdf.common.PdfConstant;
import tech.huning.treasure.box.pdf.core.ICEPdfLibrary;
import tech.huning.treasure.box.pdf.core.PdfBoxLibrary;
import tech.huning.treasure.box.pdf.model.PdfParam;
import tech.huning.treasure.box.pdf.model.PdfResult;

import java.io.File;
import java.net.URL;
import java.util.Objects;

/**
 * PdfBox测试
 *
 * <p>更多内容参看<a href="https://huning.tech"><b>胡宁Tech</b></a>
 * @author huning
 * @version 1.0
 */
public class PdfBoxTest {

    private static final Logger logger = LoggerFactory.getLogger(PdfBoxTest.class);
    private static final String FILE_NAME = "proactor.pdf";

    @Test
    public void testGetTotalPage() {
        URL url = ICEPdfTest.class.getClassLoader().getResource(FILE_NAME);
        if(Objects.isNull(url)) {
            return;
        }
        PdfParam pdfParam = new PdfParam.Builder()
                .setFilePath(url.getPath())
                .build();

        PdfResult pdfResult = PdfProcessor.getInstance().load(PdfBoxLibrary.class).getTotalPage(pdfParam);
        logger.info("PdfResult:{}", JSON.toJSONString(pdfResult));
        Assert.assertEquals(pdfResult.getTotalPage(), 28);
    }

    @Test
    public void testConvertPdfToJpg() {
        URL url = ICEPdfTest.class.getClassLoader().getResource(FILE_NAME);
        if(Objects.isNull(url)) {
            return;
        }
        StringBuilder imagePath = new StringBuilder();
        imagePath.append(System.getProperty("user.dir")).append(File.separator);
        imagePath.append("target").append(File.separator);
        imagePath.append("proactor");

        PdfParam pdfParam = new PdfParam.Builder()
                .setFilePath(url.getPath())
                .setImagePath(imagePath.toString())
                .setImageName(PdfConstant.EMPTY_STRING)
                .setImageFormat("png")
                .setImageNameDelimiter(PdfConstant.EMPTY_STRING)
                .setImageStartPageNo(10000)
                .build();

        PdfResult pdfResult = PdfProcessor.getInstance().load(PdfBoxLibrary.class).convertToImage(pdfParam);
        logger.info("PdfResult:{}", JSON.toJSONString(pdfResult));
        Assert.assertEquals(pdfResult.getImageFiles().size(), 28);
    }

}

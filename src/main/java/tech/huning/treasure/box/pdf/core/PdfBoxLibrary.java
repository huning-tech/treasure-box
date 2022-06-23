package tech.huning.treasure.box.pdf.core;

import tech.huning.treasure.box.pdf.exception.PdfException;
import tech.huning.treasure.box.pdf.model.PdfParam;
import tech.huning.treasure.box.pdf.model.PdfResult;
import tech.huning.treasure.box.pdf.specs.AbstractPdfLibrary;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Pdf处理类库之PdfBox
 *
 * <p>更多内容参看<a href="https://huning.tech"><b>胡宁Tech</b></a>
 * @author huning
 * @version 1.0
 */
public class PdfBoxLibrary extends AbstractPdfLibrary {

    @Override
    public PdfResult convert(PdfParam param) throws PdfException {
        PdfResult pdfResult = new PdfResult();
        logger.debug("convert start: {}", param.getFilePath());

        //清理文件
        cleanFileDir(param);

        PDDocument pdDocument = null;
        File file = new File(param.getFilePath());
        try {
            pdDocument = PDDocument.load(file);
            PDPageTree pageTree = pdDocument.getPages();

            long totalPage = param.getTotalPage();
            if(totalPage <= 0) {
                totalPage = pageTree.getCount();
            }
            logger.debug("totalPage:{}", totalPage);

            PDFRenderer renderer = new PDFRenderer(pdDocument);
            float scale = 2f;//清晰度，越大越清晰
            int pageNo = 0;
            List<String> imageFiles = new ArrayList<String>();
            for (PDPage page : pageTree) {
                BufferedImage image = null;
                try{
                    image = renderer.renderImage(pageNo, scale, ImageType.RGB);
                    String imageFilePath = pkgImageFilePath(param, pageNo);
                    ImageIO.write(image, param.getImageFormat(), new File(imageFilePath));
                    imageFiles.add(imageFilePath);
                    pageNo ++;
                }finally { //防止内存溢出
                    if(null != image) {
                        image.flush();
                        image.getGraphics().dispose();
                    }
                }
            }

            pdfResult.setImageFiles(imageFiles);
            pdfResult.setTotalPage(totalPage);
            pdfResult.setOk(true);
        } catch (IOException e) {
            throw new PdfException(e);
        } finally {
            if(null != pdDocument) {
                try {
                    pdDocument.close();
                } catch (IOException e) {
                    throw new PdfException(e);
                }
            }
        }

        return pdfResult;
    }

    @Override
    public long getTotalPage(String filePath) throws PdfException {
        long totalPage = -1;
        PDDocument pdDocument = null;
        File file = new File(filePath);
        try {
            pdDocument = PDDocument.load(file);
            totalPage = pdDocument.getPages().getCount();
        } catch (IOException e) {
            throw new PdfException(e);
        } finally {
            if(null != pdDocument) {
                try {
                    pdDocument.close();
                } catch (IOException e) {
                    throw new PdfException(e);
                }
            }
        }
        return totalPage;
    }

}

package tech.huning.treasure.box.pdf.core;

import tech.huning.treasure.box.pdf.exception.PdfException;
import tech.huning.treasure.box.pdf.model.PdfParam;
import tech.huning.treasure.box.pdf.model.PdfResult;
import tech.huning.treasure.box.pdf.specs.IPdfLibrary;

/**
 * Pdf处理类库之Spire.PDF
 *
 * <p>Spire.PDF<a href="https://www.e-iceblue.com/Download/pdf-for-java-free.html"></a>
 *
 * <p>更多内容参看<a href="https://huning.tech"><b>胡宁Tech</b></a>
 * @author huning
 * @version 1.0
 */
public class SpirePdfLibrary implements IPdfLibrary {

    @Override
    public PdfResult convert(PdfParam param) throws PdfException {
        throw new PdfException("not supported!");
    }

    @Override
    public void cleanFileDir(PdfParam param) throws PdfException {
        throw new PdfException("not supported!");
    }

    @Override
    public String pkgImageFilePath(PdfParam param, int pageNo) throws PdfException {
        throw new PdfException("not supported!");
    }

    @Override
    public long getTotalPage(String filePath) throws PdfException {
        throw new PdfException("not supported!");
    }

}

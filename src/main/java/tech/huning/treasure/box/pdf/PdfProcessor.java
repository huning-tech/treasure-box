package tech.huning.treasure.box.pdf;

import tech.huning.treasure.box.pdf.core.MagickPdfLibrary;
import tech.huning.treasure.box.pdf.exception.PdfException;
import tech.huning.treasure.box.pdf.model.PdfParam;
import tech.huning.treasure.box.pdf.model.PdfResult;
import tech.huning.treasure.box.pdf.specs.IPdfLibrary;
import tech.huning.treasure.box.pdf.specs.IPdfProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PdfProcessor implements IPdfProcessor {

    private static final Logger logger = LoggerFactory.getLogger(PdfProcessor.class);

    private Class<? extends IPdfLibrary> clazz = MagickPdfLibrary.class;

    private PdfProcessor(){}

    public static PdfProcessor getInstance() {
        return PdfProcessorHolder.INSTANCE;
    }

    private static class PdfProcessorHolder {
        private static final PdfProcessor INSTANCE = new PdfProcessor();
    }

    @Override
    public IPdfProcessor load(Class<? extends IPdfLibrary> clazz) {
        this.clazz = clazz;
        return this;
    }

    @Override
    public PdfResult getTotalPage(PdfParam param) {
        PdfResult pdfResult = new PdfResult();
        try {
            IPdfLibrary pdfLibrary = this.clazz.newInstance();
            pdfResult.setTotalPage(pdfLibrary.getTotalPage(param.getFilePath()));
            pdfResult.setOk(true);
        } catch (InstantiationException | IllegalAccessException | PdfException e) {
            pdfResult.setOk(false);
            logger.error(e.getMessage(), e);
        }
        return pdfResult;
    }

    @Override
    public PdfResult convertToImage(PdfParam param) {
        PdfResult pdfResult = new PdfResult();
        try {
            IPdfLibrary pdfLibrary = this.clazz.newInstance();
            pdfResult = pdfLibrary.convert(param);
            pdfResult.setOk(true);
        } catch (InstantiationException | IllegalAccessException | PdfException e) {
            pdfResult.setOk(false);
            logger.error(e.getMessage(), e);
        }
        return pdfResult;
    }

}

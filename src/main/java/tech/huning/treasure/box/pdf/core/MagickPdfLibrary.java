package tech.huning.treasure.box.pdf.core;

import tech.huning.treasure.box.pdf.common.PdfConstant;
import tech.huning.treasure.box.pdf.exception.PdfException;
import tech.huning.treasure.box.pdf.model.PdfParam;
import tech.huning.treasure.box.pdf.model.PdfResult;
import tech.huning.treasure.box.pdf.specs.AbstractPdfLibrary;
import tech.huning.treasure.box.shell.ShellConsole;
import tech.huning.treasure.box.shell.exception.ShellException;
import tech.huning.treasure.box.shell.model.ShellCommand;
import tech.huning.treasure.box.shell.model.ShellResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Pdf处理类库之Magick,使用Linux指令处理Pdf
 *
 * 软件安装
 * 1. ImageMagick
 * 2. poppler-utils
 *
 * 安装指导
 * https://huning.tech/blog/534197982652928000.shtml
 *
 * <p>更多内容参看<a href="https://huning.tech"><b>胡宁Tech</b></a>
 * @author huning
 * @version 1.0
 */
public final class MagickPdfLibrary extends AbstractPdfLibrary {

    private static final Logger logger = LoggerFactory.getLogger(MagickPdfLibrary.class);

    private static final String CMD_CONVERT_ONE = "convert -density 150 -quality 50 %s[%s] %s";

    @Override
    public PdfResult convert(PdfParam param) throws PdfException {
        PdfResult pdfResult = new PdfResult();

        logger.debug("convert start: {}", param.getFilePath());

        // 清理文件
        cleanFileDir(param);

        List<String> imageFiles = new ArrayList<>();

        long totalPage = param.getTotalPage();
        if(totalPage <= 0) {
            totalPage = getTotalPage(param.getFilePath());
        }
        logger.debug("totalPage:{}", totalPage);

        for (long pageNo = 0; pageNo < totalPage; pageNo++) {
            StringBuilder imageFileName = new StringBuilder();
            imageFileName.append(param.getImagePath());
            imageFileName.append(File.separator);
            imageFileName.append(param.getImageName());
            imageFileName.append(param.getImageNameDelimiter());
            imageFileName.append(param.getImageStartPageNo() + pageNo);
            imageFileName.append(PdfConstant.FULL_STOP).append(param.getImageFormat());

            String cmd = String.format(CMD_CONVERT_ONE, param.getFilePath(),
                    pageNo,
                    imageFileName.toString());

            logger.debug("convert cmd: {}", cmd);

            try {
                ShellResult shellResult = ShellConsole.getInstance().exec(new ShellCommand(cmd));
                if(!shellResult.isOk()){
                    throw new PdfException(shellResult.getError());
                }
            } catch (ShellException e) {
                throw new PdfException(e);
            }


            imageFiles.add(imageFileName.toString());
        }

        pdfResult.setImageFiles(imageFiles);
        pdfResult.setTotalPage(totalPage);
        return pdfResult;
    }

    // yum install poppler-utils
    // pdfinfo -box test.pdf | grep Pages | awk -F : '{print $2}' | awk '{ gsub (" ","",$0); print}'
    // identify -ping -format "%n\n" test.pdf | tail -n -1
    @Override
    public long getTotalPage(String filePath) throws PdfException {
        long totalPage = -1;
        StringBuilder cmd = new StringBuilder();
        cmd.append("pdfinfo -box ");
        cmd.append(filePath);
        cmd.append(" | grep Pages | awk -F : '{print $2}' | awk '{ gsub (\" \",\"\",$0); print}'");
        try {
            ShellResult result =  ShellConsole.getInstance().exec(new ShellCommand(cmd.toString()));

            if(!result.isOk()) {
                throw new PdfException("execute cmd failed: " + result.getError());
            }

            if(result.getData() == null || result.getData().isEmpty()) {
                throw new PdfException("no result response");
            }

            totalPage = Long.parseLong(result.getData());
        } catch (ShellException | NumberFormatException e) {
            throw new PdfException(e);
        }
        return totalPage;
    }

}

package co.id.tmli.illustration.excel;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.CheckForSigned;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.WillClose;

import org.apache.poi.POIXMLException;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public enum ExcelVersion {
    XLS {
        @Override
        @Nonnull
        public HSSFWorkbook createWorkbook() {
            return new HSSFWorkbook();
        }

        @Override
        @Nullable
        public HSSFWorkbook readWorkbook(@Nonnull @WillClose final InputStream aIS) {
            try {
                return new HSSFWorkbook(aIS);
            } catch (final OfficeXmlFileException | IOException ex) {
                return null;
            }
        }

        @Override
        @Nonnull
        public HSSFRichTextString createRichText(final String sValue) {
            return new HSSFRichTextString(sValue);
        }

        @Override
        @Nonnull
        public String getFileExtension() {
            return ".xls";
        }

        @Override
        @Nonnull
        public String getMimeType() {
            return "application/vnd.ms-excel";
        }

        @Override
        public boolean hasRowLimitPerSheet() {
            return true;
        }

        @Override
        public int getRowLimitPerSheet() {
            return 65536;
        }
    },
    XLSX {
        @Override
        @Nonnull
        public XSSFWorkbook createWorkbook() {
            return new XSSFWorkbook();
        }

        @Override
        @Nullable
        public XSSFWorkbook readWorkbook(@Nonnull @WillClose final InputStream aIS) {
            try {
                return new XSSFWorkbook(aIS);
            } catch (final POIXMLException | IOException ex) {
                return null;
            }
        }

        @Override
        @Nonnull
        public XSSFRichTextString createRichText(final String sValue) {
            return new XSSFRichTextString(sValue);
        }

        @Override
        @Nonnull
        public String getFileExtension() {
            return ".xlsx";
        }

        @Override
        @Nonnull
        public String getMimeType() {
            return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
        }

        @Override
        public boolean hasRowLimitPerSheet() {
            return false;
        }

        @Override
        public int getRowLimitPerSheet() {
            return -1;
        }
    };

    @Nonnull
    public abstract Workbook createWorkbook();

    @Nullable
    public abstract Workbook readWorkbook(@Nonnull InputStream aIS);

    @Nonnull
    public abstract RichTextString createRichText(String sValue);

    @Nonnull
    public abstract String getFileExtension();

    @Nonnull
    public abstract String getMimeType();

    public abstract boolean hasRowLimitPerSheet();

    @CheckForSigned
    public abstract int getRowLimitPerSheet();
}

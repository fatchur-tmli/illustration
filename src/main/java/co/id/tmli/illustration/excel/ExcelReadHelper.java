package co.id.tmli.illustration.excel;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import org.apache.poi.common.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import co.id.tmli.illustration.service.WorkbookService;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

@Immutable
public class ExcelReadHelper {
	
	private static final Logger s_aLogger = LoggerFactory.getLogger(ExcelReadHelper.class);

    private ExcelReadHelper() {
    }

    @Nonnull
    private static Number _getAsNumberObject(final double dValue) {
        if (dValue == (int) dValue) {
            return (int) dValue;
        }
        if (dValue == (long) dValue) {
            return (long) dValue;
        }
        return dValue;
    }

    @Nonnull
    public static Cell getCell(Sheet sheet, String cell) {
        CellReference cr = new CellReference(cell);
        Row row = sheet.getRow(cr.getRow());
        if (row == null) {
            return null;
        }
        return row.getCell(cr.getCol());
    }

    @Nullable
    public static Object getCellValueObject(@Nullable final Cell aCell) {
        
    	if (aCell == null) {
            return null;
        }

        final int nCellType = aCell.getCellType();
        switch (nCellType) {
            case Cell.CELL_TYPE_NUMERIC:
                return _getAsNumberObject(aCell.getNumericCellValue());
            case Cell.CELL_TYPE_STRING:
                return aCell.getStringCellValue();
            case Cell.CELL_TYPE_BOOLEAN:
                return aCell.getBooleanCellValue();
            case Cell.CELL_TYPE_FORMULA:
                final int nFormulaResultType = aCell.getCachedFormulaResultType();
                switch (nFormulaResultType) {
                    case Cell.CELL_TYPE_NUMERIC:
                        return _getAsNumberObject(aCell.getNumericCellValue());
                    case Cell.CELL_TYPE_STRING:
                        return aCell.getStringCellValue();
                    case Cell.CELL_TYPE_BOOLEAN:
                        return aCell.getBooleanCellValue();
                    default:
                        throw new IllegalArgumentException("The cell formula type " + nFormulaResultType + " is unsupported!");
                }
            case Cell.CELL_TYPE_BLANK:
                return null;
            default: {
                if (DateUtil.isCellDateFormatted(aCell)) {
                    return aCell.getDateCellValue();
                }
                throw new IllegalArgumentException("The cell type " + nCellType + " is unsupported!");
            }
        }
    }

    @Nullable
    public static String getCellValueString(@Nullable final Cell aCell) {
        final Object aObject = getCellValueObject(aCell);
        return aObject == null ? null : aObject.toString();
    }

    @Nullable
    public static String getCellValueNormalizedString(@Nullable final Cell aCell) {
        final String sValue = getCellValueString(aCell);
        if (sValue == null) {
            return null;
        }

        final char[] aChars = sValue.toCharArray();
        final StringBuilder aSB = new StringBuilder(aChars.length);
        for (final char c : aChars) {
            if (Character.getType(c) != Character.CONTROL) {
                aSB.append(c);
            }
        }

        return aSB.toString().trim().replace("  ", " ");
    }

    @Nullable
    @SuppressFBWarnings("NP_BOOLEAN_RETURN_NULL")
    public static Boolean getCellValueBoolean(@Nullable final Cell aCell) {
        final Object aValue = getCellValueObject(aCell);
        if (aValue != null && !(aValue instanceof Boolean)) {
            s_aLogger.warn("Failed to get cell value as boolean: {}, row={}, col={}", aValue.getClass(), aCell.getRowIndex(), aCell.getColumnIndex());
            return null;
        }
        return (Boolean) aValue;
    }

    @Nullable
    public static Number getCellValueNumber(@Nullable final Cell aCell) {
        final Object aValue = getCellValueObject(aCell);
        if (aValue != null && !(aValue instanceof Number)) {
            s_aLogger.warn("Failed to get cell value as number: {}, row={}, col={}", aValue.getClass(), aCell.getRowIndex(), aCell.getColumnIndex());
            return null;
        }
        return (Number) aValue;
    }

    @Nullable
    public static Integer getCellValueInt(@Nullable final Cell aCell) {
        Number number = getCellValueNumber(aCell);
        return number == null ? null : number.intValue();
    }

    @Nullable
    public static Double getCellValueDouble(@Nullable final Cell aCell) {
        Number number = getCellValueNumber(aCell);
        return number == null ? null : number.doubleValue();
    }

    @Nullable
    public static Date getCellValueJavaDate(@Nullable final Cell aCell) {
        if (aCell != null) {
            try {
                return aCell.getDateCellValue();
            } catch (final RuntimeException ex) {
                // fall through
                s_aLogger.warn("Failed to get cell value as date: {}, row={}, col={}", ex.getMessage(), aCell.getRowIndex(), aCell.getColumnIndex());
            }
        }
        return null;
    }

    @Nullable
    public static LocalDateTime getCellValueLocalDate(@Nullable final Cell aCell) {
        final Date aDate = getCellValueJavaDate(aCell);
        return aDate == null ? null : LocalDateTime.ofInstant(aDate.toInstant(), ZoneId.systemDefault());
    }

    @Nullable
    public static RichTextString getCellValueRichText(@Nullable final Cell aCell) {
        return aCell == null ? null : aCell.getRichStringCellValue();
    }

    @Nullable
    public static String getCellFormula(@Nullable final Cell aCell) {
        if (aCell != null) {
            try {
                return aCell.getCellFormula();
            } catch (final RuntimeException ex) {
                s_aLogger.warn("Failed to get cell formula: " + ex.getMessage());
            }
        }
        return null;
    }

    @Nullable
    public static Hyperlink getHyperlink(@Nullable final Cell aCell) {
        return aCell == null ? null : aCell.getHyperlink();
    }

    public static boolean canBeReadAsNumericCell(@Nullable final Cell aCell) {
        if (aCell == null) {
            return false;
        }
        final int nType = aCell.getCellType();
        return nType == Cell.CELL_TYPE_BLANK || nType == Cell.CELL_TYPE_NUMERIC || nType == Cell.CELL_TYPE_FORMULA;
    }

    public static Workbook getWorkbook() {
        return WorkbookService.getSingleton().getWorkbook();
    }

}

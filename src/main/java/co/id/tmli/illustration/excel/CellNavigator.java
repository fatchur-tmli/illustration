/**
 * 
 */
package co.id.tmli.illustration.excel;

import java.time.LocalDateTime;
import java.util.function.Predicate;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellReference;

@lombok.Getter
public class CellNavigator {
	
    private final Sheet sheet;
    private final int initRowIndex, initColumnIndex;
    private int rowIndex, columnIndex;

    public CellNavigator(Sheet sheet, String cellRef) {
        this.sheet = sheet;
        CellReference cr = new CellReference(cellRef);
        this.rowIndex = this.initRowIndex = cr.getRow();
        this.columnIndex = this.initColumnIndex = cr.getCol();
    }

    public CellNavigator(Sheet sheet, int initRowIndex, int initColumnIndex) {
        this.sheet = sheet;
        this.rowIndex = this.initRowIndex = initRowIndex;
        this.columnIndex = this.initColumnIndex = initColumnIndex;
    }

    public CellNavigator gotoInitRow() {
        this.rowIndex = this.initRowIndex;
        return this;
    }

    public CellNavigator gotoInitColumn() {
        this.columnIndex = this.initColumnIndex;
        return this;
    }

    public CellNavigator nextCol() {
        ++columnIndex;
        return this;
    }

    public CellNavigator prevCol() {
        --columnIndex;
        return this;
    }

    public CellNavigator setCol(int col) {
        this.columnIndex = col;
        return this;
    }

    public CellNavigator setCol(String col) {
        CellReference cr = new CellReference(col + rowIndex);
        this.columnIndex = cr.getCol();
        return this;
    }

    public CellNavigator nextRow() {
        ++rowIndex;
        return this;
    }

    public CellNavigator prevRow() {
        --rowIndex;
        return this;
    }

    public CellNavigator setRow(int row) {
        this.rowIndex = row;
        return this;
    }

    public Cell getCell() {
        Row row = sheet.getRow(rowIndex);
        return row == null ? null : row.getCell(columnIndex);
    }

    /**
     *
     * @return true if (cell != null) && (cell.type != blank)
     */
    public boolean hasCell() {
        return ExcelReadHelper.getCellValueObject(getCell()) != null;
    }

    public boolean check(Predicate<Cell> p) {
        return p.test(getCell());
    }

    public String getCellValueAsString() {
        return ExcelReadHelper.getCellValueString(getCell());
    }

    public Number getCellValueAsNumber() {
        return ExcelReadHelper.getCellValueNumber(getCell());
    }

    public Integer getCellValueAsInteger() {
        return ExcelReadHelper.getCellValueInt(getCell());
    }

    public Double getCellValueAsDouble() {
        return ExcelReadHelper.getCellValueDouble(getCell());
    }

    public LocalDateTime getCellValueAsDate() {
        return ExcelReadHelper.getCellValueLocalDate(getCell());
    }

    public CellReference getCellReference() {
        return new CellReference(rowIndex, columnIndex);
    }

}

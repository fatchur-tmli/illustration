/**
 * 
 */
package co.id.tmli.illustration.excel;

import org.apache.poi.ss.usermodel.Cell;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.IStabilityClassifier;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFormulaEvaluator {
	
	private final FormulaEvaluator m_aEvaluator;

    public ExcelFormulaEvaluator(@Nonnull final Workbook aWB) {
        m_aEvaluator = aWB.getCreationHelper().createFormulaEvaluator();
    }

    public ExcelFormulaEvaluator(@Nonnull final Workbook aWB, @Nullable final IStabilityClassifier aStability) {
        m_aEvaluator = aWB instanceof HSSFWorkbook ? new HSSFFormulaEvaluator((HSSFWorkbook) aWB, aStability)
                : XSSFFormulaEvaluator.create((XSSFWorkbook) aWB, aStability, null);
    }

    public CellValue evaluate(@Nonnull final Cell aCell) {
        return m_aEvaluator.evaluate(aCell);
    }

    public int evaluateFormulaCell(@Nonnull final Cell aCell) {
        return m_aEvaluator.evaluateFormulaCell(aCell);
    }

    @Nonnull
    public Cell evaluateInCell(@Nonnull final Cell aCell) {
        return m_aEvaluator.evaluateInCell(aCell);
    }

//    @Override
//    public String toString() {
//        return MoreObjects.toStringHelper(this).add("evaluator", m_aEvaluator).toString();
//    }
    @Override
    public String toString() {
        return "ExcelFormulaEvaluator{" + "m_aEvaluator=" + m_aEvaluator + '}';
    }

}

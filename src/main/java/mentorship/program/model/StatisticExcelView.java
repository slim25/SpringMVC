package mentorship.program.model;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class StatisticExcelView extends AbstractExcelView {
    @SuppressWarnings("unchecked")
    protected void buildExcelDocument(Map<String, Object> model,
                                      HSSFWorkbook workbook,
                                      HttpServletRequest request,
                                      HttpServletResponse response) {
        String sheetName = (String)model.get("sheetname");
        List<String> headers = (List<String>)model.get("headers");
        List<List<String>> results = (List<List<String>>)model.get("results");

        List<LogginInfo> logInfos =  (List<LogginInfo>)model.get("logInfos");
        HSSFSheet sheet = workbook.createSheet(sheetName);
        HSSFCell userNameolumnTitleCell = getCell(sheet, 0 , 0);
        HSSFCell userLogInSecondsTitleCell = getCell(sheet, 0 , 1);
        setText(userNameolumnTitleCell, "Username");
        setText(userLogInSecondsTitleCell, "Total log in duration ( in seconds )");

        for (int i = 1; i <= logInfos.size(); i++){
            HSSFCell cell = getCell(sheet, i , 0);
            setText(cell, logInfos.get(i - 1).getUserName());

            HSSFCell cell2 = getCell(sheet, i , 1);
            long durationTime = logInfos.get(i - 1).getLogInDurationTime() == null ? 0 : logInfos.get(i - 1).getLogInDurationTime();
            setText(cell2, "" + durationTime);
        }
    }
}

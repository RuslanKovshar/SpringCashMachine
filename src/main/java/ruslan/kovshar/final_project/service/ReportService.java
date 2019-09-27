package ruslan.kovshar.final_project.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ruslan.kovshar.final_project.entity.User;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import static ruslan.kovshar.final_project.view.TextConstants.*;


@Service
public class ReportService {

    public void makeReport(int countOfAllChecks, double totalMoney, Model model) {
        model.addAttribute(COUNT_OF_CHECKS, countOfAllChecks);
        model.addAttribute(TOTAL_SUM, totalMoney);
    }

    public void writeZReport(User user, int countOfAllChecks, double totalMoney) {
        try (FileWriter fileWriter = new FileWriter(REPORT_FILE_PATH + REPORT_TYPE + LocalDate.now().toString() + TXT)) {
            fileWriter.write(REPORT_FROM + LocalTime.now().toString() + ENDL);
            fileWriter.write(REPORT_CASHIER + user.getFirstNameEN() + "  " + user.getSecondNameEN() + ENDL);
            fileWriter.write(REPORT_COUNT_OF_CHECKS + countOfAllChecks + ENDL);
            fileWriter.write(REPORT_TOTAL_MONEY + totalMoney);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

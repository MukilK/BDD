package com.chase.ncj.PageObjects;

import com.mphasis.automation.GlobalInit;
import org.openqa.selenium.By;

import java.util.List;

public class FinancialPage extends GlobalInit {

    private By bdyPage = By.tagName("body");
    private By chkChecking = By.xpath("//label[@for='checking-checkbox']/div");
    private By chkSavings = By.xpath("//label[@for='savings-checkbox']/div");
    private By lstHousingType = By.id("sHousingType");
    private By txtAnnualIncome = By.id("sAnnualIncome");
    private By lstIncomeSource = By.id("sPosition");
    private By txtEmployer = By.id("sEmployerOpt");
    private By btnBack = By.id("back1");
    private By btnNext = By.id("next2");
    private By lblErrorIncompleteFields = By.cssSelector("#step2 > div.jpui-col-lg-12.jpui-col-med-10.jputil-med-push-1.jpui-col-sm-10.jputil-sm-push-1.jpui-col-xs-12.jputil-xs-half-grid-padding > div > div.error-message-panel.jpui-col-lg-8.jputil-lg-push-2.jpui-col-med-11.jputil-med-push-1.jpui-col-sm-12.jputil-sm-push-0.jpui-col-xs-12.jputil-xs-push-0 > span");

    public boolean isOnFinancePage(String txtHeader) {


        if (execEngine.getText(bdyPage).contains(txtHeader)) {
            return true;
        } else {
            return false;
        }

    }

    public void fillAnnualIncome(String income) {

        execEngine.sendText(txtAnnualIncome, income);

    }

    public String getAnnualIncome() {

        return execEngine.getAttribute(txtAnnualIncome, "value");

    }

    public void selectResidenceType(String residenceType) {

        execEngine.selectFromList(lstHousingType, residenceType);

    }

    public void selectSourceOfIncome(String incomeSource) {

        execEngine.selectFromList(lstIncomeSource, incomeSource);

    }

    public String getSourceOfIncome() {


        return execEngine.getAttribute(lstIncomeSource, "value");

    }

    public void fillEmployerInfo(String employerName) {

        execEngine.sendText(txtEmployer, employerName);

    }

    public String getEmployerInfo() {


        return execEngine.getAttribute(txtEmployer, "value");

    }

    public void submitFinanceDetails() {

        execEngine.click(btnNext);

    }

    public String getValueInTypeOfResidence() {

        return execEngine.getSelectedValueFromDropDown(lstHousingType);

    }

    public void clickHousingType() {

        execEngine.click(lstHousingType);

    }

    public List<String> getAllHousingTypeOptions() {
        return execEngine.returnListItems(lstHousingType);
    }

    public String getErrorMsgIncompleteFields() {

        return execEngine.getText(lblErrorIncompleteFields);

    }
}

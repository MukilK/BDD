package com.chase.ncj.PageObjects;

import java.util.List;

import com.mphasis.automation.GlobalInit;
import com.mphasis.automation.webdriver.override.MTAFBy;

public class FinancialPage extends GlobalInit {

	private MTAFBy bdyPage = (MTAFBy) MTAFBy.tagName("body");
	private MTAFBy chkChecking = (MTAFBy) MTAFBy.xpath("//label[@for='checking-checkbox']/div");
	private MTAFBy chkSavings = (MTAFBy) MTAFBy.xpath("//label[@for='savings-checkbox']/div");
	private MTAFBy lstHousingType = (MTAFBy) MTAFBy.id("sHousingType");
	private MTAFBy txtAnnualIncome =(MTAFBy) MTAFBy.id("sAnnualIncome");
	private MTAFBy lstIncomeSource =(MTAFBy) MTAFBy.id("sPosition");
	private MTAFBy txtEmployer =(MTAFBy) MTAFBy.id("sEmployerOpt");
	private MTAFBy btnBack =(MTAFBy) MTAFBy.id("back1");
	private MTAFBy btnNext =(MTAFBy) MTAFBy.id("next2");
	private MTAFBy lblErrorIncompleteFields=(MTAFBy) MTAFBy.cssSelector("#step2 > div.jpui-col-lg-12.jpui-col-med-10.jputil-med-push-1.jpui-col-sm-10.jputil-sm-push-1.jpui-col-xs-12.jputil-xs-half-grid-padding > div > div.error-message-panel.jpui-col-lg-8.jputil-lg-push-2.jpui-col-med-11.jputil-med-push-1.jpui-col-sm-12.jputil-sm-push-0.jpui-col-xs-12.jputil-xs-push-0 > span");

	public boolean isOnFinancePage(String txtHeader) {
		

		if (execEngine.getText(bdyPage).contains(txtHeader)) {
			return true;
		}else{
			return false;
		}
		
	}

	public void fillAnnualIncome(String income) {

		execEngine.sendText(txtAnnualIncome, income);

	}
	
	public String getAnnualIncome() {

		return execEngine.getAttribute(txtAnnualIncome,"value");

	}

	public void selectResidenceType(String residenceType) {

		execEngine.selectFromList(lstHousingType, residenceType);

	}
	
	public void selectSourceOfIncome(String incomeSource) {

		execEngine.selectFromList(lstIncomeSource, incomeSource);

	}
	
	public String getSourceOfIncome() {

		
		return execEngine.getAttribute(lstIncomeSource,"value");

	}
	
	public void fillEmployerInfo(String employerName) {

		execEngine.sendText(txtEmployer, employerName);

	}
	
	public String  getEmployerInfo() {

		
		return execEngine.getAttribute(txtEmployer,"value");

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
	
	public List<String> getAllHousingTypeOptions(){
		return execEngine.returnListItems(lstHousingType);
	}
	
	public String getErrorMsgIncompleteFields() {

		return execEngine.getText(lblErrorIncompleteFields);

	}
}

package com.thoughtservice.portal.lms.investmentdeclaration.pdf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import com.thoughtservice.portal.lms.investmentdeclaration.pdf.user.UserData;



public class DeclarationPdfDocument<DeclarationPdfDocument> {

    // Page configuration
    public static final PDRectangle PAGE_SIZE = PDPage.PAGE_SIZE_A4;    
    private static final float MARGIN = 100;
    private static final boolean IS_LANDSCAPE = false;

    // Font configuration
    private static final PDFont COLUMN_TEXT_FONT = PDType1Font.HELVETICA_BOLD;
    private static final PDFont TEXT_FONT = PDType1Font.HELVETICA;
    private static final float FONT_SIZE = 6;

    // Table configuration
    private static final float ROW_HEIGHT = 10;
    private static final float CELL_MARGIN = 1;
    
	public String createDeclarationDocument(String path, UserData userData)
			throws IOException, COSVisitorException {
		String formattedTitle = "INVESTMENT DECLARATION FORM FOR FINANCIAL YEAR 2014-2015";
		return new PDFTableGenerator().generatePDF(path, formattedTitle,
				userData.getFirstName(), userData.getGender(),
				(userData.getEmployeecode1().toString()), userData.getPan(), new Table[] {
						createContent80(userData), createContent80c(userData),
						createContentHRA(userData) });
	}
    
 
	private static Table createContent80(UserData userData) {
    	// Total size of columns must not be greater than table width.
        List<Column> columns = new ArrayList<Column>();
        columns.add(new Column("I", 10));
        columns.add(new Column("A", 10));
        columns.add(new Column("Deduction to be claimed U/S 80", 330));
        columns.add(new Column("Amount (Rs.)", 100));    
       // columns.add(new Column("", 60));  
     
        String[][] content = { 
        		{"", "1", "Mediclaim Policy Premium for self [u/s 80D-upto Rs.15,000/-]", userData.getMediclaimForSelf().toString()},
        		{"", "2", "Mediclaim Policy Premium [u/s 80D-upto Rs.15,000/-if parents are less than 60 years else Rs.20,000/-]", userData.getMediclaimForParents().toString() },
        		{"", "3", "Medical treatment of handicapped dependent [u/s80DD-upto Rs.50,000/- & Rs.100,000/- for severe disability]", userData.getMedicaltreatmentOfHandicappedDependent().toString() },
        		{"", "4", "Medical treatment of specified diseases for self/dependent [u/s80DDB-upto Rs.40,000/- & Rs.60,000/- for senior citizen]",userData.getMedicaltreatmentDiseasesforself().toString() },
        		{"", "5", "Deduction in case of self being totally blind or physically handicapped [u/s80 U]- upto Rs 50,000/- ",userData.getDeductionincaseofSelfBeingTotallyBlind().toString()},
        		{"", "6", "INTEREST paid on loan taken for higher education [U/s 80 E - No limit]", userData.getInerestPaidonLoanTakenforHigherEducation().toString()},
        		{"", "", "", ""},
        		{"", "B", "Direct Investment in Equity under RGESS", userData.getDirectInvestmentinEquityUnderrgess().toString()},
        		{"", "", "", ""},
        		{"", "C", "Medical Benefit (Rs.15000) Required", userData.getMedicalBenefit().toString()},
        		{"", "", "", ""},
        		{"", "D", "Interest on Housing Loan [u/s 24(2)] -- Maximum up to Rs.1,50,000(2,50,000 for loan upto 25lakh taken in FY 2013-14",userData.getInterestonHousingLoan().toString()},        		        	
                
        };


        float tableHeight = IS_LANDSCAPE ? PAGE_SIZE.getWidth() - (2 * MARGIN) : PAGE_SIZE.getHeight() - (2 * MARGIN);

        Table table = new TableBuilder()
            .setCellMargin(CELL_MARGIN)
            .setColumns(columns)
            .setContent(content)
            .setHeight(tableHeight)
            .setNumberOfRows(content.length)
            .setRowHeight(ROW_HEIGHT)
            .setMargin(MARGIN)
            .setTableIndex(0)
            .setPageSize(PAGE_SIZE)
            .setLandscape(false)
            .setColumnFont(COLUMN_TEXT_FONT)
            .setColumnFontSize(FONT_SIZE)
            .setTextFont(TEXT_FONT)
            .setFontSize(FONT_SIZE)
            .build();
        return table;
    }   
	
	private static Table createContent80c( UserData userData ) {
    	// Total size of columns must not be greater than table width.
        List<Column> columns = new ArrayList<Column>();
        columns.add(new Column("", 10));
        columns.add(new Column("E", 10));
        columns.add(new Column("Rebate U/S 80C", 330));
        columns.add(new Column("Amount (Rs.)", 100));    
       // columns.add(new Column("", 60));  

        String[][] content = {         		        		
        		{"", "1", "Payment of Life Insurance Premium ",userData.getPayment_of_insurance_premium().toString()},
        		{"", "2", "Employee's contribution to PF ", userData.getEmployee_contribution_to_pf().toString()},
        		{"", "3", "Deposit in Public Provident Fund(PPF){Maximum of Rs.70,000/-}",userData.getDeposit_in_public_pf().toString()},
        		{"", "4", "Equity Tax saver Mutual Funds - ELSS ", userData.getEquity_tax_saving_mutualfund().toString()},
        		{"", "5", "Purchase of National Saving Certificates (NSC)", userData.getPurchase_of_national_saving_certificate().toString()},
        		{"", "6", "National Service Scheme (NSS) deposit", userData.getNational_savings_scheme_deposit().toString()},
        		{"", "7", "Post Office/Tax saving Bonds investments", userData.getPostoffice_saving_bonds_investment().toString()},
        		{"", "8", "New pension scheme (NPS) Deposit 80CCD", userData.getNew_pension_scheme_deposit_of_80ccd().toString()},
        		{"", "9", "Housing Loan Principal repayment", userData.getHouse_loan_principle_payment().toString()},
        		{"", "10", "Tax saving Fixed Deposit for 5 yrs. or more",userData.getTax_saving_fixed_deposit().toString()},
        		{"", "11", "Stamp Duty/Registration charges for house",userData.getStamp_duty_registration_charges_for_house().toString()},
        		{"", "12", "Other Eligible Investments  (Please provide details)",userData.getOther_eligible_investment().toString()},
        		{"", "13", " Pension Fund (80 CCC) ", userData.getPension_fund().toString()},
        		      		
        };


        float tableHeight = IS_LANDSCAPE ? PAGE_SIZE.getWidth() - (2 * MARGIN) : PAGE_SIZE.getHeight() - (2 * MARGIN);

        Table table = new TableBuilder()
            .setCellMargin(CELL_MARGIN)
            .setColumns(columns)
            .setContent(content)
            .setHeight(tableHeight)
            .setNumberOfRows(content.length)
            .setRowHeight(ROW_HEIGHT)
            .setMargin(MARGIN)
            .setTableIndex(1)
            .setPageSize(PAGE_SIZE)
            .setLandscape(false)
            .setColumnFont(COLUMN_TEXT_FONT)
            .setColumnFontSize(FONT_SIZE)
            .setTextFont(TEXT_FONT)
            .setFontSize(FONT_SIZE)
            .build();
        return table;
    }  
    
    private static Table createContentHRA(  UserData userData ) {
    	// Total size of columns must not be greater than table width.
        List<Column> columns = new ArrayList<Column>();
        columns.add(new Column("II", 10));
        columns.add(new Column("", 10));
        columns.add(new Column("House Rent Allowance (H.R.A.) ", 100));
        columns.add(new Column("", 330));
       
        String[][] content = { 
       		 { "", "1", "Name & Add of the Landlord", userData.getNameOfLandLord().toString()},
       		{ "", "2", "Address of accommodation", userData.getAddressofaccomodation().toString() },
       		{ "", "3", "Rent Amount (Per Month)", userData.getRentamountpermonth().toString()},
       		{ "", "4", "Rent Amount (Per Annum)", userData.getRentamountperannum().toString()}       		   
       		
        };
        


        float tableHeight = IS_LANDSCAPE ? PAGE_SIZE.getWidth() - (2 * MARGIN) : PAGE_SIZE.getHeight() - (2 * MARGIN);

        Table table = new TableBuilder()
            .setCellMargin(CELL_MARGIN)
            .setColumns(columns)
            .setContent(content)
            .setHeight(tableHeight)
            .setNumberOfRows(content.length)
            .setRowHeight(ROW_HEIGHT)
            .setMargin(MARGIN)
            .setTableIndex(2)
            .setPageSize(PAGE_SIZE)
            .setLandscape(false)
            .setColumnFont(COLUMN_TEXT_FONT)
            .setColumnFontSize(FONT_SIZE)
            .setTextFont(TEXT_FONT)
            .setFontSize(FONT_SIZE)
            .build();
        return table;
    } 
}

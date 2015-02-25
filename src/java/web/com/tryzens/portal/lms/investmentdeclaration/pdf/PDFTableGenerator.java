package com.tryzens.portal.lms.investmentdeclaration.pdf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class PDFTableGenerator {
	
	private PDPage page; 
	private float nextTextY;

    // Generates document from Table object
    public String generatePDF(String path, String title,String name, String gender, String empCode, String pan, Table[] table) throws IOException, COSVisitorException {
    	String fileName = null;
        PDDocument doc = null;
        try {
        	fileName = path+name+"-declaration.pdf";
            doc = new PDDocument();   
            if(page == null){
				page = generatePage(doc, null);
			}	
            drawHeader(doc, title, name, gender, empCode, pan);
            drawTable(doc, table);
            drawFooter(doc);
            doc.save(fileName);            
        } finally {
            if (doc != null) {
                doc.close();
            }
        }
        return fileName;
    }
    
    public void drawFooter(PDDocument doc) throws IOException {
    	PDFont font = PDType1Font.HELVETICA;
    	PDPageContentStream contentStream = generateContentStream(doc, page, null);
    	contentStream.setFont( font, 7 );
    	
    	contentStream.beginText();    	
    	contentStream.moveTextPositionByAmount( 100, 280 );
    	contentStream.drawString( "1. By submitting this form , you declare that all the information given above are true and correct in all respect and  undertake to indemnify the" );
    	contentStream.endText();
    	
    	contentStream.beginText();    	
    	contentStream.moveTextPositionByAmount( 100, 272 );
    	contentStream.drawString( "company of  any loss/liability that may arise in the event of the above information being incorrect." );
    	contentStream.endText();
    	
    	contentStream.beginText();    	
    	contentStream.moveTextPositionByAmount( 100, 264 );
    	contentStream.drawString( "2. Any change in the investment as declared, be intimated to the finance department immediately along with proof." );
    	contentStream.endText();
    	
    	contentStream.beginText();    	
    	contentStream.moveTextPositionByAmount( 100, 254 );
    	contentStream.drawString( "I further undertake to submit all the Investment Proofs and Rent Receipts (if any) on or before 15th January 2014" );
    	contentStream.endText();
    	
    	contentStream.beginText();    	
    	contentStream.moveTextPositionByAmount( 100, 50 );
    	contentStream.drawString( "Date" );
    	contentStream.endText();
    	
    	contentStream.beginText();    	
    	contentStream.moveTextPositionByAmount( 480, 50 );
    	contentStream.drawString( "Signature" );
    	contentStream.endText();
    	
    	contentStream.close();
    }
    
    public void drawHeader(PDDocument doc, String title, String name, String gender, String empCode, String pan) throws IOException {
    	// Create a new font object selecting one of the PDF base fonts
    	PDFont font = PDType1Font.HELVETICA_BOLD;
    	PDPageContentStream contentStream = generateContentStream(doc, page, null);
    	contentStream.setFont( font, 10 );
    	
    	contentStream.beginText();    	
    	contentStream.moveTextPositionByAmount( 130, 800 );
    	contentStream.drawString( title );
    	contentStream.endText();
    	
    	contentStream.beginText();
    	contentStream.moveTextPositionByAmount( 80, 780 );
    	contentStream.drawString( "NAME: "+name );
    	contentStream.endText();
    	
    	contentStream.beginText();
    	contentStream.moveTextPositionByAmount( 480, 780 );
    	contentStream.drawString( "SEX: "+gender );
    	contentStream.endText();
    	
    	contentStream.beginText();
    	contentStream.moveTextPositionByAmount( 80, 760 );
    	contentStream.drawString( "EMP CODE: "+empCode );
    	contentStream.endText();
    	
    	contentStream.beginText();
    	contentStream.moveTextPositionByAmount( 480, 760 );
    	contentStream.drawString( "PAN: "+pan );
    	contentStream.endText();
    	

    	// Make sure that the content stream is closed:
    	contentStream.close();

    }

    // Configures basic setup for the table and draws it page by page
    public void drawTable(PDDocument doc, Table[] tables) throws IOException {
    	
		for (Table table : tables) {
			// Calculate pagination
			Integer rowsPerPage = new Double(Math.floor(table.getHeight()
					/ table.getRowHeight())).intValue() - 1; // subtract
			Integer numberOfPages = new Double(Math.ceil(table
					.getNumberOfRows().floatValue() / rowsPerPage)).intValue();
			 
					
			// Generate each page, get the content and draw it
			for (int pageCount = 0; pageCount < numberOfPages; pageCount++) {

				PDPageContentStream contentStream = generateContentStream(doc,
						page, table);
				String[][] currentPageContent = getContentForCurrentPage(table,
						rowsPerPage, pageCount);
				drawCurrentPage(table, currentPageContent, contentStream);
			}
		}
    }

    // Draws current page table grid and border lines and content
    private void drawCurrentPage(Table table, String[][] currentPageContent, PDPageContentStream contentStream)
            throws IOException {
    	float tableTopY;
    	switch (table.getTableIndex()) {
		case 1:
			tableTopY = table.isLandscape() ? table.getPageSize().getWidth() - table.getMargin() : table.getPageSize().getHeight() - (table.getMargin() + 150);
			break;
		case 2:
			tableTopY = table.isLandscape() ? table.getPageSize().getWidth() - table.getMargin() : table.getPageSize().getHeight() - (table.getMargin() + 310);
			break;
		default:
			tableTopY = table.isLandscape() ? table.getPageSize().getWidth() - table.getMargin() : table.getPageSize().getHeight() - table.getMargin();
			break;
		}
    	
        

        // Draws grid and borders
        drawTableGrid(table, currentPageContent, contentStream, tableTopY);

        // Position cursor to start drawing content
        float nextTextX = table.getMargin() + table.getCellMargin();
        // Calculate center alignment for text in cell considering font height
        nextTextY = tableTopY - (table.getRowHeight() / 2)
                - ((table.getTextFont().getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * table.getFontSize()) / 4);

        // Write column headers
        writeContentLine(table.getColumnsNamesAsArray(), contentStream, nextTextX, nextTextY, table, true);
        nextTextY -= table.getRowHeight();
        nextTextX = table.getMargin() + table.getCellMargin();

        // Write content
        for (int i = 0; i < currentPageContent.length; i++) {
            writeContentLine(currentPageContent[i], contentStream, nextTextX, nextTextY, table,false);
            nextTextY -= table.getRowHeight();
            nextTextX = table.getMargin() + table.getCellMargin();
        }

        contentStream.close();
    }
 

    // Writes the content for one line
    private void writeContentLine(String[] lineContent, PDPageContentStream contentStream, float nextTextX, float nextTextY,
            Table table, boolean isHeader) throws IOException {
    	
		contentStream.setFont(
				isHeader ? table.getColumnTextFont() : table.getTextFont(),
				isHeader ? table.getColumnFontSize() : table.getFontSize());
    	
        for (int i = 0; i < table.getNumberOfColumns(); i++) {
            String text = lineContent[i];
            contentStream.beginText();
            contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
            float size = table.getFontSize() * table.getTextFont().getStringWidth(text) / 1000;            
            //The text to input is greater that the permitted column length
            List<String> addressLines = new ArrayList<String>();
            int lastSpace = -1;
            if(size > table.getColumns().get(i).getWidth()){
            	while (text.length() > 0)
                {
                    int spaceIndex = text.indexOf(' ', lastSpace + 1);
                    if (spaceIndex < 0)
                    {
                    	addressLines.add(text);
                        text = "";
                    }
                    else
                    {
                        String subString = text.substring(0, spaceIndex);
                        float sizeIN = table.getFontSize() * table.getTextFont().getStringWidth(subString) / 1000;
                        if (sizeIN > table.getColumns().get(i).getWidth())
                        {
                            if (lastSpace < 0) // So we have a word longer than the line... draw it anyways
                                lastSpace = spaceIndex;
                            subString = text.substring(0, lastSpace);
                            addressLines.add(subString);
                            text = text.substring(lastSpace).trim();
                            lastSpace = -1;
                        }
                        else
                        {
                            lastSpace = spaceIndex;
                        }
                    }
                }            	 
            }
            if(addressLines.size() > 0){
            	for(String address : addressLines){            		
            		contentStream.drawString(address != null ? address : "");            		
            		contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
            		//nextTextX += table.getColumns().get(i).getWidth();
            		//nextTextY = nextTextY + 20;
            	}
            } else {
            	contentStream.drawString(text != null ? text : "");
            }
            	
            contentStream.endText();
            nextTextX += table.getColumns().get(i).getWidth();
        }
    }

    private void drawTableGrid(Table table, String[][] currentPageContent, PDPageContentStream contentStream, float tableTopY)
            throws IOException {
        // Draw row lines
        float nextY = tableTopY;
        for (int i = 0; i <= currentPageContent.length + 1; i++) {
            contentStream.drawLine(table.getMargin(), nextY, table.getMargin() + table.getWidth(), nextY);
            nextY -= table.getRowHeight();
        }

        // Draw column lines
        final float tableYLength = table.getRowHeight() + (table.getRowHeight() * currentPageContent.length);
        final float tableBottomY = tableTopY - tableYLength;
        float nextX = table.getMargin();
        for (int i = 0; i < table.getNumberOfColumns(); i++) {
            contentStream.drawLine(nextX, tableTopY, nextX, tableBottomY);
            nextX += table.getColumns().get(i).getWidth();
        }
        contentStream.drawLine(nextX, tableTopY, nextX, tableBottomY);
    }

    private String[][] getContentForCurrentPage(Table table, Integer rowsPerPage, int pageCount) {
        int startRange = pageCount * rowsPerPage;
        int endRange = (pageCount * rowsPerPage) + rowsPerPage;
        if (endRange > table.getNumberOfRows()) {
            endRange = table.getNumberOfRows();
        }
        return Arrays.copyOfRange(table.getContent(), startRange, endRange);
    }

    private PDPage generatePage(PDDocument doc, Table table) {
        PDPage page = new PDPage();
        page.setMediaBox(DeclarationPdfDocument.PAGE_SIZE);
        //page.setRotation(table.isLandscape() ? 90 : 0);
        doc.addPage(page);
        return page;
    }

    private PDPageContentStream generateContentStream(PDDocument doc, PDPage page, Table table) throws IOException {
        PDPageContentStream contentStream = new PDPageContentStream(doc, page, true, false);
        // User transformation matrix to change the reference when drawing.
        // This is necessary for the landscape position to draw correctly
        if (table != null && table.isLandscape()) {
            contentStream.concatenate2CTM(0, 1, -1, 0, table.getPageSize().getWidth(), 0);
        }
       // contentStream.setFont(table.getTextFont(), table.getFontSize());
        return contentStream;
    }
}

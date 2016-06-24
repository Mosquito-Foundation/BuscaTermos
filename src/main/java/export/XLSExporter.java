package export;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import configuration.language.Languages;
import gui.MainFrame;
import pojo.Term;
import utils.Token;

public class XLSExporter {

	public enum Type {
		ALL, MISSING;
		
		public static String name( final Type type ) {
			return Type.name( type, Languages.BRAZIL );
		}
		
		public static String name( final Type type, final Languages language ) {
			switch ( type ) {
			case ALL:
				return Token.SHEET_ALL;
			case MISSING:
				return Token.SHEET_MISSING + language.translationName();
			default:
				return "untitled" + type.ordinal();
			}
		}
	}
	
	private final String file;
	
	private final LinkedHashMap<Type, LinkedHashMap<Languages, Boolean>> options;
	
	public XLSExporter( final String file, final LinkedHashMap<Type, LinkedHashMap<Languages, Boolean>> options ) {
		this.file = file;
		this.options = options;
	}
	
	public void save() throws IOException {
		FileOutputStream fileOut = new FileOutputStream( this.getOutputFile() );
		this.createWorkbook().write( fileOut );
		fileOut.close();
	}
	
	private XSSFWorkbook createWorkbook() {
		final XSSFWorkbook workbook = new XSSFWorkbook();
		
		if ( this.hasOptionByType( Type.ALL ) ) {
			this.createAllSheet( workbook );
		}
		
		return workbook;
	}
	
	private XSSFSheet createAllSheet( final XSSFWorkbook workbook ) {
		long timeAllSheet = System.currentTimeMillis();
		
		final XSSFCellStyle headerCellStyle = this.createCellStyle( workbook, IndexedColors.AQUA );
		final XSSFCellStyle dataCellStyle = this.createCellStyle( workbook );
		
		final XSSFSheet sheet = workbook.createSheet( Type.name( Type.ALL ) );
		
		int cellIndex = -1;
		
		// Cria TOKENS
		XSSFRow header = sheet.createRow( 0 );
		XSSFCell cell = header.createCell( ++cellIndex );
		cell.setCellValue( "TOKENS" );
		cell.setCellStyle( headerCellStyle );
		
		// Cria valores dos TOKENS
		int rowIndex = 1;
		XSSFRow row;
		for ( final Object term : MainFrame.getInstance().getLanguagePanelMap().get( Languages.BRAZIL ).getGridData() ) {
			row = sheet.createRow( rowIndex++ );
			cell = row.createCell( cellIndex );
			cell.setCellValue( ((Term) term).getId() );
			cell.setCellStyle( dataCellStyle );
		}
		
		// Cria colunas dos idiomas
		for ( final Map.Entry<Languages, Boolean> entry : this.options.get( Type.ALL ).entrySet() ) {
			long timeColumn = System.currentTimeMillis();

			// Não está configurado para ser exibido
			if ( !entry.getValue() ) {
				continue;
			}
			
			// header da linguagem
			cell = header.createCell( ++cellIndex );
			cell.setCellValue( entry.getKey().translationName().toUpperCase() );
			cell.setCellStyle( headerCellStyle );
			
			rowIndex = 1;
			for ( final Object term : MainFrame.getInstance().getLanguagePanelMap().get( entry.getKey() ).getGridData() ) {
				row = sheet.getRow( rowIndex++ );
				cell = row.createCell( cellIndex );
				cell.setCellValue( ((Term) term).getText() );
				cell.setCellStyle( dataCellStyle );
			}
			
			System.out.println( "Tempo coluna " + entry.getKey().name() + " -> " + ( ( System.currentTimeMillis() - timeColumn ) / 1000.0 ) + " segundos" );
		}

		System.out.println( "Tempo planilha ALL TOKENS -> " + ( ( System.currentTimeMillis() - timeAllSheet ) / 1000.0 ) + " segundos" );

		sheet.setDefaultColumnWidth( 30 );
		sheet.setColumnWidth( 0, 10 );
		
		return sheet;
	}
	
	private XSSFCellStyle createCellStyle( final XSSFWorkbook workbook ) {
		return this.createCellStyle( workbook, null );
	}
	
	private XSSFCellStyle createCellStyle( final XSSFWorkbook workbook, final IndexedColors color ) {
		final XSSFCellStyle cellStyle = workbook.createCellStyle();
		
		cellStyle.setBorderTop( BorderStyle.THIN );
		cellStyle.setBorderRight( BorderStyle.THIN );
		cellStyle.setBorderBottom( BorderStyle.THIN );
		cellStyle.setBorderLeft( BorderStyle.THIN );
		
		cellStyle.setWrapText( true );
		
		if ( color != null ) {
			cellStyle.setFillForegroundColor( color.getIndex() );
			cellStyle.setFillPattern( CellStyle.SOLID_FOREGROUND );
		}
		
		return cellStyle;
	}
	
	private boolean hasOptionByType( final Type type ) {
		for ( Boolean enabled : this.options.get( type ).values() ) {
			if ( enabled ) {
				return true;
			}
		}
		return false;
	}
	
	private String getOutputFile() {
		return FilenameUtils.removeExtension( this.file ) + ".xlsx";
	}
	
}

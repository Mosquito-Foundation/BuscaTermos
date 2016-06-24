package utils;

import version.VersionControl;

public class Token {

	public static final String TITLE = "BuscaTermos";
	
	public static final String DISPLAY = "Exibir";
	
	public static final String BRAZIL = "Português (Brasil)";

	public static final String FILE_BRAZIL = "pt-br";
	
	public static final String USA = "Inglês (EUA)";
	
	public static final String FILE_USA = "en-us";
	
	public static final String SPAIN = "Espanhol";
	
	public static final String FILE_SPAIN = "es";
	
	public static final String FRANCE = "Francês";
	
	public static final String FILE_FRANCE = "fr";
	
	public static final String ITALY = "Italiano";
	
	public static final String FILE_ITALY = "it";
	
	public static final String GERMANY = "Alemão";
	
	public static final String FILE_GERMANY = "de";
	
	public static final String TURKEY = "Turco";
	
	public static final String FILE_TURKEY = "tr";
	
	public static final String SLOVAKIA = "Eslovaco";
	
	public static final String FILE_SLOVAKIA = "sk";
	
	public static final String CHINA = "Chinês";
	
	public static final String FILE_CHINA = "zh";
	
	public static final String OPTIONS = "Preferências";
	
	public static final String ALWAYS_ON_TOP = "Sempre no topo";
	
	public static final String SAVE_CONFIGURATION = "Salvar configurações";
	
	public static final String SEARCH = "Procurar por:";
	
	public static final String COPY = "Copiar";
	
	public static final String PASTE = "Colar";
	
	public static final String VIEW_ON = "Exibir em";

	public static final String CLOSE_TAB = "Fechar esta aba";
	
	public static final String ADD = "Adicionar";

	public static final String ADD_ALL = "Adicionar todas";

	public static final String CLOSE = "Fechar";
	
	public static final String CLOSE_OTHERS = "Fechar as outras";
	
	public static final String CLOSE_ALL = "Fechar todas";
	
	public static final String HELP = "Ajuda";
	
	public static final String SHOW_CHANGELOG = "Exibir changelog";
	
	public static final String ABOUT = "Sobre o BuscaTermos";
	
	public static final String ABOUT_INFO = "<html><div align='center'><font size='5'><b>BuscaTermos</b></font><br><div align='center'><font size='3'>Versão: "+VersionControl.getInstance().getCurrentVersion()+"</font></div></div><br> <b>Desenvolvido por:</b><br><br>• Giovane de Oliveira (giovane.oliveira@softexpert.com)<br>• Vinícius Lopes (vinicius.lopes@softexpert.com)<br><div align='center'><br>Copyright © 2014-2016 SoftExpert<br>Todos os direitos reservados</div>";

	public static final String CHANGE_PATH = "Alterar caminho dos arquivos";
	
	public static final String FILE_NOT_FOUND_INFO = "Arquivo de termos não encontrado. É necessário encontrar um caminho válido.";
	
	public static final String FILE_NOT_FOUND_QUESTION = "Arquivo de termos não encontrado. Deseja encontrar um caminho válido?";
	
	public static final String CONFIGURATION_SAVE_SUCCESS = "Configurações salvas com sucesso!";
	
	public static final String CONFIGURATION_SAVE_ERROR = "Houve um erro ao salvar as configurações";
	
	public static final String CLEAR = "Limpar";
	
	public static final String COLLAPSE = "Contrair";
	
	public static final String EXPAND = "Expandir";
	
	public static final String REGULAR_EXPRESSIONS = "Filtros de consulta";
	
	public static final String REGULAR_EXPRESSIONS_MESSAGE = "<html>Para encontrar o termo desejado de forma mais fácil e eficiente, utilize as seguintes opções:<br/><br/><br/>• <b>\"</b>texto<b>\"</b> - No resultado da consulta serão apresentados termos com correspondência exata ao que estiver escrito.<br/><br/>• <b>\"</b>tent - No resultado da consulta serão apresentados termos que começam com \"tent\". Por exemplo: tentar, tentativa, tentei, etc.<br/><br/>• tiva<b>\"</b> - No resultado da consulta serão apresentados termos que terminam com \"tiva\". Por exemplo: tentativa, expectativa, etc.<br/><br/>• te<b>?</b>to - Substitui um caractere. No resultado da consulta serão apresentados termos como texto, testo, tento, por exemplo.<br/></html>";

	public static final String THEMES = "Temas";
	
	public static final String THEME_DEFAULT = "Padrão";
	
	public static final String THEME_SYSTEM = "Sistema";
	
	public static final String CHANGE_THEME_WARNING = "As alterações de tema serão aplicadas ao reiniciar o aplicativo";
	
	public static final String OK = "OK";
	
	public static final String CHANGELOG = "Changelog";
	
	public static final String FEATURES = "Alterações";
	
	public static final String FIXES = "Correções";
	
	public static final String TOOLS = "Ferramentas";
	
	public static final String EXPORT_TO_XLS = "Exportar para XLS";

	public static final String EXPORT = "Exportar";
	
	public static final String CUSTOMIZE_LIST_ALL = "Todos os termos";
	
	public static final String CUSTOMIZE_LIST_MISSING = "Termos para tradução";
	
	public static final String PERMISSION_DENIED = "Você não possui permissão para criar arquivos neste diretório.";
	
	public static final String SHEET_ALL = "ALL TOKENS";
	
	public static final String SHEET_MISSING = "Need translation to ";
	
	public static final String SELECT_AT_LEAST_ONE_OPTION = "Ao menos uma opção deve estar selecionada.";
	
	public static final String EXPORT_SUCCESS = "Arquivo exportado com sucesso.";
	
	public static final String EXPORT_FILE_IN_USE = "Não foi possível salvar.\nO arquivo destino já está sendo usado por outro processo.";
	
	public static final String LOADING = "Carregando...";
}

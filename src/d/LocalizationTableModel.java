package d;

import static d.LocaleConstants.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class LocalizationTableModel implements TableModel {
	
	private static int size;
	private static ArrayList<String> keys;
	private static ArrayList<String> values;
	private static ArrayList<Integer> indexes;
	static Map<Integer, String> map;
	
	public static int localei = 1;

	LocalizationTableModel() {
		Class lc = LocaleConstants.class;
		map = new HashMap<Integer, String>();
		keys = new ArrayList<String>();
		values = new ArrayList<String>();
		indexes = new ArrayList<Integer>();
		int i2 = 0;
		for(Field f: lc.getFields()) {
			String n = f.getName();
			keys.add(n);
			int c = -1;
			try {
				c = f.getInt(null);
			} catch (Exception e) {
				e.printStackTrace();
			}
			indexes.add(c);
			values.add(s(c));
			i2++;
		}
		size = i2;
		for(int i = 0; i < i2; i++) {
			map.put(i, values.get(i));
		}
	}

	public void addTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub

	}

	public Class<?> getColumnClass(int arg0) {
		return String.class;
	}

	public int getColumnCount() {
		return 2;
	}

	public String getColumnName(int columnIndex) {
		return columnIndex == 0 ? "Key" : "Value";
	}

	public int getRowCount() {
		return size;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		if(columnIndex == 0) {
			return keys.get(rowIndex);
		} else {
			return values.get(rowIndex);
		}
		
	}

	public boolean isCellEditable(int arg0, int columnIndex) {
		if(columnIndex == 0)
			return false;
		else
			return true;
	}

	public void removeTableModelListener(TableModelListener arg0) {
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if(columnIndex == 0)
			return;
		map.put(rowIndex, (String) aValue);
		values.set(rowIndex, (String) aValue);

	}
	

	
	public static String s(int c) {
		switch(localei) {
		case 0: {
			switch(c) {
			case CMD_Settings:
				return "Settings";
			case CMD_Search:
				return "Search";
			case CMD_OK:
				return "OK";
			case CMD_Cancel:
				return "Cancel";
			case CMD_Back:
				return "Back";
			case CMD_Exit:
				return "Exit";
			case CMD_Apply:
				return "Apply";
			case CMD_Go:
				return "Go";
			case CMD_View:
				return "View";
			case CMD_Watch:
				return "Watch";
			case CMD_Download:
				return "Download";
			case CMD_OpenByID:
				return "Open by ID";
			case CMD_Open:
				return "Open";
			case CMD_Videos:
				return "Videos";
			case CMD_ViewChannel:
				return "View channel";
			case CMD_SwitchToPopular:
				return "Switch to popular";
			case CMD_SwitchToTrends:
				return "Switch to trends";
			case SET_VideoRes:
				return "Preferred video quality";
			case SET_Appearance:
				return "Appearance";
			case SET_OtherSettings:
				return "";
			case SET_DownloadDir:
				return "Download directory";
			case SET_InvAPI:
				return "Invidious API Instance";
			case SET_StreamProxy:
				return "Stream proxy server";
			case SET_ImagesProxy:
				return "Images proxy prefix";
			case SET_CountryCode:
				return "Country code (ISO 3166)";
			case TITLE_Trends:
				return "Trending";
			case TITLE_Popular:
				return "Popular";
			case TITLE_SearchQuery:
				return "Search query";
			case TITLE_Settings:
				return "Settings";
			case BTN_LatestVideos:
				return "Latest videos";
			case BTN_SearchVideos:
				return "Search videos";
			case TITLE_Loading:
				return "Loading";
			case TXT_Views:
				return "Views";
			case TXT_LikesDislikes:
				return "Likes / Dislikes";
			case TXT_Published:
				return "Published";
			case TXT_Description:
				return "Description";
			case BTN_ChannelInformation:
				return "Information";
			case TXT_Connecting:
				return "Connecting";
			case TXT_Waiting:
				return "Error! Waiting for retry...";
			case TXT_ConnectionRetry:
				return "Connection retry";
			case TXT_Redirected:
				return "Redirected";
			case TXT_Connected:
				return "Connected";
			case TXT_Downloading:
				return "Downloading";
			case TXT_Downloaded:
				return "Downloaded";
			case TXT_Canceled:
				return "Canceled";
			case TXT_DownloadFailed:
				return "Download failed";
			case TXT_Initializing:
				return "Initializing";
			case TXT_Done:
				return "Done";
			case CMD_About:
				return "About";
			case CMD_Select:
				return "Select";
			case CMD_OpenPlaylist:
				return "Open playlist";
			case CMD_Next:
				return "Next video";
			case CMD_Prev:
				return "Prev. video";
			case SET_CustomLocaleId:
				return "Custom locale identificator";
			//
			case TXT_1subscriber:
				return "subscriber";
			case TXT_10_1subscribers:
				return "subscribers";
			case TXT_subscribers:
				return "subscribers";
			case TXT_1video:
				return "video";
			case TXT_videos:
				return "videos";
			}
		}
		case 1: {
			switch(c) {
			case CMD_Settings:
				return "Настройки";
			case CMD_Search:
				return "Поиск";
			case CMD_OK:
				return "OK";
			case CMD_Cancel:
				return "Отмена";
			case CMD_Back:
				return "Назад";
			case CMD_Exit:
				return "Выйти";
			case CMD_Apply:
				return "Применить";
			case CMD_Go:
				return "Открыть";
			case CMD_View:
				return "Открыть";
			case CMD_Watch:
				return "Смотреть";
			case CMD_Download:
				return "Скачать";
			case CMD_OpenByID:
				return "Открыть по ссылке";
			case CMD_Open:
				return "Открыть";
			case CMD_Videos:
				return "Видео";
			case CMD_ViewChannel:
				return "View channel";
			case CMD_SwitchToPopular:
				return "Сменить на популярные";
			case CMD_SwitchToTrends:
				return "Сменить на тренды";
			case SET_VideoRes:
				return "Предпочитаемое качество видео";
			case SET_Appearance:
				return "Внешность";
			case SET_OtherSettings:
				return "";
			case SET_DownloadDir:
				return "Папка для скачивания";
			case SET_InvAPI:
				return "Invidious API Instance";
			case SET_StreamProxy:
				return "Stream proxy server";
			case SET_ImagesProxy:
				return "Прокси для картинок";
			case SET_CountryCode:
				return "Код страны (ISO 3166)";
			case TITLE_Trends:
				return "Тренды";
			case TITLE_Popular:
				return "Популярные";
			case TITLE_SearchQuery:
				return "Результаты поиска";
			case TITLE_Settings:
				return "Настройки";
			case BTN_LatestVideos:
				return "Последние видео";
			case BTN_SearchVideos:
				return "Поиск видео";
			case TITLE_Loading:
				return "Загрузка";
			case TXT_Views:
				return "Просмотры";
			case TXT_LikesDislikes:
				return "Понравилось / Не понравилось";
			case TXT_Published:
				return "Выпущено";
			case TXT_Description:
				return "Описание";
			case BTN_ChannelInformation:
				return "Информация";
			case TXT_Connecting:
				return "Соединение";
			case TXT_Waiting:
				return "Ошибка подключения! Ожидание...";
			case TXT_ConnectionRetry:
				return "Повторная попытка подключения";
			case TXT_Redirected:
				return "Перенаправлен";
			case TXT_Connected:
				return "Подключен";
			case TXT_Downloading:
				return "Скачивание";
			case TXT_Downloaded:
				return "Скачано";
			case TXT_Canceled:
				return "Отменено";
			case TXT_DownloadFailed:
				return "Скачивание не удалось";
			case TXT_Initializing:
				return "Инициализация";
			case TXT_Done:
				return "Готово";
			case CMD_About:
				return "О программе";
			case CMD_Select:
				return "Выбрать";
			case CMD_OpenPlaylist:
				return "Откр. плейлист";
			case CMD_Next:
				return "След. видео";
			case CMD_Prev:
				return "Пред. видео";
			case SET_CustomLocaleId:
				return "Идентификатор польз. локализации";
			//
			case TXT_1subscriber:
			case TXT_10_1subscribers:
				return "подписчик";
			case TXT_subscribers:
				return "подписчиков";
			case TXT_1video:
			case TXT_videos:
				return "видео";
			}
		}
		}
		//
		return "";
	}

}

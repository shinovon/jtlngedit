package cc.nnproject.ytapp.localeeditor;

import static cc.nnproject.ytapp.localeeditor.LocaleConstants.*;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.TableModel;

public class LocalizationTableModel implements TableModel {
	
	private static int size;
	private static ArrayList<String> keys;
	private static ArrayList<Integer> indexes;
	static Map<Integer, String> map;
	
	public static int localei = 0;

	LocalizationTableModel() {
		init(null);
	}

	void init(UI ui) {
		Class<?> lc = LocaleConstants.class;
		map = new HashMap<Integer, String>();
		keys = new ArrayList<String>();
		indexes = new ArrayList<Integer>();
		int i2 = 0;
		for(Field f: lc.getFields()) {
			String n = f.getName();
			int c = -1;
			try {
				c = f.getInt(null);
			} catch (Exception e) {
				e.printStackTrace();
			}
			keys.add(n);
			indexes.add(c);
			map.put(c, s(c));
			i2++;
		}
		size = i2;
		if(ui != null) {
			ui.idField.setText(localei == 0 ? "en" : "ru");
			updateTable(ui);
		}
	}

	public void addTableModelListener(TableModelListener arg0) {}

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
			return map.get(indexes.get(rowIndex));
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
		map.put(indexes.get(rowIndex), (String) aValue);
	}

	public void load(UI ui, boolean b) {
		JFileChooser fc = new JFileChooser(".");
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fc.setFileFilter(new FileFilter() {
			public boolean accept(File f) {
		        return f.isDirectory() || f.getName().startsWith("jtlng_");
		    }

			public String getDescription() {
				return "User localization files";
			}
		});
		int c = fc.showOpenDialog(ui.frame);
		if(c == JFileChooser.CANCEL_OPTION) {
			return;
		}

		try {
			File f = fc.getSelectedFile();
			
			FileInputStream is = new FileInputStream(f);
			DataInputStream d = new DataInputStream(is);
			ui.idField.setText(f.getName().substring("jtlng_".length()));
			try {
				int i;
				boolean a = false;
				while((i = d.readShort()) != -1) {
					String s = d.readUTF();
					if(i == 0 && !a) {
						ui.authorField.setText(s);
						a = true;
						continue;
					}
					if(i == 0) continue;
					if(b) map.put(i, s);
					else map.put(i, s);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				d.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
		updateTable(ui);
	}
	
	private void updateTable(UI ui) {
		ui.table.setModel(new TableModel() {

			public void addTableModelListener(TableModelListener arg0) {
			}

			public Class<?> getColumnClass(int arg0) {
				return null;
			}

			public int getColumnCount() {
				return 0;
			}

			public String getColumnName(int arg0) {
				return null;
			}

			public int getRowCount() {
				return 0;
			}

			public Object getValueAt(int arg0, int arg1) {
				return null;
			}

			public boolean isCellEditable(int arg0, int arg1) {
				return false;
			}

			public void removeTableModelListener(TableModelListener arg0) {
			}

			public void setValueAt(Object arg0, int arg1, int arg2) {
			}
			
		});
		ui.table.setModel(this);
		ui.table.repaint();
	}

	public static String s(int c) {
		switch(localei) {
		case 0: {
			switch(c) {
			case ISOLanguageCode:
				return "en-US";
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
				return "Misc";
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
			case SET_HTTPProxy:
				return "HTTP Proxy Streaming";
			case SET_PreLoadRMS:
				return "Pre-load images to RMS";
			case SET_RememberSearch:
				return "Remember search";
			case SET_VideoPreviews:
				return "Video previews";
			case SET_SearchChannels:
				return "Search channels";
			case SET_SearchPlaylists:
				return "Search playlists";
			case SET_VQ_AudioOnly:
				return "Audio only";
			case SET_VQ_NoAudio:
				return "no audio";
			case SET_Tip1:
				return "(Used only if http streaming is on)";
			case SET_Tip2:
				return "(Leave images proxy empty if HTTPS is supported)";
			case BTN_Playlists:
				return "Playlists";
			case CMD_ShowLink:
				return "Show link";
			case SET_Tip3:
				return "(Always used for online playback, and for downloading if HTTP streaming is enabled)";
			case SET_PlaybackMethod:
				return "Playback method";
			case SET_SymbianOnline:
				return "Online (Symbian/Bada)";
			case SET_Browser:
				return "Via browser";
			case SET_DownloadBuffer:
				return "Download buffer size (bytes)";
			case TXT_VideoDuration:
				return "Video duration";
			case SET_Via2yxa:
				return "Via 2yxa.mobi";
			case SET_CheckUpdates:
				return "Check for updates";
			case TXT_NewUpdateAvailable:
				return "New update available!";
			case CMD_Ignore:
				return "Ignore";
			case SET_On:
				return "On";
			case SET_Off:
				return "Off";
			case SET_IteroniProxy:
				return "Use iteroni proxy for playback";
			case CMD_Func:
				return "Options";
			case CMD_Refresh:
				return "Refresh";
			case SET_Amoled:
				return "Night theme";
			case SET_SmallPreviews:
				return "Small previews";
			case SET_Reset:
				return "Reset settings";
			case SET_Video:
				return "Video";
			case SET_Network:
				return "Network";
			case TXT_SearchHint:
				return "Search..";
			case SET_AutoStart:
				return "Auto-start from other applications";
			case BTN_Share:
				return "Share";
			case SET_ChooseLanguage:
				return "Choose language";
			case SET_FullScreenMode:
				return "Full-Screen mode";
			case CMD_FuncMenu:
				return "Menu";
			case SET_Input:
				return "Input";
			case SET_VirtualKeyboard:
				return "Keyboard";
			case SET_NokiaUI:
				return "NokiaUI (if available)";
			case SET_FullScreenInput:
				return "Fullscreen input";
			case SET_InputLanguages:
				return "Input languages";
			case SET_j2mekeyboardSettings:
				return "j2mekeyboard settings";
			// Numbers
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
			case TXT_1view:
				return "view";
			case TXT_views:
				return "views";
			}
		}
		case 1: {
			switch(c) {
			case ISOLanguageCode:
				return "ru-RU";
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
				return "Открыть канал";
			case CMD_SwitchToPopular:
				return "Сменить на популярные";
			case CMD_SwitchToTrends:
				return "Сменить на тренды";
			case SET_VideoRes:
				return "Предпочитаемое качество видео";
			case SET_Appearance:
				return "Внешность";
			case SET_OtherSettings:
				return "Прочие настройки";
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
			case SET_HTTPProxy:
				return "HTTP прокси стриминг";
			case SET_PreLoadRMS:
				return "Предзагрузка изображений в RMS";
			case SET_RememberSearch:
				return "Запоминание поиска";
			case SET_VideoPreviews:
				return "Изображения";
			case SET_SearchChannels:
				return "Поиск каналов";
			case SET_SearchPlaylists:
				return "Поиск плейлистов";
			case SET_VQ_AudioOnly:
				return "Только аудио";
			case SET_VQ_NoAudio:
				return "без звука";
			case SET_Tip1:
				return "(Использован только если включен HTTP стриминг через прокси)";
			case SET_Tip2:
				return "(Оставьте пустым если ваше устройство поддерживает HTTPS)";
			case BTN_Playlists:
				return "Плейлисты";
			case CMD_ShowLink:
				return "Показать ссылку";
			case SET_Tip3:
				return "(Используется всегда при онлайн проигрывании, и для скачивания если включен HTTP стриминг)";
			case SET_PlaybackMethod:
				return "Способ проигрывания";
			case SET_SymbianOnline:
				return "Онлайн (Symbian/Bada)";
			case SET_Browser:
				return "Через браузер";
			case SET_DownloadBuffer:
				return "Размер буфера скачивания (байты)";
			case TXT_VideoDuration:
				return "Длительность видео";
			case SET_Via2yxa:
				return "Через 2yxa.mobi";
			case SET_CheckUpdates:
				return "Проверять наличие обновлений";
			case TXT_NewUpdateAvailable:
				return "Доступно новое обновление!";
			case CMD_Ignore:
				return "Ок";
			case SET_On:
				return "Вкл.";
			case SET_Off:
				return "Выкл.";
			case SET_IteroniProxy:
				return "Прокси iteroni для проигрывания";
			case CMD_Func:
				return "Функции";
			case CMD_Refresh:
				return "Обновить";
			case SET_Amoled:
				return "Ночная тема";
			case SET_SmallPreviews:
				return "Маленькие превью";
			case SET_Reset:
				return "Сбросить настройки";
			case SET_Video:
				return "Видео";
			case SET_Network:
				return "Сеть";
			case TXT_SearchHint:
				return "Поиск..";
			case SET_AutoStart:
				return "Авто-старт из других приложений";
			case BTN_Share:
				return "Поделиться";
			case SET_ChooseLanguage:
				return "Выбрать язык";
			case SET_FullScreenMode:
				return "Полноэкранный режим";
			case CMD_FuncMenu:
				return "Меню";
			case SET_Input:
				return "Ввод";
			case SET_VirtualKeyboard:
				return "Клавиатура";
			case SET_NokiaUI:
				return "NokiaUI (если имеется)";
			case SET_FullScreenInput:
				return "Полноэкранный ввод";
			case SET_InputLanguages:
				return "Языки ввода";
			case SET_j2mekeyboardSettings:
				return "Настройки j2mekeyboard";
			// Numbers
			case TXT_1subscriber:
			case TXT_10_1subscribers:
				return "подписчик";
			case TXT_subscribers:
				return "подписчиков";
			case TXT_1video:
			case TXT_videos:
				return "видео";
			case TXT_1view:
				return "просмотр";
			case TXT_views:
				return "просмотров";
			}
		}
		}
		//
		return "";
	}

}

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
			switch(c) {
			case CMD_Settings:
				values.add("Settings");
				break;
			case CMD_Search:
				values.add("Search");
				break;
			case CMD_OK:
				values.add("OK");
				break;
			case CMD_Cancel:
				values.add("Cancel");
				break;
			case CMD_Back:
				values.add("Back");
				break;
			case CMD_Exit:
				values.add("Exit");
				break;
			case CMD_Apply:
				values.add("Apply");
				break;
			case CMD_Go:
				values.add("Go");
				break;
			case CMD_View:
				values.add("View");
				break;
			case CMD_Watch:
				values.add("Watch");
				break;
			case CMD_Download:
				values.add("Download");
				break;
			case CMD_OpenByID:
				values.add("Open by ID");
				break;
			case CMD_Open:
				values.add("Open");
				break;
			case CMD_Videos:
				values.add("Videos");
				break;
			case CMD_ViewChannel:
				values.add("View channel");
				break;
			case CMD_SwitchToPopular:
				values.add("Switch to popular");
				break;
			case CMD_SwitchToTrends:
				values.add("Switch to trends");
				break;
			case SET_VideoRes:
				values.add("Preferred video quality");
				break;
			case SET_Appearance:
				values.add("Appearance");
				break;
			case SET_OtherSettings:
				values.add("");
				break;
			case SET_DownloadDir:
				values.add("Download directory");
				break;
			case SET_InvAPI:
				values.add("Invidious API Instance");
				break;
			case SET_StreamProxy:
				values.add("Stream proxy server");
				break;
			case SET_ImagesProxy:
				values.add("Images proxy prefix");
				break;
			case SET_CountryCode:
				values.add("Country code (ISO 3166)");
				break;
			case TITLE_Trends:
				values.add("Trending");
				break;
			case TITLE_Popular:
				values.add("Popular");
				break;
			case TITLE_SearchQuery:
				values.add("Search query");
				break;
			case TITLE_Settings:
				values.add("Settings");
				break;
			case BTN_LatestVideos:
				values.add("Latest videos");
				break;
			case BTN_SearchVideos:
				values.add("Search videos");
				break;
			case TITLE_Loading:
				values.add("Loading");
				break;
			case TXT_Views:
				values.add("Views");
				break;
			case TXT_LikesDislikes:
				values.add("Likes / Dislikes");
				break;
			case TXT_Published:
				values.add("Published");
				break;
			case TXT_Description:
				values.add("Description");
				break;
			case BTN_ChannelInformation:
				values.add("Information");
				break;
			case TXT_Connecting:
				values.add("Connecting");
				break;
			case TXT_Waiting:
				values.add("Error! Waiting for retry...");
				break;
			case TXT_ConnectionRetry:
				values.add("Connection retry");
				break;
			case TXT_Redirected:
				values.add("Redirected");
				break;
			case TXT_Connected:
				values.add("Connected");
				break;
			case TXT_Downloading:
				values.add("Downloading");
				break;
			case TXT_Downloaded:
				values.add("Downloaded");
				break;
			case TXT_Canceled:
				values.add("Canceled");
				break;
			case TXT_DownloadFailed:
				values.add("Download failed");
				break;
			case TXT_Initializing:
				values.add("Initializing");
				break;
			case TXT_Done:
				values.add("Done");
				break;
			case CMD_About:
				values.add("About");
				break;
			case CMD_Select:
				values.add("Select");
				break;
			case CMD_OpenPlaylist:
				values.add("Open playlist");
				break;
			case CMD_Next:
				values.add("Next video");
				break;
			case CMD_Prev:
				values.add("Prev. video");
				break;
			default:
				values.add("");
				break;
			}
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
		// TODO Auto-generated method stub

	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if(columnIndex == 0)
			return;
		values.set(rowIndex, (String) aValue);

	}

}

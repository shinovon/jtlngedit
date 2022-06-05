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
		Class lc = LocaleConstants.class;
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
		        return f.isDirectory() || f.getName().startsWith("jtlng.");
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
			ui.idField.setText(f.getName().substring("jtlng.".length()));
			try {
				int i;
				boolean a = false;
				while( (i = d.readShort()) != -1) {
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
		ui.table.setModel(new TableModel() {

			public void addTableModelListener(TableModelListener arg0) {
				// TODO Auto-generated method stub
				
			}

			public Class<?> getColumnClass(int arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			public int getColumnCount() {
				// TODO Auto-generated method stub
				return 0;
			}

			public String getColumnName(int arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			public int getRowCount() {
				// TODO Auto-generated method stub
				return 0;
			}

			public Object getValueAt(int arg0, int arg1) {
				// TODO Auto-generated method stub
				return null;
			}

			public boolean isCellEditable(int arg0, int arg1) {
				// TODO Auto-generated method stub
				return false;
			}

			public void removeTableModelListener(TableModelListener arg0) {
				// TODO Auto-generated method stub
				
			}

			public void setValueAt(Object arg0, int arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
		});
		ui.table.setModel(this);
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
				return "Common";
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
				return "Opts";
			case CMD_Refresh:
				return "Refresh";
			case SET_Amoled:
				return "Night theme";
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
			case TXT_1view:
				return "view";
			case TXT_views:
				return "views";
			}
		}
		case 1: {
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
				return "Common";
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
				return "Opts";
			case CMD_Refresh:
				return "Refresh";
			case SET_Amoled:
				return "Night theme";
			//
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

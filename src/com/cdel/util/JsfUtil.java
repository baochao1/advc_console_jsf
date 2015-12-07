package com.cdel.util;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author 张苏磊
 * @desc 获取皮肤设置信息
 * 
 */
public class JsfUtil {
	public static final String DEFAULT_THEME = "cupertino";
	public static final String IMAGE = "/resources/images/theme/";
	public static final List<Theme> THEMES;

	static {
		ResourceBundle resourceBundle = ResourceBundle
				.getBundle("config/Bundle");
		THEMES = new ArrayList<Theme>();
		THEMES.add(new Theme("aristo", resourceBundle.getString("aristo"),
				IMAGE + "aristo.png"));
		THEMES.add(new Theme("black-tie",
				resourceBundle.getString("black-tie"), IMAGE + "black-tie.png"));
		THEMES.add(new Theme("blitzer", resourceBundle.getString("blitzer"),
				IMAGE + "blitzer.png"));
		THEMES.add(new Theme("bluesky", resourceBundle.getString("bluesky"),
				IMAGE + "bluesky.png"));
		THEMES.add(new Theme("casablanca", resourceBundle
				.getString("casablanca"), IMAGE + "casablanca.png"));
		THEMES.add(new Theme("cupertino",
				resourceBundle.getString("cupertino"), IMAGE + "cupertino.png"));
		THEMES.add(new Theme("dark-hive",
				resourceBundle.getString("dark-hive"), IMAGE + "dark-hive.png"));
		THEMES.add(new Theme("dot-luv", resourceBundle.getString("dot-luv"),
				IMAGE + "dot-luv.png"));
		THEMES.add(new Theme("eggplant", resourceBundle.getString("eggplant"),
				IMAGE + "eggplant.png"));
		THEMES.add(new Theme("excite-bike", resourceBundle
				.getString("excite-bike"), IMAGE + "excite-bike.png"));
		THEMES.add(new Theme("flick", resourceBundle.getString("flick"), IMAGE
				+ "flick.png"));
		THEMES.add(new Theme("glass-x", resourceBundle.getString("glass-x"),
				IMAGE + "glass-x.png"));
		THEMES.add(new Theme("hot-sneaks", resourceBundle
				.getString("hot-sneaks"), IMAGE + "hot-sneaks.png"));
		THEMES.add(new Theme("humanity", resourceBundle.getString("humanity"),
				IMAGE + "humanity.png"));
		THEMES.add(new Theme("le-frog", resourceBundle.getString("le-frog"),
				IMAGE + "le-frog.png"));
		THEMES.add(new Theme("midnight", resourceBundle.getString("midnight"),
				IMAGE + "midnight.png"));
		THEMES.add(new Theme("mint-choc",
				resourceBundle.getString("mint-choc"), IMAGE + "mint-choc.png"));
		THEMES.add(new Theme("overcast", resourceBundle.getString("overcast"),
				IMAGE + "overcast.png"));
		THEMES.add(new Theme("pepper-grinder", resourceBundle
				.getString("pepper-grinder"), IMAGE + "pepper-grinder.png"));
		THEMES.add(new Theme("redmond", resourceBundle.getString("redmond"),
				IMAGE + "redmond.png"));
		THEMES.add(new Theme("rocket", resourceBundle.getString("rocket"),
				IMAGE + "rocket.png"));
		THEMES.add(new Theme("smoothness", resourceBundle
				.getString("smoothness"), IMAGE + "smoothness.png"));
		THEMES.add(new Theme("south-street", resourceBundle
				.getString("south-street"), IMAGE + "south-street.png"));
		THEMES.add(new Theme("start", resourceBundle.getString("start"), IMAGE
				+ "start.png"));
		THEMES.add(new Theme("sunny", resourceBundle.getString("sunny"), IMAGE
				+ "sunny.png"));
		THEMES.add(new Theme("swanky-purse", resourceBundle
				.getString("swanky-purse"), IMAGE + "swanky-purse.png"));
		THEMES.add(new Theme("trontastic", resourceBundle
				.getString("trontastic"), IMAGE + "trontastic.png"));
		THEMES.add(new Theme("ui-darkness", resourceBundle
				.getString("ui-darkness"), IMAGE + "ui-darkness.png"));
		THEMES.add(new Theme("ui-lightness", resourceBundle
				.getString("ui-lightness"), IMAGE + "ui-lightness.png"));
		THEMES.add(new Theme("vader", resourceBundle.getString("vader"), IMAGE
				+ "vader.png"));
	}

}

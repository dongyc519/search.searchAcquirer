package com.andy.jenny.search.searchacquirer.acquirer.repeat;

import java.util.Map;
import java.util.function.Consumer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RepeatConsumer<T> implements Consumer<T> {

	private Map<String, T> result;
	private String[] repeatKeyFormat;

	static String[] defaultRepeatKeyFormat = new String[] { "id" };

	public RepeatConsumer(RepeatKey key, Map<String, T> tempMap) {
		result = tempMap;
		switch (key) {
		case ID:
			repeatKeyFormat = defaultRepeatKeyFormat;
			break;
		case URLANDTIME:
			repeatKeyFormat = new String[] { "url", "time" };
			break;
		case URL:
			repeatKeyFormat = new String[] { "url" };
			break;
		case TIME:
			repeatKeyFormat = new String[] { "time" };
			break;
		default:
			repeatKeyFormat = null;
			break;
		}
		if (repeatKeyFormat == null) {
			throw new IllegalArgumentException(String.format("RepeatKey:%s is not exist!!!", key.name()));
		}
	}

	public RepeatConsumer(Map<String, T> tempMap) {
		this(RepeatKey.ID, tempMap);
	}

	@Override
	public void accept(T t) {
		StringBuilder keyBuild = new StringBuilder();
		try {
			for (String string : repeatKeyFormat) {
				keyBuild.append(t.getClass().getDeclaredField(string).get(t).toString());
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		String key = keyBuild.toString();
		if (result.containsKey(key)) {
			return;
		}
		result.put(key, t);
	}
}

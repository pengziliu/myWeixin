package com.lzp.service;


import com.lzp.util.HttpRequestUtil;
import com.lzp.util.WXUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 历史上的今天
 *
 * @author Administrator
 *
 */
public class TodayInHistoryService {
	/**
	 * 从html中抽取出历史上的今天信息
	 *
	 * @param html
	 * @return
	 */
	private static String extract(String html) {
		StringBuffer buffer = null;
		// 日期标签：区分是昨天还是今天
		String dateTag = getMonthDay(0);

		Pattern p = Pattern
				.compile("(.*)(<div class=\"listren\">)(.*?)(</div>)(.*)");
		Matcher m = p.matcher(html);
		if (m.matches()) {
			buffer = new StringBuffer();
			if (m.group(3).contains(getMonthDay(-1)))
				dateTag = getMonthDay(-1);

			// 拼装标题
			buffer.append(WXUtil.emoji(0x1F449)).append(WXUtil.emoji(0x1F449))
					.append("历史上的").append(dateTag)
					.append(WXUtil.emoji(0x1F448))
					.append(WXUtil.emoji(0x1F448)).append("\n\n");

			// 抽取需要的数据
			for (String info : m.group(3).split("  ")) {
				info = info.replace(dateTag, "").replace("（图）", "")
						.replaceAll("</?[^>]+>", "")
						.replace("&nbsp;&nbsp;", "").trim();
				// 在每行末尾追加2个换行符
				if (!"".equals(info)) {
					buffer.append(WXUtil.emoji(0x1F31F)).append(info)
							.append("\n\n");
				}
			}
		}
		// 将buffer最后两个换行符移除并返回
		return (null == buffer) ? null : buffer.substring(0,
				buffer.lastIndexOf("\n\n"));
	}

	/**
	 * 获取前/后n天日期(M月d日)
	 *
	 * @return
	 */
	private static String getMonthDay(int diff) {
		DateFormat df = new SimpleDateFormat("M月d日");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_YEAR, diff);
		return df.format(c.getTime());
	}

	/**
	 * 封装历史上的今天查询方法，供外部调用
	 *
	 * @return
	 */
	public static String getTodayInHistoryInfo() {
		// 获取网页源代码
		String html = HttpRequestUtil.httpRequest("http://www.rijiben.com/");
		// 从网页中抽取信息
		String result = extract(html);

		return result;
	}

	/**
	 * 通过main在本地测试
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		String info = getTodayInHistoryInfo();
		System.out.println(info);
	}
}

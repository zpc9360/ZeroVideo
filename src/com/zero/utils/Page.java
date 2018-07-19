package com.zero.utils;

public class Page {

	/**
	 * @author Zero
	 * @version 1.1
	 * @createDate 2018-4-20 13:50
	 * @lastModifyDate 2018-6-1 10:48
	 */

	/**
	 * 
	 * 使用方法:设置参数 -> 调用init()方法 -> 获取参数
	 * 
	 * 
	 * 必须在init()执行之前进行的设置：
	 *
	 * 必要参数,通过set()方法设置
	 * 
	 * @param DBTotalCount
	 *            数据库记录总数
	 * @param curPage
	 *            当前页或者请求页,未设置情况下,自动设置为1
	 * @param actionUrl
	 *            点击分页按钮时要跳转到的目标地址
	 */
	/**
	 * 可选参数
	 * 
	 * @param rowsPrePage
	 *            每页的记录条数,默认为15条
	 */

	/**
	 * 必须在init()执行之后,你需要取的几个参数:
	 *
	 * @param startNum
	 *            开始的数据库记录位置,即limit后的第一个参数
	 * @param rowsPrePage
	 *            数据条数,即limit后的第二个参数
	 */

	private int curPage = 1;
	private long DBTotalCount = 0;
	private int rowsPrePage = 15;
	private long MaxPage = 0;
	private int startNum = 0;

	private String actionUrl = "";
	private StringBuffer pageBar;// 要返回的链接

	public Page() {
		pageBar = new StringBuffer("");
	}

	public void init() {
		MaxPage = DBTotalCount % rowsPrePage == 0 ? (DBTotalCount / rowsPrePage) : (DBTotalCount / rowsPrePage + 1);
		if (curPage > MaxPage)
			curPage = 1;
		startNum = (curPage - 1) * rowsPrePage;
		if(DBTotalCount == 0) {
			pageBar.append("暂无资源");
			return;
		}
		setMyUrl();
	}

	private void setMyUrl() {
		// System.out.println("总记录条数" + DBTotalCount);
		// System.out.println("总记页数" + MaxPage);
		if (actionUrl.contains("?")) {
			actionUrl = actionUrl + "&";
		} else {
			actionUrl = actionUrl + "?";
		}
		pageBar.append("<nav aria-label=\"curPage\">\r\n");
		pageBar.append("	<ul class=\"pagination justify-content-end  mr-3\">\r\n");
		// 上一页
		String disabledMin = "disabled";
		String disabledMax = "disabled";
		if (curPage > 1) {
			disabledMin = "";
		}
		pageBar.append("<li class=\"page-item " + disabledMin + "\"><a class=\"page-link\" href=\"" + actionUrl + "curPage="
				+ (curPage - 1) + "\">上一页</a></li>\r\n");
		// 如果获取到的总页数小于6的话
		if (MaxPage <= 6) {
			smallPage();
		} else {
			bigPage();
		}
		// 下一页
		if (curPage < MaxPage) {
			disabledMax = "";
		}
		pageBar.append("<li class=\"page-item " + disabledMax + "\"><a class=\"page-link\" href=\"" + actionUrl + "curPage="
				+ (curPage + 1) + "\" aria-label=\"Next\">下一页</a></li>\r\n");
		pageBar.append("	</ul>\r\n");
		pageBar.append("</nav>\r\n");
	}

	private void bigPage() {
		// 如果当前页大于1的话，加上上一页的链接
		if (curPage == 1) {
			pageBar.append("<li class=\"page-item active\"><span  class=\"page-link\">" + 1 + "</span></li>\r\n");
		} else {
			pageBar.append("<li class=\"page-item text-sm\"><a class=\"page-link\" href=\"" + actionUrl + "curPage=" + 1 + "\">"
					+ 1 + "</a></li>\r\n");
		}
		if (curPage <= 3) {
			for (int i = 2; i <= 5; i++) {
				// 如果是当前页数，设置为文版显示；如果不是，设置为链接
				if (curPage == i) {
					pageBar.append(
							"<li class=\"page-item active\"><span  class=\"page-link\">" + i + "</span></li>\r\n");
				} else {
					pageBar.append("<li class=\"page-item\"><a class=\"page-link\" href=\"" + actionUrl + "curPage=" + i
							+ "\">" + i + "</a></li>\r\n");
				}
			}
			pageBar.append("<li class=\"page-item\"><span  class=\"page-link\"><strong>...</strong></span></li>\r\n");
		}
		if (curPage > 3 && curPage < MaxPage - 2) {
			if (curPage > 3 && MaxPage != 7) {
				pageBar.append(
						"<li class=\"page-item\"><span  class=\"page-link\"><strong>...</strong></span></li>\r\n");
			}
			for (int i = curPage - 2; i <= curPage + 2; i++) {
				if (curPage == i) {
					pageBar.append(
							"<li class=\"page-item active\"><span  class=\"page-link\">" + i + "</span></li>\r\n");
				} else {
					pageBar.append("<li class=\"page-item\"><a class=\"page-link\" href=\"" + actionUrl + "curPage=" + i
							+ "\">" + i + "</a></li>\r\n");
				}
			}
			if (curPage < MaxPage - 3 && MaxPage != 7) {
				pageBar.append(
						"<li class=\"page-item\"><span  class=\"page-link\"><strong>...</strong></span></li>\r\n");
			}
		}
		if (curPage >= MaxPage - 2) {
			pageBar.append("<li class=\"page-item\"><span  class=\"page-link\"><strong>...</strong></span></li>\r\n");
			for (long i = MaxPage - 4; i < MaxPage; i++) {
				// 如果是当前页数，设置为文版显示；如果不是，设置为链接
				if (curPage == i) {
					pageBar.append(
							"<li class=\"page-item active\"><span  class=\"page-link\">" + i + "</span></li>\r\n");
				} else {
					pageBar.append("<li class=\"page-item\"><a class=\"page-link\" href=\"" + actionUrl + "curPage=" + i
							+ "\">" + i + "</a></li>\r\n");
				}
			}
		}
		if (curPage == MaxPage) {
			pageBar.append("<li class=\"page-item active\"><span  class=\"page-link\">" + MaxPage + "</span></li>\r\n");
		} else {
			pageBar.append("<li class=\"page-item\"><a class=\"page-link\" href=\"" + actionUrl + "curPage=" + MaxPage
					+ "\">" + MaxPage + "</a></li>\r\n");
		}
	}

	private void smallPage() {
		for (int i = 1; i <= MaxPage; i++) {
			// 如果是当前页数，设置为文版显示；如果不是，设置为链接
			if (curPage == i) {
				pageBar.append("<li class=\"page-item active\"><span  class=\"page-link\">" + i + "</span></li>\r\n");
			} else {
				pageBar.append("<li class=\"page-item\"><a class=\"page-link\" href=\"" + actionUrl + "curPage=" + i
						+ "\">" + i + "</a></li>\r\n");
			}
		}
	}

	public int getRowsPrePage() {
		return rowsPrePage;
	}

	public void setRowsPrePage(int rowsPrePage) {
		this.rowsPrePage = rowsPrePage;
	}

	public int getStartNum() {
		return startNum;
	}

	public String getPageBar() {
		return pageBar.toString();
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public void setDBTotalCount(long l) {
		DBTotalCount = l;
	}

	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}

}

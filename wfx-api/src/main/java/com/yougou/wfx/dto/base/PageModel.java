package com.yougou.wfx.dto.base;

import java.util.List;

/**
 * 分页对象(基于mmGrid定制)
 */
public class PageModel<T> extends BaseDto{

	private static final long serialVersionUID = 1L;

	/**
	 * 每页的记录数
	 */
	private int limit = 20;

	/**
	 * 当前页中存放的数据
	 */
	private List<T> items;

	/**
	 * 总记录数
	 */
	private int totalCount;

	/**
	 * 页数
	 */
	private int pageCount;

	/**
	 * 跳转页数
	 */
	private int page = 1;
	
	/**
	 * 排序字段
	 */
	private String orderSort;
	
	public PageModel(){
		
	}

	public PageModel(int page, int totalCount) {
		this.page = page;
		this.totalCount = totalCount;
		this.pageCount = getTotalPageCount();
	}

	public PageModel(int page, int limit, int totalCount) {
		this.page = page;
		this.limit = limit;
		this.totalCount = totalCount;
		this.pageCount = getTotalPageCount();
	}

	public PageModel(int page, int limit, int totalCount, List<T> items) {
		this.page = page;
		this.limit = limit;
		this.totalCount = totalCount;
		this.pageCount = getTotalPageCount();
		this.items = items;
	}

	/**
	 * 取总页数
	 */
	private final int getTotalPageCount() {
		if (totalCount % limit == 0)
			return totalCount / limit;
		else
			return totalCount / limit + 1;
	}

	/**
	 * 取每页数据数
	 */
	public int getLimit() {
		return limit;
	}

	/**
	 * 取当前页中的记录.
	 */
	public Object getResult() {
		return items;
	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
	
	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getOrderSort() {
		return orderSort;
	}

	public void setOrderSort(String orderSort) {
		this.orderSort = orderSort;
	}

	@Override
	public String toString() {
		return "PageModel [limit=" + limit + ", totalCount=" + totalCount
				+ ", pageCount=" + pageCount + ", page=" + page
				+ ", orderSort=" + orderSort + "]";
	}
}
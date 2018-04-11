package dao;

import java.util.List;

public class PageBean<T> {
   //当前页，每页要显示的记录数，分页查询到的数据，总记录数，总页数。
	private int currentPage=1; //1（0,10），2（10,10），3（20,10）
	private int pageCount=10;//一页显示的记录数
	private List<T> pageData;
	private String bookName;
	private int totalCount;//总记录数
//	private int totalPage;不需要写了
	
	public PageBean(){}
	public PageBean(int currentPage,int totalCount) {
		this.currentPage = currentPage;
		this.totalCount = totalCount;
	}
	
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public void nextPage(){
		this.currentPage++;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount){
		this.pageCount=pageCount;
	}
	public List<T> getPageData() {
		return pageData;
	}
	public void setPageData(List<T> pageData) {
		this.pageData = pageData;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPage() {
		if(totalCount%pageCount==0)
			   return totalCount/pageCount;
			else
				return totalCount/pageCount+1;
	}
	public void lastPage() {
		this.currentPage--;
	}
}

package close.gxph.core.common.page;

import java.util.Iterator;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import close.gxph.core.constant.Contants;

//import close.gxph.mail.constant.Constants;

/**
 * 封装页面信息
 * 
 * @author 作者：user
 * @version 创建时间：2011-7-19 上午03:20:33
 * @param <T>
 */
public class PageImpl<T> implements CustomPage<T> {

	/** 当前页 **/
	private int number = 0;

	/** 每页显示记录数 **/
	private int size = Contants.PAGE_SIZES;

	/** 总页数 **/
	private int totalPages = 0;

	/** 当前页有几条数据 **/
	private int numberOfElements = 0;

	/** 总记录数 **/
	private long TotalElements = 0;

	/** 分页数据 **/
	private List<T> content;

	/**
	 * 得到当前页
	 * 
	 * @return int number
	 */
	@Override
	public int getNumber() {
		return number;
	}

	@Override
	public void setNumber(int number) {
		this.number = number - 1;
	}

	/**
	 * 得到最大记录数（页面中一页最多显示记录数）
	 * 
	 * @return
	 */
	@Override
	public int getSize() {
		return size;
	}

	@Override
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * 返回总页数
	 * 
	 * @return long totalpage
	 */
	@Override
	public int getTotalPages() {
		return totalPages;
	}

	@Override
	public int getNumberOfElements() {
		return numberOfElements;
	}

	@Override
	public void setNumberOfElements(int numberOfElements) {
		this.numberOfElements = numberOfElements;

	}

	@Override
	public boolean hasPreviousPage() {
		if (number > 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean isFirstPage() {
		if (number == 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean hasNextPage() {
		if (number < totalPages - 1)
			return true;
		else
			return false;
	}

	@Override
	public boolean isLastPage() {
		if (number == totalPages - 1)
			return true;
		else
			return false;
	}

	@Override
	public Pageable nextPageable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pageable previousPageable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 得到记录信息
	 * 
	 * @return List<T> 记录信息的列表
	 */
	public List<T> getContent() {
		return content;
	}

	/**
	 * 设置List类型的记录信息
	 * 
	 * @param records
	 */
	public void setContent(List<T> content) {
		this.content = content;
	}

	@Override
	public boolean hasContent() {
		if (this.content != null && this.content.size() > 0)
			return true;
		else
			return false;
	}

	@Override
	public Sort getSort() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;

	}

	@Override
	public long getTotalElements() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setTotalElements(long totalElements) {
		this.TotalElements = totalElements;

	}

}

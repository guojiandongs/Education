package close.gxph.core.common.page;

import java.util.Iterator;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public interface CustomPage<T> extends Page<T> {
	/**
	 * Returns the number of the current page. Is always non-negative and less
	 * that {@code Page#getTotalPages()}.
	 * 
	 * @return the number of the current page
	 */
	int getNumber();

	void setNumber(int number);

	/**
	 * Returns the size of the page.
	 * 
	 * @return the size of the page
	 */
	int getSize();

	void setSize(int size);

	/**
	 * Returns the number of total pages.
	 * 
	 * @return the number of toral pages
	 */
	int getTotalPages();

	void setTotalPages(int totalPages);

	/**
	 * Returns the number of elements currently on this page.
	 * 
	 * @return the number of elements currently on this page
	 */
	int getNumberOfElements();

	void setNumberOfElements(int numberOfElements);

	/**
	 * Returns the total amount of elements.
	 * 
	 * @return the total amount of elements
	 */
	long getTotalElements();

	void setTotalElements(long totalElements);

	/**
	 * Returns if there is a previous page.
	 * 
	 * @return if there is a previous page
	 */
	boolean hasPreviousPage();

	/**
	 * Returns whether the current page is the first one.
	 * 
	 * @return
	 */
	boolean isFirstPage();

	/**
	 * Returns if there is a next page.
	 * 
	 * @return if there is a next page
	 */
	boolean hasNextPage();

	/**
	 * Returns whether the current page is the last one.
	 * 
	 * @return
	 */
	boolean isLastPage();

	/**
	 * Returns the {@link Pageable} to request the next {@link Page}. Can be
	 * {@literal null} in case the current {@link Page} is already the last one.
	 * Clients should check {@link #hasNextPage()} before calling this method to
	 * make sure they receive a non-{@literal null} value.
	 * 
	 * @return
	 */
	Pageable nextPageable();

	/**
	 * Returns the {@link Pageable} to request the previous page. Can be
	 * {@literal null} in case the current {@link Page} is already the first
	 * one. Clients should check {@link #hasPreviousPage()} before calling this
	 * method make sure receive a non-{@literal null} value.
	 * 
	 * @return
	 */
	Pageable previousPageable();

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Iterable#iterator()
	 */
	Iterator<T> iterator();

	/**
	 * Returns the page content as {@link List}.
	 * 
	 * @return
	 */
	List<T> getContent();

	void setContent(List<T> content);

	/**
	 * Returns whether the {@link Page} has content at all.
	 * 
	 * @return
	 */
	boolean hasContent();

	/**
	 * Returns the sorting parameters for the page.
	 * 
	 * @return
	 */
	Sort getSort();
}

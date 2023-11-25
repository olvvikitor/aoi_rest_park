package com.example.demoparkapi.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PageableDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("content")
    private List<Object> content = new ArrayList<>();

    @JsonProperty("first")
    private boolean first;

    @JsonProperty("last")
    private boolean last;

    @JsonProperty("page")
    private int number;

    @JsonProperty("size")
    private int size;

    @JsonProperty("pageElements")
    private int numberOfElements;

    @JsonProperty("totalPage")
    private int totalPage;

    @JsonProperty("totalElements")
    private int totalElements;

    public PageableDto() {
        // Construtor padrão
    }

    // Getters and Setters

    public List<Object> getContent() {
        return content;
    }

    public void setContent(List<Object> content) {
        this.content = content;
    }

	public boolean isFirst() {
		return first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}

	public boolean isLast() {
		return last;
	}

	public void setLast(boolean last) {
		this.last = last;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getNumberOfElements() {
		return numberOfElements;
	}

	public void setNumberOfElements(int numberOfElements) {
		this.numberOfElements = numberOfElements;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPages(int totalPage) {
		this.totalPage = totalPage-1;
	}

	public int getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(int totalElements) {
		this.totalElements = totalElements;
	}

}

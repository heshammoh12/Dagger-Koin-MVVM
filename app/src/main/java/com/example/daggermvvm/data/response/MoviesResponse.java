package com.example.daggermvvm.data.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MoviesResponse {
    long page = 0;

    List<Results> results;

    @SerializedName("total_pages")
    long totalPages = 0;

    @SerializedName("total_results")
    long totalResults;

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(long totalResults) {
        this.totalResults = totalResults;
    }
}

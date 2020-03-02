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

    public List<Results> getResults() {
        return results;
    }

}

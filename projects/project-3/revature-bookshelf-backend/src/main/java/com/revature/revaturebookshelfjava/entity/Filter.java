package com.revature.revaturebookshelfjava.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Filter {

    private List<String> genres;
    private Date[] dates;
    private long[] pgcounts;
    private double[] prices;
}

//THIS IS NOT AN ENTITY, BUT JUST A DATA MODEL NEEDED FOR THE SEARCH FUNCTIONALITY

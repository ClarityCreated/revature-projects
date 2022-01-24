package com.revature.revaturebookshelfjava.service;

import com.revature.revaturebookshelfjava.entity.Filter;
import com.revature.revaturebookshelfjava.entity.StoreProduct;

import java.util.List;

public interface FilterService {

    List<StoreProduct> doFilter(List<StoreProduct> books, Filter filter);
}

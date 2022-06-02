package com.lab2.service.fill;

import com.lab2.model.Fill;

import java.util.List;

public interface FillService {

  List<Fill> fills(String userId);

  void fill(Fill fill);
}

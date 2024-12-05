package com.myapp.assignment13;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CatalogueServiceImpl implements CatalogueService {
  // repository
  private CatalogueRepository catalogueRepo;
  // constructor
  public CatalogueServiceImpl(CatalogueRepository catalogueRepo)  {
    this.catalogueRepo = catalogueRepo;
  }

  @Override
  public List<Catalogue> findAll(){
    return catalogueRepo.findAll();
  };
}

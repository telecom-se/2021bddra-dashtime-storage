package tse.projects.tables;

import tse.projects.models.Company;

import java.util.List;

public class CompanyTable extends DatabaseTable {


    public void addCompanies(List<Company> companies) {
        this.items.addAll(companies);
    }
}

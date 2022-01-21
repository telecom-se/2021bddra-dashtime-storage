package tse.projects;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import tse.projects.models.Company;
import tse.projects.models.DatabaseModel;
import tse.projects.models.Stock;
import tse.projects.tables.CompanyTable;
import tse.projects.tables.DatabaseTable;
import tse.projects.tables.StockTable;
import tse.projects.utils.*;

import java.util.List;

public class QueryService {

    private final Gson gson;

    private final CompanyTable companyTable;
    private final StockTable stockTable;

    public QueryService() {
        this.gson = new Gson();
        this.companyTable = new CompanyTable();
        this.stockTable = new StockTable();
    }

    public String runQuery(String queryStr) {
        try {
            Query query = this.gson.fromJson(queryStr, Query.class);

            DatabaseTable table = (DatabaseTable) this.getClass().getDeclaredField(query.table + "Table").get(this);

            List<DatabaseModel> result = table.where(query.where);


            SelectSerializer serializer = new SelectSerializer(query.select);
            Gson gsonBuilder = new GsonBuilder().registerTypeHierarchyAdapter(DatabaseModel.class, serializer).create();

            if (query.limit == null) {
                return gsonBuilder.toJson(result);
            } else if (query.limit == 1) {
                return gsonBuilder.toJson(result.get(0));
            } else {
                return gsonBuilder.toJson(result.stream().limit(query.limit));
            }

        } catch (NoSuchFieldException e) {
            return this.gson.toJson(new DatabaseException("E_INVALID_QUERY", "Invalid table/column name"));
        } catch (NumberFormatException e) {
            return this.gson.toJson(new DatabaseException("E_INVALID_QUERY", "Invalid where conditions"));
        } catch (Exception e) {
            return this.gson.toJson(new DatabaseException("E_INTERNAL_SERVER_ERROR", "Internal server error"));
        }
    }

    public void generateFakeData() {
        List<Company> fakeCompanies = FakerData.getFakeCompanies();
        this.companyTable.addCompanies(fakeCompanies);

        for (Company c : fakeCompanies) {
            List<Stock> fakeStocks = FakerData.getFakeStocks(c.getSymbol());
            this.stockTable.addStocks(fakeStocks);
        }
    }
}

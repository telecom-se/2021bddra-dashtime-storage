package tse.projects;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import tse.projects.models.Company;
import tse.projects.models.DatabaseModel;
import tse.projects.models.Stock;
import tse.projects.utils.DatabaseTable;
import tse.projects.utils.*;

import java.io.*;
import java.util.*;

public class QueryService {

    /*
    conf: {
        tables:["company", "stocks"],
        index:
        }
    }
     */
    private final DatabaseConf conf;
    private final Gson gson;
    private final Map<String, DatabaseTable<?>> tables;

    public QueryService() throws IOException {
        this.gson = new Gson();
        this.tables = new HashMap<>();

        // Load configuration
        JsonReader reader = new JsonReader(new FileReader("AionDB/conf.json"));
        this.conf = gson.fromJson(reader, DatabaseConf.class);

    }

    public String runQuery(String queryStr) {
        try {
            Query query = this.gson.fromJson(queryStr, Query.class);

            DatabaseTable<?> table = this.tables.get(query.table);

            List<DatabaseModel> result = table.where(query.where);

            SelectSerializer serializer = new SelectSerializer(query.select);
            Gson gsonBuilder = new GsonBuilder().registerTypeHierarchyAdapter(DatabaseModel.class, serializer).create();

            if (query.limit == null) {
                return gsonBuilder.toJson(result);
            } else if (query.limit == 1) {
                if (result.size() == 0) {
                    return gsonBuilder.toJson(new ArrayList<DatabaseTable<?>>());
                }
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
        DatabaseTable<Company> companies = new DatabaseTable<>();
        List<Company> fakeCompanies = FakerData.getFakeCompanies();
        companies.addItems(fakeCompanies);
        this.tables.put("company", companies);

        DatabaseTable<Stock> stocks = new DatabaseTable<>();
        for (Company c : fakeCompanies) {
            List<Stock> fakeStocks = FakerData.getFakeStocks(c.getSymbol());
            stocks.addItems(fakeStocks);
        }
        this.tables.put("stock", stocks);
    }

    public void loadDatabase() {
        try {
            for (String tableName : this.conf.tables) {
                File file = new File("AionDB/" + tableName + ".adb");
                String line;
                Scanner sc = new Scanner(new FileInputStream(file));

                switch (tableName) {
                    case "company" -> {
                        DatabaseTable<Company> table = new DatabaseTable<>();
                        while (sc.hasNextLine()) {
                            line = sc.nextLine();
                            table.addItem(this.gson.fromJson(line, Company.class));
                        }
                        this.tables.put("company", table);
                    }
                    case "stock" -> {
                        DatabaseTable<Stock> table = new DatabaseTable<>();
                        while (sc.hasNextLine()) {
                            line = sc.nextLine();
                            table.addItem(this.gson.fromJson(line, Stock.class));
                        }
                        this.tables.put("stock", table);
                    }
                    default -> throw new Exception("Invalid configuration");
                }
                sc.close();

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("\nInvalid table name in conf.json");
        } catch (Exception e) {
            System.out.println("\nError during loading database");
            e.printStackTrace();
        }

        System.out.println("Done");
    }

    public void saveDatabase() {
        try {

            for (String tableName : this.tables.keySet()) {
                FileWriter fstream = new FileWriter("AionDB/" + tableName + ".adb");
                BufferedWriter buffer = new BufferedWriter(fstream);

                for (Object row : this.tables.get(tableName).items) {
                    buffer.write(this.gson.toJson(row));
                    buffer.newLine();
                }
                buffer.close();
            }

            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}


package tse.projects.utils;

import java.util.ArrayList;
import java.util.List;

public class Query {
    /*
    {
        "table": "string",
        "where": [
            { "column", "operator", "value" },
            { "column", "operator", "value" },
        ],
        "select": ["id", "name"],
        "limit": 1,
    }
     */
    public String table;
    public List<Condition> where;
    public List<String> select;
    public Integer limit;

    public Query() {
        this.table = null;
        this.where = new ArrayList<>();
        this.select = new ArrayList<>();
        this.limit = null;
    }

    public Query(String table, List<Condition> where, List<String> select, String aggregate, Integer limit) {
        this.table = table;
        this.where = where;
        this.select = select;
        this.limit = limit;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public void addWhere(Condition condition) {
        this.where.add(condition);
    }

    public void addSelect(String column) {
        this.select.add(column);
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}

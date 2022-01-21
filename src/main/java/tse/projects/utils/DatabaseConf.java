package tse.projects.utils;

public class DatabaseConf {
    /*
    conf: {
        tables:["company", "stocks"]
    }
     */
    public String[] tables;

    public DatabaseConf() {
    }

    public DatabaseConf(String[] tables) {
        this.tables = tables;
    }
}

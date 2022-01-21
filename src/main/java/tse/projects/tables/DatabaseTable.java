package tse.projects.tables;

import tse.projects.models.DatabaseModel;
import tse.projects.utils.Condition;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DatabaseTable {
    LinkedList<DatabaseModel> items;

    public DatabaseTable() {
        this.items = new LinkedList<>();
    }

    public List<DatabaseModel> where(List<Condition> conditions) throws NoSuchFieldException, NumberFormatException, IllegalAccessException {
        List<DatabaseModel> items = new ArrayList<>();

        for (int i = 0; i < this.items.size(); i++) {
            DatabaseModel item = this.items.get(i);

            if (item.passConditions(conditions)) {
                items.add(item);
            }
        }
        return items;
    }

}

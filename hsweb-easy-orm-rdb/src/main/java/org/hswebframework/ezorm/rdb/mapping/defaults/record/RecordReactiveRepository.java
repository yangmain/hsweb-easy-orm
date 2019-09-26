package org.hswebframework.ezorm.rdb.mapping.defaults.record;

import org.hswebframework.ezorm.rdb.mapping.defaults.DefaultReactiveRepository;
import org.hswebframework.ezorm.rdb.mapping.defaults.SimpleColumnMapping;
import org.hswebframework.ezorm.rdb.metadata.RDBColumnMetadata;
import org.hswebframework.ezorm.rdb.metadata.RDBTableMetadata;
import org.hswebframework.ezorm.rdb.operator.DatabaseOperator;

public class RecordReactiveRepository<K> extends DefaultReactiveRepository<Record, K> {
    public RecordReactiveRepository(DatabaseOperator operator, RDBTableMetadata table) {
        super(operator, table, Record.class, RecordResultWrapper.INSTANCE);
    }

    @Override
    protected void initMapping(Class<Record> entityType) {
        this.idColumn = table.getColumns().stream()
                .filter(RDBColumnMetadata::isPrimaryKey)
                .findFirst()
                .map(RDBColumnMetadata::getName)
                .orElse(null);
        this.mapping = SimpleColumnMapping.of(table);

        this.properties = mapping.getColumnPropertyMapping()
                .values()
                .toArray(new String[0]);
    }
}

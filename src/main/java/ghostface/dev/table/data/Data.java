package ghostface.dev.table.data;

import ghostface.dev.exception.table.ColumnException;
import ghostface.dev.table.Table;
import ghostface.dev.table.column.Column;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnknownNullability;

import java.io.Serializable;

public interface Data extends Serializable {

    @NotNull Table getTable();

    <E> @UnknownNullability E get(@NotNull Column<E> column) throws IllegalArgumentException;

    <E> void set(@NotNull Column<E> column, @Nullable E value) throws ColumnException, IllegalArgumentException;

}
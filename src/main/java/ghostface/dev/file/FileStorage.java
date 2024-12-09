package ghostface.dev.file;

import ghostface.dev.content.NamedContent;
import ghostface.dev.database.Database;
import ghostface.dev.exception.NameAlreadyExistsException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Optional;

public interface FileStorage {

    @NotNull Database database();

    @NotNull Path getDefault();

    @NotNull Files getFiles();

    // Classes

    interface Files extends NamedContent<@NotNull MetaFile> {

        @NotNull FileStorage getFileStorage();

        /**
         * @throws NameAlreadyExistsException if {@code name} is already in use
         * @throws IOException if an I/O error occurs
         * */
        @NotNull MetaFile create(@NotNull String name) throws NameAlreadyExistsException, IOException;

        /**
         * @throws NameAlreadyExistsException if {@code folder} and {@code name} is already in use
         * @throws IOException if an I/O error occurs
         * */
        @NotNull MetaFile create(@NotNull String folder, @NotNull String name) throws NameAlreadyExistsException, IOException;

        @NotNull Optional<@NotNull MetaFile> get(@NotNull Path path);

        boolean delete(@NotNull Path path);

        // Implementations

        @Override
        @NotNull Optional<@NotNull MetaFile> get(@NotNull String name);

        @Override
        boolean delete(@NotNull String name);

        @Override
        @Unmodifiable @NotNull Collection<@NotNull MetaFile> toCollection();
    }
}

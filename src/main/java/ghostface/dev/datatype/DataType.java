package ghostface.dev.datatype;

import ghostface.dev.exception.DataTypeException;
import org.jetbrains.annotations.NotNull;

import java.io.*;

public interface DataType<T> {

    // primitives

    @NotNull DataType<@NotNull Boolean> BOOLEAN = new DataType<Boolean>() {
        @Override
        public @NotNull Boolean read(@NotNull InputStream stream) throws IOException, DataTypeException {
            try (@NotNull DataInputStream input = new DataInputStream(stream)) {
                return input.readBoolean();
            } catch (EOFException e) {
                throw new DataTypeException("Data is null", e);
            }
        }
    };

    @NotNull DataType<@NotNull String> STRING = new DataType<String>() {
        @Override
        public @NotNull String read(@NotNull InputStream stream) throws IOException, DataTypeException {
            try (@NotNull InputStreamReader reader = new InputStreamReader(stream)) {
                @NotNull StringBuilder builder = new StringBuilder();
                char @NotNull [] chars = new char[1024];

                int read;
                while ((read = reader.read(chars)) != -1) {
                    builder.append(chars, 0, read);
                }

                if (builder.toString().trim().isEmpty()) {
                    throw new DataTypeException("Data cannot be empty");
                } else {
                    return builder.toString();
                }
            }
        }
    };

    @NotNull DataType<@NotNull Integer> INTEGER = new DataType<Integer>() {
        @Override
        public @NotNull Integer read(@NotNull InputStream stream) throws IOException, DataTypeException {
            try (@NotNull DataInputStream input = new DataInputStream(stream)) {
                return input.readInt();
            } catch (EOFException e) {
                throw new DataTypeException("Data is null", e);
            }
        }
    };

    @NotNull DataType<@NotNull Double> DOUBLE = new DataType<Double>() {
        @Override
        public @NotNull Double read(@NotNull InputStream stream) throws IOException, DataTypeException {
            try (@NotNull DataInputStream input = new DataInputStream(stream)) {
                return input.readDouble();
            } catch (EOFException e) {
                throw new DataTypeException("Data is null", e);
            }
        }
    };

    @NotNull DataType<@NotNull Float> FLOAT = new DataType<Float>() {
        @Override
        public @NotNull Float read(@NotNull InputStream stream) throws IOException, DataTypeException {
            try (@NotNull DataInputStream input = new DataInputStream(stream)) {
                return input.readFloat();
            } catch (EOFException e) {
                throw new DataTypeException("Data is null", e);
            }
        }
    };

    @NotNull DataType<@NotNull Long> LONG = new DataType<Long>() {
        @Override
        public @NotNull Long read(@NotNull InputStream stream) throws IOException, DataTypeException {
            try (@NotNull DataInputStream input = new DataInputStream(stream)) {
                return input.readLong();
            } catch (EOFException e) {
                throw new DataTypeException("Data is null", e);
            }
        }
    };

    @NotNull DataType<@NotNull Character> CHAR = new DataType<Character>() {
        @Override
        public @NotNull Character read(@NotNull InputStream stream) throws IOException, DataTypeException {
            try (@NotNull DataInputStream input = new DataInputStream(stream)) {
                return input.readChar();
            } catch (EOFException e) {
                throw new DataTypeException("Data is null", e);
            }
        }
    };

    @NotNull DataType<byte []> BYTE_ARRAY = new DataType<byte @NotNull []>() {
        @Override
        public byte @NotNull [] read(@NotNull InputStream stream) throws IOException, DataTypeException {
            try (@NotNull ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {
                byte[] bytes = new byte[1024];
                int read;

                while ((read = stream.read(bytes)) != -1) {
                    buffer.write(bytes, 0, read);
                    buffer.flush();
                }

                if (buffer.size() == 0) {
                    throw new DataTypeException("Data cannot be empty");
                }

                return buffer.toByteArray();
            }
        }
    };

    // Objects

    @NotNull T read(@NotNull InputStream stream) throws IOException, DataTypeException;

}

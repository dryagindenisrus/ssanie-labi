package custom.matrix;

public abstract class AbstractMatrix implements IMatrix {

    protected int rows;
    protected int columns;

    public AbstractMatrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    // методы, которые должны быть реализованы в наследниках

    public abstract double getElement(int row, int column);

    public abstract void setElement(int row, int column, double value);

    // методы, которые не зависят от реализации

    public void randomize() {
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                setElement(i, j, Math.random() * 1000);
            }
        }
    }

    public void randomize(int items) {
        for (int i = 0; i < items; i++) {
            int row = (int) (Math.random() * getRows());
            int column = (int) (Math.random() * getColumns());
            double value = Math.random() * 1000;
            setElement(row, column, value);
        }
    }

    public AbstractMatrix add(AbstractMatrix matrix) {
        if (getRows() != matrix.getRows() || getColumns() != matrix.getColumns()) {
            throw new IllegalArgumentException("Matrix sizes are not equal");
        }

        AbstractMatrix result = createMatrix(getRows(), getColumns());

        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                result.setElement(i, j, getElement(i, j) + matrix.getElement(i, j));
            }
        }

        return result;
    }

    public AbstractMatrix product(AbstractMatrix matrix) {
        if (getColumns() != matrix.getRows()) {
            throw new IllegalArgumentException("Matrix sizes are not equal");
        }

        AbstractMatrix result = createMatrix(getRows(), matrix.getColumns());

        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < matrix.getColumns(); j++) {
                double sum = 0;

                for (int k = 0; k < getColumns(); k++) {
                    sum += getElement(i, k) * matrix.getElement(k, j);
                }

                result.setElement(i, j, sum);
            }
        }

        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                builder.append(getElement(i, j)).append(" ");
            }

            builder.append("\n");
        }

        return builder.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof AbstractMatrix matrix)) {
            return false;
        }

        if (getRows() != matrix.getRows() || getColumns() != matrix.getColumns()) {
            return false;
        }

        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                if (getElement(i, j) != matrix.getElement(i, j)) {
                    return false;
                }
            }
        }

        return true;
    }
    protected abstract AbstractMatrix createMatrix(int rows, int columns);
}
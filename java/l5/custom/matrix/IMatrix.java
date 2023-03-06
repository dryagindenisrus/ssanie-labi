package custom.matrix;

public interface IMatrix {
    // интерфейс, который должны реализовать все матрицы

    public int getRows();

    public int getColumns();

    public double getElement(int row, int column);

    public void setElement(int row, int column, double value);

    public boolean equals(Object obj);
}